package com.genome.dx.wcore.filter;

import com.genome.dx.core.code.ActionCd;
import com.genome.dx.core.code.UseCd;
import com.genome.dx.core.domain.ActionHistory;
import com.genome.dx.core.domain.Url;
import com.genome.dx.core.repository.ActionHistoryRepository;
import com.genome.dx.core.service.UrlService;
import com.genome.dx.wcore.domain.security.UserDetails;
import com.genome.dx.wcore.service.SecurityService;
import com.omnicns.web.request.RequestUtil;
import com.omnicns.web.response.ResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;
import org.springframework.web.util.WebUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Optional;

/**
 * Created by hanxi on 2017/9/21.
 */
@Slf4j
public class RequestLoggingFilter extends OncePerRequestFilter implements Ordered {
	private static final org.slf4j.Logger apiLog = org.slf4j.LoggerFactory.getLogger("api");
	private static ThreadLocal<Long> requestBeginTime = new ThreadLocal<>();
	private static final int DEFAULT_MAX_PAYLOAD_LENGTH = 1024;

	private int maxPayloadLength = DEFAULT_MAX_PAYLOAD_LENGTH;
	// Not LOWEST_PRECEDENCE, but near the end, so it has a good chance of catching all
	// enriched headers, but users can add stuff after this if they want to
	private int order = Ordered.LOWEST_PRECEDENCE - 10;

	@Autowired
	UrlService urlService;

	@Autowired
	SecurityService securityService;

	@Autowired
	ActionHistoryRepository actionHistoryRepository;

	public RequestLoggingFilter() {
		super();
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
		MDC.put("traceId", request.getSession().getId());

		boolean isFirstRequest = !isAsyncDispatch(request);
		HttpServletRequest requestToUse = request;

		if (isFirstRequest && !(request instanceof ContentCachingRequestWrapper)) {
			requestToUse = new ContentCachingRequestWrapper(request, getMaxPayloadLength());
		}

		HttpServletResponse responseToUse = response;
		if (!(response instanceof ContentCachingResponseWrapper)) {
			responseToUse = new ContentCachingResponseWrapper(response);
		}

		requestBeginTime.set(System.currentTimeMillis());

		try {
			filterChain.doFilter(requestToUse, responseToUse);
		} finally {
			if (!isAsyncStarted(requestToUse)) {
				logRequest(createMessage(requestToUse, responseToUse), request.getRequestURI().startsWith("/api"));

			}
		}

		MDC.clear();
	}

	protected String createMessage(HttpServletRequest request, HttpServletResponse resp) {
		StringBuilder msg = new StringBuilder();
		msg.append(request.getMethod());
		String url = request.getRequestURI();
		msg.append(" uri=").append(url);

		ActionHistory history = new ActionHistory();
		Optional<Url> first = urlService.findAll().stream().filter(
				it -> url.equals(it.getUrl()) || (UseCd.USE001 == it.getRegexp_cd() && url.matches(it.getUrl()))
		).findFirst();

		String queryString = request.getQueryString();
		if (queryString != null) {
			msg.append('?').append(queryString);
		}


		history.setUrl(url);
		history.setActCd(ActionCd.ACT001);
		history.setIp(RequestUtil.getRemoteAddr(request));
		UserDetails userDetils = securityService.getUserDetils();
		if (null != userDetils) {
			history.setAdmSeq(userDetils.getAdmSeq());
		}
		if (first.isPresent()) {
			history.setUrlSeq(first.get().getUrlSeq());
		}



		String client = request.getRemoteAddr();
		if (StringUtils.hasLength(client)) {
			msg.append(";client=").append(client);
		}
		HttpSession session = request.getSession(false);
		if (session != null) {
			msg.append(";session=").append(session.getId());
		}
		String user = request.getRemoteUser();
		if (user != null) {
			msg.append(";user=").append(user);
		}
		msg.append(";headers=").append(new ServletServerHttpRequest(request).getHeaders());

		history.setRequest(msg.toString());

		ContentCachingRequestWrapper wrapper = WebUtils.getNativeRequest(request, ContentCachingRequestWrapper.class);
		if (wrapper != null) {
			byte[] buf = wrapper.getContentAsByteArray();
			if (buf.length > 0) {
				int length = Math.min(buf.length, getMaxPayloadLength());
				String payload;
				try {
					payload = new String(buf, 0, length, wrapper.getCharacterEncoding());
				} catch (UnsupportedEncodingException ex) {
					payload = "[unknown]";
				}
				msg.append(";payload=").append(payload);
			}
		}

		StringBuilder responseBody = new StringBuilder();
		ContentCachingResponseWrapper responseWrapper = WebUtils.getNativeResponse(resp, ContentCachingResponseWrapper.class);
		if (responseWrapper != null) {
			byte[] buf = responseWrapper.getContentAsByteArray();
			try {
				responseWrapper.copyBodyToResponse();
			} catch (IOException e) {
				log.error("Fail to write response body back", e);
			}
			if (buf.length > 0) {
				String payload;
				try {
					String contentType = resp.getHeader("Content-Type");
					int length = Math.min(buf.length, getMaxPayloadLength());
					if(!StringUtils.isEmpty(contentType) && (contentType.startsWith(MediaType.APPLICATION_JSON_VALUE))){ // || contentType.startsWith(Omnifit2MediaType.OMNIFIT2_V1_JSON_VALUE) || contentType.startsWith(Omnifit2MediaType.OMNIFIT2_V2_JSON_VALUE)
						payload = new String(buf, 0, length, responseWrapper.getCharacterEncoding());
					}else{
						payload = "[no-json]";
					}
				} catch (UnsupportedEncodingException ex) {
					payload = "[unknown]";
				}
				msg.append(";response=").append(payload);
				responseBody.append(payload);
			}
		}
//		resp.getS
//		ResponseUtil.
//		RequestUtil.getHeader()
//		resp.getStatus()
//		HttpStatus httpStatus = HttpStatus.valueOf(resp.getStatus());
		history.setResponse("httpStatus=" + resp.getStatus() + (200!=resp.getStatus() ? ";body=" + responseBody.toString(): ""));
		actionHistoryRepository.save(history);
		return msg.toString();
	}


	public int getMaxPayloadLength() {
		return maxPayloadLength;
	}

	protected void logRequest(String message, boolean isApiLogWrite) {
		long begin = requestBeginTime.get();
		long end = System.currentTimeMillis();

		long duration = end - begin;
		String msg = message + ", request time:" + duration;
		log.info(msg);
		if(isApiLogWrite) {
			apiLog.info(msg);
		}

	}

	@Override
	public int getOrder() {
		return this.order;
	}
}

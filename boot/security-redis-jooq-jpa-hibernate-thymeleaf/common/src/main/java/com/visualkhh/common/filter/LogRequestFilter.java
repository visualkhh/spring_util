package com.visualkhh.common.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.WebUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.util.LinkedHashMap;
import java.util.Map;

@Slf4j
public class LogRequestFilter extends OncePerRequestFilter implements Ordered {


	// put filter at the end of all other filters to make sure we are processing after all others
	private int order = Ordered.LOWEST_PRECEDENCE - 8;
	private ErrorAttributes errorAttributes;

	@Override
	public int getOrder() {
		return order;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		ContentCachingRequestWrapper wrappedRequest = new ContentCachingRequestWrapper(request);

		int status = HttpStatus.INTERNAL_SERVER_ERROR.value();

		// pass through filter chain to do the actual request handling
		filterChain.doFilter(wrappedRequest, response);
		status = response.getStatus();

		// only log request if there was an error
		if (status == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
			Map<String, Object> trace = getTrace(wrappedRequest, status);

			// body can only be read after the actual request handling was done!
			getBody(wrappedRequest, trace);
			logTrace(wrappedRequest, trace);
		}
	}

	private void getBody(ContentCachingRequestWrapper request, Map<String, Object> trace) {
		// wrap request to make sure we can read the body of the request (otherwise it will be consumed by the actual
		// request handler)
		ContentCachingRequestWrapper wrapper = WebUtils.getNativeRequest(request, ContentCachingRequestWrapper.class);
		if (wrapper != null) {
			byte[] buf = wrapper.getContentAsByteArray();
			if (buf.length > 0) {
				String payload;
				try {
					payload = new String(buf, 0, buf.length, wrapper.getCharacterEncoding());
				}
				catch (UnsupportedEncodingException ex) {
					payload = "[unknown]";
				}

				trace.put("body", payload);
			}
		}
	}

	private void logTrace(HttpServletRequest request, Map<String, Object> trace) {
		Object method = trace.get("method");
		Object path = trace.get("path");
		Object statusCode = trace.get("statusCode");

		logger.info(String.format("%s %s produced an error status code '%s'. Trace: '%s'", method, path, statusCode,
				trace));
	}

	protected Map<String, Object> getTrace(HttpServletRequest request, int status) {
		Throwable exception = (Throwable) request.getAttribute("javax.servlet.error.exception");

		Principal principal = request.getUserPrincipal();

		Map<String, Object> trace = new LinkedHashMap<>();
		trace.put("method", request.getMethod());
		trace.put("path", request.getRequestURI());
		trace.put("principal", principal.getName());
		trace.put("query", request.getQueryString());
		trace.put("statusCode", status);

		if (exception != null && this.errorAttributes != null) {
			trace.put("error", this.errorAttributes
					.getErrorAttributes(new ServletRequestAttributes(request), true));
		}

		return trace;
	}

}
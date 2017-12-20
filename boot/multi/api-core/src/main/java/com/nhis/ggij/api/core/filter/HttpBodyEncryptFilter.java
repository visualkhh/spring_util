package com.nhis.ggij.api.core.filter;

import com.nhis.ggij.api.core.security.HttpBodyEncryptor;
//import com.omnicns.web.request.HttpServletResponseBodyWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@Component
public class HttpBodyEncryptFilter implements Filter {
    @Autowired
	HttpBodyEncryptor httpBodyEncryptor;

    public void init(FilterConfig filterConfig) throws ServletException {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest)request;
        HttpServletResponse resp = (HttpServletResponse)response;
        HttpServletRequestBodyConvertWrapper requestConvert = new HttpServletRequestBodyConvertWrapper(req,(at->{
            try {
                return httpBodyEncryptor.decode( new String(at) );
            } catch (Exception e) {
                e.printStackTrace();
            }
            return new byte[]{};
        }));
        HttpServletResponseBodyWrapper capturingResponseWrapper = new HttpServletResponseBodyWrapper(resp);

        try {
            chain.doFilter(requestConvert, capturingResponseWrapper);
            byte[] contents = capturingResponseWrapper.getCaptureAsBytes();
            String w = httpBodyEncryptor.encode(contents);
            if(null!=w) {
                resp.getWriter().write(w);
            }
        } catch (Exception e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
            e.printStackTrace();
        }



    }

    public void destroy() {
    }
}

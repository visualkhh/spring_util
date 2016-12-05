package com.khh.project.config.filter;

import javax.servlet.*;
import java.io.IOException;

public class TestFilter implements Filter {
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("vvv");
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("vvv1");

    }

    public void destroy() {
        System.out.println("vvv1d");

    }
}

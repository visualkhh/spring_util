package com.khh.project.config.servlet;

import javax.servlet.*;
import java.io.IOException;

public class TestServlet implements Servlet {
    public void init(ServletConfig config) throws ServletException {
        System.out.println("1");
    }

    public ServletConfig getServletConfig() {
        System.out.println("12");
        return null;
    }

    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        System.out.println("13");

    }

    public String getServletInfo() {
        System.out.println("14");
        return null;
    }

    public void destroy() {
        System.out.println("144");

    }
}

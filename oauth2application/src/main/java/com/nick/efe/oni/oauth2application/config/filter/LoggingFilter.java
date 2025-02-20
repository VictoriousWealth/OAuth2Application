package com.nick.efe.oni.oauth2application.config.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Component
public class LoggingFilter implements Filter {

    /***
     * The purpose of this class to log all responses that leave through port
     * 8080 or the port by which tomcat apache is listening to
     * @param request  The request to process
     * @param response The response associated with the request
     * @param chain    Provides access to the next filter in the chain for this filter to pass the request and response
     *                     to for further processing
     *
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        System.out.println("======================================================");
        HttpServletRequest req = (HttpServletRequest) request;
        System.out.println("🌐 Incoming Request: " + req.getMethod() + " " + req.getRequestURI());

        chain.doFilter(request, response); // Continue request processing

        HttpServletResponse res = (HttpServletResponse) response;
        Collection<String> headers = res.getHeaderNames();
        Map<String, String> headerMap = new HashMap<>();
        for (String header : headers) {
            headerMap.put(header, res.getHeader(header));
        }
        System.out.println("🌐 Outgoing Response with status code " + res.getStatus() + " with headers: {" + headerMap+"}");
        System.out.println("======================================================\n");

    }
}

package com.nick.efe.oni.oauth2application.config.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class BearerFilter implements Filter {
    /***
     * This class checks if the current request headers contain an authorization header and if it starts with bearer
     * @param request  The request to process
     * @param response The response associated with the request
     * @param chain    Provides access to the next filter in the chain for this filter to pass the request and response
     *                     to for further processing
     *
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        String authorization = req.getHeader("Authorization");
        if (authorization == null) {
            System.out.println("Authorization header is null");
            return;
        }
        if (!authorization.startsWith("Bearer ")) {
            System.out.println("Authorization header is invalid");
            return;
        }
        System.out.printf("Bearer Token is <%s> in request %s.\n", authorization.substring(7), req.getRequestURL().toString());
        chain.doFilter(request, response);
    }
}

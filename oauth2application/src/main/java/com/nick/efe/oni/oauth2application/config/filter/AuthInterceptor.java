//package com.nick.efe.oni.oauth2application.config.filter;
//
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.stereotype.Component;
//import org.springframework.web.servlet.HandlerInterceptor;
//
//
//@Component
//public class AuthInterceptor implements HandlerInterceptor {
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//
//        // checking if request has a bearer authorization header
//        if (request.getHeader("Authorization") == null || !request.getHeader("Authorization").startsWith("Bearer ")) {
//            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//            response.getWriter().write("❌ Unauthorized: Missing or invalid token");
//            System.out.println("❌ Unauthorized: Missing or invalid token");
//            return false; // 🚫 Reject request
//        }
//
//        return true;
//    }
//}
package com.Bagbuilder.RestAPI.Security;


import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebFilter("/*")
public class AuthFilter implements Filter {

    private final String apiKey;

    public AuthFilter(String apiKey) {
        this.apiKey = apiKey;
    }


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String key = httpRequest.getHeader("Authorization");
        if (key == null || !key.equals(apiKey)) {
            HttpServletResponse httpResponse = (HttpServletResponse) response;
            httpResponse.sendError(HttpServletResponse.SC_FORBIDDEN, "Bad api key");
            return;
        }
        chain.doFilter(request, response);
    }

    // @Override
    // public void destroy() {
    // }

    // @Override
    // public void init(FilterConfig filterConfig) throws ServletException {
    // }
}

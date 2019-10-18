package com.le.tmall.servlet;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Servlet Filter implementation class CharacterFilter
 */
@WebFilter("/*")
public class CharacterFilter implements Filter {

    /**
     * Default constructor.
     */
    public CharacterFilter() {
        // TODO Auto-generated constructor stub
    }

    /**
     * @see Filter#destroy()
     */
    public void destroy() {
        // TODO Auto-generated method stub
    }

    /**
     * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
     */
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        // TODO Auto-generated method stub
        // place your code here
        HttpServletRequest request = (HttpServletRequest) req;
        request.setCharacterEncoding("utf-8");

        // pass the request along the filter chain
        chain.doFilter(request, res);
    }

    /**
     * @see Filter#init(FilterConfig)
     */
    public void init(FilterConfig fConfig) {
        // TODO Auto-generated method stub
    }

}

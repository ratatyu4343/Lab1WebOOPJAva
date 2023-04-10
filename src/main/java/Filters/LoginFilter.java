package Filters;

import Menegers.DataBase.DataManager;
import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginFilter implements Filter {
    private static final String UTF = "utf-8";
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();
        request.setCharacterEncoding(UTF);
        response.setCharacterEncoding(UTF);
        if(request.getRequestURI().endsWith("/login")) {
            filterChain.doFilter(request, response);
            return;
        }

        Object authHeader =  session.getAttribute("Authorization");
        if (session.getAttribute("logout")!=null||authHeader == null || !authHeader.toString().startsWith("Bearer ")) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.sendRedirect(request.getContextPath()+"/login");
            return;
        }
        String jwt = authHeader.toString().substring(7);
        DecodedJWT decodedJWT = DataManager.verifyJwt(jwt);
        if(decodedJWT != null) {
            filterChain.doFilter(request, response);
        } else {
            session.removeAttribute("Authorization");
            response.sendRedirect(request.getContextPath()+"/login");
        }
    }

    @Override
    public void destroy() {

    }
}

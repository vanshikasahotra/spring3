package com.spring.springbootmongodbas.Configuration;

import com.spring.springbootmongodbas.Services.TokenService;
import org.bson.types.ObjectId;
import org.springframework.context.annotation.Configuration;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@Configuration
public class JWTFIlter {

    public class JWTFilter {
        private TokenService tokenService;
        public JWTFilter(TokenService tokenService)
        {
            this.tokenService=tokenService;
        }

        public void doFilter(ServletRequest req, ServletResponse res, FilterChain filterChain)
                throws IOException, ServletException {
            HttpServletRequest httpServletRequest=(HttpServletRequest) req;
            HttpServletResponse httpServletResponse=(HttpServletResponse) res;

            String token=httpServletRequest.getHeader("Authorization");
            if("OPTIONS".equalsIgnoreCase(httpServletRequest.getMethod())) {
                httpServletResponse.sendError(HttpServletResponse.SC_OK, "success");
                return;
            }
            if(allowWithoutToken(httpServletRequest))
            {
                httpServletResponse.setStatus(HttpServletResponse.SC_OK,"success" );
                filterChain.doFilter(req,res);
            }
            else {
                ObjectId userId=new ObjectId(tokenService.getUserIdToken(token));

                httpServletRequest.setAttribute("userId",userId);
                filterChain.doFilter(req,res);
            }
        }
        public boolean allowWithoutToken(HttpServletRequest httpServletRequest)
        {
            System.out.printf(httpServletRequest.getRequestURI());
            if(httpServletRequest.getRequestURI().contains("/user"))
            {
                return true;
            }
            return false;
        }
    }
}

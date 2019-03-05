package com.stackroute.userauthenticationservice.security.jwt;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
;
import org.springframework.security.web.AuthenticationEntryPoint;

@Component
    public class JwtEntryPoint implements AuthenticationEntryPoint {

        private static final Logger logger = LoggerFactory.getLogger(JwtEntryPoint.class);




        @Override
        public void commence(HttpServletRequest httpServletRequest,
                             HttpServletResponse httpServletResponse,
                             org.springframework.security.core.AuthenticationException e)
                throws IOException, ServletException {
            logger.error("Unauthorized error. Message - {}", e.getMessage());
                httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Error -> Unauthorized");
            }
        }






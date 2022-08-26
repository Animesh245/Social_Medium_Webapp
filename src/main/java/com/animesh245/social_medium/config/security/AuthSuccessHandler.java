package com.animesh245.social_medium.config.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
@Configuration
public class AuthSuccessHandler implements AuthenticationSuccessHandler
{
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException
    {
        HttpSession session = request.getSession();

        User authUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        session.setAttribute("user", authUser);
        session.setAttribute("email", authUser.getUsername());
        session.setAttribute("authorities", authentication.getAuthorities());

        response.setStatus(HttpServletResponse.SC_OK);

        SavedRequest savedRequest = new HttpSessionRequestCache().getRequest(request,response);
        response.sendRedirect(savedRequest == null ? "/" : savedRequest.getRedirectUrl());
    }
}

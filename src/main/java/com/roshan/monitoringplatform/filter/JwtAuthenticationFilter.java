package com.roshan.monitoringplatform.filter;


import com.roshan.monitoringplatform.Entity.User;
import com.roshan.monitoringplatform.Repository.userRepository;
import com.roshan.monitoringplatform.service.JWTService;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JWTService jwtService;

    @Autowired
    private userRepository userRepo;


    public JwtAuthenticationFilter(JWTService jwtService){
        this.jwtService = jwtService;

    }

    @Override
    protected  void doFilterInternal(
            HttpServletRequest request , HttpServletResponse response , FilterChain filterChain
    ) throws ServletException , IOException{

        String authHeader = request.getHeader("Authorization");
        if(authHeader != null && authHeader.startsWith("Bearer")){
            String token= authHeader.substring(6);

            try {
                String email = jwtService.getEmailFromJWT(token);
                User user = userRepo.findByEmail(email);
                if(user == null ){
                    throw new IllegalArgumentException("User not found ");
                }
                String role = "ROLE_"+user.getRole().name();
                SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(role);

                if(SecurityContextHolder.getContext().getAuthentication()==null) {
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                            email, null, Collections.singletonList(simpleGrantedAuthority));

                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authentication);

                }

                }catch (JwtException | IllegalArgumentException e) {
                    SecurityContextHolder.clearContext();
                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED,
                            "Invalid or expired JWT");
                    return;
            }
            }
        filterChain.doFilter(request, response);

    }


}

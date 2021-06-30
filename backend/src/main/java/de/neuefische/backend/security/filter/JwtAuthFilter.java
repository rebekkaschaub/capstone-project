package de.neuefische.backend.security.filter;

import de.neuefische.backend.security.service.JwtService;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtService jwtService;

    @Autowired
    public JwtAuthFilter(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

       String token = getAuthToken(request);

       if(token != null && !token.isBlank()){
           try{
               Claims claims = jwtService.parseClaims(token);
               setSecurityContext(claims.getSubject());
           }catch(Exception e){
               throw new ResponseStatusException(HttpStatus.FORBIDDEN, "invalid token");
           }
       }

        filterChain.doFilter(request, response);
    }

    private String getAuthToken(HttpServletRequest request) {
        String authorization = request.getHeader("Authorization");
        if(authorization != null){
           return authorization.replace("Bearer", "").trim();
        }
        return null;
    }

    private void setSecurityContext(String subject) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(subject, "");
        SecurityContextHolder.getContext().setAuthentication(token);
    }
}

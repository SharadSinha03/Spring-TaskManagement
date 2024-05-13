package com.sharad.tmanager.config;


import java.io.IOException;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.micrometer.common.util.StringUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@Component
public class JWTAuthFilter extends OncePerRequestFilter {

	@Autowired
	JWTService jwtService;
	
	@Autowired
	private CustomUserDetailsService userDetailsService;
	
	String secretKey = "MySecretKey";
    String salt = "MySalt";

	private static List<String> skipFilterUrls = Arrays.asList("/api/v1/**", "/*.html", "/favicon.ico", "/**/*.html",
			"/**/*.css", "/**/*.js");

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		for (Enumeration<?> e = request.getHeaderNames(); e.hasMoreElements();) {
			String nextHeaderName = (String) e.nextElement();
			String headerValue = request.getHeader(nextHeaderName);
		}
		final String authHeader = request.getHeader("Authorization");
		final String jwt;
		final String userEmail;
		if (authHeader == null || !authHeader.startsWith("Bearer ")) {
			filterChain.doFilter(request, response);
			return;
		}
		jwt = authHeader.substring(7);
		userEmail = jwtService.extractUserName(jwt);
		if (userEmail != null && StringUtils.isNotEmpty(userEmail) && SecurityContextHolder.getContext().getAuthentication() == null) {
		//	UserDetails user = this.userDetailsService.loadUserByUsername(AES256Encryption.decrypt(userEmail, secretKey, salt));

			UserDetails user = this.userDetailsService.loadUserByUsername(userEmail);

			if (jwtService.isTokenValid(jwt, userEmail)) {
				
				SecurityContext context = SecurityContextHolder.createEmptyContext();
				//user.getAuthorities().stream().forEach((name) -> System.out.println(name.getAuthority()));
				UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(user, null,
						user.getAuthorities());
				authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				context.setAuthentication(authToken);
				SecurityContextHolder.setContext(context);
			}
		}
		filterChain.doFilter(request, response);
	}

}
package br.com.marcellopassos.partycoin.security;

import static br.com.marcellopassos.partycoin.security.SecurityConstants.EXPIRATION_TIME;
import static br.com.marcellopassos.partycoin.security.SecurityConstants.AUTHORIZATION_HEADER;
import static br.com.marcellopassos.partycoin.security.SecurityConstants.ACEH_HEADER;
import static br.com.marcellopassos.partycoin.security.SecurityConstants.SECRET;
import static br.com.marcellopassos.partycoin.security.SecurityConstants.TOKEN_PREFIX;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.marcellopassos.partycoin.entities.ApplicationUser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	private AuthenticationManager authenticationManager;

	public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res)
			throws AuthenticationException {
		try {
			ApplicationUser creds = new ObjectMapper().readValue(req.getInputStream(), ApplicationUser.class);
			UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
					creds.getUsername(),
					creds.getPassword(),
					new ArrayList<>());
			Authentication auth = authenticationManager.authenticate(token);
			return auth;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res, FilterChain chain,
			Authentication auth) throws IOException, ServletException {
		String token = Jwts.builder().setSubject(((User) auth.getPrincipal()).getUsername())
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
				.signWith(SignatureAlgorithm.HS512, SECRET.getBytes()).compact();
		res.addHeader(AUTHORIZATION_HEADER, TOKEN_PREFIX + token);
		res.addHeader(ACEH_HEADER, AUTHORIZATION_HEADER);
	}

}
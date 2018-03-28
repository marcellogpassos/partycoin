package br.com.marcellopassos.partycoin.security;

public class SecurityConstants {
	
	public static final String SECRET = "$2y$10$VqIBEOwjtORi4XYOOegJM.yQL..YH/kScxvWiOpS/DlaZmr8cQ7gu";
	
	public static final long EXPIRATION_TIME = 864_000_000; // 10 days
	
	public static final String TOKEN_PREFIX = "Bearer ";
	
	public static final String AUTHORIZATION_HEADER = "Authorization";
	
	public static final String ACEH_HEADER = "Access-Control-Expose-Headers";
	
	public static final String SIGN_UP_URL = "/users/sign-up";
	
}

package banking.common.infrastructure.security;

import java.util.Base64;
import java.util.Collections;
import java.util.Date;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import banking.users.application.dto.UserAuthDto;
import banking.users.application.dto.UserClaimDto;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenProvider {
	@Value("${security.jwt.token.secret-key}")
	private String secretKey;

	@Value("${security.jwt.token.expires-minutes}")
	private long minutesToExpiration;

	@PostConstruct
	protected void init() {
		secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
	}

	public String buildJwtToken(UserAuthDto userAuthDto) {
		Date now = new Date();
		Date validity = new Date(now.getTime() + (minutesToExpiration * 60 * 1000));
		JwtBuilder jwtBuilder = Jwts.builder();
		jwtBuilder
			.setSubject(userAuthDto.getName())
			.setId(UUID.randomUUID().toString())
			.claim("isAuthenticated", userAuthDto.isAuthenticated());
		for (UserClaimDto userClaimDto : userAuthDto.getClaims()) {
			jwtBuilder.claim(userClaimDto.getType(), userClaimDto.getValue());
		}
		return jwtBuilder
				.setIssuedAt(now)
				.setExpiration(validity)
				.signWith(SignatureAlgorithm.HS256, this.secretKey)
				.compact();
	}

	public String getUsername(String token) {
		return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
	}

	public String resolveToken(HttpServletRequest req) {
		String bearerToken = req.getHeader("Authorization");
		if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
			return bearerToken.substring(7, bearerToken.length());
		}
		return null;
	}

	public boolean validateToken(String token) throws Exception {
		try {
			Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
			return true;
		} catch (JwtException | IllegalArgumentException e) {
			throw new Exception("Missing, invalid or expired token");
		}
	}
	
	public Authentication getAuthentication(String token) {
		String userName = getUsername(token);
		return new UsernamePasswordAuthenticationToken(userName, null, Collections.emptyList());
	}
}

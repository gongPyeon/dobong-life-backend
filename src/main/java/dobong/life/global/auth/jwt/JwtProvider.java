package dobong.life.global.auth.jwt;

import dobong.life.global.auth.dto.TokenCommand;
import dobong.life.global.auth.exception.InvalidJwtException;
import dobong.life.global.auth.service.CustomUserDetailService;
import dobong.life.global.auth.service.principal.UserPrincipal;
import dobong.life.global.util.response.status.BaseErrorCode;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

@Service
@Slf4j
public class JwtProvider {

    private static final String AUTHORITIES_KEY = "auth";
    private static final String BEARER_TYPE = "Bearer";
    private static final String ACCESS_TYPE = "access";
    private static final String REFRESH_TYPE = "refresh";

    //@Value("${jwt.access.expiration}")
    private Long ACCESS_TOKEN_EXPIRE_TIME = 100000000L;

    //@Value("${jwt.refresh.expiration}")
    private Long REFRESH_TOKEN_EXPIRE_TIME = 100000000L;
    private final Key key;
    public JwtProvider(@Value("${jwt.secretKey}") String secretKey){
        this.key = Keys.hmacShaKeyFor(
                Decoders.BASE64.decode(secretKey));
    }

    public TokenCommand generateToken(Authentication authentication){
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();

        String email = userPrincipal.getEmail();
        String authorities = getAuthorities(authentication.getAuthorities());
        log.info("generateToken 함수의 email = {}, authorities = {}", email, authorities);

        String accessToken = createAccessToken(email, authorities);
        String refreshToken = createRefreshToken(email, authorities);
        log.info("generateToken 함수 실행, accessToken = {}, refreshToken = {}", accessToken, refreshToken);

        return TokenCommand.builder()
                .grantType(BEARER_TYPE)
                .accessToken(accessToken)
                .accessTokenExpirationTime(ACCESS_TOKEN_EXPIRE_TIME)
                .refreshToken(refreshToken)
                .refreshTokenExpirationTime(REFRESH_TOKEN_EXPIRE_TIME)
                .build();
    }
    public String extractBearer(String accessToken) {
        if (accessToken.startsWith(BEARER_TYPE)) {
            return accessToken = accessToken.substring(7);
        }
        return null;
    }

    public Claims validateToken(String token) throws InvalidJwtException {
        String error = "";

        try {
            Jws<Claims> claimsJws = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            Claims claims = claimsJws.getBody();
            return claims;
        }catch (SecurityException | MalformedJwtException e){
            error = "[ERROR] 토큰의 서명이 유효하지 않습니다.";
        }catch (ExpiredJwtException e){
            error = "[ERROR] 토큰이 만료되었습니다.";
        }catch (UnsupportedJwtException e){
            error = "[ERROR] 지원되지 않는 JWT 토큰입니다.";
        }catch (IllegalArgumentException e){
            error = "[ERROR] JWT 클레임이 포함되어있지 않습니다.";
        }catch (Exception e) {
            error = "[ERROR] 유효하지 않은 액세스 토큰입니다.";
        }

        throw new InvalidJwtException(error);
    }

    private String getAuthorities(Collection<? extends GrantedAuthority> authorities) {
        return authorities.stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));
    }

    private String createAccessToken(String email, String authorities) {
        Date now = new Date();
        String accessToken = Jwts.builder()
                .setSubject(email)
                .claim(AUTHORITIES_KEY, authorities)
                .claim("type", ACCESS_TYPE)
                .setIssuedAt(now)   //토큰 발행 시간 정보
                .setExpiration(new Date(now.getTime() + ACCESS_TOKEN_EXPIRE_TIME))  //토큰 만료 시간 설정
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();

        return accessToken;
    }

    private String createRefreshToken(String email, String authorities){
        Date now = new Date();
        String refreshToken = Jwts.builder()
                .setSubject(email)
                .claim(AUTHORITIES_KEY, authorities)
                .claim("type", REFRESH_TYPE)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + REFRESH_TOKEN_EXPIRE_TIME))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();

        return refreshToken;
    }

    public String generateTokenFromRefreshToken(String refreshToken) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(refreshToken)
                .getBody();

        String email = claims.getSubject();
        String authorities = claims.get(AUTHORITIES_KEY, String.class);

        return createAccessToken(email, authorities);
    }

}


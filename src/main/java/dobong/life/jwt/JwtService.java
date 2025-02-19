package dobong.life.jwt;

import dobong.life.dto.TokenCommand;
import dobong.life.service.LoginService;
import dobong.life.util.exception.InvalidJwtException;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

@Service
@Slf4j
public class JwtService {

    private final LoginService loginService;

    private static final String AUTHORITIES_KEY = "auth";
    private static final String BEARER_TYPE = "Bearer";
    private static final String ACCESS_TYPE = "access";
    private static final String REFRESH_TYPE = "refresh";

    @Value("${jwt.access.expiration}")
    private Long ACCESS_TOKEN_EXPIRE_TIME;

    @Value("${jwt.refresh.expiration}")
    private Long REFRESH_TOKEN_EXPIRE_TIME;
    private final Key key;
    public JwtService(@Value("${jwt.secretKey}") String secretKey, LoginService loginService){
        this.loginService = loginService;
        this.key = Keys.hmacShaKeyFor(
                Decoders.BASE64.decode(secretKey));
    }

    public TokenCommand generateToken(Authentication authentication){
        String name = authentication.getName();
        String authorities = getAuthorities(authentication.getAuthorities());
        log.info("generateToken 함수의 name = {}, authorities = {}", name, authorities);

        String accessToken = createAccessToken(name, authorities);
        String refreshToken = createRefreshToken(name, authorities);
        log.info("generateToken 함수 실행, accessToken = {}, refreshToken = {}", accessToken, refreshToken);

        return TokenCommand.builder()
                .grantType(BEARER_TYPE)
                .accessToken(accessToken)
                .accessTokenExpirationTime(ACCESS_TOKEN_EXPIRE_TIME)
                .refreshToken(refreshToken)
                .refreshTokenExpirationTime(REFRESH_TOKEN_EXPIRE_TIME)
                .build();
    }

    public Authentication getAuthentication(String accessToken){
        Claims claims = validateToken(accessToken);
        UserDetails userDetails = loginService.loadUserByUsername(claims.getSubject());

        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    public Claims validateToken(String accessToken) throws InvalidJwtException {
        try {
            Jws<Claims> claimsJws = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(accessToken);
            Claims claims = claimsJws.getBody();
            return claims;
        }catch (SecurityException | MalformedJwtException e){
            log.info("[ERROR] 토큰의 서명이 유효하지 않습니다");
        }catch (ExpiredJwtException e){
            log.info("[ERROR] 토큰이 만료되었습니다");
        }catch (UnsupportedJwtException e){
            log.info("[ERROR] 지원되지 않는 JWT 토큰입니다");
        }catch (IllegalArgumentException e){
            log.info("[ERROR] JWT 클레임이 포함되어있지 않습니다");
        }catch (Exception e) {
            log.error("[ERROR] 유효하지 않은 액세스 토큰입니다", e);
        }

        throw new InvalidJwtException("[ERROR] 유효하지 않은 액세스 토큰입니다.");
    }

    private String getAuthorities(Collection<? extends GrantedAuthority> authorities) {
        return authorities.stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));
    }

    private String createAccessToken(String name, String authorities) {
        Date now = new Date();
        String accessToken = Jwts.builder()
                .setSubject(name)
                .claim(AUTHORITIES_KEY, authorities)
                .claim("type", ACCESS_TYPE)
                .setIssuedAt(now)   //토큰 발행 시간 정보
                .setExpiration(new Date(now.getTime() + ACCESS_TOKEN_EXPIRE_TIME))  //토큰 만료 시간 설정
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();

        return accessToken;
    }

    private String createRefreshToken(String name, String authorities){
        Date now = new Date();
        String refreshToken = Jwts.builder()
                .setSubject(name)
                .claim(AUTHORITIES_KEY, authorities)
                .claim("type", REFRESH_TYPE)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + REFRESH_TOKEN_EXPIRE_TIME))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();

        return refreshToken;
    }
}

//public String generateTokenFromRefreshToken(RefreshToken refreshToken) {
//    Claims claims = Jwts.parserBuilder()
//            .setSigningKey(key)
//            .build()
//            .parseClaimsJws(refreshToken.getRefreshToken())
//            .getBody();
//
//    String username = claims.getSubject();
//    log.info("username = {}", username);
//    String authorities = claims.get(AUTHORITIES_KEY, String.class);
//
//    return createAccessToken(username, authorities);
//}
//
package com.codingseahorse.tastylab.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static java.sql.Date.valueOf;

@Profile(value = {"development", "production"})
@Component
@NoArgsConstructor
public class JwtManager {

    public String refreshTokenCreator(HttpServletRequest request, String username, Algorithm algorithm) {
        String refresh_token =
                JWT.create()
                        .withSubject(username)
                        .withExpiresAt(valueOf(LocalDate.now().plusWeeks(2))) // 2 weeks valid refresh_token
                        .withIssuer(request.getRequestURL().toString())
                        .sign(algorithm);
        return refresh_token;
    }

    public String accessTokenCreator(HttpServletRequest request, String username, Algorithm algorithm, Set<String> authorities) {
        String access_token =
                JWT.create()
                        .withSubject(username)
                        .withExpiresAt(valueOf(LocalDate.now().plusDays(1))) // 1 Day valid access_token
                        .withIssuer(request.getRequestURL().toString())
                        .withClaim(
                                "membership",
                                authorities.stream().toList())
                        .sign(algorithm);
        return access_token;
    }

    public Map<String, String> tokenCreator(String access_token, String refresh_token){

        Map<String,String> tokens = new HashMap<>();

        tokens.put("access_token", access_token);
        tokens.put("refresh_token", refresh_token);

        return tokens;
    }
}

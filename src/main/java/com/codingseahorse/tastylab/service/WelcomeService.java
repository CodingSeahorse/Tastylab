package com.codingseahorse.tastylab.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.codingseahorse.tastylab.dto.RegistrationDTO;
import com.codingseahorse.tastylab.exception.BadRequestException;
import com.codingseahorse.tastylab.exception.NotFoundException;
import com.codingseahorse.tastylab.jwt.JwtManager;
import com.codingseahorse.tastylab.model.member.Member;
import com.codingseahorse.tastylab.model.member.MemberCard;
import com.codingseahorse.tastylab.repository.MemberCardRepository;
import com.codingseahorse.tastylab.repository.MemberRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.MimeTypeUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static com.codingseahorse.tastylab.model.member.MembershipRole.*;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;

@Service
@NoArgsConstructor
public class WelcomeService implements UserDetailsService {
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    MemberCardRepository memberCardRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    JwtManager jwtManager = new JwtManager();

    public void registerMember(RegistrationDTO registrationDTO) {
        boolean existsMember =
                memberRepository.existsMemberByEmail(registrationDTO.getEmail());

        if (existsMember) {
            throw new BadRequestException(String.format(
                    "This email adress %s is already taken. " +
                            "Please enter another email address",
                    registrationDTO.getEmail()));
        }

        MemberCard memberCard = new MemberCard(
                LocalDateTime.now(),
                registrationDTO.getUsername(),
                passwordEncoder.encode(registrationDTO.getPassword()),
                BLOGGER.getGrantedAuthorities(),
                true,
                true,
                true,
                true);

        memberCard.setMembershipRole(BLOGGER);

        Member member = new Member(
                registrationDTO.getFirstName(),
                registrationDTO.getLastName(),
                registrationDTO.getEmail(),
                registrationDTO.getAge(),
                registrationDTO.getGender(),
                memberCard);

        memberCardRepository.save(memberCard);
        memberRepository.save(member);
    }

    public void refreshMyToken(HttpServletRequest request,
                               HttpServletResponse response) throws IOException {

        String authorizationHeader = request.getHeader(AUTHORIZATION);

        if(authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {

            try {
                String refresh_token = authorizationHeader.substring("Bearer ".length());

                Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());

                JWTVerifier verifier = JWT.require(algorithm).build();

                DecodedJWT decodedJWT = verifier.verify(refresh_token);

                String username = decodedJWT.getSubject();

                MemberCard memberCard = memberCardRepository.getMemberCardByUsername(username);

                Set<String> authorities =
                        memberCard.getGrantedAuthorities().stream()
                            .map(GrantedAuthority::getAuthority)
                            .collect(Collectors.toSet());

                String access_token =
                        jwtManager.accessTokenCreator(
                                request,
                                username,
                                algorithm,
                                authorities);

                Map<String, String> tokens = new HashMap<>();

                tokens.put("access_token", access_token);
                tokens.put("refresh_token", refresh_token);

                response.setContentType(MediaType.APPLICATION_JSON_VALUE);

                new ObjectMapper().writeValue(
                        response.getOutputStream(), tokens);
            }catch (Exception exception) {
                response.setHeader("error", exception.getMessage());
                response.setStatus(FORBIDDEN.value());

                Map<String, String> error = new HashMap<>();
                error.put("error_message", exception.getMessage());

                response.setContentType(MimeTypeUtils.APPLICATION_JSON_VALUE);

                new ObjectMapper().writeValue(
                        response.getOutputStream(), error);
            }
        } else {
            throw new RuntimeException("Refresh token is missing");
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MemberCard memberCard =
                memberCardRepository.getMemberCardByUsername(username);

        if (memberCard == null){
            throw new NotFoundException(String.format(
                    "User with the username: %s not found",
                    username));
        }

        return new User(
                memberCard.getUsername(),
                memberCard.getPassword(),
                memberCard.getGrantedAuthorities());
    }
}
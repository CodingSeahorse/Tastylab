package com.codingseahorse.tastylab.service;

import com.codingseahorse.tastylab.config.PasswordConfig;
import com.codingseahorse.tastylab.dto.RegistrationDTO;
import com.codingseahorse.tastylab.exception.BadRequestException;
import com.codingseahorse.tastylab.exception.NotFoundException;
import com.codingseahorse.tastylab.model.member.Gender;
import com.codingseahorse.tastylab.model.member.Member;
import com.codingseahorse.tastylab.model.member.MemberCard;
import com.codingseahorse.tastylab.repository.MemberCardRepository;
import com.codingseahorse.tastylab.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.io.IOException;
import java.time.LocalDateTime;

import static com.codingseahorse.tastylab.model.member.MembershipRole.*;
import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class WelcomeServiceTest {
    @Mock
    PasswordEncoder passwordEncoder;
    @Mock
    MemberRepository memberRepository;
    @Mock
    MemberCardRepository memberCardRepository;
    @MockBean
    PasswordConfig passwordConfig;
    @InjectMocks
    WelcomeService welcomeService;

    // <editor-fold defaultstate="collapsed" desc="created RegistrationDTO,MockHttpServletRequest and Response">
    MemberCard memberCard = new MemberCard(
            LocalDateTime.now(),
            "shaggyDoo",
            "123",
            ADMIN.getGrantedAuthorities(),
            true,
            true,
            true,
            true
    );
    RegistrationDTO registrationDTO = new RegistrationDTO(
            "shaggy",
            "123",
            "scooby",
            "Doo",
            "shaggy.doo@gmail.com",
            34,
            Gender.MALE);
    MockHttpServletRequest request = new MockHttpServletRequest();
    MockHttpServletResponse response = new MockHttpServletResponse();
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="var: username,password,encodedPassword">
    String username = "shaggyDoo";
    String password = "123";
    String encodedPassword = "$2a$10$tXC30h7iWbq2DG0fjdjo6uouXDCaSVLgihE6dWdCV2FJVJ4BpPauW";
    String token =
            "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9." +
            "eyJzdWIiOiJzaGFnZ3lEb28iLCJpc3MiOiJ" +
            "odHRwOi8vbG9jYWxob3N0OjgwODAvYXBpL3" +
            "dlbGNvbWUvbG9naW4iLCJtZW1iZXJzaGlwI" +
            "jpbIlJPTEVfQkxPR0dFUiIsImJsb2dnZXI6" +
            "cmVhZF9hbmRfd3JpdGUiXSwiZXhwIjoxNjM" +
            "yNTIwODAwfQ.J_6x_F4TdkIsGANmKVtO31U" +
            "xNdTzcKk8irpsZ8ql2ZE";
    // </editor-fold>

    @Test
    void should_addMember_to_db() {
        //given
        given(passwordEncoder.encode(any()))
                .willReturn(encodedPassword);
        registrationDTO.setMembershipRole(ADMIN);
        //when
        welcomeService.registerMember(registrationDTO);
        //then
        verify(memberRepository,times(1))
                .save(any(Member.class));
        verify(memberCardRepository,times(1))
                .save(any(MemberCard.class));
    }

    @Test
    void will_throw_when_email_is_taken() {
        String inputEmail = "shaggy.doo@gmail.com";

        given(memberRepository.existsMemberByEmail(inputEmail))
                .willReturn(true);

        assertThatThrownBy(() -> welcomeService.registerMember(registrationDTO))
                .isInstanceOf(BadRequestException.class)
                .hasMessageContaining(
                        "This email adress %s is already taken. " +
                                "Please enter another email address",
                        inputEmail);

        verify(memberRepository,never())
                .save(any(Member.class));
    }

    /*@Test
    void should_refreshToken() throws IOException {
        // given
        request.addHeader("authorization","Bearer "+ token);
        request.addParameter("sub","shaggyDoo");
        request.addParameter("membership","ROLE_BLOGGER");

        given(memberCardRepository.getMemberCardByUsername(username))
                .willReturn(memberCard);
        // when
        welcomeService.refreshMyToken(request,response);
        // then
        verify(memberCardRepository,times(1))
                .getMemberCardByUsername(username);
    }*/

    @Test
    void should_throw_exception_if_token_is_missing() throws IOException {
        // then
        assertThatThrownBy(()-> welcomeService.refreshMyToken(request,response))
                .isInstanceOf(RuntimeException.class).hasMessage("Refresh token is missing");
    }

    @Test
    void should_loadByUsername() {
        // given
        given(passwordEncoder.encode(any()))
                .willReturn(encodedPassword);
        // <editor-fold defaultstate="collapsed" desc="MemberCard">
        MemberCard memberCard = new MemberCard(
                LocalDateTime.now(),
                username,
                passwordEncoder.encode(password),
                ADMIN.getGrantedAuthorities(),
                true,
                true,
                true,
                true
        );
        // </editor-fold>
        // <editor-fold defaultstate="collapsed" desc="User">
        User user = new User(
                username,
                encodedPassword,
                true,
                true,
                true,
                true,
                ADMIN.getGrantedAuthorities()
        );
        // </editor-fold>
        given(memberCardRepository.getMemberCardByUsername(username))
                .willReturn(memberCard);
        // when
        when(welcomeService.loadUserByUsername(username))
                .thenReturn(memberCard);

        UserDetails result = welcomeService.loadUserByUsername(username);
        // then
        assertThat(result)
                .isEqualTo(user);
    }

    @Test
    void should_throw_exception_if_username_not_found() {
        // given
        given(memberCardRepository.getMemberCardByUsername(username))
                .willReturn(null);
        // then
        assertThatThrownBy(
                ()-> welcomeService.loadUserByUsername(username))
                .isInstanceOf(NotFoundException.class)
                .hasMessage("User with the username: shaggyDoo not found");
    }
}
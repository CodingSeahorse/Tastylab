package com.codingseahorse.tastylab.service;

import com.codingseahorse.tastylab.dto.RegistrationDTO;
import com.codingseahorse.tastylab.exception.BadRequestException;
import com.codingseahorse.tastylab.model.member.Gender;
import com.codingseahorse.tastylab.model.member.Member;
import com.codingseahorse.tastylab.model.member.MemberCard;
import com.codingseahorse.tastylab.repository.MemberCardRepository;
import com.codingseahorse.tastylab.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class WelcomeServiceTest {
    @Mock
    MemberRepository memberRepository;
    @Mock
    MemberCardRepository memberCardRepository;
    @InjectMocks
    WelcomeService welcomeService;

    // <editor-fold desc="created RegistrationDTO">
    RegistrationDTO registrationDTO = new RegistrationDTO(
            "shaggy",
            "123",
            "scooby",
            "Doo",
            "shaggy.doo@gmail.com",
            34,
            Gender.MALE);
    // </editor-fold>

    @Test
    void should_addMember_to_db() {
        //When
        welcomeService.registerMember(registrationDTO);
        //Then
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

    @Test
    void check_if_WelcomeService_isNotNull() {
        WelcomeService welcomeService = new WelcomeService();
        assertThat(welcomeService)
                .isNotNull();
    }
}
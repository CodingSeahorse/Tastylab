package com.codingseahorse.tastylab.service;

import com.codingseahorse.tastylab.dto.RegistrationDTO;
import com.codingseahorse.tastylab.exception.BadRequestException;
import com.codingseahorse.tastylab.model.member.Member;
import com.codingseahorse.tastylab.model.member.MemberCard;
import com.codingseahorse.tastylab.repository.MemberCardRepository;
import com.codingseahorse.tastylab.repository.MemberRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@NoArgsConstructor
public class WelcomeService {
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    MemberCardRepository memberCardRepository;

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
                registrationDTO.getPassword());

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
}

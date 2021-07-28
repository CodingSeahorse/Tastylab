package com.codingseahorse.tastylab.repository;

import com.codingseahorse.tastylab.model.member.Gender;
import com.codingseahorse.tastylab.model.member.Member;
import com.codingseahorse.tastylab.model.member.MemberCard;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.*;

@DataJpaTest
class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;
    @Autowired
    MemberCardRepository memberCardRepository;

    @BeforeEach
    public void createMember_with_memberCard(){
        // <editor-fold desc="created MemberCard">
        MemberCard memberCardDoo = new MemberCard(
                LocalDateTime.now(),
                "scooby",
                "dooo"
        );

        memberCardRepository.save(memberCardDoo);
        // </editor-fold>
        // <editor-fold desc="created Member">
        Member scoobyDoo = new Member(
                "scooby",
                "doo",
                "scooby.doo@gmail.com",
                8,
                Gender.MALE,
                memberCardDoo
        );

        memberRepository.save(scoobyDoo);
        // </editor-fold>
    }
    @Test
    public void check_if_getMemberByEmailAndFirstName_returns_the_right_member(){
        Member getMember = memberRepository.getMemberByEmailAndFirstName("scooby.doo@gmail.com","scooby");

        assertThat(getMember)
                .satisfies(member -> {
                    assertThat(member.getEmail()).isEqualTo("scooby.doo@gmail.com");
                    assertThat(member.getFirstName()).isEqualTo("scooby");
                })
                .isInstanceOf(Member.class)
                .isNotNull();
    }

    @Test
    public void check_if_existsMemberByEmail_returns_true_or_false(){
        boolean alreadyExistsMemberWithEmail = memberRepository.existsMemberByEmail("scooby.doo@gmail.com");

        assertThat(alreadyExistsMemberWithEmail)
                .isNotNull()
                .isTrue();
    }
}
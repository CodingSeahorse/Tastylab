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

//TODO WRITE MEMBER-REPOSITORY-TEST
@DataJpaTest
class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;
    @Autowired
    MemberCardRepository memberCardRepository;

    MemberCard scoobyDooCard;
    Member scoobyDoo;

    @BeforeEach
    void createMember_with_memberCard(){
        // <editor-fold defaultstate="collapsed" desc="created MemberCard">
        scoobyDooCard = new MemberCard(
                LocalDateTime.now(),
                "scooby",
                "dooo"
        );

        memberCardRepository.save(scoobyDooCard);
        // </editor-fold>
        // <editor-fold defaultstate="collapsed" desc="created Member">
        scoobyDoo = new Member(
                "scooby",
                "doo",
                "scooby.doo@gmail.com",
                8,
                Gender.MALE,
                scoobyDooCard
        );

        memberRepository.save(scoobyDoo);
        // </editor-fold>
    }

    @Test
    void should_getMemberByEmailAndFirstName_return_Member(){
        Member getMember = memberRepository.getMemberByEmailAndFirstName("scooby.doo@gmail.com","scooby");

        assertThat(getMember)
                .isNotNull()
                .isEqualTo(scoobyDoo);
    }
    // TODO: FIX NULLPOINT (Problems with OneToOne Relationship(MemberCard))
    @Test
    void should_getMemberByMemberCardUsername_return_Member(){
        Member searchedMember = memberRepository.getMemberByMembercardUsername("scooby");

        assertThat(searchedMember)
                .isNotNull()
                .isEqualTo(scoobyDoo);
    }
    // TODO: FIX NULLPOINT (Problems with OneToOne Relationship(MemberCard))
    @Test
    void should_getMemberByMembercardUsernameAndMembercardPassword_return_Member(){
        Member searchedMember = memberRepository.getMemberByMembercardUsernameAndMembercardPassword("scooby","dooo");

        assertThat(searchedMember)
                .isNotNull();
    }

    @Test
    void should_existsMemberByEmail_return_true_or_false(){
        boolean searchedMember = memberRepository.existsMemberByEmail("scooby.doo@gmail.com");

        assertThat(searchedMember)
                .isTrue();
    }
}
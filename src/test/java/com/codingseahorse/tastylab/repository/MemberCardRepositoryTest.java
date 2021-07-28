package com.codingseahorse.tastylab.repository;

import com.codingseahorse.tastylab.model.member.MemberCard;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.*;

@DataJpaTest
class MemberCardRepositoryTest {
    @Autowired
    MemberCardRepository memberCardRepository;

    @BeforeEach
    public void createMemberCard(){
        MemberCard daphneCard = new MemberCard(
                LocalDateTime.now(),
                "daphne",
                "doo"
        );
        memberCardRepository.save(daphneCard);
    }
    @Test
    public void check_if_getMemberCardByUsername_returns_the_right_MemberCard(){
        MemberCard getMemberCard = memberCardRepository.getMemberCardByUsername("daphne");

        assertThat(getMemberCard)
                .satisfies(memberCard -> {
                    assertThat(memberCard.getUsername()).isEqualTo("daphne");
                    assertThat(memberCard.getPassword()).isEqualTo("doo");
                })
                .isInstanceOf(MemberCard.class)
                .isNotNull();
    }

}
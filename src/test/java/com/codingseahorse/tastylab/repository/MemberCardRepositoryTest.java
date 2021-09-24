package com.codingseahorse.tastylab.repository;

import com.codingseahorse.tastylab.model.member.MemberCard;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;

import static com.codingseahorse.tastylab.model.member.MembershipRole.*;
import static org.assertj.core.api.Assertions.*;

@DataJpaTest
class MemberCardRepositoryTest {
    @Autowired
    MemberCardRepository memberCardRepository;

    MemberCard daphneCard;

    @BeforeEach
    public void createMemberCard() {
        daphneCard = new MemberCard(
                LocalDateTime.now(),
                "daphne",
                "doo",
                BLOGGER.getGrantedAuthorities(),
                true,
                true,
                true,
                true);
        daphneCard.setMembershipRole(BLOGGER);
        memberCardRepository.save(daphneCard);
    }

    @Test
    public void check_if_getMemberCardByUsername_returns_the_right_MemberCard() {
        MemberCard getMemberCard = memberCardRepository.getMemberCardByUsername("daphne");

        assertThat(getMemberCard)
                .isNotNull()
                .isInstanceOf(MemberCard.class)
                .isEqualTo(daphneCard);
    }
}
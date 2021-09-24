package com.codingseahorse.tastylab.service;

import com.codingseahorse.tastylab.dto.MemberDTO;
import com.codingseahorse.tastylab.model.member.Gender;
import com.codingseahorse.tastylab.model.member.Member;
import com.codingseahorse.tastylab.model.member.MemberCard;
import com.codingseahorse.tastylab.repository.MemberRepository;
import com.codingseahorse.tastylab.requestsModels.MemberRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static com.codingseahorse.tastylab.model.member.MembershipRole.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class MemberServiceTest {

    @Mock
    MemberRepository memberRepository;
    @InjectMocks
    MemberService memberService;

    // <editor-fold defaultstate="collapsed" desc="created MemberCard & Member">
    MemberCard memberCard = new MemberCard(
            LocalDateTime.now(),
            "Eddy",
            "123",
            ADMIN.getGrantedAuthorities(),
            true,
            true,
            true,
            true);

    Member member = new Member(
            "Edward",
            "Elric",
            "Edward.Elric@resembool.org",
            17,
            Gender.MALE,
            memberCard);
    // </editor-fold>

    @Test
    void should_editMemberData_and_return_MemberDTO() {
        // <editor-fold defaultstate="collapsed" desc="created MemberDTO & MemberRequest">
        MemberDTO updatedMember = new MemberDTO(
                "Edward",
                "Elric",
                18,
                Gender.MALE);

        MemberRequest memberRequest = new MemberRequest(
                1,
                "Edward",
                "Elric",
                "Edward.Elric@resembool.org",
                18,
                "Male");
        // </editor-fold>

        when(memberRepository.findById(anyInt()))
                .thenReturn(Optional.of(member));

        when(memberRepository.save(any()))
                .thenReturn(member);

        MemberDTO resultUpdatedMember = memberService.editMemberData(memberRequest);

        assertThat(resultUpdatedMember)
                .isEqualTo(updatedMember);

    }

    @Test
    void should_deleteMember() {
        doNothing().when(memberRepository).deleteById(anyInt());

        memberService.deleteMember(1);

        verify(memberRepository).deleteById(anyInt());
    }

    @Test
    void check_if_MemberService_isNotNull() {
        MemberService checkedMemberService = new MemberService();

        assertThat(checkedMemberService)
                .isNotNull();
    }

    @Test
    void should_return_null_if_searchedMemberIsEmpty() {
        // <editor-fold defaultstate="collapsed" desc="created MemberRequest & MemberDTO">
        MemberRequest memberRequest = new MemberRequest(
                100,
                "jack",
                "noris",
                "jack.noris@world.com",
                120,
                "Male");

        MemberDTO result = memberService.editMemberData(memberRequest);
        // </editor-fold>
        assertThat(result)
                .isNull();
    }
}
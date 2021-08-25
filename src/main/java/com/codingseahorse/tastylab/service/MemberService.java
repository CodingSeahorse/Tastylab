package com.codingseahorse.tastylab.service;

import com.codingseahorse.tastylab.dto.MemberDTO;
import com.codingseahorse.tastylab.model.member.Gender;
import com.codingseahorse.tastylab.model.member.Member;
import com.codingseahorse.tastylab.repository.MemberRepository;
import com.codingseahorse.tastylab.requestsModels.MemberRequest;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@NoArgsConstructor
public class MemberService {
    @Autowired
    MemberRepository memberRepository;

    // ===== UPDATE =====
    public MemberDTO editMemberData(MemberRequest memberRequest) {
        Optional<Member> searchedMember =
                memberRepository.findById(memberRequest.getId());

        if (searchedMember.isEmpty()) {
            return null;
        }

        Gender gender = Gender.valueOf(memberRequest.getGender().toUpperCase());
        Member member = searchedMember.get();

        member.setFirstName(memberRequest.getFirstName());
        member.setLastName(memberRequest.getLastName());
        member.setAge(memberRequest.getAge());
        member.setEmail(memberRequest.getEmail());
        member.setGender(gender);

        memberRepository.save(member);

        return new MemberDTO(
                member.getFirstName(),
                member.getLastName(),
                member.getAge(),
                member.getGender()
        );
    }
    // ===== DELETE =====
    public void deleteMember(Integer memberId) {
        memberRepository.deleteById(memberId);
    }
}

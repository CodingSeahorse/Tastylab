package com.codingseahorse.tastylab.controller;

import com.codingseahorse.tastylab.dto.MemberCardDTO;
import com.codingseahorse.tastylab.dto.MemberDTO;
import com.codingseahorse.tastylab.model.member.Gender;
import com.codingseahorse.tastylab.model.member.Member;
import com.codingseahorse.tastylab.model.member.MemberCard;
import com.codingseahorse.tastylab.repository.MemberRepository;
import com.codingseahorse.tastylab.requestsModels.MemberRequest;
import com.codingseahorse.tastylab.service.MemberService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//TODO: FIX THE MEMBER-CONTROLLER-TEST
@WebMvcTest
class MemberControllerTest {
    /*@MockBean
    MemberService memberService;
    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper mapper;

    @Test
    void should_updateMemberData_and_return_MemberDTO() throws Exception {
        // <editor-fold defaultstate="collapsed" desc="created memberRequest,MemberCardDTO & MemberDTO">
        MemberRequest memberRequest = new MemberRequest(
                1,
                "taylor",
                "blue",
                "taylor.blue@gmail.com",
                23,
                "Male"
        );
        MemberCardDTO memberCardDTO = new MemberCardDTO(
                "tayblue",
                "123"
        );
        MemberDTO memberDTO = new MemberDTO(
                "taylor",
                "blue",
                22,
                Gender.MALE
        );
        MemberCard memberCard = new MemberCard(
                LocalDateTime.now(),
                "tayblue",
                "123"
        );
        Member member = new Member(
                "taylor",
                "blue",
                "taylor.blue@gmail.com",
                22,
                Gender.QUEERGENDER,
                memberCard
        );
        // </editor-fold>
        // <editor-fold defaultstate="collapsed"desc="added data to MemberDTO">
        memberDTO.setMemberCardDTO(memberCardDTO);
        memberDTO.setEmail("taylor.red@gmail.com");
        // </editor-fold>
        when(memberService.editMemberData(any()))
                .thenReturn(memberDTO);

        mockMvc.perform(put("/api/member")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(memberRequest)))
                .andDo(print())
                .andExpect(status().isOk());
    }*/
}
package com.codingseahorse.tastylab.controller;

import com.codingseahorse.tastylab.dto.MemberCardDTO;
import com.codingseahorse.tastylab.dto.MemberDTO;
import com.codingseahorse.tastylab.model.member.Gender;
import com.codingseahorse.tastylab.model.member.Member;
import com.codingseahorse.tastylab.model.member.MemberCard;
import com.codingseahorse.tastylab.requestsModels.MemberRequest;
import com.codingseahorse.tastylab.service.MemberService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static com.codingseahorse.tastylab.model.member.MembershipRole.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc(addFilters = false)
@WebMvcTest(MemberController.class)
class MemberControllerTest {
    @MockBean
    MemberService memberService;
    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper mapper;

    MemberRequest memberRequest;
    MemberCardDTO memberCardDTO;
    MemberDTO memberDTO;
    MemberCard memberCard;
    Member member;

    @BeforeEach
    void setup(){
        createContent();
    }

    @Test
    void should_updateMemberData_and_return_MemberDTO() throws Exception {
        when(memberService.editMemberData(any(MemberRequest.class)))
                .thenReturn(memberDTO);

        mockMvc.perform(put("/api/member")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(memberRequest)))
                .andDo(print())
                .andExpect(status().isOk());
    }

    // TODO:FIX TransactionSystemException
    /*@Test
    void should_throw_TransactionSystemException() throws Exception{
        when(memberService.editMemberData(any()))
                .thenThrow(TransactionSystemException.class);

        mockMvc.perform(put("/api/member")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(memberRequest)))
                .andDo(print())
                .andExpect(status().isServiceUnavailable());
    }*/

    @Test
    void should_throw_notFoundException_() throws Exception {
        when(memberService.editMemberData(any(MemberRequest.class)))
                .thenReturn(null);

        mockMvc.perform(put("/api/member")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(memberRequest)))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    void should_deleteAMember_and_return_NotFoundStatus() throws Exception{
        doNothing().when(memberService).deleteMember(anyInt());

        mockMvc.perform(delete("/api/member/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    void should_throw_DataRetrievalFailureException_when_deleteMember() throws Exception {
        doThrow(EmptyResultDataAccessException.class)
                .when(memberService)
                .deleteMember(101);

        mockMvc.perform(delete("/api/member/1"))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    void createContent(){
        // <editor-fold defaultstate="collapsed" desc="created memberRequest,MemberCardDTO & MemberDTO">
        memberRequest = new MemberRequest(
                1,
                "taylor",
                "blue",
                "taylor.blue@gmail.com",
                23,
                "Male");

        memberCardDTO = new MemberCardDTO(
                "tayblue",
                "123");

        memberDTO = new MemberDTO(
                "taylor",
                "blue",
                22,
                Gender.MALE);

        memberCard = new MemberCard(
                LocalDateTime.now(),
                "tayblue",
                "123",
                ADMIN.getGrantedAuthorities(),
                true,
                true,
                true,
                true);

        member = new Member(
                "taylor",
                "blue",
                "taylor.blue@gmail.com",
                22,
                Gender.QUEERGENDER,
                memberCard);
        // </editor-fold>
        // <editor-fold defaultstate="collapsed"desc="added data to MemberDTO">
        memberDTO.setMemberCardDTO(memberCardDTO);
        memberDTO.setEmail("taylor.red@gmail.com");
        // </editor-fold>
    }
}
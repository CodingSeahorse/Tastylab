package com.codingseahorse.tastylab.controller;

import com.codingseahorse.tastylab.dto.MemberDTO;
import com.codingseahorse.tastylab.exception.NotFoundException;
import com.codingseahorse.tastylab.requestsModels.MemberRequest;
import com.codingseahorse.tastylab.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/member")
public class MemberController {

    @Autowired
    MemberService memberService;

    // ===== UPDATE =====
    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public MemberDTO updateMemberData(@RequestBody MemberRequest memberRequest) {
        MemberDTO updatedMember;

        try {
            updatedMember = memberService.editMemberData(memberRequest);
        }catch (TransactionSystemException exception) {
            throw new TransactionSystemException(
                    "Member was not updated. " +
                    "Error while committing the transaction");
        }

        if (updatedMember == null) {
            throw new NotFoundException(String.format(
                    "Member with the id %s not found",
                    memberRequest.getId()));
        }

        return updatedMember;
    }

    // ===== DELETE =====
    @DeleteMapping("/{memberId}")
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public void deleteMember(@PathVariable("memberId") Integer memberId) {
        try {
            memberService.deleteMember(memberId);
        }catch (DataRetrievalFailureException exception) {
            throw new DataRetrievalFailureException(
                    "There is no member with id"
                    + memberId +
                    " in database");
        }
    }
}

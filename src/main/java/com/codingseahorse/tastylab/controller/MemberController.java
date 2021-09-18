package com.codingseahorse.tastylab.controller;

import com.codingseahorse.tastylab.dto.MemberDTO;
import com.codingseahorse.tastylab.exception.NotFoundException;
import com.codingseahorse.tastylab.requestsModels.MemberRequest;
import com.codingseahorse.tastylab.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import static com.codingseahorse.tastylab.utils.ExceptionUtils.createErrorMessageAndThrowEntityValidationException;


@RestController
@RequestMapping("/api/member")
public class MemberController {

    @Autowired
    MemberService memberService;

    // ===== UPDATE =====
    @Operation (summary = "update member data")
    @ApiResponses(
            value = {
                @ApiResponse(
                    responseCode = "200",
                    description = "successfully updated",
                    content = { @Content(
                            mediaType = "application/json",
                            schema = @Schema (implementation = MemberDTO.class))}),
                @ApiResponse(
                        responseCode = "400",
                        description = "Invalid payload. Please correct your MemberRequest or url",
                        content =  @Content),
                @ApiResponse(
                        responseCode = "404",
                        description = "Member not found",
                        content = @Content)})
    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public MemberDTO updateMemberData(
            @Parameter (description = "RequestBody(MemberRequest) to pass")
            @RequestBody MemberRequest memberRequest, BindingResult bindingResult) {
        MemberDTO updatedMember;

        if (bindingResult.hasErrors()) {
            createErrorMessageAndThrowEntityValidationException(bindingResult);
        }

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
    @Operation (summary = "delete member")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "204",
                            description = "successfully deleted.No Content",
                            content = @Content),
                    @ApiResponse(
                            responseCode = "400",
                            description = "id or path invalid",
                            content = @Content)})
    @DeleteMapping("/{memberId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
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
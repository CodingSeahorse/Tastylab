package com.codingseahorse.tastylab.controller;

import com.codingseahorse.tastylab.dto.RegistrationDTO;
import com.codingseahorse.tastylab.model.member.Gender;
import com.codingseahorse.tastylab.requestsModels.RegistrationRequest;
import com.codingseahorse.tastylab.service.WelcomeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/api/welcome")
@CrossOrigin(origins = "http://localhost:3000")
public class WelcomeController {
    @Autowired
    WelcomeService welcomeService;

    @Operation(summary = "signup")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "created successfully a new Member",
                            content = @Content),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Invalid payload. Please correct your RegistrationRequest(form) or url",
                            content =  @Content),
                    @ApiResponse(
                            responseCode = "404",
                            description = "No pages found",
                            content = @Content)})
    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)
    public void signUp (
            @Parameter(description = "send a request-body(RegistrationRequest) to sign-up")
            @RequestBody RegistrationRequest registrationRequest) {

        Gender postedGender = Gender.valueOf(registrationRequest.getGender().toUpperCase());

        RegistrationDTO form = new RegistrationDTO(
                registrationRequest.getUsername(),
                registrationRequest.getPassword(),
                registrationRequest.getFirstName(),
                registrationRequest.getLastName(),
                registrationRequest.getEmail(),
                registrationRequest.getAge(),
                postedGender);

        welcomeService.registerMember(form);
    }

    @Operation(summary = "refresh the token")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "refreshed token successfully",
                            content = @Content),
                    @ApiResponse(
                            responseCode = "403",
                            description = "Access_Token is Invalid",
                            content =  @Content),
                    @ApiResponse(
                            responseCode = "404",
                            description = "No pages found",
                            content = @Content)})
    @GetMapping("/token-refresh")
    @ResponseStatus(HttpStatus.OK)
    public void refreshToken (
            HttpServletRequest request,
            HttpServletResponse response) throws IOException {
        welcomeService.refreshMyToken(request,response);
    }
}
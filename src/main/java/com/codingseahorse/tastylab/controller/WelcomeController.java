package com.codingseahorse.tastylab.controller;

import com.codingseahorse.tastylab.dto.RegistrationDTO;
import com.codingseahorse.tastylab.model.member.Gender;
import com.codingseahorse.tastylab.requestsModels.RegistrationRequest;
import com.codingseahorse.tastylab.service.WelcomeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/welcome")
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
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public void signUp (
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
}

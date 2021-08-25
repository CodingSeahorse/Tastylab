package com.codingseahorse.tastylab.controller;

import com.codingseahorse.tastylab.dto.RegistrationDTO;
import com.codingseahorse.tastylab.model.member.Gender;
import com.codingseahorse.tastylab.repository.MemberCardRepository;
import com.codingseahorse.tastylab.repository.MemberRepository;
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
@RequestMapping("/api/welcome/signup")
public class RegistrationController {
    @Autowired
    WelcomeService welcomeService;

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

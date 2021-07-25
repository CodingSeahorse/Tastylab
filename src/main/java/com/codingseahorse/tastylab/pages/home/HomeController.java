package com.codingseahorse.tastylab.pages.home;

import com.codingseahorse.tastylab.dto.HomeDTO;
import com.codingseahorse.tastylab.service.HomeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/home")
public class HomeController {
    @Autowired
    HomeService homeService;

    @Operation(
            summary = "this_rest-endpoint_fetches_the_starting_content"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "fetched_starting_content",
                    content = @Content(mediaType = "application/json")
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Path not found"
            )
    })
    @ResponseStatus(HttpStatus.OK)
    @GetMapping( "/")
    public HomeDTO getStartingContent(
            @RequestParam Integer[] page,
            @RequestParam Integer[] size
    ) {
        PageRequest pageRequestExplore = PageRequest.of(
                page[0],
                size[0],
                Sort.by("createdAt")
        );
        PageRequest pageRequestHighlight = PageRequest.of(
                page[1],
                size[1],
                Sort.by("createdAt")
        );
        return homeService.retrieveStartingContent(pageRequestExplore,pageRequestHighlight);
    }
}

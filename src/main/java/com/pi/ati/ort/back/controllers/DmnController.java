package com.pi.ati.ort.back.controllers;

import com.pi.ati.ort.back.classes.DmnRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.bimserver.shared.exceptions.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RestController
@CrossOrigin
@RequestMapping("/dmn")
public class DmnController {

    private final WebClient.Builder webClientBuilder;

    @Autowired
    public DmnController(WebClient.Builder webClientBuilder) {
        this.webClientBuilder = webClientBuilder;
    }

    @Operation(summary = "Retrieves all the DMN model norms for given container")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok",
                    content = {@Content(mediaType = "application/json")})
    })
    @GetMapping("/container/{name}")
    public Mono<ResponseEntity<String>> retrieveDmnContainer(@Parameter(description = "The container´s name") @PathVariable String name) throws ServiceException {
        String url = "http://localhost:8080/kie-server/services/rest/server/containers/" + name + "/dmn";
        return webClientBuilder.build()
                .get()
                .uri(url)
                .header("Authorization", "Basic cGktYXRpLW9ydDpUZXNpczIwMjM=")
                .retrieve()
                .bodyToMono(String.class)
                .map(responseBody -> new ResponseEntity<String>(responseBody, HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<String>(HttpStatus.NOT_FOUND))
                .onErrorReturn(new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR));
    }

    @Operation(summary = "Evaluate DMN norm for given container")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok",
                    content = {@Content(mediaType = "application/xml",
                            schema = @Schema(implementation = DmnRequest.class))})
    })
    @PostMapping("/container/{name}/evaluate")
    public Mono<ResponseEntity<String>> evaluateDmnContainer(@Parameter(description = "The container´s name") @PathVariable String name, @RequestBody DmnRequest dmnRequest) throws ServiceException {
        String url = "http://localhost:8080/kie-server/services/rest/server/containers/" + name + "/dmn";
        System.out.println(dmnRequest.toString());
        return webClientBuilder.build()
                .post()
                .uri(url)
                .header("Authorization", "Basic cGktYXRpLW9ydDpUZXNpczIwMjM=")
                .bodyValue(dmnRequest)
                .retrieve()
                .bodyToMono(String.class)
                .map(responseBody -> new ResponseEntity<String>(responseBody, HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<String>(HttpStatus.NOT_FOUND))
                .onErrorReturn(new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR));
    }
}


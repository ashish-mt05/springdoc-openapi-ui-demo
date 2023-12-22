package com.ashish.controller;

import com.ashish.model.User;
import com.ashish.model.UserResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class UserController {
    @GetMapping("/fetch/{id}")
//    @Parameter(name = "authorization", in = ParameterIn.HEADER, description = "Bearer token", required = true, examples = {
//            @ExampleObject(name = "example1", value = "Bearer 1233rada"),
//            @ExampleObject(name = "example2", value = "Bearer ASDSFA") })
//    @Parameter(name = "contentType", in = ParameterIn.HEADER, description = "Content-Type", required = true, schema = @Schema(type = "string", allowableValues = {
//            "application/pdf", "application/json", "application/xml" }))
    public ResponseEntity<UserResponse> findById(@PathVariable("id") Integer id, HttpServletRequest request) {

        String contentType = request.getHeader("Content-Type");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.valueOf(contentType));

        return ResponseEntity.ok()
                .headers(headers)
                .body(UserResponse
                        .builder()
                        .id(100)
                        .name("ashish kumar")
                        .email("ashish@gmail.com")
                        .build());
    }

    @PostMapping
    public ResponseEntity<UserResponse> save(@RequestBody User user) {

        return new ResponseEntity<>(UserResponse
                .builder()
                .id(100)
                .name("ashish kumar")
                .email("ashish@gmail.com")
                .build(), HttpStatus.CREATED);

    }
}

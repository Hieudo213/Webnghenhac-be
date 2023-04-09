package com.tmt.project.webnghenhac.controller;

import com.tmt.project.webnghenhac.service.AccountService;
import com.tmt.project.webnghenhac.service.request.AuthenticateRequest;
import com.tmt.project.webnghenhac.service.request.AuthenticationResponse;
import com.tmt.project.webnghenhac.service.request.RegisterRequest;
import com.tmt.project.webnghenhac.service.request.RegisterResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AccountController {
    private final AccountService service;

    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> register(
          @Valid @RequestBody RegisterRequest request
    ) {
        return ResponseEntity.ok(service.registerForUser(request));
    }
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
         @Valid   @RequestBody AuthenticateRequest request
    ) {
        return ResponseEntity.ok(service.authenticate(request));
    }

    @PostMapping("/admin/register")
    public ResponseEntity<RegisterResponse> dangky(
         @Valid  @RequestBody RegisterRequest request
    ) {
        return ResponseEntity.ok(service.registerForAdmin(request));
    }


}

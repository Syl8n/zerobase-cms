package com.zerobase.cms.zerobasecms.controller;

import com.zerobase.cms.zerobasecms.application.SignInApplication;
import com.zerobase.cms.zerobasecms.domain.SignInForm;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/signIn")
public class SignInController {

    private final SignInApplication signInApplication;

    @PostMapping("/customer")
    public ResponseEntity<?> signInCustomer(@RequestBody SignInForm form){
        return ResponseEntity.ok(signInApplication.customerLogin(form));
    }

    @PostMapping("/seller")
    public ResponseEntity<?> signInSeller(@RequestBody SignInForm form){
        return ResponseEntity.ok(signInApplication.sellerLogin(form));
    }
}

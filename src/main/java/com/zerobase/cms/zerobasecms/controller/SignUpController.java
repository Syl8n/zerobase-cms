package com.zerobase.cms.zerobasecms.controller;

import com.zerobase.cms.zerobasecms.application.SignUpApplication;
import com.zerobase.cms.zerobasecms.domain.SignUpForm;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/signup")
public class SignUpController {
    private final SignUpApplication signUpApplication;

    @PostMapping
    public ResponseEntity<?> customerSignUp(@RequestBody SignUpForm form){
        return ResponseEntity.ok(signUpApplication.customerSignUp(form));
    }
}

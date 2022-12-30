package com.zerobase.cms.zerobasecms.controller;

import com.zerobase.cms.zerobasecms.application.SignUpApplication;
import com.zerobase.cms.zerobasecms.domain.SignUpForm;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/signup")
public class SignUpController {
    private final SignUpApplication signUpApplication;

    @PostMapping("/customer")
    public ResponseEntity<?> customerSignUp(@RequestBody SignUpForm form){
        return ResponseEntity.ok(signUpApplication.customerSignUp(form));
    }

    @GetMapping("/customer/verify")
    public ResponseEntity<?> verifyCustomer(String email, String code){
        signUpApplication.customerVerify(email, code);
        return ResponseEntity.ok("인증이 완료되었습니다");
    }

    @PostMapping("/seller")
    public ResponseEntity<?> sellerSignUp(@RequestBody SignUpForm form){
        return ResponseEntity.ok(signUpApplication.sellerSignUp(form));
    }

    @GetMapping("/seller/verify")
    public ResponseEntity<?> verifySeller(String email, String code){
        signUpApplication.sellerVerify(email, code);
        return ResponseEntity.ok("인증이 완료되었습니다");
    }
}

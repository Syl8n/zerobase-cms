package com.zerobase.cms.zerobasecms.application;

import com.zerobase.cms.zerobasecms.domain.SignInForm;
import com.zerobase.cms.zerobasecms.domain.model.Customer;
import com.zerobase.cms.zerobasecms.service.CustomerService;
import com.zerobase.domain.common.UserType;
import com.zerobase.domain.config.JwtAuthenticationProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SignInApplication {
    private final CustomerService customerService;
    private final JwtAuthenticationProvider provider;
    public String customerLogin(SignInForm form){
        // 로그인 가능 여부
        Customer customer = customerService.findValidCustomer(form.getEmail(), form.getPassword());
        // 토큰 발행
        // 토큰 return
        return provider.createToken(customer.getEmail(), customer.getId(), UserType.CUSTOMER);
    }

}

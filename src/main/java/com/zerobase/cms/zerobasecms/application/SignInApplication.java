package com.zerobase.cms.zerobasecms.application;

import com.zerobase.cms.zerobasecms.domain.SignInForm;
import com.zerobase.cms.zerobasecms.domain.model.Customer;
import com.zerobase.cms.zerobasecms.domain.model.Seller;
import com.zerobase.cms.zerobasecms.service.customer.CustomerService;
import com.zerobase.cms.zerobasecms.service.seller.SellerService;
import com.zerobase.domain.common.UserType;
import com.zerobase.domain.config.JwtAuthenticationProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SignInApplication {
    private final CustomerService customerService;
    private final SellerService sellerService;
    private final JwtAuthenticationProvider provider;
    public String customerLogin(SignInForm form){
        // 로그인 가능 여부
        Customer customer = customerService.findValidCustomer(form.getEmail(), form.getPassword());
        // 토큰 발행
        return provider.createToken(customer.getEmail(), customer.getId(), UserType.CUSTOMER);
    }

    public String sellerLogin(SignInForm form){
        Seller seller = sellerService.findValidSeller(form.getEmail(), form.getPassword());
        return provider.createToken(seller.getEmail(), seller.getId(), UserType.SELLER);
    }

}

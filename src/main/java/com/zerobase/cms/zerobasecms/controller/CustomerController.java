package com.zerobase.cms.zerobasecms.controller;

import com.zerobase.cms.zerobasecms.domain.customer.ChangeBalanceForm;
import com.zerobase.cms.zerobasecms.domain.customer.CustomerDto;
import com.zerobase.cms.zerobasecms.domain.model.Customer;
import com.zerobase.cms.zerobasecms.exception.CustomException;
import com.zerobase.cms.zerobasecms.exception.ErrorCode;
import com.zerobase.cms.zerobasecms.service.customer.CustomerBalanceService;
import com.zerobase.cms.zerobasecms.service.customer.CustomerService;
import com.zerobase.domain.common.UserVo;
import com.zerobase.domain.config.JwtAuthenticationProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/customer")
public class CustomerController {

    private final JwtAuthenticationProvider provider;
    private final CustomerService customerService;
    private final CustomerBalanceService customerBalanceService;

    @GetMapping("/getInfo")
    public ResponseEntity<?> getInfo(@RequestHeader(name = "X-Auth-Token") String token) {
        UserVo userVo = provider.getUserVo(token);
        Customer customer = customerService.findByIdAndEmail(userVo.getId(), userVo.getEmail())
            .orElseThrow(() -> new CustomException(ErrorCode.ACCOUNT_NOT_FOUND));
        return ResponseEntity.ok(CustomerDto.from(customer));
    }

    @PostMapping("/balance")
    public ResponseEntity<?> changeBalance(@RequestHeader(name = "X-Auth-Token") String token,
        @RequestBody ChangeBalanceForm form) {
        UserVo userVo = provider.getUserVo(token);
        return ResponseEntity.ok(customerBalanceService.changeBalance(userVo.getId(), form));
    }

}

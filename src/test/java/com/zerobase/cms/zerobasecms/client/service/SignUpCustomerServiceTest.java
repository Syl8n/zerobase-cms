package com.zerobase.cms.zerobasecms.client.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.zerobase.cms.zerobasecms.domain.SignUpForm;
import com.zerobase.cms.zerobasecms.domain.model.Customer;
import com.zerobase.cms.zerobasecms.service.customer.SignUpCustomerService;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SignUpCustomerServiceTest {

    @Autowired
    private SignUpCustomerService service;

    @Test
    public void signUp() {
        SignUpForm form = SignUpForm.builder()
            .name("name")
            .birth(LocalDateTime.now())
            .email("abc@gmail.com")
            .password("1")
            .phone("0101")
            .build();
        Customer customer = service.signUp(form);
        assertNotNull(customer.getId());
    }
}
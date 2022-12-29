package com.zerobase.cms.zerobasecms.service;

import com.zerobase.cms.zerobasecms.domain.model.Customer;
import com.zerobase.cms.zerobasecms.exception.CustomException;
import com.zerobase.cms.zerobasecms.exception.ErrorCode;
import com.zerobase.cms.zerobasecms.repository.CustomerRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;

    public Optional<Customer> findByIdAndEmail(Long id, String email){
        return customerRepository.findById(id)
            .filter(c -> c.getEmail().equals(email));
    }

    public Customer findValidCustomer(String email, String password){
        return customerRepository.findByEmail(email)
            .filter(c -> c.getPassword().equals(password) && c.isVerify())
            .orElseThrow(() -> new CustomException(ErrorCode.LOGIN_CHECK_FAIL));
    }


}

package com.zerobase.cms.zerobasecms.service.customer;

import com.zerobase.cms.zerobasecms.client.MailgunClient;
import com.zerobase.cms.zerobasecms.client.mailgun.SendMailForm;
import com.zerobase.cms.zerobasecms.domain.model.Customer;
import com.zerobase.cms.zerobasecms.exception.CustomException;
import com.zerobase.cms.zerobasecms.exception.ErrorCode;
import com.zerobase.cms.zerobasecms.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailingService {
    private final MailgunClient mailgunClient;
    private final CustomerRepository customerRepository;

    public ResponseEntity<String> send(Long id, SendMailForm form) {
        Customer customer = customerRepository.findById(id)
            .orElseThrow(() -> new CustomException(ErrorCode.ACCOUNT_NOT_FOUND));

        if(!customer.getEmail().equals(form.getTo())){
            throw new CustomException(ErrorCode.EMAIL_DEST_NOT_MATCHED);
        }

        return mailgunClient.sendEmail(form);
    }
}

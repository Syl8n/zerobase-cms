package com.zerobase.cms.zerobasecms.controller;

import com.zerobase.cms.zerobasecms.client.mailgun.SendMailForm;
import com.zerobase.cms.zerobasecms.service.customer.MailingService;
import com.zerobase.domain.config.JwtAuthenticationProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/mailing")
public class MailingController {

    private final JwtAuthenticationProvider provider;
    private final MailingService mailingService;


    @PostMapping
    public ResponseEntity<?> send(@RequestHeader(name = "X-Auth-Token") String token,
        @RequestBody SendMailForm form) {
        return ResponseEntity.ok(
            mailingService.send(provider.getUserVo(token).getId(), form).getBody());
    }

}

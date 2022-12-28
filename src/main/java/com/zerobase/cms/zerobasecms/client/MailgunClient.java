package com.zerobase.cms.zerobasecms.client;


import com.zerobase.cms.zerobasecms.client.mailgun.SendMailForm;
import feign.Response;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "mailgun", url = "https://api.mailgun.net/v3/")
@Qualifier("mailgun")
public interface MailgunClient {

    @PostMapping("sandbox612913052cd244448992cef20163faa1.mailgun.org/messages")
    ResponseEntity<Response> sendEmail(@SpringQueryMap SendMailForm form);
}
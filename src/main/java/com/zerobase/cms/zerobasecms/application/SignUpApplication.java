package com.zerobase.cms.zerobasecms.application;

import com.zerobase.cms.zerobasecms.client.MailgunClient;
import com.zerobase.cms.zerobasecms.client.mailgun.SendMailForm;
import com.zerobase.cms.zerobasecms.domain.SignUpForm;
import com.zerobase.cms.zerobasecms.domain.model.Customer;
import com.zerobase.cms.zerobasecms.domain.model.Seller;
import com.zerobase.cms.zerobasecms.exception.CustomException;
import com.zerobase.cms.zerobasecms.exception.ErrorCode;
import com.zerobase.cms.zerobasecms.service.customer.SignUpCustomerService;
import com.zerobase.cms.zerobasecms.service.seller.SellerService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SignUpApplication {

    private final MailgunClient mailgunClient;
    private final SignUpCustomerService signUpCustomerService;
    private final SellerService sellerService;

    public void customerVerify(String email, String code){
        signUpCustomerService.verifyEmail(email, code);
    }

    public String customerSignUp(SignUpForm form) {
        if (signUpCustomerService.isEmailExist(form.getEmail())) {
            throw new CustomException(ErrorCode.ALREADY_REGISTER_ACCOUNT);
        } else {
            Customer customer = signUpCustomerService.signUp(form);

            String code = getRandomCode();
            SendMailForm sendMailForm = SendMailForm.builder()
                .from("tester@zb.com")
                .to("magi8520@gmail.com")
                .subject("verification Email!")
                .text(getVerificationEmailBody(form.getEmail(), form.getName(), "customer", code))
                .build();
            mailgunClient.sendEmail(sendMailForm);

            return "회원 가입에 성공하였습니다. " + signUpCustomerService.changeCustomerValidateEmail(
                customer.getId(), code);
        }
    }

    public void sellerVerify(String email, String code){
        sellerService.verifyEmail(email, code);
    }

    public String sellerSignUp(SignUpForm form) {
        if (sellerService.isEmailExist(form.getEmail())) {
            throw new CustomException(ErrorCode.ALREADY_REGISTER_ACCOUNT);
        } else {
            Seller seller = sellerService.signUp(form);

            String code = getRandomCode();
            SendMailForm sendMailForm = SendMailForm.builder()
                .from("tester@zb.com")
                .to("magi8520@gmail.com")
                .subject("verification Email!")
                .text(getVerificationEmailBody(form.getEmail(), form.getName(), "seller", code))
                .build();
            mailgunClient.sendEmail(sendMailForm);

            return "회원 가입에 성공하였습니다. " + sellerService.changeSellerValidateEmail(
                seller.getId(), code);
        }
    }

    private String getRandomCode() {
        return RandomStringUtils.random(10, true, true);
    }

    private String getVerificationEmailBody(String email, String name, String type, String code) {
        StringBuilder sb = new StringBuilder();
        return sb.append("Hello ").append(name)
            .append("! Please click this link for verification\n\n")
            .append("http://localhost:8080/signup/"+ type + "/verify/?email=")
            .append(email)
            .append("&code=")
            .append(code)
            .toString();
    }

}

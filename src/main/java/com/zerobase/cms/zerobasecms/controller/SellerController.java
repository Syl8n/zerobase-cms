package com.zerobase.cms.zerobasecms.controller;

import com.zerobase.cms.zerobasecms.service.seller.SellerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SellerController {
    private final SellerService sellerService;

}

package com.zerobase.cms.order.controller;

import com.zerobase.cms.order.domain.product.AddProductForm;
import com.zerobase.cms.order.domain.product.AddProductItemForm;
import com.zerobase.cms.order.domain.product.ProductDto;
import com.zerobase.cms.order.domain.product.ProductItemDto;
import com.zerobase.cms.order.domain.product.UpdateProductForm;
import com.zerobase.cms.order.domain.product.UpdateProductItemForm;
import com.zerobase.cms.order.service.ProductItemService;
import com.zerobase.cms.order.service.ProductService;
import com.zerobase.domain.config.JwtAuthenticationProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/seller/product")
public class SellerProductController {

    private final ProductService productService;
    private final ProductItemService productItemService;
    private final JwtAuthenticationProvider provider;

    @PostMapping
    public ResponseEntity<?> addProduct(@RequestHeader(name = "X-Auth-Token") String token,
        @RequestBody AddProductForm form) {
        return ResponseEntity.ok(ProductDto.from(
            productService.addProduct(provider.getUserVo(token).getId(), form)));
    }

    @PostMapping("/item")
    public ResponseEntity<?> addProductItem(@RequestHeader(name = "X-Auth-Token") String token,
        @RequestBody AddProductItemForm form) {
        return ResponseEntity.ok(ProductDto.from(
            productItemService.addProductItem(provider.getUserVo(token).getId(), form)));
    }

    @PutMapping
    public ResponseEntity<?> updateProduct(@RequestHeader(name = "X-Auth-Token") String token,
        @RequestBody UpdateProductForm form) {
        return ResponseEntity.ok(ProductDto.from(
            productService.updateProduct(provider.getUserVo(token).getId(), form)));
    }

    @PutMapping("/item")
    public ResponseEntity<?> updateProductItem(@RequestHeader(name = "X-Auth-Token") String token,
        @RequestBody UpdateProductItemForm form) {
        return ResponseEntity.ok(ProductItemDto.from(
            productItemService.updateProductItem(provider.getUserVo(token).getId(), form)));
    }

    @DeleteMapping
    public ResponseEntity<?> deleteProduct(@RequestHeader(name = "X-Auth-Token") String token,
        @RequestParam Long id) {
        return ResponseEntity.ok(ProductDto.from(
            productService.deleteProduct(provider.getUserVo(token).getId(), id)));
    }

    @DeleteMapping("/item")
    public ResponseEntity<?> deleteProductItem(@RequestHeader(name = "X-Auth-Token") String token,
        @RequestParam Long id) {
        return ResponseEntity.ok(ProductItemDto.from(
            productItemService.deleteProductItem(provider.getUserVo(token).getId(), id)));
    }
}

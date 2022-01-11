package com.fastcampus.investment.api;

import com.fastcampus.investment.dto.InvestmentDto;
import com.fastcampus.investment.dto.ProductDto;
import com.fastcampus.investment.entity.InvestmentStatus;
import com.fastcampus.investment.entity.Response;
import com.fastcampus.investment.exception.NotFoundInvestmentException;
import com.fastcampus.investment.exception.NotFoundProductException;
import com.fastcampus.investment.service.InvestmentService;
import com.fastcampus.investment.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api")
@RequiredArgsConstructor
public class Apis {

    private final ProductService productService;
    private final InvestmentService investmentService;

    @ExceptionHandler({NotFoundProductException.class, NotFoundInvestmentException.class})
    public Response<?> handlerNotFoundProductException(Exception exception) {
        return new Response<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/product")
    public Response<?> getAllProduct() {
        List<ProductDto> validProducts = productService.getValidProducts();
        return new Response<>(validProducts, HttpStatus.OK);
    }

    @GetMapping("/investment")
    public Response<?> getInvestment(@RequestHeader("X-USER-ID") Long userId) {
        List<InvestmentDto> investment = investmentService.getInvestment(userId);
        return new Response<>(investment, HttpStatus.OK);
    }

    @PostMapping("/investment")
    public Response<?> doInvest(
            @RequestHeader("X-USER-ID") Long userId,
            @RequestParam Long productId,
            @RequestParam Long investAmount
    ) {
        InvestmentDto invest = investmentService.invest(userId, productId, investAmount);
        return new Response<>(invest, HttpStatus.OK);
    }

    @PutMapping("/investment/{investmentId}")
    public Response<?> updateInvest(
            @RequestHeader("X-USER-ID") Long userId,
            @PathVariable Long investmentId,
            @RequestParam InvestmentStatus status
            ) {
        InvestmentDto investment = investmentService.updateInvestment(userId, investmentId, status);
        return new Response<>(investment, HttpStatus.OK);
    }
}

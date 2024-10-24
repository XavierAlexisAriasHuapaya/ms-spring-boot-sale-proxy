package dev.arias.huapaya.ms_sale_proxy.presentation.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.arias.huapaya.ms_sale_proxy.model.Sale;
import dev.arias.huapaya.ms_sale_proxy.service.interfaces.SaleService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping(path = "sale")
public class SaleController {

    private final SaleService saleService;

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Sale sale) {
        Map<String, Object> response = new HashMap<>();
        response = this.saleService.save(sale);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

}

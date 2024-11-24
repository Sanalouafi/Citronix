package com.example.citronix.controller;

import com.example.citronix.dto.SaleDto;
import com.example.citronix.request.SaleRequest;
import com.example.citronix.service.SaleService;
import com.example.citronix.mapper.SaleMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/sales")
@RequiredArgsConstructor
public class SaleController {

    private final SaleService saleService;
    private final SaleMapper saleMapper;

    @PostMapping
    public ResponseEntity<SaleDto> createSale(@Valid @RequestBody SaleRequest saleRequest) {
        SaleDto saleDto = saleMapper.toDto(saleMapper.toEntity(saleRequest));
        SaleDto createdSale = saleService.createSale(saleDto);
        return new ResponseEntity<>(createdSale, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SaleDto> getSaleById(@PathVariable Long id) {
        SaleDto saleDto = saleService.getSaleById(id);
        return ResponseEntity.ok(saleDto);
    }

    @GetMapping
    public ResponseEntity<List<SaleDto>> getAllSales() {
        List<SaleDto> sales = saleService.getAllSales();
        return ResponseEntity.ok(sales);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSale(@PathVariable Long id) {
        saleService.deleteSale(id);
        return ResponseEntity.noContent().build();
    }
}

package com.example.citronix.service;

import com.example.citronix.dto.SaleDto;

import java.util.List;

public interface SaleService {

    SaleDto createSale(SaleDto saleDto);
    void deleteSale(Long id);
    SaleDto getSaleById(Long id);
    List<SaleDto> getAllSales();
}

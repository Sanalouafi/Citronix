package com.example.citronix.service.impl;

import com.example.citronix.dto.SaleDto;
import com.example.citronix.entity.Sale;
import com.example.citronix.entity.Harvest;
import com.example.citronix.exception.customException.SaleNotFoundException;
import com.example.citronix.mapper.SaleMapper;
import com.example.citronix.repository.SaleRepository;
import com.example.citronix.repository.HarvestRepository;
import com.example.citronix.service.SaleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SaleServiceImpl implements SaleService {

    private final SaleRepository saleRepository;
    private final HarvestRepository harvestRepository;
    private final SaleMapper saleMapper;

    @Override
    public SaleDto createSale(SaleDto saleDto) {
        Harvest harvest = harvestRepository.findById(saleDto.getHarvestId())
                .orElseThrow(() -> new RuntimeException("Harvest not found"));

        Sale sale = saleMapper.toEntity(saleDto);
        sale.setHarvest(harvest);
        sale.setRevenue(saleDto.getQuantity() * saleDto.getUnitPrice());

        Sale savedSale = saleRepository.save(sale);

        return saleMapper.toDto(savedSale);
    }

    @Override
    public void deleteSale(Long id) {
        saleRepository.deleteById(id);
    }

    @Override
    public SaleDto getSaleById(Long id) {
        Sale sale = saleRepository.findById(id)
                .orElseThrow(() -> new SaleNotFoundException("Sale not found"));
        return saleMapper.toDto(sale);
    }

    @Override
    public List<SaleDto> getAllSales() {
        return saleRepository.findAll().stream()
                .map(saleMapper::toDto)
                .collect(Collectors.toList());
    }
}

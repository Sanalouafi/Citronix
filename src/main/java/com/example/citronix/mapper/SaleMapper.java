package com.example.citronix.mapper;

import com.example.citronix.dto.SaleDto;
import com.example.citronix.entity.Sale;
import com.example.citronix.request.SaleRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SaleMapper {

    @Mapping(target = "revenue", expression = "java(calculateRevenue(sale))")
    SaleDto toDto(Sale sale);

    Sale toEntity(SaleDto saleDto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "revenue", ignore = true)
    Sale toEntity(SaleRequest saleRequest);

    default double calculateRevenue(Sale sale) {
        return sale.getQuantity() * sale.getUnitPrice();
    }
}

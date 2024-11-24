package com.example.citronix.mapper;

import com.example.citronix.dto.FarmDto;
import com.example.citronix.entity.Farm;
import com.example.citronix.request.FarmRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface FarmMapper {

    FarmDto toDto(Farm farm);
    Farm toEntity(FarmDto farmDto);

    @Mapping(target = "id", ignore = true)
    Farm toEntity(FarmRequest farmRequest);
}

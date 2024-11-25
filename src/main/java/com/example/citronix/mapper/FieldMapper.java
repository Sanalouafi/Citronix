package com.example.citronix.mapper;

import com.example.citronix.dto.FarmDto;
import com.example.citronix.dto.FieldDto;
import com.example.citronix.entity.Farm;
import com.example.citronix.entity.Field;
import com.example.citronix.request.FieldRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
@Mapper(componentModel = "spring")
public interface FieldMapper {

    @Mapping(target = "farmId", source = "farm.id")  // Map the farm's id to farmId in the DTO
    FieldDto toDto(Field field);

    @Mapping(target = "farm", source = "farmId")  // Map farmId in DTO to farm in the Field entity
    Field toEntity(FieldDto fieldDto);

    @Mapping(target = "farm", source = "farmId")  // Map farmId from FieldRequest to farm in Field entity
    Field toEntity(FieldRequest fieldRequest);

    default Farm map(Long farmId) {
        if (farmId == null) {
            return null;
        }
        Farm farm = new Farm();
        farm.setId(farmId);
        return farm;
    }
}



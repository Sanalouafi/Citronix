package com.example.citronix.mapper;

import com.example.citronix.dto.FieldDto;
import com.example.citronix.entity.Field;
import com.example.citronix.request.FieldRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface FieldMapper {
    FieldDto toDto(Field field);
    @Mapping(target = "farm",ignore = true)
    Field toEntity(FieldDto filedDto);
    @Mapping(target = "id",ignore = true)
    @Mapping(target = "farm",ignore = true)
    Field toEntity(FieldRequest fieldRequest);
}

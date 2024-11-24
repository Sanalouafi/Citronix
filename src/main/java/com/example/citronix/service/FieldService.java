package com.example.citronix.service;

import com.example.citronix.dto.FieldDto;

import java.util.List;

public interface FieldService {
    FieldDto createField(FieldDto fieldDto);
    void deleteField(Long id);
    FieldDto getFieldById(Long id);
    List<FieldDto> getAllFieldsByFarm(Long farmId);
}

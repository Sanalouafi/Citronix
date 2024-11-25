package com.example.citronix.service;

import com.example.citronix.dto.FieldDto;
import com.example.citronix.request.FieldRequest;

import java.util.List;

public interface FieldService {
    FieldDto createField(FieldRequest fieldRequest);
    void deleteField(Long id);
    FieldDto getFieldById(Long id);
    List<FieldDto> getAllFieldsByFarm(Long farmId);
}

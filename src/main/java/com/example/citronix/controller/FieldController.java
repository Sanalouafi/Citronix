package com.example.citronix.controller;

import com.example.citronix.dto.FieldDto;
import com.example.citronix.request.FieldRequest;
import com.example.citronix.service.FieldService;
import com.example.citronix.mapper.FieldMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/fields")
@RequiredArgsConstructor
public class FieldController {

    private final FieldService fieldService;
    private final FieldMapper fieldMapper;

    @PostMapping
    public ResponseEntity<FieldDto> createField(@Valid @RequestBody FieldRequest fieldRequest) {
        FieldDto fieldDto = fieldMapper.toDto(fieldMapper.toEntity(fieldRequest));
        FieldDto createdField = fieldService.createField(fieldDto);
        return new ResponseEntity<>(createdField, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FieldDto> getFieldById(@PathVariable Long id) {
        FieldDto fieldDto = fieldService.getFieldById(id);
        return ResponseEntity.ok(fieldDto);
    }

    @GetMapping("/farm/{farmId}")
    public ResponseEntity<List<FieldDto>> getAllFieldsByFarm(@PathVariable Long farmId) {
        List<FieldDto> fields = fieldService.getAllFieldsByFarm(farmId);
        return ResponseEntity.ok(fields);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteField(@PathVariable Long id) {
        fieldService.deleteField(id);
        return ResponseEntity.noContent().build();
    }
}

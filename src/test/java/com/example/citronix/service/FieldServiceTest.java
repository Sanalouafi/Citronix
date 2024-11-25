package com.example.citronix.service;

import com.example.citronix.dto.FieldDto;
import com.example.citronix.entity.Farm;
import com.example.citronix.entity.Field;
import com.example.citronix.exception.customException.FarmNotFoundException;
import com.example.citronix.exception.customException.InvalidFieldAreaException;
import com.example.citronix.mapper.FieldMapper;
import com.example.citronix.repository.FarmRepository;
import com.example.citronix.repository.FieldRepository;
import com.example.citronix.request.FieldRequest;
import com.example.citronix.service.impl.FieldServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FieldServiceTest {

    @Mock
    private FieldRepository fieldRepository;

    @Mock
    private FarmRepository farmRepository;

    @Mock
    private FieldMapper fieldMapper;

    @InjectMocks
    private FieldServiceImpl fieldService;

    private Farm mockFarm;
    private Field mockField;
    private FieldRequest fieldRequest;
    private FieldDto fieldDto;

    @BeforeEach
    void setUp() {
        mockFarm = new Farm(1L, "Farm A", "Location A", 100.0, null);
        mockField = new Field(1L, mockFarm, 10.0);
        fieldRequest = new FieldRequest();
        fieldRequest.setFarmId(1L);
        fieldRequest.setArea(10.0);
        fieldDto = new FieldDto();
        fieldDto.setId(1L);
        fieldDto.setFarmId(1L);
        fieldDto.setArea(10.0);
    }

    @Test
    void testCreateFieldSuccess() {
        when(farmRepository.findById(1L)).thenReturn(Optional.of(mockFarm));
        when(fieldRepository.countByFarm(mockFarm)).thenReturn(5);
        when(fieldRepository.sumAreaByFarm(mockFarm)).thenReturn(30.0);
        when(fieldMapper.toEntity(fieldRequest)).thenReturn(mockField);
        when(fieldRepository.save(any(Field.class))).thenReturn(mockField);
        when(fieldMapper.toDto(mockField)).thenReturn(fieldDto);

        FieldDto result = fieldService.createField(fieldRequest);

        assertNotNull(result);
        assertEquals(fieldDto.getId(), result.getId());
        assertEquals(fieldDto.getFarmId(), result.getFarmId());
        verify(fieldRepository, times(1)).save(any(Field.class));
    }

    @Test
    void testCreateFieldFarmNotFound() {
        when(farmRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(FarmNotFoundException.class, () -> fieldService.createField(fieldRequest));
    }

    @Test
    void testCreateFieldExceedsMaxFields() {
        when(farmRepository.findById(1L)).thenReturn(Optional.of(mockFarm));
        when(fieldRepository.countByFarm(mockFarm)).thenReturn(10);

        assertThrows(IllegalStateException.class, () -> fieldService.createField(fieldRequest));
    }

    @Test
    void testCreateFieldExceedsAreaLimit() {
        mockFarm.setArea(100.0);

        when(farmRepository.findById(1L)).thenReturn(Optional.of(mockFarm));
        when(fieldRepository.countByFarm(mockFarm)).thenReturn(5);
        when(fieldRepository.sumAreaByFarm(mockFarm)).thenReturn(60.0);

        fieldRequest.setArea(45.0);

        assertThrows(InvalidFieldAreaException.class, () -> fieldService.createField(fieldRequest));
    }

    @Test
    void testGetFieldByIdSuccess() {
        when(fieldRepository.findById(1L)).thenReturn(Optional.of(mockField));
        when(fieldMapper.toDto(mockField)).thenReturn(fieldDto);

        FieldDto result = fieldService.getFieldById(1L);

        assertNotNull(result);
        assertEquals(fieldDto.getId(), result.getId());
    }

    @Test
    void testGetFieldByIdNotFound() {
        when(fieldRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> fieldService.getFieldById(1L));
    }

    @Test
    void testGetAllFieldsByFarmSuccess() {
        when(farmRepository.findById(1L)).thenReturn(Optional.of(mockFarm));
        when(fieldRepository.findByFarm(mockFarm)).thenReturn(List.of(mockField));
        when(fieldMapper.toDto(mockField)).thenReturn(fieldDto);

        List<FieldDto> result = fieldService.getAllFieldsByFarm(1L);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(fieldDto.getId(), result.get(0).getId());
    }

    @Test
    void testDeleteField() {
        fieldService.deleteField(1L);
        verify(fieldRepository, times(1)).deleteById(1L);
    }
}

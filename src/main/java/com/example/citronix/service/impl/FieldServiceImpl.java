package com.example.citronix.service.impl;

import com.example.citronix.dto.FieldDto;
import com.example.citronix.entity.Farm;
import com.example.citronix.entity.Field;
import com.example.citronix.exception.customException.FarmNotFoundException;
import com.example.citronix.exception.customException.InvalidFieldAreaException;
import com.example.citronix.mapper.FieldMapper;
import com.example.citronix.repository.FarmRepository;
import com.example.citronix.repository.FieldRepository;
import com.example.citronix.request.FieldRequest;
import com.example.citronix.service.FieldService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FieldServiceImpl implements FieldService {
    private final FieldRepository fieldRepository;
    private final FieldMapper fieldMapper;
    private final FarmRepository farmRepository;

    @Override
    public FieldDto createField(FieldRequest fieldRequest) {
        // Fetch the Farm entity based on the farmId
        Farm farm = farmRepository.findById(fieldRequest.getFarmId())
                .orElseThrow(() -> new FarmNotFoundException(fieldRequest.getFarmId()));  // Ensure Farm exists

        // Check if the farm already has 10 fields
        if (fieldRepository.countByFarm(farm) >= 10) {
            throw new IllegalStateException("A farm cannot have more than 10 fields.");
        }

        // Get the total area of fields for this farm, default to 0.0 if no fields exist
        Double totalFieldArea = fieldRepository.sumAreaByFarm(farm);
        if (totalFieldArea == null) {
            totalFieldArea = 0.0;  // Handle null and default to 0.0
        }

        // Check if the total area of fields exceeds farm's area limits
        if (fieldRequest.getArea() > (farm.getArea() * 0.5) || (totalFieldArea + fieldRequest.getArea()) > farm.getArea()) {
            throw new InvalidFieldAreaException();
        }

        // Create the Field entity and set the farm reference
        Field field = fieldMapper.toEntity(fieldRequest);  // Map FieldRequest to Field entity
        field.setFarm(farm);  // Set the farm for the field

        // Save the field entity
        Field savedField = fieldRepository.save(field);

        // Return the created field as a DTO
        return fieldMapper.toDto(savedField);
    }

    @Override
    public void deleteField(Long id){
        fieldRepository.deleteById(id);
    }
    @Override
    public  FieldDto getFieldById(Long id){
        Field field=fieldRepository.findById(id)
                .orElseThrow(()->new RuntimeException("field not found."));
        return  fieldMapper.toDto(field);
    }
    @Override
    public List<FieldDto> getAllFieldsByFarm(Long farmId){
        Farm farm=farmRepository.findById(farmId)
                .orElseThrow(()->new FarmNotFoundException(farmId));
        List<Field> fields=fieldRepository.findByFarm(farm);
        return fields.stream().map(fieldMapper::toDto).collect(Collectors.toList());
    }

}

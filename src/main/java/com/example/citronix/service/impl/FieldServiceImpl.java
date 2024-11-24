package com.example.citronix.service.impl;

import com.example.citronix.dto.FieldDto;
import com.example.citronix.entity.Farm;
import com.example.citronix.entity.Field;
import com.example.citronix.exception.customException.FarmNotFoundException;
import com.example.citronix.exception.customException.InvalidFieldAreaException;
import com.example.citronix.mapper.FieldMapper;
import com.example.citronix.repository.FarmRepository;
import com.example.citronix.repository.FieldRepository;
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
    public FieldDto createField(FieldDto fieldDto) {
        Farm farm = farmRepository.findById(fieldDto.getFarmId())
                .orElseThrow(() -> new FarmNotFoundException(fieldDto.getFarmId()));

        if (fieldRepository.countByFarm(farm) >= 10) {
            throw new IllegalStateException("A farm cannot have more than 10 fields.");
        }

        double totalFieldArea = fieldRepository.sumAreaByFarm(farm);
        if (fieldDto.getArea() > (farm.getArea() * 0.5) || (totalFieldArea + fieldDto.getArea()) > farm.getArea()) {
            throw new InvalidFieldAreaException();
        }

        Field field = fieldMapper.toEntity(fieldDto);
        field.setFarm(farm);
        Field savedField = fieldRepository.save(field);
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

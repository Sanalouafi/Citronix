package com.example.citronix.service.impl;

import com.example.citronix.dto.TreeDto;
import com.example.citronix.entity.Field;
import com.example.citronix.entity.Tree;
import com.example.citronix.exception.customException.FieldNotFoundException;
import com.example.citronix.mapper.TreeMapper;
import com.example.citronix.repository.TreeRepository;
import com.example.citronix.repository.FieldRepository;
import com.example.citronix.service.TreeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TreeServiceImpl implements TreeService {

    private final TreeRepository treeRepository;
    private final FieldRepository fieldRepository;
    private final TreeMapper treeMapper;

    @Override
    public TreeDto createTree(TreeDto treeDto) {
        Field field = fieldRepository.findById(treeDto.getFieldId())
                .orElseThrow(() -> new FieldNotFoundException(treeDto.getFieldId()));

        Tree tree = treeMapper.toEntity(treeDto);
        tree.setField(field);
        Tree savedTree = treeRepository.save(tree);
        return treeMapper.toDto(savedTree);
    }

    @Override
    public void deleteTree(Long id) {
        treeRepository.deleteById(id);
    }

    @Override
    public TreeDto getTreeById(Long id) {
        Tree tree = treeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tree not found."));
        return treeMapper.toDto(tree);
    }

    @Override
    public List<TreeDto> getAllTreesByField(Long fieldId) {
        Field field = fieldRepository.findById(fieldId)
                .orElseThrow(() -> new FieldNotFoundException(fieldId));
        List<Tree> trees = treeRepository.findByField(field);
        return trees.stream().map(treeMapper::toDto).collect(Collectors.toList());
    }
}

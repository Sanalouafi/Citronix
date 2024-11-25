package com.example.citronix.service;

import com.example.citronix.dto.TreeDto;

import java.util.List;

public interface TreeService {

    TreeDto createTree(TreeDto treeDto);
    void deleteTree(Long id);
    TreeDto getTreeById(Long id);
    List<TreeDto> getAllTreesByField(Long fieldId);
    TreeDto updateTree(Long id, TreeDto treeDto);
}

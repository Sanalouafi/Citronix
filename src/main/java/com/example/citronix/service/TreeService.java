package com.example.citronix.service;

import com.example.citronix.vm.TreeVM;
import com.example.citronix.dto.TreeDto;

import java.util.List;

public interface TreeService {

    TreeDto createTree(TreeDto treeDto);
    void deleteTree(Long id);
    TreeVM getTreeById(Long id);
    List<TreeVM> getAllTreesByField(Long fieldId);
}

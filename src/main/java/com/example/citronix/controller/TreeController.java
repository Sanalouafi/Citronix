package com.example.citronix.controller;

import com.example.citronix.dto.TreeDto;
import com.example.citronix.request.TreeRequest;
import com.example.citronix.service.TreeService;
import com.example.citronix.mapper.TreeMapper;
import com.example.citronix.vm.TreeVM;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/trees")
@RequiredArgsConstructor
public class TreeController {

    private final TreeService treeService;
    private final TreeMapper treeMapper;

    @PostMapping
    public ResponseEntity<TreeDto> createTree(@Valid @RequestBody TreeRequest treeRequest) {
        TreeDto treeDto = treeMapper.toDto(treeMapper.toEntity(treeRequest));
        TreeDto createdTree = treeService.createTree(treeDto);
        return new ResponseEntity<>(createdTree, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TreeVM> getTreeById(@PathVariable Long id) {
        TreeVM treeVM = treeService.getTreeById(id);
        return ResponseEntity.ok(treeVM);
    }

    @GetMapping("/field/{fieldId}")
    public ResponseEntity<List<TreeVM>> getAllTreesByField(@PathVariable Long fieldId) {
        List<TreeVM> trees = treeService.getAllTreesByField(fieldId);
        return ResponseEntity.ok(trees);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTree(@PathVariable Long id) {
        treeService.deleteTree(id);
        return ResponseEntity.noContent().build();
    }
}

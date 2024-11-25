package com.example.citronix.mapper;

import com.example.citronix.dto.SaleDto;
import com.example.citronix.dto.TreeDto;
import com.example.citronix.entity.Sale;
import com.example.citronix.entity.Tree;
import com.example.citronix.request.TreeRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TreeMapper {

    @Mapping(target = "fieldId", source = "field.id")
    TreeDto toDto(Tree tree);
    @Mapping(target = "field.id", source = "fieldId")
    Tree toEntity(TreeDto treeDto);

    @Mapping(target = "field", ignore = true)
    @Mapping(target = "id", ignore = true)
    Tree toEntity(TreeRequest treeRequest);
}

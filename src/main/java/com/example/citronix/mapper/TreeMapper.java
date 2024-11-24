package com.example.citronix.mapper;

import com.example.citronix.dto.TreeDto;
import com.example.citronix.entity.Tree;
import com.example.citronix.entity.enums.TreeStatus;
import com.example.citronix.request.TreeRequest;
import com.example.citronix.vm.TreeVM;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.LocalDate;

@Mapper(componentModel = "spring")
public interface TreeMapper {

    @Mapping(target = "age", expression = "java(calculateAge(tree.getPlantingDate()))")
    @Mapping(target = "productivity", expression = "java(calculateProductivity(tree.getPlantingDate()))")
    @Mapping(target = "status", expression = "java(determineTreeStatus(tree.getPlantingDate()))")
    TreeDto toDto(Tree tree);

    @Mapping(target = "age", expression = "java(calculateAge(tree.getPlantingDate()))")
    @Mapping(target = "productivity", expression = "java(calculateProductivity(tree.getPlantingDate()))")
    @Mapping(target = "status", expression = "java(determineTreeStatus(tree.getPlantingDate()))")
    TreeVM toVM(Tree tree);

    @Mapping(target = "field", ignore = true)
    Tree toEntity(TreeDto treeDto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "field", ignore = true)
    Tree toEntity(TreeRequest treeRequest);

    default int calculateAge(LocalDate plantingDate) {
        return plantingDate != null ? java.time.Period.between(plantingDate, java.time.LocalDate.now()).getYears() : 0;
    }

    default double calculateProductivity(LocalDate plantingDate) {
        int age = calculateAge(plantingDate);
        if (age < 3) {
            return 2.5;
        } else if (age <= 10) {
            return 12.0;
        } else if (age <= 20) {
            return 20.0;
        } else {
            return 0.0;  // Non-productive
        }
    }

    default TreeStatus determineTreeStatus(LocalDate plantingDate) {
        int age = calculateAge(plantingDate);
        if (age < 3) {
            return TreeStatus.YOUNG;
        } else if (age <= 10) {
            return TreeStatus.MATURE;
        } else if (age <= 20) {
            return TreeStatus.OLD;
        } else {
            return TreeStatus.NON_PRODUCTIVE;
        }
    }
}

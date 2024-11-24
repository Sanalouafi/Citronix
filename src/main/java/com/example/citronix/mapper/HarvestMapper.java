package com.example.citronix.mapper;

import com.example.citronix.dto.HarvestDto;
import com.example.citronix.dto.HarvestDetailDto;
import com.example.citronix.entity.Harvest;
import com.example.citronix.entity.HarvestDetail;
import com.example.citronix.request.HarvestRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface HarvestMapper {

    HarvestDto toDto(Harvest harvest);

    @Mapping(target = "id", ignore = true)
    Harvest toEntity(HarvestDto harvestDto);

    @Mapping(target = "id", ignore = true)
    Harvest toEntity(HarvestRequest harvestRequest);

    HarvestDetailDto toDetailDto(HarvestDetail harvestDetail);

    @Mapping(target = "harvest", ignore = true)
    HarvestDetail toEntity(HarvestDetailDto harvestDetailDto);
}

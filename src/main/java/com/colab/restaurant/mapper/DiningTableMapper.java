package com.colab.restaurant.mapper;

import com.colab.restaurant.model.dto.DiningTableDTO;
import com.colab.restaurant.model.entity.DiningTable;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DiningTableMapper {
    DiningTableMapper INSTANCE = Mappers.getMapper(DiningTableMapper.class);

    DiningTable toEntity(DiningTableDTO dto);

    DiningTableDTO toDto(DiningTable entity);

    List<DiningTableDTO> toDtoList(List<DiningTable> entities);
}

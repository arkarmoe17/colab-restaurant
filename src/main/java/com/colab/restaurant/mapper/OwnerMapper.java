package com.colab.restaurant.mapper;

import com.colab.restaurant.model.dto.OwnerDTO;
import com.colab.restaurant.model.entity.Owner;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OwnerMapper {
    OwnerMapper INSTANCE = Mappers.getMapper(OwnerMapper.class);

    Owner toEntity(OwnerDTO dto);

    OwnerDTO toDto(Owner entity);

    List<OwnerDTO> toDtoList(List<Owner> entities);
}

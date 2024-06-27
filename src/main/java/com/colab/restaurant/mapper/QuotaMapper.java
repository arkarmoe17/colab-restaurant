package com.colab.restaurant.mapper;

import com.colab.restaurant.model.dto.QuotaDTO;
import com.colab.restaurant.model.entity.Quota;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface QuotaMapper {
    QuotaMapper INSTANCE = Mappers.getMapper(QuotaMapper.class);

    Quota toEntity(QuotaDTO dto);
    QuotaDTO toDto(Quota entity);
}

package com.colab.restaurant.mapper;

import com.colab.restaurant.model.dto.QuotaTransactionDTO;
import com.colab.restaurant.model.entity.QuotaTransaction;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface QuotaTransMapper {
    QuotaTransMapper INSTANCE = Mappers.getMapper(QuotaTransMapper.class);

    QuotaTransaction toEntity(QuotaTransactionDTO dto);

    QuotaTransactionDTO toDto(QuotaTransaction entity);
}

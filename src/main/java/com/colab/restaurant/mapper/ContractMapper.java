package com.colab.restaurant.mapper;

import com.colab.restaurant.model.dto.ContractDTO;
import com.colab.restaurant.model.entity.Contract;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ContractMapper {
    ContractMapper INSTANCE = Mappers.getMapper(ContractMapper.class);

    Contract toEntity(ContractDTO dto);

    ContractDTO toDto(Contract entity);

    List<ContractDTO> toDtoList(List<Contract> entities);
}

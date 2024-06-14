package com.colab.restaurant.mapper;

import com.colab.restaurant.model.dto.RestaurantDTO;
import com.colab.restaurant.model.entity.Restaurant;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RestaurantMapper {
    RestaurantMapper INSTANCE = Mappers.getMapper(RestaurantMapper.class);

    Restaurant toEntity(RestaurantDTO dto);

    RestaurantDTO toDto(Restaurant entity);

    List<RestaurantDTO> toDtoList(List<Restaurant> entities);
}

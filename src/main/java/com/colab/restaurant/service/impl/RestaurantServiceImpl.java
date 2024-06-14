package com.colab.restaurant.service.impl;

import com.colab.restaurant.mapper.RestaurantMapper;
import com.colab.restaurant.model.dto.RestaurantDTO;
import com.colab.restaurant.model.entity.Restaurant;
import com.colab.restaurant.repo.RestaurantRepo;
import com.colab.restaurant.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class RestaurantServiceImpl implements RestaurantService {
    private final RestaurantRepo restaurantRepo;

    @Override
    public List<RestaurantDTO> fetchAllRestaurants() {
        log.info("Fetching all the restaurants");
        return RestaurantMapper.INSTANCE.toDtoList(restaurantRepo.findAll()
                .stream()
                .sorted(Comparator.comparing(Restaurant::getName))
                .toList()
        );
    }

    @Override
    public List<String> fetchAllActiveShopCodes() {
        log.info("Fetching all the active shop codes.");
        return restaurantRepo.findAll()
                .stream()
                .filter(Restaurant::isActive)
                .map(Restaurant::getShopCode)
                .sorted()
                .toList();
    }

    @Override
    public void registerRestaurant(RestaurantDTO requestDTO) {
        log.info("Registering the restaurant name: {} | code:{}", requestDTO.getName(), requestDTO.getShopCode());
        Optional<Restaurant> restaurantOpt = restaurantRepo.findByShopCode(requestDTO.getShopCode());
        if (restaurantOpt.isPresent()) {
            log.error("Code: {} is already existed in system.", requestDTO.getShopCode());
            return;
        }
        Restaurant restaurant = RestaurantMapper.INSTANCE.toEntity(requestDTO);
        log.info("restaurant name:{} is created successfully.", restaurant.getName());
        restaurantRepo.save(restaurant);
    }

    @Override
    public RestaurantDTO updateRestaurant(String id, RestaurantDTO requestDTO) {
        log.info("Update the restaurant by id: {} | code:{}", id, requestDTO.getShopCode());

        // find by id
        Optional<Restaurant> restaurantOpt = restaurantRepo.findById(id);
        if (restaurantOpt.isEmpty()) {
            log.error("Id is not found.");
            return null;
        }

        // code is already existed or not
        Optional<Restaurant> restaurantOpt2 = restaurantRepo.findByShopCode(requestDTO.getShopCode());
        if (restaurantOpt2.isPresent() && !restaurantOpt2.get().getId().equals(id)) {
            log.error("Code: {} is already existed in system.", requestDTO.getShopCode());
            return null;
        }

        // update
        Restaurant restaurant = RestaurantMapper.INSTANCE.toEntity(requestDTO);
        restaurant.setId(id);
        restaurantRepo.save(restaurant);
        return RestaurantMapper.INSTANCE.toDto(restaurant);
    }
}

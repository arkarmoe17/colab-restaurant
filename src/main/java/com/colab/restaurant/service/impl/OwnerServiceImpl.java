package com.colab.restaurant.service.impl;

import com.colab.restaurant.exceptions.BadRequestException;
import com.colab.restaurant.mapper.OwnerMapper;
import com.colab.restaurant.model.dto.OwnerDTO;
import com.colab.restaurant.model.entity.Owner;
import com.colab.restaurant.model.entity.Restaurant;
import com.colab.restaurant.repo.OwnerRepo;
import com.colab.restaurant.repo.RestaurantRepo;
import com.colab.restaurant.service.OwnerService;
import com.colab.restaurant.utility.Constant;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class OwnerServiceImpl implements OwnerService {
    private final RestaurantRepo restaurantRepo;
    private final OwnerRepo ownerRepo;

    @Override
    public void createOwner(Owner owner) {
        log.info("Create owner by name :{} into shopCode: {}", owner.getName(), owner.getShopCode());
        // check restaurant id
        Optional<Restaurant> restaurantOptional = restaurantRepo.findByShopCode(owner.getShopCode());
        if (restaurantOptional.isEmpty()) {
            log.error("Restaurant code: {} is not found.", owner.getShopCode());
            throw new BadRequestException(Constant.Message.SHOP_CODE_NOT_FOUND);
        }
        ownerRepo.save(owner);
    }

    @Override
    public List<OwnerDTO> findAllByShopCode(String shopCode) {
        log.info("Find all owners by shop code:{}", shopCode);
        return OwnerMapper.INSTANCE.toDtoList(ownerRepo.findAllByShopCodeOrderByName(shopCode));
    }
}

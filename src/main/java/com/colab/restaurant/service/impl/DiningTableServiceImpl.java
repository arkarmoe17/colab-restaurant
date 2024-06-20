package com.colab.restaurant.service.impl;

import com.colab.restaurant.exceptions.BadRequestException;
import com.colab.restaurant.mapper.DiningTableMapper;
import com.colab.restaurant.model.dto.DiningTableDTO;
import com.colab.restaurant.model.entity.DiningTable;
import com.colab.restaurant.repo.DiningTableRepo;
import com.colab.restaurant.repo.RestaurantRepo;
import com.colab.restaurant.service.DiningTableService;
import com.colab.restaurant.utility.CommonUtils;
import com.colab.restaurant.utility.Constant;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class DiningTableServiceImpl implements DiningTableService {
    private final RestaurantRepo restaurantRepo;
    private final DiningTableRepo diningTableRepo;

    @Override
    public List<DiningTableDTO> fetchingDiningTables(String shopCode, Boolean isActive) {
        log.info("Fetching the dining tables by shopCodes:{}", shopCode);
        log.info("isActive: {}", isActive);
        List<DiningTable> diningTables = diningTableRepo.findAllByShopCodeOrderByName(shopCode);
        if (isActive != null) {
            diningTables = diningTables.stream()
                    .filter(d -> d.isActive() == isActive)
                    .toList();
        }
        return DiningTableMapper.INSTANCE.toDtoList(diningTables);
    }

    @Override
    public DiningTableDTO createDiningTable(DiningTable diningTable) {
        log.info("Creating the dining table of shopCode:{}", diningTable.getShopCode());
        // check shop code exists or not
        restaurantRepo.findByShopCode(diningTable.getShopCode())
                .orElseThrow(() -> new BadRequestException(Constant.Message.SHOP_CODE_NOT_FOUND));

        // table code is already existed or not
        String tableCode = CommonUtils.getUUID(diningTable.getShopCode());
        log.info("TableCode: {}", tableCode);

        // check table code
        Optional<DiningTable> diningTableOpt = diningTableRepo.findByTableCode(tableCode);
        if (diningTableOpt.isPresent())
            throw new BadRequestException(Constant.Message.TABLE_CODE_IS_EXISTED);

        // check table name
        Optional<DiningTable> diningTableOpt2 = diningTableRepo.findByShopCodeAndName(diningTable.getShopCode(), diningTable.getName());
        if (diningTableOpt2.isPresent())
            throw new BadRequestException(Constant.Message.TABLE_NAME_IS_EXISTED);

        diningTable.setTableCode(tableCode);
        diningTable.setActive(true);
        diningTableRepo.save(diningTable);

        return DiningTableMapper.INSTANCE.toDto(diningTable);
    }

    @Override
    public DiningTableDTO updateDiningTable(DiningTable diningTable) {
        log.info("Updating the dining table by shopCode:{}", diningTable.getTableCode());

        // table code is already existed or not
        String tableCode = diningTable.getTableCode();
        log.info("TableCode: {}", tableCode);

        // check name and shop code
        Optional<DiningTable> diningTableOpt = diningTableRepo.findByShopCodeAndName(diningTable.getShopCode(), diningTable.getName());
        if(diningTableOpt.isPresent() && !diningTableOpt.get().getTableCode().equals(diningTable.getTableCode())){
            throw new BadRequestException(Constant.Message.TABLE_CODE_IS_EXISTED);

        }

        // check table code
        Optional<DiningTable> diningTableOpt2 = diningTableRepo.findByTableCode(tableCode);
        if (diningTableOpt2.isEmpty()) {
            throw new BadRequestException(Constant.Message.TABLE_CODE_NOT_FOUND);
        }

        diningTable.setId(diningTableOpt2.get().getId());
        diningTable.setTableCode(tableCode);
        diningTable.setActive(true);
        diningTableRepo.save(diningTable);
        return DiningTableMapper.INSTANCE.toDto(diningTable);
    }
}

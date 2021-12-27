package com.arkarmoe.springbootjwt.service.impl;

import com.arkarmoe.springbootjwt.model.entity.Menu;
import com.arkarmoe.springbootjwt.repo.MenuRepo;
import com.arkarmoe.springbootjwt.service.MenuService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class MenuServiceImpl implements MenuService {
    private final MenuRepo menuRepo;
    @Override
    public ResponseEntity<List<Menu>> fetchAllMenuLists() {
        log.info("[START] Fetch all the menu lists.");
        List<Menu> menuList = menuRepo.findAllByOrderByName();
        log.info("Total elements:{}",menuList.size());
        log.info("[END] Fetch all the menu lists.\n");
        return ResponseEntity.ok(menuList);
    }

    @Override
    public ResponseEntity<?> createMenu(Menu menu) {
        log.info("[START] Create the menu name.");
        Optional<Menu> menuOptional = menuRepo.findByName(menu.getName());
        if(menuOptional.isPresent())return new ResponseEntity<>("Menu name is already existed.", HttpStatus.BAD_REQUEST);
        menu.setName(menu.getName());
        menuRepo.save(menu);
        log.info("[END] Create the menu name.\n");
        return ResponseEntity.ok(menu);
    }
}

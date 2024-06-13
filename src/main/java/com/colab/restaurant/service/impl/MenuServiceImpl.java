package com.colab.restaurant.service.impl;

import com.colab.restaurant.repo.MenuRepo;
import com.colab.restaurant.service.MenuService;
import com.colab.restaurant.model.entity.Menu;
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
        log.info("[START] Fetching all the menu lists.");
        List<Menu> menuList = menuRepo.findAllByOrderByName();
        log.info("Total elements:{}",menuList.size());
        log.info("[END] Fetching all the menu lists.\n");
        return ResponseEntity.ok(menuList);
    }

    @Override
    public ResponseEntity<?> createMenu(Menu menu) {
        log.info("[START] Creating the menu name.");
        Optional<Menu> menuOptional = menuRepo.findByName(menu.getName());
        if(menuOptional.isPresent())return new ResponseEntity<>("Menu name is already existed.", HttpStatus.BAD_REQUEST);
        menu.setName(menu.getName());
        menuRepo.save(menu);
        log.info("[END] Creating the menu name.\n");
        return ResponseEntity.ok(menu);
    }

    @Override
    public ResponseEntity<?> updateMenu(Long id,Menu req) {
        log.info("[START] Updating the menu name by Id:{}",id);
        Optional<Menu> menuOptional = menuRepo.findById(id);
        if(!menuOptional.isPresent())
            return new ResponseEntity<>("Id is not found.",HttpStatus.BAD_REQUEST);
        Menu menu = menuOptional.get();
        menu.setName(req.getName());
        menuRepo.save(menu);
        log.info("[END] Updating the menu name by Id:{}\n",id);
        return ResponseEntity.ok(menu);
    }

    @Override
    public ResponseEntity<?> deleteMenu(Long id) {
        log.info("[START] Deleting the menu name by Id:{}\n",id);
        Optional<Menu> menuOptional = menuRepo.findById(id);
        if(!menuOptional.isPresent())
            return new ResponseEntity<>("Id is not found.",HttpStatus.BAD_REQUEST);
        menuRepo.delete(menuOptional.get());
        log.info("[END] Deleting the menu name by Id:{}\n",id);
        return ResponseEntity.ok("SUCCESS.");
    }
}

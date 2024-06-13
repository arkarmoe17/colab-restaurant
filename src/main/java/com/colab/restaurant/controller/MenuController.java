package com.colab.restaurant.controller;

import com.colab.restaurant.service.MenuService;
import com.colab.restaurant.model.entity.Menu;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Arkar on 27-Dec-2021
 **/
@RestController
@RequestMapping("/api/menu")
@RequiredArgsConstructor
public class MenuController {
    private final MenuService menuService;

    //fetch all
    @GetMapping("/lists")
    public ResponseEntity<List<Menu>> fetchAllMenuLists() {
        return menuService.fetchAllMenuLists();
    }

    //create
    @PostMapping()
    public ResponseEntity<?> createMenu(@RequestBody Menu menu) {
        return menuService.createMenu(menu);
    }

    //update
    @PutMapping("/{id}")
    public ResponseEntity<?> updateMenu(@PathVariable("id") Long id,
                                        @RequestBody Menu menu) {
        return menuService.updateMenu(id, menu);
    }

    //delete
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMenu(@PathVariable("id") Long id) {
        return menuService.deleteMenu(id);
    }
}

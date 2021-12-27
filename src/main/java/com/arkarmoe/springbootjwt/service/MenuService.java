package com.arkarmoe.springbootjwt.service;

import com.arkarmoe.springbootjwt.model.entity.Menu;
import org.springframework.http.ResponseEntity;

import java.util.List;

/**
 * Created by Arkar on 27-Dec-2021
 * **/
public interface MenuService {
    ResponseEntity<List<Menu>> fetchAllMenuLists();
    ResponseEntity<?> createMenu(Menu menu);
}

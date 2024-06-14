package com.colab.restaurant.controller;

import com.colab.restaurant.model.dto.RestaurantDTO;
import com.colab.restaurant.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1.0/restaurants")
@RequiredArgsConstructor
public class RestaurantController {
    private final RestaurantService restaurantService;

    // Fetch Restaurants
    @GetMapping
    public ResponseEntity<List<RestaurantDTO>> fetchAllRestaurants() {
        return ResponseEntity.ok(restaurantService.fetchAllRestaurants());
    }

    // Fetch Shop Codes
    @GetMapping("/shopCodes")
    public ResponseEntity<List<String>> fetchAllActiveShopCodes() {
        return ResponseEntity.ok(restaurantService.fetchAllActiveShopCodes());
    }

    // Register Restaurant
    @PostMapping
    public ResponseEntity<Void> registerRestaurant(@RequestBody RestaurantDTO requestDTO) {
        restaurantService.registerRestaurant(requestDTO);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<RestaurantDTO> updateRestaurant(@RequestHeader("restaurant-id") String restaurantId,
                                                          @RequestBody RestaurantDTO requestDTO) {
        return ResponseEntity.ok(restaurantService.updateRestaurant(restaurantId, requestDTO));
    }

}

package com.colab.restaurant.controller;

import com.colab.restaurant.model.dto.RestaurantDTO;
import com.colab.restaurant.service.RestaurantService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1.0/restaurants")
@Tag(name = "Restaurant", description = "Restaurant API")
@RequiredArgsConstructor
public class RestaurantController {
    private final RestaurantService restaurantService;

    @Operation(summary = "Fetch all the restaurants")
    @GetMapping
    public ResponseEntity<List<RestaurantDTO>> fetchAllRestaurants() {
        return ResponseEntity.ok(restaurantService.fetchAllRestaurants());
    }

    @Operation(summary = "Fetch all shopCodes")
    @GetMapping("/shopCodes")
    public ResponseEntity<List<String>> fetchAllActiveShopCodes() {
        return ResponseEntity.ok(restaurantService.fetchAllActiveShopCodes());
    }

    @Operation(summary = "Register the restaurant")
    @PostMapping
    public ResponseEntity<Void> registerRestaurant(@RequestBody RestaurantDTO requestDTO) {
        restaurantService.registerRestaurant(requestDTO);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Update the restaurant")
    @PutMapping
    public ResponseEntity<RestaurantDTO> updateRestaurant(@RequestHeader("restaurant-id") String restaurantId,
                                                          @RequestBody RestaurantDTO requestDTO) {
        return ResponseEntity.ok(restaurantService.updateRestaurant(restaurantId, requestDTO));
    }

}

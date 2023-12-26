package com.example.dinemaster.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.ArrayList;
import java.util.List;

import com.example.dinemaster.model.*;
import com.example.dinemaster.repository.*;

@Service
public class RestaurantJpaService implements RestaurantRepository {

    @Autowired
    private RestaurantJpaRepository restaurantJpaRepository;

    @Override
    public ArrayList<Restaurant> getRestaurants() {
        List<Restaurant> restaurantList = restaurantJpaRepository.findAll();
        ArrayList<Restaurant> restaurants = new ArrayList<>(restaurantList);
        return restaurants;
    }

    @Override
    public Restaurant getRestaurantById(int id) {
        try {
            Restaurant restaurant = restaurantJpaRepository.findById(id).get();
            return restaurant;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public Restaurant addRestaurant(Restaurant restaurant) {
        restaurantJpaRepository.save(restaurant);
        return restaurant;
    }

    @Override
    public Restaurant updateRestaurant(int id, Restaurant restaurant) {
        try {
            Restaurant existingRestaurant = restaurantJpaRepository.findById(id).get();
            if (restaurant.getName() != null) {
                existingRestaurant.setName(restaurant.getName());
            }
            if (restaurant.getAddress() != null) {
                existingRestaurant.setAddress(restaurant.getAddress());
            }
            if (restaurant.getCuisineType() != null) {
                existingRestaurant.setCuisineType(restaurant.getCuisineType());
            }
            if (restaurant.getRating() != 0) {
                existingRestaurant.setRating(restaurant.getRating());
            }

            return restaurantJpaRepository.save(existingRestaurant);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public void deleteRestaurant(int id) {
        try {
            restaurantJpaRepository.deleteById(id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        throw new ResponseStatusException(HttpStatus.NO_CONTENT);
    }
}

package com.example.dinemaster.repository;

import java.util.ArrayList;
import com.example.dinemaster.model.*;

public interface RestaurantRepository {
    ArrayList<Restaurant> getRestaurants();
    Restaurant getRestaurantById(int id);
    Restaurant addRestaurant(Restaurant restaurant);
    Restaurant updateRestaurant(int id, Restaurant restaurant);
    void deleteRestaurant(int id);
}

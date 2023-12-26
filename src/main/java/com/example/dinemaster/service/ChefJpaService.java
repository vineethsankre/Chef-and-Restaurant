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
public class ChefJpaService implements ChefRepository {
    @Autowired
    private ChefJpaRepository chefJpaRepository;

    @Autowired
    private RestaurantJpaRepository restaurantJpaRespository;

    @Override
    public ArrayList<Chef> getChefs() {
        List<Chef> chefList = chefJpaRepository.findAll();
        ArrayList<Chef> chefs = new ArrayList<>(chefList);
        return chefs;
    }

    @Override
    public Chef getChefById(int id) {
        try {
            Chef chef = chefJpaRepository.findById(id).get();
            return chef;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public Chef addChef(Chef chef) {
        Restaurant restaurant = chef.getRestaurant();
        int id = restaurant.getId();

        try {
            restaurant = restaurantJpaRepository.findById(id).get();
            chef.setRestaurant(restaurant);
            chefJpaRepository.save(chef);
            return chef;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public Chef updateChef(int id, Chef chef) {
        try {
            Chef existingChef = chefJpaRepository.findById(id).get();
            if (chef.getFirstName() != null) {
                existingChef.setFirstName(chef.getFirstName());
            }
            if (chef.getLastName() != null) {
                existingChef.setLastName(chef.getLastName());
            }
            if (chef.getExpertise() != null) {
                existingChef.setExpertise(chef.getExpertise());
            }
            if (chef.getExperienceYears() != 0) {
                existingChef.setExperienceYears(chef.getExperienceYears());
            }

            return chefJpaRepository.save(existingChef);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public void deleteChef(int id) {
        try {
            chefJpaRepository.deleteById(id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        throw new ResponseStatusException(HttpStatus.NO_CONTENT);
    }

	@Override
	public Restaurant getChefRestaurant(int id) {
		try {
            Chef chef = chefJpaRepository.findById(id).get();
            Restaurant restaurant = restaurant.getRestaurant();
            return restaurant;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
	}

}

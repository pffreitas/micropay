package br.com.micropay.business

import br.com.micropay.business.object.Restaurant
import br.com.micropay.business.repository.IRestaurantRepository

import javax.inject.Inject
import javax.inject.Named

@Named
class RestaurantListing {

    IRestaurantRepository restaurantRepository

    @Inject
    RestaurantListing(IRestaurantRepository restaurantRepository){
        this.restaurantRepository = restaurantRepository
    }

    final List<Restaurant> findAll(){
        return restaurantRepository.findAll()
    }
}

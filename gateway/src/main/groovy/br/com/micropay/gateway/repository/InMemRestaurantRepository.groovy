package br.com.micropay.gateway.repository

import br.com.micropay.business.object.Restaurant
import br.com.micropay.business.repository.IRestaurantRepository

import javax.inject.Named

@Named
class InMemRestaurantRepository implements IRestaurantRepository {

    Map<Long, Restaurant> data = [1L: new Restaurant(id: 1L, name: "R01")]

    @Override
    Restaurant findById(Long id) {
        return data[id]
    }
}

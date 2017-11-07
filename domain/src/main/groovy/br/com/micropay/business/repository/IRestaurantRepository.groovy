package br.com.micropay.business.repository

import br.com.micropay.business.object.Restaurant

interface IRestaurantRepository {

    Restaurant findById(Long id)
}
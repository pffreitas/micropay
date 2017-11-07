package br.com.micropay.gateway.repository

import br.com.micropay.business.object.PaymentMethod
import br.com.micropay.business.object.PaymentType
import br.com.micropay.business.object.Restaurant
import br.com.micropay.business.repository.IRestaurantRepository

import javax.inject.Named

@Named
class InMemRestaurantRepository implements IRestaurantRepository {

    static final r1PaymentMethods = [
            new PaymentMethod(id: 1, description: "Cash", type: PaymentType.Cash),
            new PaymentMethod(id: 2, description: "Rede POSMachine", type: PaymentType.POSMachine),
            new PaymentMethod(id: 3, description: "Cielo POSMachine", type: PaymentType.POSMachine),
            new PaymentMethod(id: 4, description: "Mastercard", type: PaymentType.Online),
            new PaymentMethod(id: 5, description: "Visa", type: PaymentType.Online)
    ]

    static final r2PaymentMethods = [
            new PaymentMethod(id: 1, description: "Cash", type: PaymentType.Cash),
            new PaymentMethod(id: 4, description: "Mastercard", type: PaymentType.Online),
            new PaymentMethod(id: 5, description: "Visa", type: PaymentType.Online)
    ]

    static final Map<Long, Restaurant> data = [
            1L: new Restaurant(id: 1L, name: "R01", paymentMethods: r1PaymentMethods),
            2L: new Restaurant(id: 2L, name: "Pf Chang's", paymentMethods: r2PaymentMethods)
    ]

    @Override
    Restaurant findById(Long id) {
        data[id]
    }

    @Override
    List<Restaurant> findAll() {
        data.collect { it.value }
    }
}

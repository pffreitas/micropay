package br.com.micropay.business

import br.com.micropay.business.object.PaymentConfiguration
import br.com.micropay.business.object.Restaurant
import br.com.micropay.business.object.User
import br.com.micropay.business.repository.ICache

import javax.inject.Inject
import javax.inject.Named

@Named
class CachedPaymentConfiguration {

    ICache cache

    @Inject
    CachedPaymentConfiguration(ICache cache) {
        this.cache = cache
    }

    final void updatePaymentConfiguration(PaymentConfiguration paymentConfiguration){
        def key = getKey(paymentConfiguration.restaurant, paymentConfiguration.user)
        cache.put(key, paymentConfiguration)
    }

    final PaymentConfiguration getPaymentConfiguration( Restaurant restaurant, User user){
        def key = getKey(restaurant, user)
        return cache.get(key) as PaymentConfiguration
    }

    private static final String getKey(Restaurant restaurant, User user){
        return restaurant.id + "-" + user.id
    }
}

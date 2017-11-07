package br.com.micropay.business

import br.com.micropay.business.object.AntiFraudResponse
import br.com.micropay.business.object.PaymentMethod
import br.com.micropay.business.object.Restaurant
import br.com.micropay.business.object.User

import javax.inject.Named

@Named
class AntiFraudValidation {

    AntiFraudResponse process(User user, Restaurant restaurant, PaymentMethod paymentMethod) {
        return new AntiFraudResponse(denied: true)
    }

}

package br.com.micropay.business

import br.com.micropay.business.object.*

import javax.inject.Named

@Named
class AntiFraudValidation {

    static final AntiFraudResponse process(User user, Restaurant restaurant, PaymentMethod paymentMethod) {
        def denied = false

        if(user.id == 1L && paymentMethod.type == PaymentType.Online) {
            denied = true
        }

        return new AntiFraudResponse(denied: denied)
    }

}

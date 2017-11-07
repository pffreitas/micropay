package br.com.micropay.business

import br.com.micropay.business.object.*

import javax.inject.Inject
import javax.inject.Named

@Named
class PaymentMethodListing {

    AntiFraudValidation antiFraudValidation

    CachedPaymentConfiguration cachedPaymentConfiguration

    @Inject
    PaymentMethodListing(AntiFraudValidation antiFraudValidation, CachedPaymentConfiguration cachedPaymentConfiguration) {
        this.antiFraudValidation = antiFraudValidation
        this.cachedPaymentConfiguration = cachedPaymentConfiguration
    }

    final List<PaymentMethod> list(User user, Restaurant restaurant) {
        def paymentConfiguration = cachedPaymentConfiguration.getPaymentConfiguration(restaurant, user)

        if (!paymentConfiguration) {
            def allPaymentMethods = restaurant.paymentMethods

            def availablePaymentMethods = allPaymentMethods.findAll { paymentMethod ->
                def available = true

                if (paymentMethod.type == PaymentType.Online) {
                    def response = antiFraudValidation.process(user, restaurant, paymentMethod)
                    available = !response.isDenied()
                }

                return available
            }

            paymentConfiguration = new PaymentConfiguration(restaurant, user, availablePaymentMethods)
            cachedPaymentConfiguration.updatePaymentConfiguration(paymentConfiguration)
        }

        return paymentConfiguration.paymentMethods
    }
}

package br.com.micropay.business

import br.com.micropay.business.object.*
import br.com.micropay.business.repository.IPaymentMethodRepository

import javax.inject.Inject
import javax.inject.Named

@Named
class PaymentMethodListing {

    AntiFraudValidation antiFraudValidation

    IPaymentMethodRepository paymentMethodRepository

    CachedPaymentConfiguration cachedPaymentConfiguration

    @Inject
    PaymentMethodListing(AntiFraudValidation antiFraudValidation, IPaymentMethodRepository paymentMethodRepository, CachedPaymentConfiguration cachedPaymentConfiguration) {
        this.antiFraudValidation = antiFraudValidation
        this.paymentMethodRepository = paymentMethodRepository
        this.cachedPaymentConfiguration = cachedPaymentConfiguration
    }

    List<PaymentMethod> list(User user, Restaurant restaurant) {
        def paymentConfiguration = cachedPaymentConfiguration.getPaymentConfiguration(restaurant, user)

        if (!paymentConfiguration) {
            def allPaymentMethods = paymentMethodRepository.findAll()

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

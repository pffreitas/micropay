package br.com.micropay.gateway.repository

import br.com.micropay.business.object.PaymentMethod
import br.com.micropay.business.object.PaymentType
import br.com.micropay.business.repository.IPaymentMethodRepository

import javax.inject.Named

@Named
class InMemPaymentMethodRepository implements IPaymentMethodRepository{

    @Override
    List<PaymentMethod> findAll() {
        return [
                new PaymentMethod(id: 1, description: "Cash", type: PaymentType.Cash),
                new PaymentMethod(id: 2, description: "Rede POSMachine", type: PaymentType.POSMachine),
                new PaymentMethod(id: 2, description: "Cielo POSMachine", type: PaymentType.POSMachine),
                new PaymentMethod(id: 2, description: "Mastercard", type: PaymentType.Online),
                new PaymentMethod(id: 2, description: "Visa", type: PaymentType.Online)
        ]
    }

}

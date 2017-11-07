package br.com.micropay.business.repository

import br.com.micropay.business.object.PaymentMethod

interface IPaymentMethodRepository {

    List<PaymentMethod> findAll()
}
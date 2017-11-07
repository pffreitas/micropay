package br.com.micropay.business.object

class PaymentConfiguration {

    Restaurant restaurant

    User user

    List<PaymentMethod> paymentMethods

    PaymentConfiguration(Restaurant restaurant, User user, List<PaymentMethod> paymentMethods){
        this.restaurant = restaurant
        this.user = user
        this.paymentMethods = paymentMethods
    }
}

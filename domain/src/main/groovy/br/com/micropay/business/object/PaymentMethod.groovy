package br.com.micropay.business.object

/**
 * Represents a Payment Method
 */
class PaymentMethod {

    Long id

    String description

    PaymentType type
}

enum PaymentType {
    Online,

    POSMachine,

    Cash
}

package br.com.micropay.business.object

class AntiFraudResponse {

    Boolean denied

    Boolean isDenied(){
        return this.denied
    }
}

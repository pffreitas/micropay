package br.com.micropay.gateway.repository

import br.com.micropay.business.repository.ICache

import javax.inject.Named

@Named
class InMemCache implements ICache{

    Map<String,Object> registry = [:]

    @Override
    def get(String key) {
        return registry[key]
    }

    @Override
    void put(String key, Object object) {
        registry[key] = object
    }
}

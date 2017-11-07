package br.com.micropay.business.repository

interface ICache {

    def get(String key)

    void put(String key, object)
}
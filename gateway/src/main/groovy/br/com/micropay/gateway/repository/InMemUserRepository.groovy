package br.com.micropay.gateway.repository

import br.com.micropay.business.object.User
import br.com.micropay.business.repository.IUserRepository

import javax.inject.Named

@Named
class InMemUserRepository implements IUserRepository{

    Map<Long, User> data = [1L: new User(id: 1L, name: "U01")]

    @Override
    User findById(Long id) {
        return data[id]
    }
}

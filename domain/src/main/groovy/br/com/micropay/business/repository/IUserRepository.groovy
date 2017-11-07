package br.com.micropay.business.repository

import br.com.micropay.business.object.User

interface IUserRepository {

    User findById(Long id)
}
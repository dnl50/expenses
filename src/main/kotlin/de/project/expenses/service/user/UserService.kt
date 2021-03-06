package de.project.expenses.service.user

import de.project.expenses.persistence.user.User

interface UserService {

    fun getUserById(userId: String): User

    fun userExistsById(userId: String): Boolean

    fun getAuthenticatedUserId(): String

    fun getAuthenticatedUser(): User

    fun saveUser(user: User): User

}
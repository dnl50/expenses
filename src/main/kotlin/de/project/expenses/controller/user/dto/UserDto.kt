package de.project.expenses.controller.user.dto

import de.project.expenses.persistence.user.User

data class UserDto(

    val userId: String

) {

    constructor(user: User) : this(user.id)

}
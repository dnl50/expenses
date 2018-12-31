package de.project.expenses.security.auth

interface UserAuthInfoRetriever {

    fun getAuthenticatedUserId(): String

}
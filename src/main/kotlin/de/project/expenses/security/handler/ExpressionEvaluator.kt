package de.project.expenses.security.handler

import org.apache.catalina.User
import org.springframework.security.core.Authentication

interface ExpressionEvaluator {

    /**
     *
     * @param authentication The authentication instance of the authenticated user.
     * @param user The user instance of the authenticated user.
     * @param userId The ID of the user that the user wants to access.
     * @return `true`, iff the authenticated user is permitted to access the user
     *          with the given `userId`
     */
    fun hasAccessToUser(authentication: Authentication, user: User, userId: String): Boolean

}
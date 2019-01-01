package de.project.expenses.security.handler

import de.project.expenses.service.user.UserService
import org.springframework.security.access.expression.SecurityExpressionRoot
import org.springframework.security.access.expression.method.MethodSecurityExpressionOperations
import org.springframework.security.core.Authentication

class CustomMethodSecurityExpressionRoot(

    auth: Authentication,

    private val userService: UserService

): SecurityExpressionRoot(auth), MethodSecurityExpressionOperations {

    private lateinit var returnObj: Any

    private lateinit var filterObj: Any

    override fun getReturnObject(): Any {
        return this.returnObj
    }

    override fun setReturnObject(returnObject: Any?) {
        this.returnObj = returnObject!!
    }

    override fun getFilterObject(): Any {
        return this.filterObj
    }

    override fun setFilterObject(filterObject: Any?) {
        this.filterObj = filterObject!!
    }

    override fun getThis(): Any {
        return this
    }

    // ------------------------------
    // --- CUSTOM METHOD SECURITY ---
    // -------- EXPRESSIONS ---------

    fun hasAccessToUser(userId: String): Boolean {
        return userService.getAuthenticatedUserId() == userId
    }

}
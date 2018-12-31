package de.project.expenses.security.handler

import de.project.expenses.service.user.UserService
import org.aopalliance.intercept.MethodInvocation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler
import org.springframework.security.access.expression.method.MethodSecurityExpressionOperations
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component

@Component
class CustomMethodSecurityExpressionHandler @Autowired constructor(
    private val userService: UserService
) : DefaultMethodSecurityExpressionHandler() {

    override fun createSecurityExpressionRoot(authentication: Authentication?, invocation: MethodInvocation?): MethodSecurityExpressionOperations {
        val root = CustomMethodSecurityExpressionRoot(authentication!!)

        root.setPermissionEvaluator(permissionEvaluator)
        root.setTrustResolver(trustResolver)
        root.setRoleHierarchy(roleHierarchy)

        return root
    }

}
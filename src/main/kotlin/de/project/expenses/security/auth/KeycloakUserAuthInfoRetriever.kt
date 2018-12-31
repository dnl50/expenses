package de.project.expenses.security.auth

import org.keycloak.KeycloakPrincipal
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component

@Component
class KeycloakUserAuthInfoRetriever : UserAuthInfoRetriever {

    override fun getAuthenticatedUserId(): String {
        val principal = SecurityContextHolder.getContext().authentication.principal as KeycloakPrincipal<*>
        return principal.name
    }

}
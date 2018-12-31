package de.project.expenses.service.user

import de.project.expenses.persistence.user.User
import de.project.expenses.persistence.user.UserRepository
import de.project.expenses.security.auth.KeycloakUserAuthInfoRetriever
import de.project.expenses.service.user.exceptions.UserNotFoundException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class RepoUserService @Autowired constructor(
    private val userRepository: UserRepository,
    private val userAuthInfoRetriever: KeycloakUserAuthInfoRetriever
) : UserService {

    override fun getUserById(userId: String): User {
        return when(userId) {
            getAuthenticatedUserId() -> {
                userRepository.findById(userId).orElseGet {
                    userRepository.save(User(userId))
                }
            }

            else -> {
                userRepository.findById(userId).orElseThrow { UserNotFoundException(userId) }
            }
        }
    }

    override fun userExistsById(userId: String): Boolean {
        return userRepository.existsById(userId)
    }

    override fun getAuthenticatedUser(): User {
        return getUserById(getAuthenticatedUserId())
    }

    override fun getAuthenticatedUserId(): String {
        return userAuthInfoRetriever.getAuthenticatedUserId()
    }

    override fun saveUser(user: User): User {
        return userRepository.save(user)
    }

}
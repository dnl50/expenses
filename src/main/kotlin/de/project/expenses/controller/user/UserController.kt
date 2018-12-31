package de.project.expenses.controller.user

import de.project.expenses.controller.user.dto.UserDto
import de.project.expenses.service.user.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(path = ["/user"])
class UserController @Autowired constructor(

    private val userService: UserService

) {

    @PreAuthorize("hasAccessToUser(#userId) || hasRole('admin')")
    @GetMapping(path = ["/{userId}"])
    fun getUserById(@PathVariable userId: String): UserDto {
        return UserDto(userService.getUserById(userId))
    }

}
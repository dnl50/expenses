package de.project.expenses.service.user.exceptions

import java.lang.RuntimeException

class UserNotFoundException(userId: String): RuntimeException("User with ID \"$userId\" not found!")

package de.project.expenses.service.user.exceptions

import de.project.expenses.service.exceptions.NoSuchResourceException

class UserNotFoundException(userId: String): NoSuchResourceException("User with ID '$userId' not found!")

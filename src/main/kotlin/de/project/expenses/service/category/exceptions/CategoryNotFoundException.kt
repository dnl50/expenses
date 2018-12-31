package de.project.expenses.service.category.exceptions

import de.project.expenses.service.exceptions.NoSuchResourceException

class CategoryNotFoundException(id: Long) : NoSuchResourceException("Category with ID '$id' not found!")

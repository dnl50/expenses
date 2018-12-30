package de.project.expenses.service.category.exceptions

import java.lang.RuntimeException

class CategoryNotFoundException(id: Long) : RuntimeException("Category with ID \"$id\" not found!")

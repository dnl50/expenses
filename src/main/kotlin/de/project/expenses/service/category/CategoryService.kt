package de.project.expenses.service.category

import de.project.expenses.persistence.category.Category

interface CategoryService {

    fun getCategoryById(id: Long): Category

    fun categoryExistsById(id: Long): Boolean

    fun getCategoriesOfUser(userId: String): List<Category>

    fun categoryExistsForUserByName(userId: String, name: String): Boolean

}
package de.project.expenses.service.category

import de.project.expenses.persistence.category.Category
import org.springframework.data.domain.Sort

interface CategoryService {

    fun getCategoryById(id: Long): Category

    fun categoryExistsById(id: Long): Boolean

    fun getCategoriesOfUser(userId: String, sort: Sort): List<Category>

    fun categoryExistsForUserByName(userId: String, name: String): Boolean

    fun createCategoryForUser(userId: String, name: String, hexColor: String): Category

    fun saveCategory(category: Category): Category

    fun searchCategoriesForUser(userId: String, exampleCategory: Category): List<Category>

}
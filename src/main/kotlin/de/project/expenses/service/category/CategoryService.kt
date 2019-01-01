package de.project.expenses.service.category

import de.project.expenses.persistence.category.Category
import org.springframework.data.domain.Sort

interface CategoryService {

    fun getCategoryById(categoryId: Long): Category

    fun categoryExistsById(categoryId: Long): Boolean

    fun getCategoriesOfUser(userId: String, sort: Sort): List<Category>

    fun getCategoryOfUserById(userId: String, categoryId: Long): Category

    fun createCategoryForUser(userId: String, name: String, hexColor: String): Category

    fun updateCategoryForUserById(userId: String, categoryId: Long, name: String, hexColor: String): Category

    fun saveCategory(category: Category): Category

}
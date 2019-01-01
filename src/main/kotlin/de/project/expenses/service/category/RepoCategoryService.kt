package de.project.expenses.service.category

import de.project.expenses.persistence.category.Category
import de.project.expenses.persistence.category.CategoryRepository
import de.project.expenses.service.category.exceptions.CategoryNotFoundException
import de.project.expenses.service.user.RepoUserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class RepoCategoryService @Autowired constructor(

        private val userService: RepoUserService,

        private val categoryRepository: CategoryRepository

) : CategoryService {

    override fun getCategoryById(categoryId: Long): Category {
        return categoryRepository.findById(categoryId).orElseThrow()
    }

    override fun categoryExistsById(categoryId: Long): Boolean {
        return categoryRepository.existsById(categoryId)
    }

    override fun getCategoriesOfUser(userId: String, sort: Sort): List<Category> {
        val user = userService.getUserById(userId)
        return categoryRepository.findAllByUser(user, sort)
    }

    override fun getCategoryOfUserById(userId: String, categoryId: Long): Category {
        val user = userService.getUserById(userId)
        return categoryRepository.findByIdAndUser(categoryId, user).orElseThrow { CategoryNotFoundException(categoryId) }
    }

    @Transactional
    override fun createCategoryForUser(userId: String, name: String, hexColor: String): Category {
        val user = userService.getUserById(userId)
        val category = Category(user, name, hexColor)

        return categoryRepository.save(category)
    }

    @Transactional
    override fun updateCategoryForUserById(userId: String, categoryId: Long, name: String, hexColor: String): Category {
        val category = getCategoryOfUserById(userId, categoryId)
        val updatedCategory = category.copy(name = name, hexColor = hexColor)

        return categoryRepository.save(updatedCategory)
    }

    @Transactional
    override fun saveCategory(category: Category): Category {
        return categoryRepository.save(category)
    }

}
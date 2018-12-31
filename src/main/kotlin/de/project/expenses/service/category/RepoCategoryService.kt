package de.project.expenses.service.category

import de.project.expenses.persistence.category.Category
import de.project.expenses.persistence.category.CategoryRepository
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

    override fun getCategoryById(id: Long): Category {
        return categoryRepository.findById(id).orElseThrow()
    }

    override fun categoryExistsById(id: Long): Boolean {
        return categoryRepository.existsById(id)
    }

    override fun getCategoriesOfUser(userId: String, sort: Sort): List<Category> {
        val user = userService.getUserById(userId)
        return categoryRepository.findAllByUser(user, sort)
    }

    override fun categoryExistsForUserByName(userId: String, name: String): Boolean {
        val user = userService.getUserById(userId)
        return categoryRepository.existsByUserAndName(user, name)
    }

    @Transactional
    override fun createCategoryForUser(userId: String, name: String, hexColor: String): Category {
        val user = userService.getUserById(userId)
        val category = Category(user, name, hexColor)

        return categoryRepository.save(category)
    }

    @Transactional
    override fun saveCategory(category: Category): Category {
        return categoryRepository.save(category)
    }

    override fun searchCategoriesForUser(userId: String, exampleCategory: Category): List<Category> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}
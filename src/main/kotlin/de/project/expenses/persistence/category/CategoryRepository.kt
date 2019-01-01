package de.project.expenses.persistence.category

import de.project.expenses.persistence.user.User
import org.springframework.data.domain.Sort
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface CategoryRepository : JpaRepository<Category, Long> {

    fun findAllByUser(user: User, sort: Sort): List<Category>

    fun findByIdAndUser(categoryId: Long, user: User): Optional<Category>

}
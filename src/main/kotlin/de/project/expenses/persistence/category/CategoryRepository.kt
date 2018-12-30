package de.project.expenses.persistence.category

import de.project.expenses.persistence.user.User
import org.springframework.data.jpa.repository.JpaRepository

interface CategoryRepository : JpaRepository<Category, Long> {

    fun existsByUserAndName(user: User, name: String): Boolean

}
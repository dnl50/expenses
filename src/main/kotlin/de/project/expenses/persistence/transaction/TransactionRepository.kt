package de.project.expenses.persistence.transaction

import de.project.expenses.persistence.user.User
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface TransactionRepository : JpaRepository<Transaction, Long> {

    fun findAllByUser(user: User, pageable: Pageable): Page<Transaction>

    fun existsByIdAndUser(transactionId: Long, user: User): Boolean

    fun findByIdAndUser(transactionId: Long, user: User): Optional<Transaction>

}

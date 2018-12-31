package de.project.expenses.service.transaction

import de.project.expenses.persistence.transaction.Transaction
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface PageableTransactionService : TransactionService {

    fun getTransactionsOfUserPageable(userId: String, pageable: Pageable): Page<Transaction>

}
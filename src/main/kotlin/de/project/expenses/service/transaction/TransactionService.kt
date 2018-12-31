package de.project.expenses.service.transaction

import de.project.expenses.persistence.transaction.Transaction
import org.springframework.data.domain.Sort
import java.time.LocalDate

interface TransactionService {

    fun getTransactionById(transactionId: Long): Transaction

    fun transactionExistsById(transactionId: Long): Boolean

    fun getTransactionOfUserById(userId: String, transactionId: Long): Transaction

    fun transactionOfUserExistsById(userId: String, transactionId: Long): Boolean

    fun getTransactionsOfUser(userId: String, sort: Sort): List<Transaction>

    fun deleteTransaction(transaction: Transaction)

    fun deleteTransactionById(transactionId: Long)

    fun saveTransaction(transaction: Transaction): Transaction

    fun createTransactionForUser(userId: String, categoryId: Long, amount: Float, type: Transaction.Type, date: LocalDate): Transaction

}

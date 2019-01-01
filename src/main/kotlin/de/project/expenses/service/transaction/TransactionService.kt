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

    fun createTransactionForUser(userId: String, categoryId: Long, title: String, amount: Float, type: Transaction.Type, date: LocalDate): Transaction

    fun updateTransactionForUserById(userId: String, transactionId: Long, categoryId: Long, title: String, amount: Float, type: Transaction.Type, date: LocalDate): Transaction

    fun deleteTransactionOfUserById(userId: String, transactionId: Long)

    fun saveTransaction(transaction: Transaction): Transaction

    fun deleteTransaction(transaction: Transaction)

}

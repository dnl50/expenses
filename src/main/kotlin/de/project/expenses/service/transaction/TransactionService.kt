package de.project.expenses.service.transaction

import de.project.expenses.persistence.transaction.Transaction

interface TransactionService {

    fun getTransactionById(transactionId: Long): Transaction

    fun transactionExistsById(transactionId: Long): Boolean

    fun getTransactionsForUser(transactionId: Long): List<Transaction>

    fun deleteTransaction(transaction: Transaction)

    fun deleteTransactionById(transactionId: Long)

    fun save(transaction: Transaction): Transaction

}

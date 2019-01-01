package de.project.expenses.service.transaction

import de.project.expenses.persistence.transaction.Transaction
import de.project.expenses.persistence.transaction.TransactionRepository
import de.project.expenses.service.category.RepoCategoryService
import de.project.expenses.service.transaction.exceptions.TransactionNotFoundException
import de.project.expenses.service.user.RepoUserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDate

@Service
@Transactional(readOnly = true)
class RepoTransactionService @Autowired constructor(

    private val userService: RepoUserService,

    private val categoryService: RepoCategoryService,

    private val transactionRepository: TransactionRepository

) : PageableTransactionService {

    override fun transactionExistsById(transactionId: Long): Boolean {
        return transactionRepository.existsById(transactionId)
    }

    override fun getTransactionById(transactionId: Long): Transaction {
        return transactionRepository
                .findById(transactionId)
                .orElseThrow { TransactionNotFoundException(transactionId) }
    }

    override fun getTransactionOfUserById(userId: String, transactionId: Long): Transaction {
        val user = userService.getUserById(userId)
        return transactionRepository
                .findByIdAndUser(transactionId, user)
                .orElseThrow { TransactionNotFoundException(transactionId) }
    }

    override fun transactionOfUserExistsById(userId: String, transactionId: Long): Boolean {
        val user = userService.getUserById(userId)
        return transactionRepository.existsByIdAndUser(transactionId, user)
    }

    override fun getTransactionsOfUser(userId: String, sort: Sort): List<Transaction> {
        return getTransactionsOfUserPageable(userId, PageRequest.of(0, Integer.MAX_VALUE, sort))
                .content
    }

    override fun getTransactionsOfUserPageable(userId: String, pageable: Pageable): Page<Transaction> {
        val user = userService.getUserById(userId)

        return transactionRepository.findAllByUser(user, pageable)
    }

    @Transactional
    override fun updateTransactionForUserById(userId: String, transactionId: Long, categoryId: Long, title: String, amount: Float, type: Transaction.Type, date: LocalDate): Transaction {
        val category = categoryService.getCategoryOfUserById(userId, categoryId)
        val transaction = getTransactionOfUserById(userId, transactionId)
        val updatedTransaction = transaction.copy(category = category, title = title, amount = amount, type = type, date = date)

        return saveTransaction(updatedTransaction)
    }

    @Transactional
    override fun createTransactionForUser(userId: String, categoryId: Long, title: String, amount: Float, type: Transaction.Type, date: LocalDate): Transaction {
        val category = categoryService.getCategoryById(categoryId)
        val user = userService.getUserById(userId)
        val transaction = Transaction(user, category, title, amount, type, date)

        return saveTransaction(transaction)
    }

    @Transactional
    override fun deleteTransactionOfUserById(userId: String, transactionId: Long) {
        val transaction = getTransactionOfUserById(userId, transactionId)
        deleteTransaction(transaction)
    }

    @Transactional
    override fun saveTransaction(transaction: Transaction): Transaction {
        return transactionRepository.save(transaction)
    }

    @Transactional
    override fun deleteTransaction(transaction: Transaction) {
        transactionRepository.delete(transaction)
    }

}
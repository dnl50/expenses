package de.project.expenses.controller.transaction

import de.project.expenses.controller.category.dto.CategoryDto
import de.project.expenses.controller.transaction.dto.TransactionDto
import de.project.expenses.controller.transaction.dto.TransactionRequestDto
import de.project.expenses.service.transaction.PageableTransactionService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.web.SortDefault
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(path = ["/user"])
class TransactionController @Autowired constructor(

    private val transactionService: PageableTransactionService

) {

    @PreAuthorize("hasAccessToUser(#userId) || hasRole('admin')")
    @GetMapping(path = ["/{userId}/transactions"])
    fun getTransactionsOfUser(@PathVariable userId: String, @SortDefault(sort = ["date"], direction = Sort.Direction.DESC) pageable: Pageable): Page<TransactionDto> {
        return transactionService.getTransactionsOfUserPageable(userId, pageable)
                .map { TransactionDto(it, CategoryDto(it.category)) }
    }

    @PreAuthorize("hasAccessToUser(#userId) || hasRole('admin')")
    @GetMapping(path = ["/{userId}/transactions/{transactionId}"])
    fun getTransactionOfUserById(@PathVariable userId: String, @PathVariable transactionId: Long): TransactionDto {
        val transaction = transactionService.getTransactionOfUserById(userId, transactionId)

        return TransactionDto(transaction, CategoryDto(transaction.category))
    }

    @PreAuthorize("hasAccessToUser(#userId) || hasRole('admin')")
    @PutMapping(path = ["/{userId}/transactions/{transactionId}"])
    fun updateTransactionOfUserById(@PathVariable userId: String, @PathVariable transactionId: Long, @RequestBody transactionDto: TransactionRequestDto): TransactionDto {
        val updatedTransaction = transactionService.updateTransactionForUserById(userId, transactionId, transactionDto.categoryId,
                transactionDto.title ,transactionDto.amount, transactionDto.type, transactionDto.date)

        return TransactionDto(updatedTransaction, CategoryDto(updatedTransaction.category))
    }

    @PreAuthorize("hasAccessToUser(#userId) || hasRole('admin')")
    @PostMapping(path = ["/{userId}/transactions"])
    fun createTransactionForUser(@PathVariable userId: String, @RequestBody transactionDto: TransactionRequestDto): TransactionDto {
        val transaction = transactionService.createTransactionForUser(userId, transactionDto.categoryId,
                transactionDto.title, transactionDto.amount, transactionDto.type, transactionDto.date)

       return TransactionDto(transaction, CategoryDto(transaction.category))
    }

    @PreAuthorize("hasAccessToUser(#userId) || hasRole('admin')")
    @DeleteMapping(path = ["/{userId}/transactions/{transactionId}"])
    fun deleteTransactionOfUser(@PathVariable userId: String, @PathVariable transactionId: Long) {
        transactionService.deleteTransactionOfUserById(userId, transactionId)
    }

}
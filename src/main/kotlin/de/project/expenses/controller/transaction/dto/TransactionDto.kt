package de.project.expenses.controller.transaction.dto

import de.project.expenses.controller.category.dto.CategoryDto
import de.project.expenses.persistence.transaction.Transaction
import java.time.LocalDate

data class TransactionDto(

    val transactionId: Long,

    val userId: String,

    val category: CategoryDto,

    val title: String,

    val amount: Float,

    val type: Transaction.Type,

    val date: LocalDate

) {

    constructor(transaction: Transaction, categoryDto: CategoryDto) : this(transaction.id, transaction.user.id,
            categoryDto, transaction.title, transaction.amount, transaction.type, transaction.date)

}
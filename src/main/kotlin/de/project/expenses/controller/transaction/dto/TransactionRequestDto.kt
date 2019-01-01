package de.project.expenses.controller.transaction.dto

import de.project.expenses.persistence.transaction.Transaction
import java.time.LocalDate

data class TransactionRequestDto(

    val categoryId: Long,

    val title: String,

    val amount: Float,

    val type: Transaction.Type,

    val date: LocalDate

)
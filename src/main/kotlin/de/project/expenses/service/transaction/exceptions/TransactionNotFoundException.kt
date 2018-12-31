package de.project.expenses.service.transaction.exceptions

import de.project.expenses.service.exceptions.NoSuchResourceException

class TransactionNotFoundException(transactionId: Long) : NoSuchResourceException("Transaction with ID '$transactionId' not found!")

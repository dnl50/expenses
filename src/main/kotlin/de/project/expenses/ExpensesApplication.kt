package de.project.expenses

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ExpensesApplication

fun main(args: Array<String>) {
	runApplication<ExpensesApplication>(*args)
}


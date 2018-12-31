package de.project.expenses

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.web.config.EnableSpringDataWebSupport

@SpringBootApplication
@EnableSpringDataWebSupport
class ExpensesApplication

fun main(args: Array<String>) {
	runApplication<ExpensesApplication>(*args)
}

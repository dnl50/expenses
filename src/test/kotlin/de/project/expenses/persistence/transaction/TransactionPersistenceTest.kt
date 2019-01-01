package de.project.expenses.persistence.transaction

import de.project.expenses.persistence.category.CategoryRepository
import de.project.expenses.persistence.user.UserRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.jdbc.Sql
import org.springframework.test.context.junit4.SpringRunner
import java.time.LocalDate

@RunWith(SpringRunner::class)
@DataJpaTest
class TransactionPersistenceTest {

    @Autowired
    lateinit var transactionRepository: TransactionRepository

    @Autowired
    lateinit var userRepository: UserRepository

    @Autowired
    lateinit var categoryRepository: CategoryRepository

    @Test
    @Sql(scripts = [
        "classpath:de/project/expenses/persistence/Users.sql",
        "classpath:de/project/expenses/persistence/Categories.sql"
    ])
    fun save() {
        // given
        val expectedUser = userRepository.findById("User1").orElseThrow()
        val expectedCategory = categoryRepository.findById(1L).orElseThrow()
        val expectedTitle = "Expected Title"
        val expectedAmount = 13.37F
        val expectedType = Transaction.Type.EXPENSE
        val expectedDate = LocalDate.now()

        val expectedTransaction = Transaction(expectedUser, expectedCategory, expectedTitle, expectedAmount, expectedType, expectedDate)

        // when
        val savedTransaction = transactionRepository.save(expectedTransaction)
        val actualTransaction = transactionRepository.findById(expectedTransaction.id).orElseThrow()

        expectedTransaction.id = savedTransaction.id

        // then
        assertThat(actualTransaction).isEqualTo(expectedTransaction)
    }

}
package de.project.expenses.persistence.category

import de.project.expenses.persistence.user.UserRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.jdbc.Sql
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@DataJpaTest
class CategoryPersistenceTest {

    @Autowired
    lateinit var categoryRepository: CategoryRepository

    @Autowired
    lateinit var userRepository: UserRepository

    @Test
    @Sql(scripts = [
        "classpath:de/project/expenses/persistence/Users.sql"
    ])
    fun save() {
        // given
        val expectedUser = userRepository.findById("User1").orElseThrow()
        val expectedName = "name"
        val expectedHexColor = "000000"

        val expectedCategory = Category(expectedUser, expectedName, expectedHexColor)

        // when
        val savedCategory = categoryRepository.save(expectedCategory)
        val actualCategory = categoryRepository.findById(savedCategory.id).orElseThrow()

        expectedCategory.id = savedCategory.id

        // then
        assertThat(actualCategory).isEqualTo(expectedCategory)
    }

}
package de.project.expenses.persistence.user

import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@DataJpaTest
class UserPersistenceTest {

    @Autowired
    lateinit var userRepository: UserRepository

    @Test
    fun save() {

    }

}
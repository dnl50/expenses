package de.project.expenses.persistence.transaction

import de.project.expenses.persistence.category.Category
import de.project.expenses.persistence.user.User
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import java.time.LocalDate

@RunWith(MockitoJUnitRunner::class)
class TransactionTest {

    @Mock
    lateinit var category: Category

    @Mock
    lateinit var otherCategory: Category

    @Mock
    lateinit var user: User

    @Mock
    lateinit var otherUser: User

    @Before
    fun setUp() {
        // set up mocks to return different IDs
        given(user.id).willReturn("user")
        given(otherUser.id).willReturn("other-user")
    }

    @Test
    fun equalsReturnsTrueWhenSameInstance() {
        // given
        val transactionId = 1L
        val amount = 13.37F
        val type =  Transaction.Type.EXPENSE
        val date = LocalDate.now()
        val title = "Title"

        val transaction = Transaction(transactionId, user, category, title, amount, type, date)

        // when
        // then
        assertThat(transaction).isEqualTo(transaction)
    }

    @Test
    fun equalsReturnsTrueWhenSameFieldValues() {
        // given
        val transactionId = 1L
        val amount = 13.37F
        val type =  Transaction.Type.EXPENSE
        val date = LocalDate.now()
        val title = "Title"

        val transaction = Transaction(transactionId, user, category, title, amount, type, date)
        val otherTransaction = Transaction(transactionId, user, category, title, amount, type, date)

        // when
        // then
        assertThat(transaction).isEqualTo(otherTransaction)
    }

    @Test
    fun equalsReturnsFalseWhenSameFieldValuesButIdIsDifferent() {
        // given
        val transactionId = 1L
        val otherTransactionId = 2L
        val amount = 13.37F
        val type =  Transaction.Type.EXPENSE
        val date = LocalDate.now()
        val title = "Title"

        val transaction = Transaction(transactionId, user, category, title, amount, type, date)
        val otherTransaction = Transaction(otherTransactionId, user, category, title, amount, type, date)

        // when
        // then
        assertThat(transaction).isNotEqualTo(otherTransaction)
    }

    @Test
    fun equalsReturnsFalseWhenSameFieldValuesButUserIdIsDifferent() {
        // given
        val transactionId = 1L
        val amount = 13.37F
        val type =  Transaction.Type.EXPENSE
        val date = LocalDate.now()
        val title = "Title"

        val transaction = Transaction(transactionId, user, category, title, amount, type, date)
        val otherTransaction = Transaction(transactionId, otherUser, category, title, amount, type, date)

        // when
        // then
        assertThat(transaction).isNotEqualTo(otherTransaction)
    }

    @Test
    fun equalsReturnsFalseWhenSameFieldValuesButCategoryIsDifferent() {
        // given
        val transactionId = 1L
        val amount = 13.37F
        val type =  Transaction.Type.EXPENSE
        val date = LocalDate.now()
        val title = "Title"

        val transaction = Transaction(transactionId, user, category, title, amount, type, date)
        val otherTransaction = Transaction(transactionId, user, otherCategory, title, amount, type, date)

        // when
        // then
        assertThat(transaction).isNotEqualTo(otherTransaction)
    }

    @Test
    fun equalsReturnsFalseWhenSameFieldValuesButTitleIsDifferent() {
        // given
        val transactionId = 1L
        val amount = 13.37F
        val type =  Transaction.Type.EXPENSE
        val date = LocalDate.now()
        val title = "Title"
        val otherTitle = "Other Title"

        val transaction = Transaction(transactionId, user, category, title, amount, type, date)
        val otherTransaction = Transaction(transactionId, user, otherCategory, otherTitle, amount, type, date)

        // when
        // then
        assertThat(transaction).isNotEqualTo(otherTransaction)
    }

    @Test
    fun equalsReturnsFalseWhenSameFieldValuesButAmountIsDifferent() {
        // given
        val transactionId = 1L
        val amount = 13.37F
        val otherAmount = 20F
        val type =  Transaction.Type.EXPENSE
        val date = LocalDate.now()
        val title = "Title"

        val transaction = Transaction(transactionId, user, category, title, amount, type, date)
        val otherTransaction = Transaction(transactionId, user, category, title, otherAmount, type, date)

        // when
        // then
        assertThat(transaction).isNotEqualTo(otherTransaction)
    }

    @Test
    fun equalsReturnsFalseWhenSameFieldValuesButTypeIsDifferent() {
        // given
        val transactionId = 1L
        val amount = 13.37F
        val type =  Transaction.Type.EXPENSE
        val otherType = Transaction.Type.INCOME
        val date = LocalDate.now()
        val title = "Title"

        val transaction = Transaction(transactionId, user, category, title, amount, type, date)
        val otherTransaction = Transaction(transactionId, user, category, title, amount, otherType, date)

        // when
        // then
        assertThat(transaction).isNotEqualTo(otherTransaction)
    }

    @Test
    fun equalsReturnsFalseWhenSameFieldValuesButDateIsDifferent() {
        // given
        val transactionId = 1L
        val amount = 13.37F
        val type =  Transaction.Type.EXPENSE
        val date = LocalDate.now()
        val otherDate = date.plusDays(1L)
        val title = "Title"

        val transaction = Transaction(transactionId, user, category, title, amount, type, date)
        val otherTransaction = Transaction(transactionId, user, category, title, amount, type, otherDate)

        // when
        // then
        assertThat(transaction).isNotEqualTo(otherTransaction)
    }

}
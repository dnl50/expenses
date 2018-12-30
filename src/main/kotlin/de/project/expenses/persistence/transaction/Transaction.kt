package de.project.expenses.persistence.transaction

import de.project.expenses.persistence.category.Category
import de.project.expenses.persistence.user.User
import java.time.LocalDate
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "TRANSACTION")
data class Transaction(

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "ID")
        var id: Long,

        @ManyToOne(optional = false)
        @JoinColumn(name = "USER_ID")
        var user: User,

        @ManyToOne(
                optional = false
        )
        @JoinColumn(name = "CATEGORY_ID")
        var category: Category,

        @Column(name = "AMOUNT")
        var amount: Float,

        @Column(name = "TYPE")
        var type: Type,

        @Column(name = "DATE")
        var date: LocalDate

) {

    constructor(user: User, category: Category, amount: Float, type: Type, date: LocalDate) : this(0L, user, category, amount, type, date)

    override fun equals(other: Any?): Boolean {
        if(other is Transaction) {
            return Objects.equals(this.id, other.id) &&
                    Objects.equals(this.user.id, other.user.id) &&
                    Objects.equals(this.category, other.category) &&
                    Objects.equals(this.amount, other.amount) &&
                    Objects.equals(this.type, other.type) &&
                    Objects.equals(this.date, other.date)
        }

        return false
    }

    override fun toString(): String {
        return "User(id=$id, user id=${user.id}, category=$category, amount=$amount, type=$type, date=$date)"
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + user.id.hashCode()
        result = 31 * result + category.hashCode()
        result = 31 * result + amount.hashCode()
        result = 31 * result + type.hashCode()
        result = 31 * result + date.hashCode()
        return result
    }

    enum class Type {
        INCOME,
        EXPENSE;
    }

}
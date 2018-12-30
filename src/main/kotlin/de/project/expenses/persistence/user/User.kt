package de.project.expenses.persistence.user

import de.project.expenses.persistence.category.Category
import de.project.expenses.persistence.transaction.Transaction
import javax.persistence.*

@Entity
@Table(name = "EXP_USER")
data class User(

    @Id
    @Column(name = "ID")
    var id: String,

    @OneToMany(
        mappedBy = "user",
        cascade = [CascadeType.ALL],
        orphanRemoval = true
    )
    var categories: List<Category>,

    @OneToMany(
        mappedBy = "user",
        cascade = [CascadeType.ALL],
        orphanRemoval = true
    )
    var transactions: List<Transaction>

) {

    constructor(id: String) : this(id, ArrayList<Category>(), ArrayList<Transaction>())

}
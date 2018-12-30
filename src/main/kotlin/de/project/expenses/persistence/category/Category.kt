package de.project.expenses.persistence.category

import de.project.expenses.persistence.user.User
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "CATEGORY")
data class Category(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    var id: Long,

    @ManyToOne(optional = false)
    @JoinColumn(name = "USER_ID")
    var user: User,

    @Column(name = "C_NAME")
    var name: String,

    @Column(
        name = "HEX_COLOR",
        length = 6
    )
    var hexColor: String

) {

    constructor(user: User, name: String, colorHex: String) : this(0L, user, name, colorHex)

    override fun equals(other: Any?): Boolean {
        if(other is Category) {
            return Objects.equals(this.id, other.id)
                    && Objects.equals(this.user.id, other.user.id)
                    && Objects.equals(this.name, other.name)
                    && Objects.equals(this.hexColor, other.hexColor)
        }

        return false
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + user.id.hashCode()
        result = 31 * result + name.hashCode()
        result = 31 * result + hexColor.hashCode()
        return result
    }

    override fun toString(): String {
        return "Category(id=$id, user id=${user.id}, name=$name, colorHex=$hexColor)"
    }

}
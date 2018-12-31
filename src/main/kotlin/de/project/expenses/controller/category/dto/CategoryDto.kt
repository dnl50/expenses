package de.project.expenses.controller.category.dto

import de.project.expenses.persistence.category.Category

data class CategoryDto(

    val id: Long,

    val userId: String,

    val name: String,

    val hexColor: String

) {

    constructor(category: Category) : this(category.id, category.user.id, category.name, category.hexColor)

}
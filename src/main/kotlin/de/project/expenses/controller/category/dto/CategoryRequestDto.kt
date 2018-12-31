package de.project.expenses.controller.category.dto

import org.hibernate.validator.constraints.Length

data class CategoryRequestDto(

    @Length(min = 1)
    val name: String,

    @Length(min = 6, max = 6)
    val hexColor: String

)
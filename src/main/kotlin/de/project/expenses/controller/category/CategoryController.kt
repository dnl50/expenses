package de.project.expenses.controller.category

import de.project.expenses.controller.category.dto.CategoryDto
import de.project.expenses.controller.category.dto.CategoryRequestDto
import de.project.expenses.service.category.CategoryService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Sort
import org.springframework.data.web.SortDefault
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping(path = ["/user"])
class CategoryController @Autowired constructor(

    private val categoryService: CategoryService

) {

    @PreAuthorize("hasAccessToUser(#userId)")
    @GetMapping(path = ["/{userId}/categories"])
    fun getCategoriesOfUser(@PathVariable userId: String, @SortDefault(sort = ["name"], direction = Sort.Direction.ASC) sort: Sort): List<CategoryDto> {
        return categoryService.getCategoriesOfUser(userId, sort)
                .map { CategoryDto(it) }
    }

    @PreAuthorize("hasAccessToUser(#userId)")
    @PostMapping(path = ["/{userId}/categories"])
    fun createCategoryForUser(@PathVariable userId: String, @Valid @RequestBody categoryDto: CategoryRequestDto): CategoryDto {
        return CategoryDto(categoryService.createCategoryForUser(userId, categoryDto.name, categoryDto.hexColor))
    }

    @PreAuthorize("hasAccessToUser(#userId)")
    @PutMapping(path = ["/{userId}/categories/{categoryId}"])
    fun updateCategoryOfUser(@PathVariable userId: String, @PathVariable categoryId: Long, @Valid @RequestBody categoryDto: CategoryRequestDto): CategoryDto {
        return CategoryDto(categoryService.updateCategoryForUserById(userId, categoryId, categoryDto.name, categoryDto.hexColor))
    }

}
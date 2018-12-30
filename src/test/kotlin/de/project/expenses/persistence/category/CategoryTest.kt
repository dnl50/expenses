package de.project.expenses.persistence.category

import de.project.expenses.persistence.user.User
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.SoftAssertions
import org.junit.Test

class CategoryTest {

    @Test
    fun equalsReturnsTrueWhenSameInstanceAndHashCodeEquals() {
        // given
        val categoryId = 1L
        val categoryName = "name"
        val categoryHexColor = "101010"
        val userId = "user"
        val user = User(userId)

        val category = Category(categoryId, user, categoryName, categoryHexColor)

        // when
        // then
        val softly = SoftAssertions()

        softly.assertThat(category).isEqualTo(category)
        softly.assertThat(category.hashCode()).isEqualTo(category.hashCode())

        softly.assertAll()
    }

    @Test
    fun equalsReturnsTrueWhenSameIdAndNameAndColorAndUserIdAndHashCodeEquals() {
        // given
        val categoryId = 1L
        val categoryName = "name"
        val categoryHexColor = "101010"
        val userId = "user"

        val user = User(userId)
        val otherUser = User(userId)

        val category = Category(categoryId, user, categoryName, categoryHexColor)
        val otherCategory = Category(categoryId, otherUser, categoryName, categoryHexColor)

        // when
        // then
        val softly = SoftAssertions()

        softly.assertThat(category).isEqualTo(otherCategory)
        softly.assertThat(category.hashCode()).isEqualTo(otherCategory.hashCode())

        softly.assertAll()
    }

    @Test
    fun equalsReturnsFalseWhenSameNameAndColorAndUserIdButDifferentId() {
        // given
        val categoryId = 1L
        val otherCategoryId = 2L
        val categoryName = "name"
        val categoryHexColor = "101010"
        val userId = "user"

        val user = User(userId)

        val category = Category(categoryId, user, categoryName, categoryHexColor)
        val otherCategory = Category(otherCategoryId, user, categoryName, categoryHexColor)

        // when
        // then
        assertThat(category).isNotEqualTo(otherCategory)
    }

    @Test
    fun equalsReturnsFalseWhenSameIdAndNameAndColorButDifferentUserId() {
        // given
        val categoryId = 1L
        val categoryName = "name"
        val categoryHexColor = "101010"
        val userId = "user"
        val otherUserId = "other-user"

        val user = User(userId)
        val otherUser = User(otherUserId)

        val category = Category(categoryId, user, categoryName, categoryHexColor)
        val otherCategory = Category(categoryId, otherUser, categoryName, categoryHexColor)

        // when
        // then
        assertThat(category).isNotEqualTo(otherCategory)
    }

    @Test
    fun equalsReturnsFalseWhenSameIdAndColorAndUserIdButDifferentName() {
        // given
        val categoryId = 1L
        val categoryName = "name"
        val otherCategoryName = "other-name"
        val categoryHexColor = "101010"
        val userId = "user"

        val user = User(userId)

        val category = Category(categoryId, user, categoryName, categoryHexColor)
        val otherCategory = Category(categoryId, user, otherCategoryName, categoryHexColor)

        // when
        // then
        assertThat(category).isNotEqualTo(otherCategory)
    }

    @Test
    fun equalsReturnsFalseWhenSameIdAndNameAndUserIdButDifferentColor() {
        // given
        val categoryId = 1L
        val categoryName = "name"
        val categoryHexColor = "101010"
        val otherHexColor = "110011"
        val userId = "user"

        val user = User(userId)

        val category = Category(categoryId, user, categoryName, categoryHexColor)
        val otherCategory = Category(categoryId, user, categoryName, otherHexColor)

        // when
        // then
        assertThat(category).isNotEqualTo(otherCategory)
    }

}
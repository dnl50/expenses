package de.project.expenses.persistence.user

import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, String>

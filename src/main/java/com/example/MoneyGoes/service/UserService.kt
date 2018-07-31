package com.example.MoneyGoes.service

import com.vaadin.spring.annotation.SpringComponent
import org.springframework.data.jpa.repository.JpaRepository
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
data class User(
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Id
        val id: Long = 0,
        val firstName: String = "",
        val lastName: String = "",
        val money: Float = 0f
)

@SpringComponent
interface UserRepository: JpaRepository<User, Long>

@SpringComponent
class UserService {

}
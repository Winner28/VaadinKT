package com.example.TODO

import com.vaadin.spring.annotation.SpringComponent
import org.springframework.data.jpa.repository.JpaRepository
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.transaction.Transactional

@Entity
data class Todo(
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Id
        val id: Long = 0,
        val done: Boolean = false,
        val text: String = ""
)

@SpringComponent
interface TodoRepository: JpaRepository<Todo, Long> {

    @Transactional
    fun deleteByDone(done: Boolean)
}
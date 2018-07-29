package com.example

import com.vaadin.data.Binder
import com.vaadin.spring.annotation.SpringComponent
import com.vaadin.ui.*
import com.vaadin.ui.themes.ValoTheme
import org.springframework.beans.factory.annotation.Autowired
import javax.annotation.PostConstruct

@SpringComponent
class TodoLayout: VerticalLayout() {

    @Autowired
    lateinit var repository: TodoRepository

    @PostConstruct
    fun init() {
        update()
    }

    private fun setTodos(findAll: List<Todo>) {
        removeAllComponents()
        findAll.forEach { todo ->
            addComponent(TodoItemLayout(todo))
        }
    }

    private fun update() {
        setTodos(repository.findAll())
    }

    fun add(todo: Todo) {
        repository.save(todo)
        update()
    }

    fun deleteCompleted() {
        repository.deleteByDone(true)
        update()
    }
}

class TodoItemLayout(val todo: Todo): HorizontalLayout() {

    val done = CheckBox()
    val text = TextField()

    init {
        text.addStyleName(ValoTheme.TEXTFIELD_BORDERLESS)

        addComponents(done)
        addComponentsAndExpand(text)
        defaultComponentAlignment = Alignment.MIDDLE_LEFT

        val binder = Binder<Todo>(Todo::class.java)
        binder.bindInstanceFields(this)
        binder.bean = todo
    }

}

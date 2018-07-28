package com.example

import com.vaadin.server.VaadinRequest
import com.vaadin.spring.annotation.SpringUI
import com.vaadin.ui.*
import org.springframework.beans.factory.annotation.Autowired

@SpringUI
class TodoUI: UI() {

    val root by lazy {
        VerticalLayout()
    }


    @Autowired
    lateinit var todoLayout: TodoLayout

    init {
        content = root
    }

    override fun init(rq: VaadinRequest?) {
        addHeader()
        addForm()
        addTodoList()
        addDeleteButton()
    }

    private fun addHeader() = root.addComponent(Label("TODOs"))

    private fun addForm() {
        root.addComponent(HorizontalLayout().apply {
            addComponents(Button("Add"), TextField())
        })
    }

    private fun addTodoList() = root.addComponent(todoLayout)

    private fun addDeleteButton() = root.addComponent(Button("Delete"))

}

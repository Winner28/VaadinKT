package com.example

import com.vaadin.server.VaadinRequest
import com.vaadin.spring.annotation.SpringUI
import com.vaadin.ui.Label
import com.vaadin.ui.UI
import com.vaadin.ui.VerticalLayout
import org.springframework.beans.factory.annotation.Autowired

@SpringUI
class TodoUI: UI() {

    val rootLayout by lazy {
        VerticalLayout()
    }


    @Autowired
    lateinit var todoLayout: TodoLayout

    init {
        content = rootLayout
    }

    override fun init(rq: VaadinRequest?) {
        addHeader()
    }

    private fun addHeader() = rootLayout.addComponent(Label("TODOs"))

    private fun addForm() {

    }


}

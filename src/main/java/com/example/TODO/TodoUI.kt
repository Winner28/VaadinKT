package com.example.TODO

import com.vaadin.icons.VaadinIcons
import com.vaadin.server.VaadinRequest
import com.vaadin.spring.annotation.SpringUI
import com.vaadin.ui.*
import com.vaadin.ui.themes.ValoTheme
import org.springframework.beans.factory.annotation.Autowired

//@SpringUI
class TodoUI: UI() {

    val root by lazy {
        VerticalLayout()
    }


    @Autowired
    lateinit var todoLayout: TodoLayout

    init {
        root.defaultComponentAlignment = Alignment.MIDDLE_CENTER
        content = root
    }

    override fun init(rq: VaadinRequest?) {
        addHeader()
        addForm()
        addTodoList()
        addDeleteButton()
    }

    private fun addHeader() = root.addComponent(Label("TODOs").apply {
        styleName = ValoTheme.LABEL_H1
    })

    private fun addForm() = root.addComponent(HorizontalLayout().apply {
        setWidth("80%")
        val textField = TextField()
        addComponentsAndExpand(textField)
        addComponent(Button().apply {
            styleName = ValoTheme.BUTTON_PRIMARY
            icon = VaadinIcons.PLUS
            addClickListener {
                todoLayout.add(Todo(text = textField.value))
                textField.clear()
                textField.focus()
            }
        })
    })


    private fun addTodoList() = root.addComponent(todoLayout.apply {
        setWidth("80%")
    })

    private fun addDeleteButton() = root.addComponent(Button("Delete completed") { click-> todoLayout.deleteCompleted() })

}

fun HorizontalLayout.withComponents(vararg components: Component): HorizontalLayout = apply {
    this.addComponents(*components)
}

fun HorizontalLayout.withExpandComponents(vararg components: Component): HorizontalLayout = apply {
    this.addComponentsAndExpand(*components)
}
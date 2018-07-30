package com.example

import com.vaadin.server.Page
import com.vaadin.server.VaadinRequest
import com.vaadin.spring.annotation.SpringUI
import com.vaadin.ui.*

@SpringUI
class VaadinMain: UI() {

    override fun init(rq: VaadinRequest?) {
        Page.getCurrent().setTitle("Admin Page")
        val root = AbsoluteLayout().apply {
            setSizeFull()
        }
        content = root
        root.addComponent(createApp())

    }

    private fun createApp(): Component {
        val mainContent = VerticalLayout().apply {
            setSizeFull()
        }
        val menuContent = VerticalLayout().apply {
            setWidthUndefined()
            setHeight("100%")
            isSpacing = true
            defaultComponentAlignment = Alignment.MIDDLE_CENTER
        }
        val app = HorizontalLayout().apply {
            setSizeFull()
            setMargin(false)
            isSpacing = false
            addComponent(menuContent)
            addComponentsAndExpand(mainContent)
        }
        return app
    }

}
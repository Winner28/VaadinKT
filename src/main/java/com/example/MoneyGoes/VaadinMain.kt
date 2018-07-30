package com.example.MoneyGoes

import com.example.MoneyGoes.views.MoneyView
import com.vaadin.navigator.Navigator
import com.vaadin.navigator.View
import com.vaadin.server.Page
import com.vaadin.server.VaadinRequest
import com.vaadin.spring.annotation.SpringUI
import com.vaadin.ui.*
import com.vaadin.ui.themes.ValoTheme
import java.lang.annotation.Inherited
import kotlin.reflect.KClass
import kotlin.reflect.full.createInstance

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
        val navigator = Navigator(this, mainContent)
        navigator.addView(MoneyView::class)

        val menuContent = VerticalLayout().apply {
            setWidthUndefined()
            setHeight("100%")
            isSpacing = true
            defaultComponentAlignment = Alignment.MIDDLE_LEFT
            addComponent(Label("Menu").apply {
                styleName = ValoTheme.LABEL_H1
            })
            addComponent(Button("Money") { _ ->
                navigator.navigateTo(MoneyView::class)
            })
            addComponent(Button("MoneyGoes"))

            addComponentsAndExpand(VerticalLayout())
            addComponent(Button("About"))
            addComponent(Button("Leave"))
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

    fun Navigator.addView(component: KClass<out View>) = addView(getNavigationState(component), component.createInstance())
}

fun Navigator.getNavigationState(component: KClass<out View>): String =
        component.java.getAnnotation(NavigableView::class.java)?.state
                ?: throw Throwable("View should be annotated with NavigableView")



fun Navigator.navigateTo(component: KClass<out View>, vararg params: String?) {
    navigateTo(arrayOf(getNavigationState(component), *params.filterNotNull().toTypedArray()).joinToString("/"))
}

@Inherited
annotation class NavigableView(val state: String)
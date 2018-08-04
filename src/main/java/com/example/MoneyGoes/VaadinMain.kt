package com.example.MoneyGoes

import com.example.MoneyGoes.views.AccountView
import com.example.MoneyGoes.views.MoneyView
import com.vaadin.annotations.Theme
import com.vaadin.navigator.Navigator
import com.vaadin.navigator.PushStateNavigation
import com.vaadin.navigator.View
import com.vaadin.navigator.ViewChangeListener
import com.vaadin.server.Page
import com.vaadin.server.VaadinRequest
import com.vaadin.spring.annotation.SpringUI
import com.vaadin.ui.*
import java.lang.annotation.Inherited
import kotlin.reflect.KClass
import kotlin.reflect.full.createInstance
import com.vaadin.ui.HorizontalLayout



@SpringUI
@PushStateNavigation
@Theme("darktheme")
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
        val nav = Navigator(this, mainContent).apply {
            addView("", RedirectTo("profile"))
            fillWithViews()
        }

        val menuContent = VerticalLayout().apply {
            setWidthUndefined()
            setHeight("100%")
            isSpacing = true
            defaultComponentAlignment = Alignment.TOP_LEFT
            addComponent(Button("Profile") { _ ->
                nav.navigateTo(AccountView::class)
            })
            addComponent(Button("Money") { _ ->
                nav.navigateTo(MoneyView::class)
            })
            val space = VerticalLayout()
            addComponentsAndExpand(space)
            addComponent(Button("Leave").apply {
            })
            setExpandRatio(space, 1f)
            addStyleName("menuBar")
        }

        val app = HorizontalLayout().apply {
            setSizeFull()
            setMargin(false)
            isSpacing = false
            addComponent(menuContent)
            addComponentsAndExpand(mainContent)
        }
        navigator = nav
        return app
    }

    private fun Navigator.addView(component: KClass<out View>) {
        addView(getNavigationState(component), component.createInstance())
    }

    private fun Navigator.fillWithViews() {
        addView(MoneyView::class)
        addView(AccountView::class)
    }

}

fun getNavigationState(component: KClass<out View>): String =
        component.java.getAnnotation(NavigableView::class.java)?.state
                ?: throw Throwable("View should be annotated with NavigableView")

fun Navigator.navigateTo(component: KClass<out View>, vararg params: String?) {
    navigateTo(arrayOf(getNavigationState(component), *params.filterNotNull().toTypedArray()).joinToString("/"))
}




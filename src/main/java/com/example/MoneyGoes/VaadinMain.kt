package com.example.MoneyGoes

import com.example.MoneyGoes.views.AccountView
import com.example.MoneyGoes.views.MoneyView
import com.vaadin.annotations.Theme
import com.vaadin.icons.VaadinIcons
import com.vaadin.navigator.Navigator
import com.vaadin.navigator.View
import com.vaadin.navigator.ViewChangeListener
import com.vaadin.server.Page
import com.vaadin.server.VaadinRequest
import com.vaadin.spring.annotation.SpringUI
import com.vaadin.ui.*
import com.vaadin.ui.themes.ValoTheme
import java.lang.annotation.Inherited
import java.util.logging.Logger
import kotlin.reflect.KClass
import kotlin.reflect.full.createInstance
import com.vaadin.ui.CssLayout



@SpringUI
@Theme("moneyGoes")
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
        val nav = Navigator(this, mainContent).apply {  }
        nav.fillWithViews()

        val menu = CssLayout().apply {
            addStyleName(ValoTheme.MENU_ROOT)
            addComponent(Label("Menu").apply {
                styleName = ValoTheme.LABEL_H1
            })
            addComponent(Button("Money") { _ ->
                nav.navigateTo(MoneyView::class)
            })
            addComponent(Button("Profile") {_ ->
                nav.navigateTo(AccountView::class)
            })
            addComponent(Button("MoneyGoes"))

            addComponent(Button("About"))
            addComponent(Button("Leave"))
        }
        val app = HorizontalLayout().apply {
            setSizeFull()
            setMargin(false)
            isSpacing = false
            addComponent(menu)
            addComponentsAndExpand(mainContent)
        }
        navigator = nav
        return app
    }

}

private fun Navigator.addView(component: KClass<out View>) {
    addView(getNavigationState(component), component.createInstance())
}

private fun Navigator.fillWithViews() {
    addView(MoneyView::class)
    addView(AccountView::class)
    addView("", RedirectTo(getNavigationState(MoneyView::class)))
}

fun Navigator.getNavigationState(component: KClass<out View>): String =
        component.java.getAnnotation(NavigableView::class.java)?.state
                ?: throw Throwable("View should be annotated with NavigableView")

fun Navigator.navigateTo(component: KClass<out View>, vararg params: String?) {
    navigateTo(arrayOf(getNavigationState(component), *params.filterNotNull().toTypedArray()).joinToString("/"))
}

@Inherited
annotation class NavigableView(val state: String)

class RedirectTo(val state: String): View, CustomComponent() {
    override fun enter(p0: ViewChangeListener.ViewChangeEvent?) {
        ui.navigator.navigateTo(state)
    }
}

open class VerticalView: VerticalLayout(), View {
    override fun enter(p0: ViewChangeListener.ViewChangeEvent?) {
        println(this)
    }
}
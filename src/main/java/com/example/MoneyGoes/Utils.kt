package com.example.MoneyGoes

import com.vaadin.navigator.View
import com.vaadin.navigator.ViewChangeListener
import com.vaadin.ui.CustomComponent
import com.vaadin.ui.VerticalLayout
import java.lang.annotation.Inherited
import java.util.logging.Level
import java.util.logging.Logger

fun Logger.info(any: Any) = this.log(Level.INFO, any.toString())

val logger = Logger.getLogger("UI")

open class VerticalView: VerticalLayout(), View {
    override fun enter(p0: ViewChangeListener.ViewChangeEvent?) {
        logger.info(this)
    }
}

class RedirectTo(val state: String): View, CustomComponent() {
    override fun enter(p0: ViewChangeListener.ViewChangeEvent?) {
        ui.navigator.navigateTo(state)
    }
}

@Inherited
annotation class NavigableView(val state: String)
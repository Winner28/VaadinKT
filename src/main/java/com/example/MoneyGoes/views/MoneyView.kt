package com.example.MoneyGoes.views

import com.example.MoneyGoes.NavigableView
import com.vaadin.navigator.View
import com.vaadin.navigator.ViewChangeListener
import com.vaadin.ui.Label
import com.vaadin.ui.VerticalLayout
import com.vaadin.ui.themes.ValoTheme
import java.util.logging.Logger

@NavigableView("mymoney")
class MoneyView: VerticalLayout(), View {

    val logger = Logger.getLogger("UI")

    init {
        setMargin(false)
        setSizeFull()
        addComponent(Label("Your money: 10_000").apply {
            styleName = ValoTheme.LABEL_H1
        })
    }


    override fun enter(p0: ViewChangeListener.ViewChangeEvent?) {
        logger.info("Entering in MoneyView")
    }
}

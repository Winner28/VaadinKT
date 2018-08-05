package com.example.MoneyGoes.views

import com.example.MoneyGoes.NavigableView
import com.example.MoneyGoes.VerticalView
import com.vaadin.ui.Alignment
import com.vaadin.ui.Label
import com.vaadin.ui.themes.ValoTheme

@NavigableView("profile")
class AccountView: VerticalView() {
    
    init {
        setMargin(false)
        setSizeFull()
        addComponent(Label("Your money: 10_000").apply {
            styleName = ValoTheme.LABEL_H1
            defaultComponentAlignment = Alignment.MIDDLE_CENTER
        })
    }

}
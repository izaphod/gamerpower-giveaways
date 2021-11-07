package com.nimadugarov.gamerpowergiveaways.ui.extensions

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.TextView
import com.google.android.material.appbar.MaterialToolbar
import com.nimadugarov.gamerpowergiveaways.R

/**
 * @MaterialToolbar с центрированием заголовка
 * Для корректной работы заголовок должен иметь id = R.id.toolbar_title_text
 * Все View, находящиеся в одном контейнере с заголовком будут центроваться
 */
class CenteredTitleToolbar : MaterialToolbar {

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attributeSet: AttributeSet?) : super(
        context,
        attributeSet,
        R.attr.toolbarStyle
    )

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        super.onLayout(changed, l, t, r, b)
        val titleText = findViewById<TextView>(R.id.toolbar_title_text)

        titleText?.apply {
            val parent = titleText.parent
            if (parent == this@CenteredTitleToolbar) {
                forceTitleCenter(titleText)
            } else {
                forceTitleCenter(parent as View)
            }
        }
    }

    private fun forceTitleCenter(view: View) {
        val toolbarWidth = measuredWidth
        val relativeLayoutWidth = view.measuredWidth
        val leftPadding = view.left
        val rightPadding = maxOf(0, toolbarWidth - leftPadding - relativeLayoutWidth)
        val newLeftPadding = maxOf(0, rightPadding - leftPadding)
        val newRightPadding = maxOf(0, leftPadding - rightPadding)
        view.setPadding(newLeftPadding, view.paddingTop, newRightPadding, view.paddingBottom)
    }
}
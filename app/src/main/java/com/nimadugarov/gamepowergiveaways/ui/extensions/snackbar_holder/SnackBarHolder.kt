package com.nimadugarov.gamepowergiveaways.ui.extensions.snackbar_holder

import android.text.TextUtils
import android.view.View
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import com.google.android.material.snackbar.Snackbar
import java.lang.ref.WeakReference

/**
 * Управляет показом всплывающих сообщений
 */
abstract class SnackBarHolder(
    lifecycleOwner: LifecycleOwner,
    view: View
) {
    private var weakReferenceView = WeakReference(view)
    private var currentSnackBar: Snackbar? = null

    init {
        lifecycleOwner.lifecycle.addObserver(LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_DESTROY) {
                hideMessage()
            }
        })
    }

    protected abstract fun createSnackBar(
        view: View,
        message: String,
        actionText: String?,
        duration: Int,
        actionListener: SnackBarActionListener?,
        snackBarCallback: Snackbar.Callback
    ): Snackbar

    /**
     * Скрывает сообщение
     */
    fun hideMessage() {
        currentSnackBar?.dismiss()
    }

    /**
     * Показывает сообщение с короткой длительностью показа
     *
     * @param message        текст сообщения
     * @param actionText     текст для кнопки действия
     * @param actionListener слушатель для действия
     */
    fun showShortDurationMessage(
        message: String,
        actionText: String? = null,
        actionListener: SnackBarActionListener? = null
    ) {
        showSnackBar(message, Snackbar.LENGTH_SHORT, actionText, actionListener)
    }

    /**
     * Показывает сообщение с долговременной длительностью показа
     *
     * @param message        текст сообщения
     * @param actionText     текст для кнопки действия
     * @param actionListener слушатель для действия
     */
    fun showLongDurationMessage(
        message: String,
        actionText: String? = null,
        actionListener: SnackBarActionListener? = null
    ) {
        showSnackBar(message, Snackbar.LENGTH_LONG, actionText, actionListener)
    }

    /**
     * Показывает сообщение с неограниченной длительностью показа
     *
     * @param message        текст сообщения
     * @param actionText     строковой идентификатор для кнопки действия
     * @param actionListener слушатель для действия
     */
    fun showIndefiniteDurationMessage(
        message: String,
        actionText: String? = null,
        actionListener: SnackBarActionListener? = null
    ) {
        showSnackBar(message, Snackbar.LENGTH_INDEFINITE, actionText, actionListener)
    }

    /**
     * Задать @View в виде якоря
     */
    fun setAnchorView(view: View) {
        currentSnackBar?.anchorView = view
    }

    /**
     * Показывает SnackBar
     *
     * @param message        текст сообщения
     * @param actionText     текст для кнопки действия
     * @param actionListener слушатель для действия
     * @param duration       продолжительность показа сообщения
     */
    private fun showSnackBar(
        message: String,
        duration: Int,
        actionText: String? = null,
        actionListener: SnackBarActionListener? = null
    ) {
        val view = weakReferenceView.get()
        if (view == null || TextUtils.isEmpty(message)) {
            return
        }
        currentSnackBar = createSnackBar(
            view, message, actionText, duration, actionListener, getSnackBarCallback()
        )
        currentSnackBar!!.show()
    }

    private fun getSnackBarCallback(): Snackbar.Callback {
        return object : Snackbar.Callback() {
            override fun onShown(sb: Snackbar) {
                currentSnackBar = sb
            }

            override fun onDismissed(transientBottomBar: Snackbar, event: Int) {
                currentSnackBar = null
            }
        }
    }
}
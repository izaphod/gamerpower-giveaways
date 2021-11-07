package com.nimadugarov.gamerpowergiveaways.ui.extensions.snackbar_holder

import android.view.View
import androidx.lifecycle.LifecycleOwner
import com.google.android.material.snackbar.Snackbar

/**
 * Держатель [Snackbar] с разметкой по умолчанию
 */
class DefaultSnackBarHolder(
    lifecycleOwner: LifecycleOwner,
    view: View
) : SnackBarHolder(lifecycleOwner, view) {
    override fun createSnackBar(
        view: View,
        message: String,
        actionText: String?,
        duration: Int,
        actionListener: SnackBarActionListener?,
        snackBarCallback: Snackbar.Callback
    ): Snackbar {
        return Snackbar.make(view, message, duration)
            .addCallback(snackBarCallback)
            .setAction(actionText) {
                actionListener?.onActionClick()
                hideMessage()
            }
    }
}
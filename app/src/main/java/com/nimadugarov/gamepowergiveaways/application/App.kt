package com.nimadugarov.gamepowergiveaways.application

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import com.nimadugarov.extensions.image_loader.GlideLoaderCreator
import com.nimadugarov.extensions.image_loader.ImageLoader

/**
 * Класс приложения
 */
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        context = this
        ImageLoader.loaderCreator = GlideLoaderCreator()
    }

    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context
    }
}
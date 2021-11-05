package com.nimadugarov.gamepowergiveaways.application

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import com.nimadugarov.extensions.image_loader.GlideLoaderCreator
import com.nimadugarov.extensions.image_loader.ImageLoader
import com.nimadugarov.gamepowergiveaways.di.giveawayModelModule
import com.nimadugarov.gamepowergiveaways.di.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

/**
 * Класс приложения
 */
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        context = this
        ImageLoader.loaderCreator = GlideLoaderCreator()
        startKoin {
            androidContext(this@App)
            modules(listOf(networkModule, giveawayModelModule))
        }
    }

    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context
    }
}
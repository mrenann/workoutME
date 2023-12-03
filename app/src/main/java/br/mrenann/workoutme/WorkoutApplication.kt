package br.mrenann.workoutme

import android.app.Application
import br.mrenann.workoutme.di.stepModelModule
import org.kodein.di.DI
import org.kodein.di.DIAware
import org.kodein.di.bind
import org.kodein.di.conf.ConfigurableDI
import org.kodein.di.provider

class WorkoutApplication : Application(), DIAware {

    private val appModule = DI.Module(name = "application") {
        bind<Application>() with provider {
            this@WorkoutApplication
        }
    }

    override val di = ConfigurableDI(mutable = true).apply {
        addImport(appModule)
        loadAppModules()
    }

}

internal fun ConfigurableDI.loadAppModules() {
    addImport(stepModelModule)
}
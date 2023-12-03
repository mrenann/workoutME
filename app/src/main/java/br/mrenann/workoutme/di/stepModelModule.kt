package br.mrenann.workoutme.di

import br.mrenann.workoutme.view.steps.login.LoginScreenStepModel
import org.kodein.di.DI
import org.kodein.di.bindProvider

val stepModelModule = DI.Module(name = "viewModelModule") {
    bindProvider { LoginScreenStepModel() }
}
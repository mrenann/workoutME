package br.mrenann.workoutme.di

import br.mrenann.workoutme.domain.FirebaseRepositoryRemote
import br.mrenann.workoutme.view.steps.home.tabs.homeTab.HomeScreenStepModel
import br.mrenann.workoutme.view.steps.login.LoginScreenStepModel
import org.kodein.di.DI
import org.kodein.di.bindProvider
import org.kodein.di.instance

val stepModelModule = DI.Module(name = "viewModelModule") {
    bindProvider { LoginScreenStepModel() }
    bindProvider { HomeScreenStepModel(instance<FirebaseRepositoryRemote>()) }
}
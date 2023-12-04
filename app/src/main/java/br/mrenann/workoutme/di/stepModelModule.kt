package br.mrenann.workoutme.di

import br.mrenann.workoutme.domain.FirebaseRepositoryRemote
import br.mrenann.workoutme.view.steps.home.tabs.exercisesTab.listScreen.ExerciseTabFirstStepModel
import br.mrenann.workoutme.view.steps.home.tabs.trainingSheetTab.listScreen.TrainingSheetScreenStepModel
import br.mrenann.workoutme.view.steps.home.tabs.exercisesTab.updateExerciseScreen.UpdateExerciseStepModel
import br.mrenann.workoutme.view.steps.home.tabs.trainingSheetTab.updateSheetScreen.UpdateSheetStepModel
import br.mrenann.workoutme.view.steps.login.LoginScreenStepModel
import org.kodein.di.DI
import org.kodein.di.bindProvider
import org.kodein.di.instance

val stepModelModule = DI.Module(name = "viewModelModule") {
    bindProvider { LoginScreenStepModel() }
    bindProvider { TrainingSheetScreenStepModel(instance<FirebaseRepositoryRemote>()) }
    bindProvider { ExerciseTabFirstStepModel(instance<FirebaseRepositoryRemote>()) }
    bindProvider { UpdateSheetStepModel() }
    bindProvider { UpdateExerciseStepModel() }
}
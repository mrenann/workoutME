package br.mrenann.workoutme.domain

import br.mrenann.workoutme.domain.model.ExerciseUI
import br.mrenann.workoutme.domain.model.TrainingSheetUI

interface FirebaseRepositoryRemote {

    suspend fun getAllTrainingSheet(): List<TrainingSheetUI?>
    suspend fun getExercises(): List<ExerciseUI?>
}
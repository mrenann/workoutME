package br.mrenann.workoutme.data.mapper

import br.mrenann.workoutme.data.models.ExerciseUIFirebase
import br.mrenann.workoutme.data.models.TrainingSheetUIFirebase
import br.mrenann.workoutme.domain.model.ExerciseUI
import br.mrenann.workoutme.domain.model.TrainingSheetUI

internal object ResponseItemMapper {

    fun mapToData(sheetUIFirebase: TrainingSheetUIFirebase): TrainingSheetUI = with(sheetUIFirebase) {
        TrainingSheetUI(
            id = id,
            nome = nome,
            descricao = descricao,
            date = date,
            userId = userId,
            creatorId = creatorId,
        )
    }

    fun mapToData(sheetUI: TrainingSheetUI): TrainingSheetUIFirebase = with(sheetUI) {
        TrainingSheetUIFirebase(
            id = id,
            nome = nome,
            descricao = descricao,
            date = date,
            userId = userId,
            creatorId = creatorId,
        )
    }

    fun mapToData(exerciseUI: ExerciseUI): ExerciseUIFirebase = with(exerciseUI) {
        ExerciseUIFirebase(
            id = id,
            nome = nome,
            observacoes = observacoes,
            imagem = imagem
        )
    }

    fun mapToData(exerciseUIFirebase: ExerciseUIFirebase): ExerciseUI = with(exerciseUIFirebase) {
        ExerciseUI(
            id = id,
            nome = nome,
            observacoes = observacoes,
            imagem = imagem
        )
    }

}
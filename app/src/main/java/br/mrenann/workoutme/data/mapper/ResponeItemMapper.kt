package br.mrenann.workoutme.data.mapper

import br.mrenann.workoutme.data.models.TrainingSheetUIFirebase
import br.mrenann.workoutme.domain.model.TrainingSheetUI

internal object ResponseItemMapper {

    fun mapToData(sheetUIFirebase: TrainingSheetUIFirebase): TrainingSheetUI = with(sheetUIFirebase) {
        TrainingSheetUI(
            id = id,
            nome = nome,
            descricao = descricao,
            date = date,
            userId = userId,
            createrId = createrId,
        )
    }

    fun mapToData(sheetUI: TrainingSheetUI): TrainingSheetUIFirebase = with(sheetUI) {
        TrainingSheetUIFirebase(
            id = id,
            nome = nome,
            descricao = descricao,
            date = date,
            userId = userId,
            createrId = createrId,
        )
    }

}
package br.mrenann.workoutme.domain.model

data class TrainingSheetUI(
    var id: String?,
    var nome: String? = "",
    var descricao: String? = "",
    var userId: String? = null,
    var creatorId: String? = null,
    var date: String? ="",
)
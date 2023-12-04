package br.mrenann.workoutme.domain.model

import android.net.Uri

data class ExerciseUI(
    var id: String?,
    var nome: String? = "",
    var observacoes: String? = "",
    var imagem: String = "",
)
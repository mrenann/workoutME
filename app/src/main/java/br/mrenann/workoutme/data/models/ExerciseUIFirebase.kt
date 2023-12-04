package br.mrenann.workoutme.data.models

import android.net.Uri
import com.google.firebase.firestore.Exclude

data class ExerciseUIFirebase(
    @Exclude
    var id: String? = null,
    var nome: String? = "",
    var observacoes: String? = "",
    var imagem: String = "",
)
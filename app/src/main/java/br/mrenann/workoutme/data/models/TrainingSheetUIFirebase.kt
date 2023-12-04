package br.mrenann.workoutme.data.models

import com.google.firebase.firestore.Exclude
import com.google.firebase.firestore.PropertyName

data class TrainingSheetUIFirebase(
    @Exclude
    var id: String? = null,
    var nome: String? = "",
    var descricao: String? = "",
    @get:PropertyName("user_id")
    @set:PropertyName("user_id")
    var userId: String? = null,
    @get:PropertyName("creater_id")
    @set:PropertyName("creater_id")
    var createrId: String? = null,
    var date: String? ="",
)
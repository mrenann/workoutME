package br.mrenann.workoutme.view.steps.home.tabs.trainingSheetTab.updateSheetScreen

import br.mrenann.workoutme.data.mapper.ResponseItemMapper
import br.mrenann.workoutme.domain.model.TrainingSheetUI
import br.mrenann.workoutme.domain.model.UserUI
import br.mrenann.workoutme.utils.uiState.UiStateLogin
import cafe.adriel.voyager.core.model.StateScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch

class UpdateSheetStepModel : StateScreenModel<UiStateLogin>(UiStateLogin.Loading) {

    private val auth: FirebaseAuth = Firebase.auth

    fun createTrainingSheet() {
        val userId = auth.currentUser?.uid
        val user = TrainingSheetUI(
            id = null,
            nome = "Nome",
            descricao = "Descricao",
            userId = userId.toString(),
            creatorId = userId.toString(),
            date = "Datee"
        )
        val userMapped = ResponseItemMapper.mapToData(user)

        FirebaseFirestore
            .getInstance()
            .collection("sheet")
            .add(userMapped)
    }
}
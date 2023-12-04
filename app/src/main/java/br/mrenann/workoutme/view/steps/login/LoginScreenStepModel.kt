package br.mrenann.workoutme.view.steps.login

import br.mrenann.workoutme.domain.model.UserUI
import br.mrenann.workoutme.utils.uiState.UiStateLogin
import cafe.adriel.voyager.core.model.StateScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch

class LoginScreenStepModel : StateScreenModel<UiStateLogin>(UiStateLogin.Loading) {

    private val auth: FirebaseAuth = Firebase.auth

    fun signInWithEmailAndPassword(email: String, password: String) = screenModelScope.launch {
        try {
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        mutableState.value = UiStateLogin.SuccessToHome
                    } else {
                        val error = Exception()
                        mutableState.value = UiStateLogin.Error(error)
                    }
                }
        } catch (error: Exception) {
            mutableState.value = UiStateLogin.Error(error)
        }
    }

    fun createUserWithEmailAndPassword(email: String, password: String, isPersonal: Boolean) {
        try {
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val displayName = task.result?.user?.email?.split('@')?.get(0)
                        createUser(displayName,isPersonal)
                        mutableState.value = UiStateLogin.SuccessToLogin
                    } else {
                        mutableState.value = UiStateLogin.Error(Exception())
                    }
                }
        } catch (error: Exception) {
            mutableState.value = UiStateLogin.Error(error)
        }
    }

    private fun createUser(displayName: String?, isPersonal: Boolean) {
        val userId = auth.currentUser?.uid
        val user = UserUI(
            id = null,
            userId = userId.toString(),
            displayName = displayName.toString(),
            isPersonal = isPersonal,
        ).toMap()

        FirebaseFirestore
            .getInstance()
            .collection("users")
            .add(user)
    }
}
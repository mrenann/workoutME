package br.mrenann.workoutme.view.steps.home.tabs.exercisesTab.updateExerciseScreen

import android.content.Context
import android.net.Uri
import br.mrenann.workoutme.data.mapper.ResponseItemMapper
import br.mrenann.workoutme.domain.model.ExerciseUI
import br.mrenann.workoutme.utils.uiState.UiStateUpdate
import cafe.adriel.voyager.core.model.StateScreenModel
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.ktx.storage
import java.util.UUID

class UpdateExerciseStepModel : StateScreenModel<UiStateUpdate>(UiStateUpdate.Loading) {

    private val storage: FirebaseStorage = Firebase.storage
    var storageRef = storage.reference


    fun uploadPhotoToStorage(context: Context, uri: Uri, nome: String, observacoes: String) {
        try {
            val unique_image_name = UUID.randomUUID()
            var spaceRef: StorageReference = storageRef.child("images/$unique_image_name.jpg")

            val byteArray: ByteArray? = context.contentResolver
                .openInputStream(uri)
                ?.use { it.readBytes() }

            byteArray?.let {
                var uploadTask = spaceRef.putFile(uri)
                uploadTask.addOnSuccessListener { task ->
                    task.storage.downloadUrl.addOnSuccessListener{ url ->
                        createExercise(nome, observacoes, url)
                    }

                }.addOnFailureListener {
                    mutableState.value = UiStateUpdate.Error(Exception())
                }
            }

        } catch (error: Exception) {
            mutableState.value = UiStateUpdate.Error(error)
        }
    }

    private fun createExercise(nome: String, observacoes: String, uploadSessionUri: Uri?) {
        uploadSessionUri?.let {
            val exercise = ExerciseUI(
                id = null,
                nome = nome,
                observacoes = observacoes,
                imagem = it.toString()
            )
            val exerciseMapped = ResponseItemMapper.mapToData(exercise)
            FirebaseFirestore
                .getInstance()
                .collection("exercise")
                .add(exerciseMapped)
        }
    }
}
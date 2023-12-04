package br.mrenann.workoutme.data.network

import br.mrenann.workoutme.data.mapper.ResponseItemMapper
import br.mrenann.workoutme.data.models.TrainingSheetUIFirebase
import br.mrenann.workoutme.domain.FirebaseRepositoryRemote
import br.mrenann.workoutme.domain.model.TrainingSheetUI
import com.google.firebase.firestore.Query
import kotlinx.coroutines.tasks.await

class FirebaseRepositoryRemoteImpl(
    private val querySheet: Query,
) : FirebaseRepositoryRemote {

    override suspend fun getAllTrainingSheet(): List<TrainingSheetUI?> {
        val resultFirebase = querySheet.get().await().documents.map { documentSnapshot ->
            documentSnapshot.toObject(TrainingSheetUIFirebase::class.java)
        }
        return resultFirebase.map {
            it?.let { ResponseItemMapper.mapToData(it) }
        }
    }
}
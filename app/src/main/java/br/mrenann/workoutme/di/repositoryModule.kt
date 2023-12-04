package br.mrenann.workoutme.di

import br.mrenann.workoutme.data.network.FirebaseRepositoryRemoteImpl
import br.mrenann.workoutme.domain.FirebaseRepositoryRemote
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.singleton

val repositoryModule = DI.Module("repositoryModule") {

    bind<FirebaseRepositoryRemote>() with singleton {
        FirebaseRepositoryRemoteImpl(instance())
    }
}
package br.mrenann.workoutme.resource

import cafe.adriel.lyricist.LyricistStrings

@LyricistStrings(languageTag = Locales.PT, default = false)
internal val StringsPt = Strings(
    appName = "WorkoutMe",
    login = StringsLogin(
        title = "Gerencie Treinos\nde Musculação",
        alreadyUserSubtitle = "Já possui cadastro ?",
        newUserSubtitle = "Não possui um cadastro ?",
        signUpOption = "Cadastrar-se",
        loginOption = "Logar",
        contiueLoginOption = "Logar",
        contiueSignUpOption = "Cadastrar"
    )
)
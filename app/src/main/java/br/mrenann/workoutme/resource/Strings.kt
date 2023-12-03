package br.mrenann.workoutme.resource

data class Strings(
    val appName: String,
    val login: StringsLogin
)

data class StringsLogin(
    val title: String,
    val alreadyUserSubtitle: String,
    val newUserSubtitle: String,
    val loginOption: String,
    val signUpOption: String,
    val contiueLoginOption: String,
    val contiueSignUpOption: String,
    val isPersonalOption: String,
)
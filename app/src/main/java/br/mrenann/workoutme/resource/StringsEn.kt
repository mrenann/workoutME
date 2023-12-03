package br.mrenann.workoutme.resource

import cafe.adriel.lyricist.LyricistStrings

@LyricistStrings(languageTag = Locales.EN, default = false)
internal val StringsEn = Strings(
    appName = "WorkoutMe",
    login = StringsLogin(
        title = "Manage Gym\nWorkouts",
        alreadyUserSubtitle = "Already a user ?",
        newUserSubtitle = "Dont have a account ?",
        signUpOption = "Sign Up",
        loginOption = "Login",
        contiueLoginOption = "Login",
        contiueSignUpOption = "Register",
        isPersonalOption = "Its a Personal ?"
    )
)
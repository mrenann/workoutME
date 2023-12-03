package br.mrenann.workoutme.view.steps.login

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.mrenann.workoutme.HomeScreen
import br.mrenann.workoutme.resource.LocalStrings
import br.mrenann.workoutme.utils.uiState.UiStateLogin
import br.mrenann.workoutme.view.BaseApp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.kodein.rememberScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow


internal object LoginScreen : Screen {

    @Composable
    override fun Content() {
        val viewModel = rememberScreenModel<LoginScreenStepModel>()
        val state by viewModel.state.collectAsState()

        val navigator = LocalNavigator.currentOrThrow
        val showLoginForm = rememberSaveable { mutableStateOf(true) }

        Surface(modifier = Modifier.fillMaxSize()) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {

                when(state){
                    is UiStateLogin.Loading -> {}
                    is UiStateLogin.Error -> {}
                    is UiStateLogin.SuccessToLogin -> showLoginForm.value = true
                    is UiStateLogin.SuccessToHome -> navigator.push(HomeScreen)
                }

                if (showLoginForm.value) UserForm(loading = false, isCreateAccount = false) { email, password ->
                    viewModel.signInWithEmailAndPassword(email = email, password = password)
                }
                else UserForm(loading = false, isCreateAccount = true) { email, password ->
                    viewModel.createUserWithEmailAndPassword(email, password)
                }

                Spacer(modifier = Modifier.height(15.dp))

                Row(
                    modifier = Modifier.padding(15.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Text(text = if (!showLoginForm.value) "Already a user?" else "New User?")
                    Text(
                        text = if (!showLoginForm.value) "Login" else "Sign up",
                        modifier = Modifier
                            .padding(5.dp)
                            .clickable {
                                showLoginForm.value = !showLoginForm.value
                            },
                        fontWeight = FontWeight.Bold,
                        color = Color.Green.copy(alpha = 0.5f)
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
private fun UserForm(
    loading: Boolean = false,
    isCreateAccount: Boolean = false,
    onDone: (String, String) -> Unit,
) {

    val email = rememberSaveable { mutableStateOf("marcos.renann.br@gmail.com") }
    val password = rememberSaveable { mutableStateOf("123456a") }
    val keyboardController = LocalSoftwareKeyboardController.current
    val valid = remember(email.value, password.value) {
        email.value.trim().isNotEmpty() && password.value.trim().isNotEmpty()
    }

    Column(
        modifier = Modifier
            .background(Color.Transparent)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        if (isCreateAccount) Text(
            text = "TESTE",
            modifier = Modifier.padding(8.dp),
            fontStyle = FontStyle.Italic,
            color = Color.Yellow.copy(alpha = 0.7f)
        ) else Spacer(modifier = Modifier.height(56.dp))


        SubmitButton(
            textId = if (isCreateAccount) "Create Account" else "Login",
            loading = loading,
            validInputs = valid
        ) {
            onDone(email.value.trim(), password.value.trim())
            keyboardController?.hide()
        }
    }
}

@Composable
private fun SubmitButton(
    textId: String,
    loading: Boolean,
    validInputs: Boolean,
    onClick: () -> Unit,
) {
    Button(
        onClick = { onClick() },
        modifier = Modifier
            .padding(3.dp)
            .fillMaxWidth(),
        enabled = !loading && validInputs,
        shape = CircleShape,
    ) {
        if (!loading)
            Text(
                text = textId,
                modifier = Modifier.padding(5.dp),
                color = Color.White
            )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    BaseApp { }
}
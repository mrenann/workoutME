package br.mrenann.workoutme.view.steps.login

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.geometry.center
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.mrenann.workoutme.HomeScreen
import br.mrenann.workoutme.R
import br.mrenann.workoutme.resource.LocalStrings
import br.mrenann.workoutme.resource.strings
import br.mrenann.workoutme.utils.uiState.UiStateLogin
import br.mrenann.workoutme.view.BaseApp
import br.mrenann.workoutme.view.components.InputField
import br.mrenann.workoutme.view.components.InputFieldPassword
import br.mrenann.workoutme.view.components.Loading
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.kodein.rememberScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.google.accompanist.systemuicontroller.rememberSystemUiController


internal object LoginScreen : Screen {

    @Composable
    override fun Content() {
        val viewModel = rememberScreenModel<LoginScreenStepModel>()
        val state by viewModel.state.collectAsState()
        val strings = LocalStrings.current
        val navigator = LocalNavigator.currentOrThrow
        val showLoginForm = rememberSaveable { mutableStateOf(true) }
        val systemUiController = rememberSystemUiController()
        SideEffect {
            systemUiController.setStatusBarColor(
                color = Color(0xFF91B46E)
            )
        }
        Surface(modifier = Modifier.fillMaxSize()) {

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {

                Card(
                    shape = RoundedCornerShape(0.dp, 0.dp, 20.dp, 20.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color.Black,
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.55F),

                    ) {
                    Box(
                        modifier = Modifier
                            .background(
                                brush = Brush.linearGradient(
                                    colors = listOf(
                                        Color(0xFF91B46E),
                                        Color(0xFF34653B),
                                    )
                                )
                            )
                            .drawBehind {
                                drawCircle(
                                    brush = Brush.linearGradient(
                                        colors = listOf(
                                            Color(0xFF91B46E).copy(alpha = 0.4f),
                                            Color(0xFF34653B).copy(alpha = 0.2f)
                                        )
                                    ),
                                    center = this.size.center * 2f,
                                    radius = this.size.width / 2f
                                )
                                drawCircle(
                                    brush = Brush.linearGradient(
                                        tileMode = TileMode.Mirror,
                                        colors = listOf(
                                            Color(0xFF91B46E).copy(alpha = 0.4f),
                                            Color(0xFF34653B).copy(alpha = 0.2f)
                                        )
                                    ),
                                    center = this.size.center.copy(y = this.size.height * 1.4f),
                                    radius = this.size.width / 2.5f
                                )
                            }
                            .heightIn(min = 185.dp),
                    ) {
                        Image(
                            modifier = Modifier
                                .align(Alignment.BottomEnd)
                                .fillMaxHeight(0.5F)
                                .fillMaxWidth(0.7F),
                            painter = painterResource(id = R.drawable.ic_login_image),
                            contentDescription = ""
                        )
                        Column(
                            modifier = Modifier
                                .padding(30.dp)
                                .fillMaxSize()
                        ) {
                            Text(
                                text = strings.appName,
                                color = Color.White,
                                fontWeight = FontWeight.Thin
                            )
                            Spacer(modifier = Modifier.padding(20.dp))
                            Text(
                                modifier = Modifier,
                                text = strings.login.title,
                                color = Color.White,
                                fontWeight = FontWeight.Medium,
                                fontSize = 35.sp,
                                lineHeight = 40.sp
                            )
                        }
                    }

                }

                when (state) {
                    is UiStateLogin.Loading -> {}
                    is UiStateLogin.Error -> {}
                    is UiStateLogin.SuccessToLogin -> showLoginForm.value = true
                    is UiStateLogin.SuccessToHome -> navigator.push(HomeScreen)
                }

                if (showLoginForm.value) {
                    UserForm(
                        modifier = Modifier.weight(1F).padding(top = 7.dp), loading = false, isCreateAccount = false
                    ) { email, password, _ ->
                        viewModel.signInWithEmailAndPassword(email = email, password = password)
                    }
                } else {
                    UserForm(
                        modifier = Modifier.weight(1F).padding(top = 7.dp), loading = false, isCreateAccount = true
                    ) { email, password, isPersonal ->
                        viewModel.createUserWithEmailAndPassword(email, password, isPersonal)
                    }
                }

                Spacer(modifier = Modifier.height(3.dp))

                Column(
                    modifier = Modifier.padding(12.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Text(text = if (!showLoginForm.value) strings.login.alreadyUserSubtitle else strings.login.newUserSubtitle)
                    Text(
                        text = if (!showLoginForm.value) strings.login.loginOption else strings.login.signUpOption,
                        modifier = Modifier
                            .padding(vertical = 5.dp)
                            .clickable {
                                showLoginForm.value = !showLoginForm.value
                            },
                        fontWeight = FontWeight.Medium,
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
    modifier: Modifier,
    loading: Boolean = false,
    isCreateAccount: Boolean = false,
    onDone: (String, String, Boolean) -> Unit,
) {
    val strings = LocalStrings.current
    val email = rememberSaveable { mutableStateOf("") }
    val password = rememberSaveable { mutableStateOf("") }
    val isPersonal = rememberSaveable { mutableStateOf(false) }
    val passwordVisibility = rememberSaveable { mutableStateOf(false) }
    val passwordFocusRequest = FocusRequester.Default
    val keyboardController = LocalSoftwareKeyboardController.current
    val valid = remember(email.value, password.value) {
        email.value.trim().isNotEmpty() && password.value.trim().isNotEmpty()
    }

    Column(
        modifier = modifier
            .background(Color.Transparent)
            .padding(horizontal = 12.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.height(5.dp))

        InputField(valueState = email,
            labelId = "Email",
            keyboardType = KeyboardType.Email,
            enabled = !loading,
            onAction = KeyboardActions {
                passwordFocusRequest.requestFocus()
            })

        InputFieldPassword(modifier = Modifier.focusRequester(passwordFocusRequest),
            valueState = password,
            labelId = "Password",
            keyboardType = KeyboardType.Password,
            imeAction = ImeAction.Done,
            enabled = !loading,
            passwordVisibility = passwordVisibility,
            onAction = KeyboardActions {
                if (!valid) return@KeyboardActions
                onDone(email.value.trim(), password.value.trim(), isPersonal.value)
            })

        if (isCreateAccount) {
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp)
                    .selectable(
                        selected = isPersonal.value,
                        onClick = { isPersonal.value = !isPersonal.value },
                        role = Role.RadioButton
                    )
                    .padding(vertical = 5.dp), verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = isPersonal.value, onClick = null

                )
                Text(modifier = Modifier.padding(start = 10.dp), text = strings.login.isPersonalOption)
            }

        }

        SubmitButton(
            textId = if (isCreateAccount) strings.login.contiueSignUpOption else strings.login.contiueLoginOption,
            loading = loading,
            validInputs = valid
        ) {
            onDone(email.value.trim(), password.value.trim(), isPersonal.value)
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
            .padding(vertical = 3.dp)
            .fillMaxWidth(),
        enabled = !loading && validInputs,
        shape = RoundedCornerShape(12.dp),
    ) {
        if (loading) Loading()
        else Text(
            text = textId, modifier = Modifier.padding(5.dp), color = Color.White
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    BaseApp { }
}
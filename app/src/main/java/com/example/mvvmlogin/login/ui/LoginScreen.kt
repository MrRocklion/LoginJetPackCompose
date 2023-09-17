package com.example.mvvmlogin.login.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mvvmlogin.R
import kotlinx.coroutines.launch


@Composable
fun LoginScreen(viewModel: LoginViewModel){
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ){
        Login(Modifier.align(Alignment.Center),viewModel)
    }
}




@Composable
fun  Login(modifier: Modifier ,model:LoginViewModel) {
    val email:String by model.email.observeAsState(initial = "")
    val password:String by model.password.observeAsState(initial = "")
    val btnEnable:Boolean by model.loginEnable.observeAsState(initial = false)
    val isloading:Boolean by model.isLoading.observeAsState(initial = false)
    val coroutineScope = rememberCoroutineScope()
    if(isloading){
        Box(
            Modifier.fillMaxSize()
        ){
            CircularProgressIndicator(Modifier.align(Alignment.Center))
        }
    }
    else {
        Column(modifier = modifier) {
            HeaderImage(Modifier.align(Alignment.CenterHorizontally))
            Spacer(modifier = Modifier.padding(16.dp))
            EmailField(email, { model.onLoginChange(it, password) })
            Spacer(modifier = Modifier.padding(4.dp))
            PasswordField(password) { model.onLoginChange(email, it) }
            Spacer(modifier = Modifier.padding(8.dp))
            ForgotPassword(Modifier.align(Alignment.End))
            Spacer(modifier = Modifier.padding(16.dp))
            LoginButton(
                Modifier.align(Alignment.CenterHorizontally),
                btnEnable,
               ){
                coroutineScope.launch { model.onLoginSelected() }
            }
        }
    }
}

@Composable
fun LoginButton(modifier: Modifier,btnEnabled:Boolean,onLoginSelected:()->Unit) {
    Button(
        onClick = {onLoginSelected()},
        modifier = modifier
            .fillMaxWidth()
            .height(48.dp),
        enabled = btnEnabled
    ) {
        Text("Iniciar Sesion")
    }
}

@Composable
fun ForgotPassword(modifier: Modifier) {
    Text(
        text = "Olvidateste la Contraseña?",
        modifier = modifier.clickable {  },
        fontSize = 12.sp,
        fontWeight = FontWeight.Bold,
        color = Color(0xFF58D68D)

    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordField(password: String, onTextFieldChange:(String)-> Unit) {
    TextField(value = password,
        onValueChange = {onTextFieldChange(it)},
        modifier = Modifier.fillMaxWidth(),
        placeholder = {
            Text("Password")
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password
        ),
        singleLine = true,
        maxLines = 1,
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color(0xFF636262),
            containerColor = Color(0xFFDEDDDD),
            focusedIndicatorColor = androidx.compose.ui.graphics.Color.Transparent,
            unfocusedIndicatorColor = androidx.compose.ui.graphics.Color.Transparent,

            )

    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EmailField(email:String,onTextFieldChange:(String)-> Unit) {
    TextField(
        value = email,
        onValueChange = {onTextFieldChange(it)},
        modifier = Modifier.fillMaxWidth(),
        placeholder = {
            Text("Email")
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Email
        ),
        singleLine = true,
        maxLines = 1,
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color(0xFF636262),
            containerColor = Color(0xFFDEDDDD),
            focusedIndicatorColor = androidx.compose.ui.graphics.Color.Transparent,
            unfocusedIndicatorColor = androidx.compose.ui.graphics.Color.Transparent,

        )

    )
}


@Composable
fun HeaderImage(modifier: Modifier) {
    Image(painter = painterResource(id = R.drawable.cohete ), contentDescription = "header", modifier = modifier )
}

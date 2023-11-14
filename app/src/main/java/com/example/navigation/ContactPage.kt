package com.example.navigation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContactPage(
    onSubmitButtonClicked: (MutableList<String>) -> Unit
){
    var txtName by remember {
        mutableStateOf("")
    }
    var txtAddress by remember {
        mutableStateOf("")
    }
    var txtTlp by remember {
        mutableStateOf("")
    }
    var listDataTxt: MutableList<String> = mutableListOf(txtName, txtAddress, txtTlp)

    Column (
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(dimensionResource(id = R.dimen.padding_medium))
            .fillMaxSize()
    ){
        OutlinedTextField(
            value = txtName,
            onValueChange = { txtName = it },
            label = {
                Text(text = stringResource(id = R.string.name))
            }
        )
        OutlinedTextField(
            value = txtAddress,
            onValueChange = { txtAddress = it },
            label = {
                Text(text = stringResource(id = R.string.address))
            }
        )
        OutlinedTextField(
            value = txtTlp,
            onValueChange = { txtTlp = it },
            label = {
                Text(text = stringResource(id = R.string.tlp))
            }
        )
        Spacer(modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_medium)))
        Button(onClick = { onSubmitButtonClicked(listDataTxt) }) {
            Text(text = stringResource(id = R.string.btn_submit))
        }
    }
}
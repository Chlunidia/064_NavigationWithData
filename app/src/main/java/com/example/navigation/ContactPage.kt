package com.example.navigation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedButton
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
import androidx.compose.ui.tooling.preview.Preview
import com.example.navigation.data.OrderUIState
import com.example.navigation.ui.theme.NavigationTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContactPage(
    onSubmitButtonClicked: (OrderUIState) -> Unit,
    onCancelButtonClicked: () -> Unit,
) {
    var txtName by remember {
        mutableStateOf("")
    }
    var txtAddress by remember {
        mutableStateOf("")
    }
    var txtTlp by remember {
        mutableStateOf("")
    }

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(dimensionResource(id = R.dimen.padding_medium))
            .fillMaxSize()
    ) {
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
        Row(
            modifier = Modifier
                .weight(1f, false)
                .padding(dimensionResource(R.dimen.padding_medium)),
            horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_small))
        ) {
            OutlinedButton(
                onClick = onCancelButtonClicked
            ) {
                Text(stringResource(R.string.cancel))
            }
            Button(
                onClick = {
                    val orderUIState = OrderUIState(
                        name = txtName,
                        address = txtAddress,
                        tlp = txtTlp
                    )
                    onSubmitButtonClicked(orderUIState)
                }
            ) {
                Text(text = stringResource(id = R.string.btn_submit))
            }
        }
    }
}

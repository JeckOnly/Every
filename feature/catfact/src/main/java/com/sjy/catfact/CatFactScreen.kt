package com.sjy.catfact

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel

/**
 * Created by JeckOnly on 2023/9/12
 * Describe:
 */

@Composable
fun CatFactScreen(vm: CatFactViewModel = viewModel()) {

    val catFact = vm.catFact.collectAsStateWithLifecycle()
    
    Surface {
        Column {
            Button(onClick = {
                vm.askCatFact()
            }) {
                Text(text = "Get a cat fact")
            }

            Button(onClick = {
                vm.askCatFactSandwich()
            }) {
                Text(text = "Get a cat fact sandwich")
            }

            Button(onClick = {
                vm.askCatFactSandwich3()
            }) {
                Text(text = "Get a cat fact sandwich add retry")
            }
            Text(text = "CatFact: ${catFact.value}")
        }
    }
}
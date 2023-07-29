package com.ekzakh.vknewsclient

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import coil.compose.AsyncImage

@Composable
fun ActivityResultTest() {
    var imageUri by remember {
        mutableStateOf(Uri.EMPTY)
    }
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { imageUri = it },
    )

    Column(modifier = Modifier.fillMaxSize()) {
        AsyncImage(
            modifier = Modifier.weight(1F),
            model = imageUri,
            contentDescription = null,
        )
        Button(onClick = { launcher.launch("image/*") }) {
            Text(text = "Chose picture")
        }
    }
}

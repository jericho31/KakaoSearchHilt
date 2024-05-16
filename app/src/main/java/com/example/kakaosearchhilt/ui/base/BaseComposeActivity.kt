package com.example.kakaosearchhilt.ui.base

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import com.example.kakaosearchhilt.ui.theme.KakaoSearchHiltTheme

abstract class BaseComposeActivity : ComponentActivity() {

    @Composable
    abstract fun Content()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            KakaoSearchHiltTheme {
                Content()
            }
        }
    }

}

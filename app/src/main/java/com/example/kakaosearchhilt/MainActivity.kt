package com.example.kakaosearchhilt

import androidx.compose.runtime.Composable
import com.example.kakaosearchhilt.ui.KakaoSearchApp
import com.example.kakaosearchhilt.ui.base.BaseComposeActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseComposeActivity() {

    @Composable
    override fun Content() {
        KakaoSearchApp()
    }

}

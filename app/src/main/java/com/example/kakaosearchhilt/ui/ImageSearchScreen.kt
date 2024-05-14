package com.example.kakaosearchhilt.ui

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.kakaosearchhilt.SharedViewModel
import com.example.kakaosearchhilt.model.ItemModel

private const val JJTAG = "jj-ImageSearchScreen"
private const val IMAGE_SEARCH_ROUTE = "image_search_route"

// 뷰모델 받는거 나중에 어떻게 바꿔야 할 듯.
fun NavGraphBuilder.imageSearchScreen(viewModel: SharedViewModel) {
    composable(
        route = IMAGE_SEARCH_ROUTE,
    ) {
        ImageSearchScreen(viewModel)
    }
}

@Composable
fun ImageSearchScreen(
    viewModel: SharedViewModel = hiltViewModel()
) {
    val textState = rememberSaveable(stateSaver = TextFieldValue.Saver) {
        mutableStateOf(TextFieldValue(viewModel.loadKeyword() ?: ""))
    }
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current

    ImageSearchScreen(
        itemModels = viewModel.searchResultSF.collectAsState().value,
        textState = textState,
        buttonClickListener = {
            keyboardController?.hide()
            focusManager.clearFocus()

            val input = textState.value.text
            Log.d(JJTAG, "검색버튼 클릭리스너: $input")  //ddd
            viewModel.searchImage(input)
        },
        onItemClick = { _, model ->
            Log.d(JJTAG, "cb:onItemClick) $model")

            // 나중에 공유뷰모델로 바꿔야되는데 지금 구조 똥이라서 그냥 이렇게 씀
            if (model.isLoved) {
                viewModel.updateLoved(model, false)
                viewModel.removeFromMyBoxList(model.thumbnailURL)
            } else {
                viewModel.updateLoved(model, true)
                viewModel.addToMyBoxList(model)
            }
        },
    )
}

@Composable
fun ImageSearchScreen(
    itemModels: List<ItemModel> = emptyList(),
    textState: MutableState<TextFieldValue>,
    modifier: Modifier = Modifier,
    buttonClickListener: () -> Unit = {},
    onItemClick: ((setIsLoved: (isLoved: Boolean) -> Unit, itemModel: ItemModel) -> Unit)?,
) {
    Column(modifier = modifier) {
        Row {
            Box(modifier = Modifier.weight(1f)) {
                UserInputTextField(
                    textFieldValue = textState.value,
                    onTextChanged = { textState.value = it })
            }

            Button(onClick = buttonClickListener) {
                Text("검색")
            }
        }
        RvImageSearch(
            itemModels = itemModels,
            onItemClick = onItemClick,
        )
    }
}

@Preview
@Composable
fun CompImageSearchFragmentPreview() {
    MaterialTheme {
        Surface {
            ImageSearchScreen(
                itemModels = List(8) { ItemModel.new() },
                textState = remember {
                    mutableStateOf(TextFieldValue())
                },
                onItemClick = null,
            )
        }
    }
}

@Composable
private fun BoxScope.UserInputTextField(
    textFieldValue: TextFieldValue,
    onTextChanged: (TextFieldValue) -> Unit,
    modifier: Modifier = Modifier
) {
    TextField(
        value = textFieldValue,
        onValueChange = { onTextChanged(it) },
        modifier = modifier
            .padding(start = 32.dp)
            .align(Alignment.CenterStart),
        maxLines = 1,
        placeholder = { Text("검색어를 입력하세요") }
    )
}

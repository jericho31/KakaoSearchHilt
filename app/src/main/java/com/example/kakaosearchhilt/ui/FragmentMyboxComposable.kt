package com.example.kakaosearchhilt.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.kakaosearchhilt.model.ItemModel

@Composable
fun FragmentMyboxComposable(
    items: List<ItemModel>,
) {

    MaterialTheme {
        Scaffold { paddingValues ->
            Box(
                modifier = Modifier.padding(paddingValues)
            ) {
                LazyVerticalStaggeredGrid(columns = StaggeredGridCells.Fixed(2)) {
                    items(items) { item ->
                        GridItem(
                            item = item,
                        )
                    }
                }
                if (items.isEmpty()) {
                    Text(
                        text = "보관된 사진이 없습니다.",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 24.dp),
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}

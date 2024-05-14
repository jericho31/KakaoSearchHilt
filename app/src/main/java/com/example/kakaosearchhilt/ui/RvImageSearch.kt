package com.example.kakaosearchhilt.ui

import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.runtime.Composable
import com.example.kakaosearchhilt.model.ItemModel

@Composable
fun RvImageSearch(
    itemModels: List<ItemModel>,
    onItemClick: ((setIsLoved: (isLoved: Boolean) -> Unit, itemModel: ItemModel) -> Unit)?,
) {
    LazyVerticalStaggeredGrid(columns = StaggeredGridCells.Fixed(2)) {
        items(itemModels) { item ->
            GridItem(
                item = item,
                onClick = onItemClick,
            )
        }
    }
}

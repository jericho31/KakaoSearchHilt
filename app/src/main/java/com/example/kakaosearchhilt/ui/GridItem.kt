package com.example.kakaosearchhilt.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import coil.compose.AsyncImage
import com.example.kakaosearchhilt.R
import com.example.kakaosearchhilt.model.ItemModel

@Composable
fun GridItem(
    item: ItemModel,
    modifier: Modifier = Modifier,
    onClick: ((
        setIsLoved: (isLoved: Boolean) -> Unit,
        itemModel: ItemModel,
    ) -> Unit)? = null,
) {
    ConstraintLayout(
        modifier = modifier
            .padding(4.dp)
            .clickable {
                onClick?.invoke({}, item)
//                onClick?.invoke(::setIsLoved, item)
            }
    ) {
        val (iv_item_thumbnail, iv_item_love, ll_item) = createRefs()

        AsyncImage(model = item.thumbnailURL,
            contentDescription = "thumbnail",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .sizeIn(minHeight = 100.dp, maxHeight = 300.dp)
                .constrainAs(iv_item_thumbnail) {
                    top.linkTo(parent.top)
                }
        )
        if (item.isLoved)
            Image(
                painterResource(R.drawable.love_no_line),
                contentDescription = "love icon",
                modifier = Modifier
                    .size(48.dp)
                    .constrainAs(iv_item_love) {
                        top.linkTo(iv_item_thumbnail.top, margin = 8.dp)
                        end.linkTo(iv_item_thumbnail.end, margin = 8.dp)
                    },
            )
        // 리니어에 패딩 가로 12 세로 8 줘야하는데 모르겠다
        Column(modifier = Modifier.constrainAs(ll_item) {
            top.linkTo(iv_item_thumbnail.bottom)
        }) {
            Text(
                item.displaySitename,
                modifier = Modifier
            )
            Text(
                item.datetime,
                modifier = Modifier.padding(top = 8.dp)
            )
        }
    }
}

@Preview
@Composable
private fun GridItemPreview() {
    MaterialTheme {
        Surface {
            GridItem(
                item = ItemModel.new()
            )
        }
    }
}

package ui.dashboard

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

data class DashboardItem(
    val itemName: String,
    val icon: ImageVector,
)

/*
         In this item
         Only one line of text is can be present
         if the more text then it will not shown
         as a result all the card will have the same height
         later we have to modify such that:
         all the child takes the same height as it tallest siblibg
          */

@Composable
fun DashBoard(
    onDashboardItemClick: (String) -> Unit,
    list: List<DashboardItem>,
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        items(list) {
            DashboardItem(
                label = it.itemName,
                icon = it.icon,
                onDashboardItemClick = onDashboardItemClick
            )
        }

    }

}


@Composable
fun DashboardItem(
    modifier: Modifier = Modifier,
    onDashboardItemClick: (String) -> Unit,
    icon: ImageVector,
    label: String,
) {
    Surface(
        modifier = modifier
            .clickable { onDashboardItemClick(label) },
        tonalElevation = 5.dp,
        shadowElevation = 5.dp
    ) {
        Column(
            modifier = Modifier
                .wrapContentSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null
            )
            /*
            In this item
            Only one line of text is can be present
            if the more text then it will not shown
            as a result all the card will have the same height
            later we have to modify such that:
            all the child takes the same height as it tallest siblibg
             */
            Text(
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                text = label
            )
        }
    }
}

@Preview
@Composable
fun DashboardItemPreview() {
    DashboardItem(
        label = "Edit Profile Request",
        icon = Icons.Default.Edit,
        onDashboardItemClick = {}
    )
}


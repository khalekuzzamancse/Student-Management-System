package ui.dashboard

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material.icons.filled.VerifiedUser
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class DashboardItem(
    val itemName: String,
    val icon: ImageVector,
)


@Composable
fun DashBoard(
    onDashboardItemClick: (String) -> Unit,
    list: List<DashboardItem>,
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(150.dp),
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
            .fillMaxWidth()
            .padding(16.dp)
            .clickable { onDashboardItemClick(label) },
        tonalElevation = 5.dp,
        shadowElevation = 5.dp
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null
            )
            Text(text = label)
        }
    }
}


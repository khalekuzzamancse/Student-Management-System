package teacher_section.view_asignment

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.khalekuzzaman.just.cse.common_ui_element.drop_down_menu.TextualDropDownMenu

@Composable
fun StudentWorkTopAppbar(
    modifier: Modifier = Modifier,
    onBackArrowClick:()->Unit,
    onShareButtonClick:()->Unit,
) {
    Row(modifier = modifier) {
        IconButton(onClick = onBackArrowClick) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = null
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        IconButton(onClick = onShareButtonClick) {
            Icon(
                imageVector = Icons.Default.Share,
                contentDescription = null
            )
        }
        TextualDropDownMenu(
            onMenuItemClick = {},
            menuItems = listOf("Refresh", "Edit", "Delete")
        )
    }
}
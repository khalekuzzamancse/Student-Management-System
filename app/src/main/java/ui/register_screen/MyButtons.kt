package ui.register_screen

import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview


@Composable
fun MyButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    label: String,
    icon: ImageVector?=null,
) {
    Button(
        modifier = modifier,
        onClick = onClick
    ) {
        if (icon != null) {
            Icon(
                imageVector = icon,
                contentDescription = null
            )
        }
        Text(text = label)
    }

}

@Preview
@Composable
fun MyButtonPreview() {
    MyButton(
        onClick = { /*TODO*/ },
        label ="Register",
    )
    
}
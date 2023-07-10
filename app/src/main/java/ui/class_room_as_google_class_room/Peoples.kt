package ui.class_room_as_google_class_room

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PersonAdd
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.khalekuzzaman.just.cse.studentmanagementsystem.R
import ui.drop_down_menu.TextualDropDownMenu

data class PeopleItem(
    val name: String,
    val imageId: Int,
)

val fakeList = listOf(
    PeopleItem("Mr Bean (823056)", R.drawable.photo),
    PeopleItem("Mr Bean (823057)", R.drawable.photo),
    PeopleItem("Mr Bean (823058)", R.drawable.photo)
)

@Preview
@Composable
fun PeopleListPreview() {
    PeopleList(peoples = fakeList)

}

@Composable
fun PeopleList(peoples: List<PeopleItem>) {
    Column(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
    ) {
        TeacherSectionHeader(onAddPeopleIconClick = {})
        Divider(modifier = Modifier.fillMaxWidth())
        StudentSectionHeader(
            onAddPeopleIconClick = {},
            onMoreIconClick = {},

            )
        Divider(modifier = Modifier.fillMaxWidth())
        Column(modifier = Modifier.fillMaxWidth()) {
            peoples.forEach {
                PeopleItem(
                    modifier = Modifier.padding(8.dp),
                    imageId = it.imageId,
                    onMoreIconClick = { /*TODO*/ },
                    name = it.name
                )
            }
        }

    }
}

@Composable
fun StudentSectionHeader(
    modifier: Modifier = Modifier,
    onMoreIconClick: () -> Unit,
    onAddPeopleIconClick: () -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxWidth(),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(style = MaterialTheme.typography.titleLarge, text = "Students")
            Spacer(modifier = Modifier.weight(1f))
            Icon(
                modifier = Modifier.clickable { onAddPeopleIconClick() },
                imageVector = Icons.Default.PersonAdd,
                contentDescription = null
            )
            Spacer(modifier = Modifier.width(16.dp))
            TextualDropDownMenu(
                menuItems = listOf("Sort By"),
                onMenuItemClick = { onMoreIconClick() }
            )
        }
        Divider(modifier = Modifier.fillMaxWidth())
    }

}

@Composable
fun TeacherSectionHeader(
    modifier: Modifier = Modifier,
    onAddPeopleIconClick: () -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxWidth(),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(style = MaterialTheme.typography.titleLarge, text = "Teachers")
            Spacer(modifier = Modifier.weight(1f))
            Icon(
                modifier = Modifier.clickable { onAddPeopleIconClick() },
                imageVector = Icons.Default.PersonAdd,
                contentDescription = null
            )
        }

    }

}

@Composable
private fun PeopleItem(
    modifier: Modifier = Modifier,
    imageId: Int,
    onMoreIconClick: () -> Unit,
    name: String,
) {
    Row(
        modifier = modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            modifier = Modifier
                .size(50.dp)
                .clip(CircleShape),
            painter = painterResource(id = imageId), contentDescription = null
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = name)
        Spacer(modifier = Modifier.weight(1f))
        TextualDropDownMenu(
            menuItems = listOf("Email student", "Mute", "Remove"),
            onMenuItemClick = { onMoreIconClick() }
        )

    }

}
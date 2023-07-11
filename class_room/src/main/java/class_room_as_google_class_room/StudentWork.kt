package class_room_as_google_class_room

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.khalekuzzaman.just.cse.class_room.R

enum class StudentWorkStatus {
    ASSIGNED, MARKED
}

@Composable
 fun StudentWorkList() {
    Column(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
    ) {
        StudentWorkItem(
            studentWorkStatus = StudentWorkStatus.ASSIGNED,
            profileImage = R.drawable.photo,
            studentName = "Mr Bean",
            isChecked = false,
            onCheckChanged = {},
        )
        StudentWorkItem(
            studentWorkStatus = StudentWorkStatus.MARKED,
            profileImage = R.drawable.photo,
            studentName = "Mr Bean",
            totalMark = "100",
            obtainedMark = "15",
            isChecked = true,
            onCheckChanged = {},
        )
    }
}

@Composable
fun StudentWorkItem(
    modifier: Modifier = Modifier,
    studentWorkStatus: StudentWorkStatus,
    profileImage: Int,
    studentName: String,
    totalMark: String = "",
    isChecked: Boolean,
    onCheckChanged: (Boolean) -> Unit,
    obtainedMark: String? = "",
) {

    Column(modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(checked = isChecked, onCheckedChange = onCheckChanged)
            Text(text = "$studentWorkStatus")
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = isChecked,
                onCheckedChange = onCheckChanged
            )
            Image(
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape),
                painter = painterResource(id = profileImage),
                contentDescription = null
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = studentName)
            Spacer(modifier = Modifier.weight(1f))
            if (studentWorkStatus == StudentWorkStatus.MARKED) {
                Text(text = "$obtainedMark/$totalMark")
            }
        }
    }


}
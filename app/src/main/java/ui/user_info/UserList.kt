package ui.user_info

import FakeUserData
import Student
import Teacher
import User
import UserLabels
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.khalekuzzaman.just.cse.studentmanagementsystem.R

@Preview
@Composable
private fun UserListPreview() {
    UserList(users = FakeUserData.users)
}

@Composable
fun UserList(users: List<User>) {
    //later use the lazy column,
    //because of lagging we are using column composable now
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        users.forEach {
            UserInfo(user = it)
        }
    }
}

@Composable
fun UserInfo(user: User) {

    val textMeasurer = rememberTextMeasurer()
    val density = LocalDensity.current.density
    val getTextWidth: (String) -> Dp = {
        val width = textMeasurer
            .measure(it)
            .size
            .width
        (width / density).dp
    }
    val labelMaxWidth = UserLabels.labelList.maxOf { getTextWidth(it) }

    Surface(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        shadowElevation = 5.dp,
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            ShowProfileImage(image = R.drawable.photo)
            ShowUserData(labelMaxWidth=labelMaxWidth, user=user)
        }
    }
}

@Composable
private fun ShowUserData(modifier: Modifier=Modifier,labelMaxWidth: Dp, user: User) {
    Column(modifier = modifier.wrapContentSize()) {
        EachRow(label = UserLabels.NAME, value = user.name, labelWidth = labelMaxWidth)
        EachRow(label = UserLabels.EMAIL, value = user.email, labelWidth = labelMaxWidth)
        EachRow(label = UserLabels.PHONE, value = user.phone, labelWidth = labelMaxWidth)
        EachRow(
            label = UserLabels.BLOOD_GROUP,
            value = user.bloodGroup,
            labelWidth = labelMaxWidth
        )
        EachRow(label = UserLabels.GENDER, value = user.gender, labelWidth = labelMaxWidth)
        when (user) {
            is Teacher -> {
                EachRow(
                    label = UserLabels.DEPARTMENT,
                    value = user.department,
                    labelWidth = labelMaxWidth
                )
                EachRow(label = UserLabels.NID, value = user.nid, labelWidth = labelMaxWidth)
            }

            is Student -> {
                EachRow(
                    label = UserLabels.FATHER_NAME,
                    value = user.fatherName,
                    labelWidth = labelMaxWidth
                )
                EachRow(
                    label = UserLabels.MOTHER_NAME,
                    value = user.motherName,
                    labelWidth = labelMaxWidth
                )
                EachRow(
                    label = UserLabels.FATHER_PHONE,
                    value = user.fatherPhone,
                    labelWidth = labelMaxWidth
                )
                EachRow(
                    label = UserLabels.MOTHER_PHONE,
                    value = user.motherPhone,
                    labelWidth = labelMaxWidth
                )
            }
        }
        EachRow(label = UserLabels.DISTRICT, value = user.district, labelWidth = labelMaxWidth)
        EachRow(
            label = UserLabels.SUB_DISTRICT,
            value = user.subDistrict,
            labelWidth = labelMaxWidth
        )
        EachRow(label = UserLabels.LOCATION, value = user.location, labelWidth = labelMaxWidth)
    }
}

@Composable
private fun ShowProfileImage(image: Int) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        Image(
            modifier = Modifier
                .size(50.dp)
                .clip(CircleShape),
            painter = painterResource(id = image),
            contentDescription = null
        )
    }
}

@Composable
private fun EachRow(label: String, value: String, labelWidth: Dp) {
    Row(modifier = Modifier.fillMaxWidth()) {
        Text(modifier = Modifier.width(labelWidth), text = label)
        Spacer(Modifier.width(4.dp))
        Text(text = ":")
        Spacer(Modifier.width(4.dp))
        Text(modifier = Modifier.weight(1f), text = value)
    }
}

@Preview
@Composable
private fun PreviewEachRow() {
    EachRow(label = "Name", value = "Mr Bean", labelWidth = 50.dp)

}
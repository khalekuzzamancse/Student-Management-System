package ui.table

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview
@Composable
fun ScrollTest() {

    val list = mutableListOf(
        "Assssssssssssssssssssssssssdddddddddddduuuuuuuu" +
                "uuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuueeeeee",
        "B", "C"
    )
    for(i in 1..50)
        list.add("$i")

    LazyColumn(
        modifier = Modifier.horizontalScroll(rememberScrollState())
    ) {
        items(list) {
            Row(modifier=Modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Min)
            ){
                Text(modifier=Modifier
                    .width(100.dp)
                    .fillMaxHeight(),
                    text = it)
                Text(modifier=Modifier
                    .width(100.dp)
                    .fillMaxHeight(),
                    text = it)
                Text(modifier=Modifier
                    .width(100.dp)
                    .fillMaxHeight(),
                    text = it)
                Text(modifier=Modifier
                    .width(100.dp)
                    .fillMaxHeight(),
                    text = it)
                Text(modifier=Modifier
                    .width(100.dp)
                    .fillMaxHeight(),
                    text = it)

            }

        }
    }

}
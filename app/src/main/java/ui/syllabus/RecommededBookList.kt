package ui.syllabus

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp


@Preview
@Composable
private fun RecommendedBooksPreview() {
    RecommendedBooks(books = CourseComponentFakeData.books)
}

@Composable
fun RecommendedBooks(
    books: List<Book>,
) {
    val textMeasurer = rememberTextMeasurer()
    val density = LocalDensity.current.density
    val getTextWidth: (String) -> Dp = {
        val width = textMeasurer
            .measure(it)
            .size
            .width
        (width / density).dp
    }
    val labels = listOf("Serial No", "Title")
    val labelWidth = labels.map { getTextWidth(it) + 20.dp }

    Column(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
    ) {
        /*
        Header

         */
        EachRow(
            _1stCellWidth = labelWidth[0],
            _2ndCellWidth = labelWidth[1] + 50.dp,
            serialNo = "Serial No",
            title = "Title",
            authors = "Authors"
        )
        books.forEachIndexed { index, book ->
            EachRow(
                _1stCellWidth = labelWidth[0],
                _2ndCellWidth = labelWidth[1] + 50.dp,
                serialNo = index.toString(),
                title = book.title,
                authors = book.authors.joinToString(", ")
            )
        }


    }
}

@Composable
private fun EachRow(
    _1stCellWidth: Dp,
    _2ndCellWidth: Dp,
    serialNo: String,
    title: String,
    authors: String,
) {
    Row(
        modifier = Modifier
            .padding(10.dp)
            .height(IntrinsicSize.Min)
            .fillMaxWidth()
    ) {

        Text(
            modifier = Modifier
                .width(_1stCellWidth)
                .fillMaxHeight(),
            text = serialNo
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            modifier = Modifier
                .width(_2ndCellWidth)
                .fillMaxHeight(),
            text = title
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight(),
            text = authors,
        )

    }
}
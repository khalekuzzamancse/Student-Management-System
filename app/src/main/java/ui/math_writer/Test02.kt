package ui.math_writer

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp

@Composable
fun F() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        EquationItem(
            line = listOf(
                "g + ",
                EquationItem(
                    line = "x",
                    sqrt = 2
                )
            ),
            underline = listOf(
                "r",
                EquationItem(
                    line = "y",
                    sqrt = 2
                )
            )
        ).Show()

        EquationItem(
            line = listOf(
                "x = ",
                EquationItem(
                    line = EquationItem(
                        line = "x",
                        superscript = "2",
                        subscript = "2"
                    ),
                    underline = "y",
                    sqrt = 2
                )
            )
        ).Show()

        EquationItem(
            line =

            EquationItem(
                line = listOf(
                    EquationItem(
                        line = "x",
                        superscript = "2"
                    ),
                    " + ",
                    EquationItem(
                        line = "4",
                        superscript = EquationItem(
                            line = "1 + 1234",
                            underline = "2",
                            sqrt = 2
                        )
                    )
                ),
                sqrt = 2,
                underline = "hello"

            ),
            underline = EquationItem(
                line = "some value",
                sqrt = 2
            )
        ).Show()


        EquationItem(
            line = listOf(
                "f(x) =",
                EquationItem(
                    line = "ax",
                    superscript = "2",
                    sqrt = 2
                ),
                " + bx + c"
            ),
        ).Show(
            FontParams(
                fontSize = 30.sp,
                fontFamily = FontFamily.Cursive,
                fontStyle = FontStyle.Normal
            )
        )
    }
}

@Preview
@Composable
private fun Preview() {
    F()
}
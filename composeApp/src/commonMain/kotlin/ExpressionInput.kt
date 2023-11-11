import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ExpressionInput() {
	var inputExpression by remember { mutableStateOf("") }
	val focusRequester = remember { FocusRequester() }

	TextField(
		value = inputExpression,
		onValueChange = { inputExpression = it },
		label = null,
		colors = TextFieldDefaults.colors(
			focusedContainerColor = Color.Transparent,
			unfocusedContainerColor = Color.Transparent,
			focusedIndicatorColor = Color.Transparent,
			unfocusedIndicatorColor = Color.Transparent,
		),
		maxLines = 1,
		modifier = Modifier
			.padding(16.dp)
			.focusRequester(focusRequester)
			.fillMaxWidth()
	)

	LaunchedEffect(Unit) {
		focusRequester.requestFocus()
	}
}

@Preview
@Composable
fun PreviewExpressionInput() {
	DeskTheme(darkTheme = false) {
		ExpressionInput()
	}
	DeskTheme(darkTheme = true) {
		ExpressionInput()
	}
}
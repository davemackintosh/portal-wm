import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ExpressionInput() {
	var inputExpression by remember { mutableStateOf("") }
	val focusRequester = remember { FocusRequester() }

	TextField(
		value = inputExpression,
		onValueChange = { inputExpression = it },
		label = { Text("Enter expression") },
		maxLines = 1,
		modifier = Modifier
			.padding(20.dp)
			.focusRequester(focusRequester)
	)

	LaunchedEffect(Unit) {
		focusRequester.requestFocus()
	}
}

@Preview
@Composable
fun PreviewExpressionInput() {
	DeskTheme {
		ExpressionInput()
	}
}
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.KeyEvent
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.unit.dp
import co.dav3.desk.ExpressionState

@Composable
fun ExpressionInput(
	expressionState: ExpressionState,
	onValueChange: (String) -> Unit,
	onKeyEvent: (KeyEvent) -> Boolean
) {
	val focusRequester = remember { FocusRequester() }

	TextField(
		value = expressionState.expression,
		onValueChange = { value -> onValueChange(value) },
		label = null,
		colors = TextFieldDefaults.colors(
			focusedContainerColor = Color.Transparent,
			unfocusedContainerColor = Color.Transparent,
			focusedIndicatorColor = Color.Transparent,
			unfocusedIndicatorColor = Color.Transparent,
		),
		maxLines = 1,
		modifier = Modifier
			.padding(8.dp)
			.focusRequester(focusRequester)
			.fillMaxWidth()
			.onKeyEvent { onKeyEvent(it) }
	)

	LaunchedEffect(Unit) {
		focusRequester.requestFocus()
	}
}

//@Preview
//@Composable
//fun PreviewExpressionInput() {
//	DeskTheme(darkTheme = false) {
//		ExpressionInput()
//	}
//	DeskTheme(darkTheme = true) {
//		ExpressionInput()
//	}
//}
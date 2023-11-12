import adapters.AndroidAppsResultsAdapter
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyEvent
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import co.dav3.desk.ExpressionState
import ports.ResultsPort


@Composable
fun App() {
	val context = LocalContext.current
	val pm = context.packageManager
	var expressionState by remember {
		mutableStateOf(ExpressionState())
	}
	val ports = listOf<ResultsPort>(
		AndroidAppsResultsAdapter(pm)
	)

	fun handleSelectedIndexChange(difference: Int) {
//		var newIndex = expressionState.selectedIndex + difference
//		val totalResults = expressionState.results.flatMap { it.value }
//			.count()
//
//		if (newIndex > totalResults) {
//			newIndex = 0
//		} else if (newIndex < 0) {
//			newIndex = totalResults - 1
//		}
		print(difference)
//		expressionState.selectedIndex = newIndex
	}

	fun handleKeyboard(keyEvent: KeyEvent): Boolean {
		return when (keyEvent.key) {
			Key.DirectionUp -> {
				handleSelectedIndexChange(-1)
				false
			}

			Key.DirectionDown -> {
				handleSelectedIndexChange(1)
				false
			}

			else -> true
		}
	}


	Column(
		modifier = Modifier
			.fillMaxSize()
			.onKeyEvent { keyEvent -> handleKeyboard(keyEvent) },
		verticalArrangement = Arrangement.Top,
		horizontalAlignment = Alignment.CenterHorizontally
	) {
		Container {
			ExpressionInput(expressionState, onValueChange = {
				expressionState = expressionState.copy(
					expression = it,
					selectedIndex = 0
				)
			}, onKeyEvent = { handleKeyboard(it) })
			ExpressionResultsList(expressionState, ports)
			Footer(expressionState)
		}
	}
}


@Preview
@Composable
fun AppPreview() {
	App()
}
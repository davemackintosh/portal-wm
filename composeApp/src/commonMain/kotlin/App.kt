import android.content.Intent
import android.content.pm.PackageManager
import android.content.pm.ResolveInfo
import android.os.Build
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyEvent
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import co.dav3.desk.ExpressionResult
import co.dav3.desk.ExpressionResultState
import co.dav3.desk.ExpressionResultType


@Composable
fun App() {
	val expressionResult = ExpressionResultState()

	fun setResultsForCategory(category: ExpressionResultType, results: List<ExpressionResult>) {
		expressionResult.results[category] = results
	}

	fun handleSelectedIndexChange(difference: Int) {
		var newIndex = expressionResult.selectedIndex + difference
		val totalResults = expressionResult.results.flatMap { it.value }
			.count()

		if (newIndex > totalResults) {
			newIndex = 0
		} else if (newIndex < 0) {
			newIndex = totalResults - 1
		}

		expressionResult.selectedIndex = newIndex
	}

	fun handleKeyboard(keyEvent: KeyEvent): Boolean {
		return when (keyEvent.key) {
			Key.DirectionUp -> {
				handleSelectedIndexChange(-1)
				true
			}

			Key.DirectionDown -> {
				handleSelectedIndexChange(1)
				true
			}

			else -> true
		}
	}

	val context = LocalContext.current
	val pm = context.packageManager
	val intent = Intent(Intent.ACTION_MAIN, null)
	intent.addCategory(Intent.CATEGORY_LAUNCHER)
	val appList: List<ResolveInfo> =
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
			context.packageManager.queryIntentActivities(
				Intent(Intent.ACTION_MAIN, null).addCategory(Intent.CATEGORY_LAUNCHER),
				PackageManager.ResolveInfoFlags.of(PackageManager.MATCH_DEFAULT_ONLY.toLong())
			)
		} else {
			context.packageManager.queryIntentActivities(
				Intent(Intent.ACTION_MAIN, null).addCategory(Intent.CATEGORY_LAUNCHER),
				PackageManager.GET_META_DATA
			)
		}
	if (appList.isNotEmpty()) {
		setResultsForCategory(
			category = ExpressionResultType.APP,
			results = appList.map { app ->
				ExpressionResult(
					app.loadLabel(pm).toString(),
					app.activityInfo.applicationInfo.loadIcon(pm),
					app.activityInfo.splitName,
					ExpressionResultType.APP
				)
			}
		)
	}

	Column(
		modifier = Modifier
			.fillMaxSize()
			.onKeyEvent { keyEvent -> handleKeyboard(keyEvent) },
		verticalArrangement = Arrangement.Top,
		horizontalAlignment = Alignment.CenterHorizontally
	) {
		Container {
			ExpressionInput(expressionResult)
			ExpressionResultsList(expressionResult)
			Footer(expressionResult)
		}
	}
}


@Preview
@Composable
fun AppPreview() {
	App()
}
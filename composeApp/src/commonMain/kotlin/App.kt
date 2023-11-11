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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun App() {
	val context = LocalContext.current
	val pm = context.packageManager
	val intent = Intent(Intent.ACTION_MAIN, null)
	intent.addCategory(Intent.CATEGORY_LAUNCHER)
	val appList: List<ResolveInfo> = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
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
	val apps = appList.map { app ->
		ExpressionResult(
			app.loadLabel(pm).toString(),
			app.activityInfo.applicationInfo.loadIcon(pm),
			null

		)
	}

	Column(
		modifier = Modifier.fillMaxSize(),
		verticalArrangement = Arrangement.Center,
		horizontalAlignment = Alignment.CenterHorizontally
	) {
		Container {
			Column {
				ExpressionInput()
				ExpressionResultsList(results = apps)
			}
		}
	}
}

@Preview
@Composable
fun AppPreview() {
	App()
}
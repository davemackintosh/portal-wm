package adapters

import android.content.Intent
import android.content.pm.PackageManager
import android.content.pm.ResolveInfo
import android.os.Build
import co.dav3.desk.ExpressionResult
import co.dav3.desk.ExpressionState
import co.dav3.desk.ExpressionResultType

class AndroidAppsResultsAdapter(private val pm: PackageManager) : ports.ResultsPort {
	override fun getResults(expressionState: ExpressionState): List<ExpressionResult> {
		val intent = Intent(Intent.ACTION_MAIN, null)
		intent.addCategory(Intent.CATEGORY_LAUNCHER)
		val appList: List<ResolveInfo> =
			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
				pm.queryIntentActivities(
					Intent(Intent.ACTION_MAIN, null).addCategory(Intent.CATEGORY_LAUNCHER),
					PackageManager.ResolveInfoFlags.of(PackageManager.MATCH_DEFAULT_ONLY.toLong())
				)
			} else {
				pm.queryIntentActivities(
					Intent(Intent.ACTION_MAIN, null).addCategory(Intent.CATEGORY_LAUNCHER),
					PackageManager.GET_META_DATA
				)
			}

		val allApps = appList.map { app ->
			ExpressionResult(
				app.loadLabel(pm).toString(),
				app.activityInfo.applicationInfo.loadIcon(pm),
				app.activityInfo.splitName,
				ExpressionResultType.APP
			)
		}

		return if (expressionState.expression != "") {
			allApps.filter { it.name.contains(expressionState.expression) }
		} else {
			allApps
		}
	}

	override fun getCategory(): ExpressionResultType {
		return ExpressionResultType.APP
	}
}
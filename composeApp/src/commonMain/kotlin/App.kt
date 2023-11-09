import android.content.pm.PackageManager
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.ExperimentalResourceApi

@OptIn(ExperimentalResourceApi::class)
@Composable
fun App() {
    var inputExpression by remember { mutableStateOf("") }
    val focusRequester = remember { FocusRequester() }
    val context = LocalContext.current
    val packages = context.packageManager.getInstalledApplications(PackageManager.GET_META_DATA)
    val backgroundColour = Color(1.0f, 1.0f, 1.0f, 0.6f)
    val boxStyles =
            Modifier.padding(20.dp)
                    .background(color = backgroundColour, shape = RoundedCornerShape(16.dp))

    val pm = context.packageManager
    val resources = pm.getResourcesForApplication(resolveInfo.activityInfo.applicationInfo)
    //    val appName =
    //            if (resolveInfo.activityInfo.labelRes != 0) {
    //                // getting proper label from resources
    //                resources.getString(resolveInfo.activityInfo.labelRes)
    //            } else {
    //                // getting it out of app info - equivalent to
    //                // context.packageManager.getApplicationInfo
    //                resolveInfo.activityInfo.applicationInfo.loadLabel(pm).toString()
    //            }
    //    val packageName = resolveInfo.activityInfo.packageName
    //    val iconDrawable = resovleInfo.activityInfo.loadIcon(pm)
    val pm = context.packageManager
    val mainIntent = Intent(Intent.ACTION_MAIN, null)
    mainIntent.addCategory(Intent.CATEGORY_LAUNCHER)

    val resolvedInfos =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                pm.queryIntentActivities(mainIntent, PackageManager.ResolveInfoFlags.of(0L))
            } else {
                pm.queryIntentActivities(mainIntent, 0)
            }

    MaterialTheme {
        Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(boxStyles) {
                Box(boxStyles) {
                    TextField(
                            value = inputExpression,
                            onValueChange = { inputExpression = it },
                            label = { Text("Enter expression") },
                            maxLines = 1,
                            modifier = Modifier.padding(20.dp).focusRequester(focusRequester)
                    )
                }
            }
        }
    }
}

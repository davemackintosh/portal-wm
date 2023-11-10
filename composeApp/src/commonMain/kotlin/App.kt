import android.content.Intent
import android.content.pm.PackageManager
import android.content.pm.ResolveInfo
import android.os.Build
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.ExperimentalResourceApi

@OptIn(ExperimentalResourceApi::class)
@Composable
fun App() {
    var inputExpression by remember { mutableStateOf("") }
    val focusRequester = remember { FocusRequester() }
    val context = LocalContext.current
    val backgroundColour = Color(1.0f, 1.0f, 1.0f, 0.6f)
    val boxStyles =
        Modifier
            .padding(16.dp)
            .background(color = backgroundColour, shape = RoundedCornerShape(16.dp))

    val pm = context.packageManager
    val intent = Intent(Intent.ACTION_MAIN, null)
    intent.addCategory(Intent.CATEGORY_LAUNCHER)
    val appList: List<ResolveInfo> = pm.queryIntentActivities(
        intent,
        0
    )
    val apps = appList.map { app -> ExpressionResult(
        app.loadLabel(pm).toString(),
        app.icon,
        null

    )}

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
                            modifier = Modifier
                                .padding(20.dp)
                                .focusRequester(focusRequester)
                    )
                }
            }
        }
        ExpressionResultsList(results = apps)
    }
}

@Preview
@Composable
fun AppPreview() {
    App()
}
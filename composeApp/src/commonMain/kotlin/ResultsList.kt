import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.getDrawable
import androidx.core.graphics.drawable.toBitmap
import co.dav3.desk.R
import org.jetbrains.compose.resources.ExperimentalResourceApi

data class ExpressionResult (
	var name: String,
	var icon: Drawable,
	var meta: String?,
)

@OptIn(ExperimentalResourceApi::class)
@Composable
fun ExpressionResultsList(results: List<ExpressionResult>) {
	LazyColumn {
		items(results) { result ->
			ExpressionResultListItem(result = result)
		}
	}
}

@OptIn(ExperimentalResourceApi::class)
@Composable
fun ExpressionResultListItem(result: ExpressionResult) {
	Row(modifier = Modifier.padding(0.dp), verticalAlignment = Alignment.CenterVertically) {
		Image(
			result.icon.toBitmap(config = Bitmap.Config.ARGB_8888).asImageBitmap()
			, contentDescription = "Image", modifier = Modifier
				.size(50.dp)

		)
		Text(result.name)
	}
}

@Preview
@Composable
fun PreviewExpressionResultListItem() {
	val result = ExpressionResult(
		"Test 1",
		LocalContext.current.getDrawable(R.drawable.ic_launcher_foreground)!!,
		null
	)

	ExpressionResultListItem(result = result)
}

@Preview
@Composable
fun PreviewExpressionResultsList() {
	val results = listOf(ExpressionResult(
		"Test 1",
		LocalContext.current.getDrawable(R.drawable.ic_launcher_foreground)!!,
		null
		),ExpressionResult(
		"Test 2",
		LocalContext.current.getDrawable(R.drawable.ic_launcher_foreground)!!,
		null
	),ExpressionResult(
		"Test 3",
		LocalContext.current.getDrawable(R.drawable.ic_launcher_foreground)!!,
		null
	))
	ExpressionResultsList(results = results)
}
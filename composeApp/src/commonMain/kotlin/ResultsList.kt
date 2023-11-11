import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.drawable.toBitmap
import co.dav3.desk.R

enum class ExpressionResultType {
	APP, MATHEMATICAL, TERMUX_COMMAND, VISUAL_MEDIA, ASSISTANT
}

data class ExpressionResult(
	var name: String,
	var icon: Drawable? = null,
	var meta: String? = null,
	var type: ExpressionResultType? = ExpressionResultType.APP
)

@Composable
fun ExpressionResultsList(results: List<ExpressionResult>, selectedIndex: Int = 0) {
	LazyColumn(
		modifier = Modifier.fillMaxWidth()
	) {
		itemsIndexed(results) { index, result ->
			ExpressionResultListItem(result = result, highlighted = selectedIndex == index)
		}
	}
}

@Composable
fun ExpressionResultListItem(result: ExpressionResult, highlighted: Boolean = false) {
	Row(
		modifier = Modifier
			.background(
				color = if (highlighted) {
					MaterialTheme.colorScheme.onBackground.copy(alpha = 0.25f)
				} else {
					Color.Transparent
				}, shape = RoundedCornerShape(6.dp)
			)
			.padding(16.dp)
			.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically
	) {
		result.icon?.toBitmap(config = Bitmap.Config.ARGB_8888)?.let {
			Image(
				it.asImageBitmap(),
				contentDescription = "Image",
				Modifier
					.width(18.dp)
			)
		}
		Column(
			modifier = Modifier
				.fillMaxWidth()
				.padding(start = 16.dp, top = 0.dp, bottom = 0.dp, end = 8.dp)
		) {
			Row {
				print(MaterialTheme.colorScheme.onBackground)
				Text(
					result.name,
					color = MaterialTheme.colorScheme.onBackground,
					fontSize = 18.0.sp,
					fontWeight = FontWeight.Medium
				)
				result.meta?.let {
					Text(
						it,
						color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f),
						fontSize = 18.0.sp,
						fontWeight = FontWeight.Medium
					)
				}
				result.type?.let {
					Text(
						it.name.lowercase().replaceFirstChar(Char::titlecase),
						color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f),
						fontSize = 18.0.sp,
						fontWeight = FontWeight.Medium,
						textAlign = TextAlign.End,
						modifier = Modifier.fillMaxWidth()
					)
				}
			}
		}
	}
}

@Preview
@Composable
fun PreviewExpressionResultListItem() {
	val result = ExpressionResult(
		"Toggle System Appearance",
		LocalContext.current.getDrawable(R.drawable.ic_launcher_foreground),
		null,
		null
	)

	DeskTheme {
		ExpressionResultListItem(result = result)
	}
}

@Preview
@Composable
fun PreviewExpressionResultsList() {
	val results = listOf(
		ExpressionResult(
			"Toggle System Appearance",
			LocalContext.current.getDrawable(R.drawable.ic_launcher_foreground),
			null,
			ExpressionResultType.APP
		), ExpressionResult(
			"Test 2", null, null, null
		), ExpressionResult(
			"Test 3",
			LocalContext.current.getDrawable(R.drawable.ic_launcher_foreground),
			null,
			null
		)
	)
	DeskTheme {
		ExpressionResultsList(results = results)
	}
}
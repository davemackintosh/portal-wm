import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.materialIcon
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.core.graphics.ColorUtils

fun getTextFromType(type: ExpressionResultType): String {
	return when (type) {
		ExpressionResultType.APP -> "Open app"
		ExpressionResultType.MATHEMATICAL -> "Copy result"
		ExpressionResultType.TERMUX_COMMAND -> "Execute in Termux"
	}
}

@Composable
fun Footer(selectedMode: ExpressionResultType) {
	Row(
		modifier = Modifier
			.fillMaxWidth()
			.background(
				color = Color(
					ColorUtils.blendARGB(
						MaterialTheme.colorScheme.background.toArgb(),
						Color.Black.toArgb(),
						0.5f
					)
				)
			)
			.padding(horizontal = 16.0.dp, vertical = 8.0.dp),
		horizontalArrangement = Arrangement.End
	) {
		Text(
			getTextFromType(selectedMode),
			color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.75f),
			textAlign = TextAlign.Center
		)
		Icon(
			Icons.Filled.ArrowForward,
			contentDescription = getTextFromType(selectedMode),
			tint = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.75f)
		)
	}
}
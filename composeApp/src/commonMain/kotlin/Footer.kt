import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.core.graphics.ColorUtils

@Composable
fun Footer() {
	Row(
		modifier = Modifier
			.fillMaxWidth()
			.background(
				color = Color(ColorUtils.blendARGB(
					MaterialTheme.colorScheme.background.toArgb(),
					Color.Black.toArgb(),
					0.5f
				))
			)
			.padding(horizontal = 16.0.dp, vertical = 8.0.dp),
		horizontalArrangement = Arrangement.End
	) {
		Text(
			"Open App",
			color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.75f),
			textAlign = TextAlign.Center
		)
	}
}
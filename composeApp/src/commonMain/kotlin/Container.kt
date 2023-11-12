import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun Container(content: @Composable () -> Unit) {
	val colorStops = arrayOf(
		0.0f to Color.White.copy(alpha = 0.1f),
		0.5f to Color.Transparent,
		1f to Color.White.copy(alpha = 0.1f)
	)
	Column(
		modifier = Modifier
			.fillMaxWidth()
			.background(color = MaterialTheme.colorScheme.background.copy(alpha = 0.75f))
			.border(width = 1.0.dp, brush = Brush.horizontalGradient(colorStops = colorStops), shape = RoundedCornerShape(8.dp))
	) {
			content()
	}
}

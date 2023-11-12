package co.dav3.desk

import android.app.Application
import android.graphics.drawable.Drawable
import android.content.Intent
import android.content.pm.PackageManager
import android.content.pm.ResolveInfo
import android.os.Build
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

enum class ExpressionResultType(var value: String) {
	APP("App"),
	MATHEMATICAL("Math"),
	TERMUX_COMMAND("Termux command"),
	VISUAL_MEDIA("Media"),
	ASSISTANT("Assistant")
}

data class ExpressionResult(
	var name: String,
	var icon: Drawable? = null,
	var meta: String? = null,
	var type: ExpressionResultType? = ExpressionResultType.APP
)

data class ExpressionResultState(
	var expression: String? = null,
	var selectedIndex: Int = 0,
	var results: MutableMap<ExpressionResultType, List<ExpressionResult>> = mutableMapOf()
)

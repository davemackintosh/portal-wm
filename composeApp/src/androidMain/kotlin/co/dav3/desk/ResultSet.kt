package co.dav3.desk

import android.graphics.drawable.Drawable

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

) {
	fun getCurrentlySelected(): ExpressionResult? {
		val keysAsList = results.keys.toList()
		val keyIndex = selectedIndex % keysAsList.size
		val keyAtIndex = keysAsList[keyIndex]
		val listAtIndex = results[keyAtIndex]

		return if (listAtIndex != null) {
			val itemIndex = selectedIndex / keysAsList.size
			if (itemIndex < listAtIndex.size) {
				listAtIndex[itemIndex]
			} else {
				null
			}
		} else {
			null
		}
	}
}

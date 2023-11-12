package ports

import co.dav3.desk.ExpressionResult
import co.dav3.desk.ExpressionState
import co.dav3.desk.ExpressionResultType

interface ResultsPort {
	fun getResults(expressionState: ExpressionState): List<ExpressionResult>
	fun getCategory(): ExpressionResultType
}
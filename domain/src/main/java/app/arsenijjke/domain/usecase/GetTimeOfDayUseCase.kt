package app.arsenijjke.domain.usecase

import java.util.Calendar
import javax.inject.Inject

//TODO("Implement Enum instead of String, and pass object, in feature module return string from resources for multilanguage support")
class GetTimeOfDayUseCase @Inject constructor() {
    operator fun invoke(): String {
        val hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
        return when (hour) {
            in 6..11 -> "Morning"
            in 12..17 ->  "Afternoon"
            in 18..21 -> "Evening"
            else -> "Night"
        }
    }
}

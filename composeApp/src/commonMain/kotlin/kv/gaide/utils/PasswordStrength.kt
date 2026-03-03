package kv.gaide.utils

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

enum class PasswordStrength(val message: String) {
    BLANK(""),
    WEAK("Слабый"),
    MEDIUM("Средний"),
    STRONG("Сильный"),
    VERY_STRONG("Идеальный")
}

fun String.passwordStrength(): PasswordStrength {
    val hasUpperCase = this.any { it.isUpperCase() }
    val hasLowerCase = this.any { it.isLowerCase() }
    val hasDigit = this.any { it.isDigit() }
    val hasSpecialCharacter = this.any { "!@#\$%^&*(),.?\":{}|<>".contains(it) }

    return when {
        this.isBlank() -> PasswordStrength.BLANK
        hasUpperCase && hasLowerCase && (hasDigit || hasSpecialCharacter) && length >= 8 ->
            PasswordStrength.VERY_STRONG
        hasUpperCase && hasLowerCase && length >= 8 ->
            PasswordStrength.STRONG
        hasLowerCase && length >= 8 ->
            PasswordStrength.MEDIUM
        else ->
            PasswordStrength.WEAK
    }
}

@Composable
fun getColorForStrength(strength: PasswordStrength): Color {
    return when (strength) {
        PasswordStrength.WEAK -> MaterialTheme.colorScheme.error
        PasswordStrength.MEDIUM -> MaterialTheme.colorScheme.secondary
        else -> MaterialTheme.colorScheme.primary
    }
}
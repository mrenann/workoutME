package br.mrenann.workoutme.domain.model

data class UserUI(
    val id: String?,
    val userId: String,
    val displayName: String,
    val isPersonal: Boolean,
) {
    fun toMap(): MutableMap<String, Any> {
        return mutableMapOf(
            "user_id" to this.userId,
            "display_name" to this.displayName,
            "is_personal" to this.isPersonal,
        )
    }
}
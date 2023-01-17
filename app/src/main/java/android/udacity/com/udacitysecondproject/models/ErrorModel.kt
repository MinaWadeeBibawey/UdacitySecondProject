package android.udacity.com.udacitysecondproject.models

data class ErrorModel(val error: Status)

data class Status(
    val code: Int,
    val message: String
)
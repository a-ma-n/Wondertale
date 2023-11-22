package com.aman.wondertale.repository.model

import com.google.gson.annotations.SerializedName

/**
 * @author aman
 */
data class GrammarResponseModel(
    val message: String = "",
    val isCorrect: Boolean = false,
)

data class GrammarResponseModelList(
    val grammarList: List<GrammarError>? = null
)

data class GrammarError(
    @SerializedName("error")
    val error: String
){
    override fun toString(): String {
        return error
    }
}
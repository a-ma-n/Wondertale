package com.aman.wondertale.ui.grammar

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aman.wondertale.repository.Repository
import com.aman.wondertale.repository.model.GrammarResponseModel
import kotlinx.coroutines.launch

/**
 * @author aman
 */
class GrammarViewModel : ViewModel() {
    private val repository = Repository()
    val isListening = MutableLiveData(false)
    val response = MutableLiveData<GrammarResponseModel?>(null)

    fun checkGrammar(answer: String) {
        viewModelScope.launch {
            try {
                val res = repository.checkGrammar(answer.replaceFirstChar { it.uppercase() })
                if (res.isSuccessful) {
                    res.body()?.let { list ->
                        if (list.isNullOrEmpty()) {
                            response.value =
                                GrammarResponseModel(
                                    "Well done! Keep practising…",
                                    true
                                )
                        } else {
                            val msg = list.joinToString(
                                separator = "\n",
                                prefix = "- ",
                                transform = { it.error }
                            )
                            response.value =
                                GrammarResponseModel(
                                    msg,
                                    false,
                                )
                        }
                    } ?: run {
                        response.value =
                            GrammarResponseModel(
                                "Well done! Keep practising…",
                                true
                            )
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
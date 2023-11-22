package com.aman.wondertale.repository

import com.aman.wondertale.RetrofitService

/**
 * @author aman
 */
class Repository {
    private val service by lazy { RetrofitService.getInstance() }

    suspend fun getSounds(text: String) = service.getSounds(hashMapOf("text" to text))

    suspend fun getSound(text: String) = service.getSound(hashMapOf("text" to text))

    suspend fun checkGrammar(text: String) = service.checkGrammar(hashMapOf("text" to text))

    suspend fun getKeywords(text: String) = service.getKeywords(hashMapOf("text" to text))
}
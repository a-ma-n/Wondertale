package com.aman.wondertale

import com.localebro.okhttpprofiler.OkHttpProfilerInterceptor
import com.aman.wondertale.repository.model.GrammarError
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST


/**
 * @author aman
 */
interface RetrofitService {

    @POST("/keywords")
    suspend fun getKeywords(@Body map: HashMap<String, String>): Response<List<String>>

    @POST("/sound")
    suspend fun getSound(@Body map: HashMap<String, String>): Response<Map<String, String>>

    @POST("/sounds")
    suspend fun getSounds(@Body map: HashMap<String, String>): Response<Map<String, String>>

    @POST("/grammar")
    suspend fun checkGrammar(@Body map: HashMap<String, String>): Response<List<GrammarError>>

    companion object {
        fun getInstance(): RetrofitService {
            val builder = OkHttpClient.Builder()
            val interceptor = HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }
            if (BuildConfig.DEBUG) {
                builder.addInterceptor(interceptor)
                builder.addInterceptor(OkHttpProfilerInterceptor())
            }
            val client = builder.build()
            return Retrofit.Builder()
                .baseUrl("https://learn-with-us.azurewebsites.net/")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(RetrofitService::class.java)
        }
    }
}
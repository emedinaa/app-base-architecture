package com.emedinaa.basearchitecture

import okhttp3.OkHttpClient
import okhttp3.Request

private const val URL = "https://emedinaa.azurewebsites.net"

class RemoteApi {

    private val httpClient by lazy {
        OkHttpClient.Builder()
            .build()
    }

    fun buildGetRequest(api: String): Request {
        return Request.Builder().apply {
            get()
            url(URL.plus(api))
        }.build()
    }

    fun callService(request: Request) = httpClient.newCall(request).execute()
}
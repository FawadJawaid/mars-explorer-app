package com.fawadjmalik.data.remote.network


import com.fawadjmalik.marsexplorer.data.model.MarsPhotosResponse
import com.fawadjmalik.marsexplorer.core.utils.Constants.KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Author: Muhammad Fawad Jawaid Malik
 * This is the interface which defines the webservice for getting mars photos and its information.
 */
interface MarsPhotosApi {
    @GET("rovers/curiosity/photos")
    suspend fun fetchPhotos(
        @Query("sol") sol: Int = 1000,
        @Query("api_key") apiKey: String = KEY
    ): Response<MarsPhotosResponse>
}

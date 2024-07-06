package com.mkrdeveloper.retrofitpostjetpack.data

import com.mkrdeveloper.retrofitpostjetpack.models.UserListItem
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path


interface ApiInterface {

    @POST("posts")
    suspend fun createPost(
        @Body user : UserListItem

    ):Response<UserListItem>

    @FormUrlEncoded
    @POST("posts")
    suspend fun createUrlPost(
        @Field("userId") userId : Int,
        @Field("title") title : String,
        @Field("body") body : String,
    ):Response<UserListItem>

    @PUT("posts/{id}")
    suspend fun putData(
        @Path("id") id: Int,
        @Body user : UserListItem
    ):Response<UserListItem>

    @PATCH("posts/{id}")
    suspend fun patchData(
        @Path("id") id: Int,
        @Body user : UserListItem
    ):Response<UserListItem>

    @DELETE("posts/{id}")
    suspend fun deleteUser(
        @Path("id") id: Int
    ):Response<UserListItem>


}
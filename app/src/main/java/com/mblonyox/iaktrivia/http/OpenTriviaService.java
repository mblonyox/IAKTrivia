package com.mblonyox.iaktrivia.http;

import com.mblonyox.iaktrivia.model.ResponseCategories;
import com.mblonyox.iaktrivia.model.ResponseToken;
import com.mblonyox.iaktrivia.model.ResponseTrivia;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Sukirno on 17/02/2018.
 */

public interface OpenTriviaService {
    @GET("api_category.php")
    Call<ResponseCategories> listCategories();

    @GET("api_token.php?command=request")
    Call<ResponseToken> requestToken();

    @GET("api_token.php?command=reset")
    Call<ResponseToken> resetToken(@Query("token") String token);

    @GET("api.php")
    Call<ResponseTrivia> getTrivia(
            @Query("amount") int amount,
            @Query("category") int category_id,
            @Query("token") String token
    );
}

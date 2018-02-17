package com.mblonyox.iaktrivia.http;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Sukirno on 17/02/2018.
 */

public class OpenTriviaAPI {

    private static final String OPEN_TRIVIA_URL = "https://opentdb.com/";
    private static OpenTriviaService client;

    public static OpenTriviaService getClient() {
        if (client == null) {
            setupClient();
        }
        return client;
    }

    private static void setupClient() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(OPEN_TRIVIA_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        client = retrofit.create(OpenTriviaService.class);
    }
}

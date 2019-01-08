package com.drivingschool.android.restclient;

import com.drivingschool.android.R;
import com.drivingschool.android.response.feedback.FeedbackSuccess;
import com.drivingschool.android.response.instructorList.InstructorData;
import com.drivingschool.android.response.register.RegisterResponse;
import com.drivingschool.android.response.schooldata.SchoolSuccess;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.HashMap;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public class RestClient {

    private static GitApiInterface gitApiInterface;

    private static String baseUrl = String.valueOf(R.string.api_url);

    public static GitApiInterface getClient() {

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        //The logging interceptor will be added to the http client

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(logging);
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        //The Retrofit builder will have the client attached, in order to get connection logs
        Retrofit retrofit = new Retrofit.Builder()
                .client(httpClient.build())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(baseUrl)
                .build();


        gitApiInterface = retrofit.create(GitApiInterface.class);

        return gitApiInterface;


    }

    public interface GitApiInterface {

        @POST("register")
        Call<RegisterResponse> register(@Body HashMap<String, String> hashMap);

        @GET("schools")
        Call<SchoolSuccess> schoolList();

        @POST("contact")
        Call<FeedbackSuccess> sendFeedback(@Body HashMap<String,String> hashMap);

        @GET("school/1/instructors")
        Call<InstructorData> instructorsList();

    }
}


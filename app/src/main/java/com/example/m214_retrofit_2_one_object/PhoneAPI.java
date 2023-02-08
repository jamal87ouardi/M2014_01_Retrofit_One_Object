package com.example.m214_retrofit_2_one_object;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PhoneAPI {

    @GET("v3/47c41a0d-10e4-425c-b8aa-0fa27bfff31b")
    Call<Phone> getPhone();
}

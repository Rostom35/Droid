package com.example.adou.mydrone.consumers;

import com.example.adou.mydrone.dto.Request.KeyPasswordDto;
import com.example.adou.mydrone.dto.Request.UserDto;
import com.example.adou.mydrone.constants.Endpoints;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by hakima on 3/12/18.
 */

public interface AccountConsumer extends Consumer{
    @POST(Endpoints.REGISTER)
    Call<Integer> register(UserDto user);

    @GET(Endpoints.ACTIVATE)
    Call<Void> activate(@Query("key") String key);

    @GET(Endpoints.IS_AUTHENTICATED)
    Call<String> isAuthenticated();

    @GET(Endpoints.ACCOUNT)
    Call<UserDto>  account();

    @POST(Endpoints.ACCOUNT)
    Call<Void> saveAccount(UserDto userDTO);

    @POST(Endpoints.ACCOUNT_CHANGE_PWD)
    Call<Void> changePwd(String pwd);

    @POST(Endpoints.ACCOUNT_RESET_PWD_INIT)
    Call<Void> requestPwdReset(String mail);

    @POST(Endpoints.ACCOUNT_RESET_PWD_FINISH)
    Call<Void> finishPwdReset(KeyPasswordDto keyAndPassword);


}

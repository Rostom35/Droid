package com.example.adou.mydrone.consumers;

import com.example.adou.mydrone.constants.Endpoints;
import com.example.adou.mydrone.dto.LocationDto;
import com.example.adou.mydrone.dto.Request.MissionDto;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.PartMap;
import retrofit2.http.Path;

/**
 * Created by hakima on 3/13/18.
 */

public interface MissionRessourceConsumer extends Consumer {

    @POST(Endpoints.MISSIONS)
    Call<MissionDto> createMission(MissionDto mission);

    @GET(Endpoints.MISSIONS)
    Call<List<MissionDto>> getAllMissions(/*@Path("id") String toto,*/ MissionDto mission);

    @GET(Endpoints.MISSIONS)
    Call<MissionDto> getMission(MissionDto missionDto);

    @PUT(Endpoints.MISSIONS)
    Call<MissionDto> updateMission(MissionDto mission);

    @PUT(Endpoints.MISSIONS_ORDER_MOVE)
    Call<MissionDto> orderMove(@Path("id") String id, LocationDto location);

    @PUT(Endpoints.MISSION_ORDER_MOVE_PICTURE)
    Call<MissionDto> orderMovePicture(@Path("id") String id, LocationDto locationDto);










}

package com.pranjal.safetydevice.API.Service;

import com.pranjal.safetydevice.API.Response.LocationOfUserIndividualResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by royalpranjal on 11/23/2016.
 */

public interface LocationOfUsersService {
    @GET("/locationofusers/")
    Call<List<LocationOfUserIndividualResponse>> location();
}

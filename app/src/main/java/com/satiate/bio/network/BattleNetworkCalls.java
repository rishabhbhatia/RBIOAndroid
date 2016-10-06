package com.satiate.bio.network;

import com.satiate.bio.utils.Const;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Rishabh Bhatia on 10/7/2016.
 */

public interface BattleNetworkCalls {

    @GET(Const.GET_USERS)
    Call<JSONObject> getTaskList();
}

package com.satiate.bio.events;

import retrofit2.Response;

/**
 * Created by Rishabh Bhatia on 5/30/2016.
 */
public class RetrofitSuccessEvent {

    private Response response;
    private String tag;

    public RetrofitSuccessEvent(Response response, String tag) {
        try {
            this.response = response;
            this.tag = tag;
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}

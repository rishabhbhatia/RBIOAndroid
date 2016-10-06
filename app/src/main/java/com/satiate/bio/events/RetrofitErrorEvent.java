package com.satiate.bio.events;


import com.satiate.bio.models.Error;

/**
 * Created by Rishabh Bhatia on 5/30/2016.
 */
public class RetrofitErrorEvent {

    private Error apiError;
    private String tag;

    public RetrofitErrorEvent(Error apiError, String tag) {
        try {
            this.apiError = apiError;
            this.tag = tag;
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public Error getApiError() {
        return apiError;
    }

    public void setApiError(Error apiError) {
        this.apiError = apiError;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}

package com.satiate.bio.network;

import android.content.Context;
import android.util.Log;

import com.satiate.bio.events.RetrofitErrorEvent;
import com.satiate.bio.events.RetrofitSuccessEvent;
import com.satiate.bio.models.Error;
import com.satiate.bio.utils.Const;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Rishabh Bhatia on 10/7/2016.
 */
public class RetrofitNetworkCalls {

    public static void makeRetrofitCall(final Context context, Call call, final String tag) {

        try {
            final EventBus eventBus = EventBus.getDefault();
            Log.d(Const.TAG, "hitting it with api: " + call.request().toString());

            call.enqueue(new Callback() {
                @Override
                public void onResponse(Call call, Response response) {

                    if (response.isSuccessful()) {
                        eventBus.post(new RetrofitSuccessEvent(response, tag));
                    } else {
                        try {
                            Error apiError = null;
                            apiError = RetrofitErrorUtils.parseErrorResponse(response.errorBody().string());
                            eventBus.post(new RetrofitErrorEvent(apiError, tag));

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }

                @Override
                public void onFailure(Call call, Throwable t) {
                    Error apiError = new Error(-1, t.getMessage());
                    eventBus.post(new RetrofitErrorEvent(apiError, tag));
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

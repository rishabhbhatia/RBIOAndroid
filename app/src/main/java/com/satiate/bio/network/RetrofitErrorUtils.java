package com.satiate.bio.network;

import org.json.JSONObject;
import com.satiate.bio.models.Error;
import com.satiate.bio.utils.Const;


/**
 * Created by Rishabh Bhatia on 5/30/2016.
 */
public class RetrofitErrorUtils {

    public static Error parseErrorResponse(String errorResponse) {
        Error error = new Error();

        try {

            JSONObject errorJsonObject = new JSONObject(errorResponse);

            JSONObject errorObject = errorJsonObject.getJSONObject(Const.KEY_ERROR_JSON_OBJECT);

            if (errorObject.has(Const.KEY_ERROR_NAME)) {
                error.setName(errorObject.getString(Const.KEY_ERROR_NAME));
            }

            if (errorObject.has(Const.KEY_ERROR_STATUS)) {
                error.setStatus(errorObject.getInt(Const.KEY_ERROR_STATUS));
            }

            if (errorObject.has(Const.KEY_ERROR_MESSAGE)) {
                error.setMessage(errorObject.getString(Const.KEY_ERROR_MESSAGE));
            }

            if (errorObject.has(Const.KEY_ERROR_STATUS_CODE)) {
                error.setStatusCode(errorObject.getInt(Const.KEY_ERROR_STATUS_CODE));
            }

            if (errorObject.has(Const.KEY_ERROR_CODE)) {
                error.setCode(errorObject.getString(Const.KEY_ERROR_CODE));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return error;
    }
}

package com.ntg.user.mvpsample.poc.data.remote;

import java.io.IOException;
import java.lang.annotation.Annotation;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Response;

/**
 * Created by user on 6/15/2017.
 */

public class ApiErrorUtil {

    public static ApiError parseError(Response response) {
        Converter<ResponseBody, ApiError> converter = ApiClient.getClient().responseBodyConverter(ApiError.class, new Annotation[0]);
        ApiError apiError;

        try {
            apiError = converter.convert(response.errorBody());
        } catch (IOException e) {
            return new ApiError();
        }

        return apiError;
    }
}

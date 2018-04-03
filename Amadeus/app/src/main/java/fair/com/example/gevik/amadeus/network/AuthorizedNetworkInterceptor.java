package fair.com.example.gevik.amadeus.network;

import android.content.Context;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import fair.com.example.gevik.amadeus.R;
import fair.com.example.gevik.amadeus.util.RequestUtility;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class AuthorizedNetworkInterceptor implements Interceptor {

    // region Constants
    private static final String KEY_API_KEY = "api_key";
    private static final String KEY_LANGUAGE = "language";
    private Context context;

    public AuthorizedNetworkInterceptor(Context context) {
        this.context = context;
    }


    @Override
    public Response intercept(Chain chain) throws IOException {
        if (chain != null) {
            Request originalRequest = chain.request();
            Map<String, String> queryParamsMap = new HashMap<>();
            queryParamsMap.put(KEY_API_KEY, context.getString(R.string.api_key));
            Request modifiedRequest = RequestUtility.addQueryParams(originalRequest, queryParamsMap);

            return chain.proceed(modifiedRequest);
        }

        return null;
    }
}

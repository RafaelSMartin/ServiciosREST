package volleysample.example.org.model.remote;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;

public class BasicRequest extends Request {

    private Response.Listener mListener;
    private HashMap<String, String> mParams;
    private RetryPolicy mPolicy;

    public BasicRequest(BaseRequestConfiguration configuration, Response.Listener listener,
                        Response.ErrorListener errorListener, RetryPolicy policy) {
        super(configuration.getMethod(), configuration.getUrl(), errorListener);

        mListener = listener;
        mParams = configuration.getParams();
        mPolicy = policy;
    }



    @Override
    protected void deliverResponse(Object response) {
        mListener.onResponse(response);
    }

    @Override
    public HashMap<String, String> getParams() {

        return mParams;
    }

    @Override
    public RetryPolicy getRetryPolicy() {

        return mPolicy;
    }

    @Override
    protected Response parseNetworkResponse(NetworkResponse response) {

        JsonObject parentJson = null;
        String stringResponse;

        try {

            stringResponse = new String(response.data, HttpHeaderParser.parseCharset(response.headers));

            parentJson = new JsonParser().parse(stringResponse).getAsJsonObject();

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return Response.success(parentJson, HttpHeaderParser.parseCacheHeaders(response));
    }
}

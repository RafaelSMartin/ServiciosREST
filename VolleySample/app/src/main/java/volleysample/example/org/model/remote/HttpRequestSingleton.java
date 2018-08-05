package volleysample.example.org.model.remote;

import android.content.Context;

import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;

public class HttpRequestSingleton {

    private static HttpRequestSingleton singleton;
    private RequestQueue mRequestQueue;
    private static Context mContext;

    private HttpRequestSingleton (Context context) {
        HttpRequestSingleton.mContext = context;
        mRequestQueue = getRequestQueue();
    }

    public static synchronized HttpRequestSingleton getInstance (Context context) {

        if (singleton == null) {
            singleton = new HttpRequestSingleton(context);
        }

        return singleton;
    }

    public RequestQueue getRequestQueue() {

        if (mRequestQueue == null) {

            Cache cache = new DiskBasedCache(mContext.getCacheDir(), 10 * 1024 *1024);
            Network network = new BasicNetwork(new HurlStack());
            mRequestQueue = new RequestQueue(cache, network);
            mRequestQueue.start();
        }

        return mRequestQueue;
    }

    public void addToRequestQueue (Request req) {

        getRequestQueue().add(req);
    }
}

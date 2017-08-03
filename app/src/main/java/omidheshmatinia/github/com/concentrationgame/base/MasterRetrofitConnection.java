package omidheshmatinia.github.com.concentrationgame.base;

import android.support.annotation.Nullable;
import android.support.annotation.StringRes;

        import android.util.Log;
        import com.google.gson.Gson;
        import com.google.gson.GsonBuilder;

        import java.util.concurrent.TimeUnit;
        import okhttp3.OkHttpClient;
        import okhttp3.RequestBody;

        import org.json.JSONObject;

import omidheshmatinia.github.com.concentrationgame.interfaces.WebApiErrorInterface;

        import retrofit2.Retrofit;
        import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by Omid Heshmatinia on 8/3/17.
 */

public abstract class MasterRetrofitConnection <K extends WebApiErrorInterface> {

    protected K mWebApiListener;
    public void setWebApiListener(K listener) {
        this.mWebApiListener = listener;
    }

    protected static class Url {
        public class Px {
            public static final String Search="/v1/photos/search";
        }
    }

    public String getMessageString(@StringRes int resID) {
        return MasterApplication.getInstance().getString(resID);
    }

    protected Retrofit initRetrofit(String mainUrl, @Nullable OkHttpClient client) {
        Gson gson = new GsonBuilder().setLenient().create();
        if (client == null) {
            client = new OkHttpClient.Builder()
                    .connectTimeout(12, TimeUnit.SECONDS)
                    .readTimeout(12, TimeUnit.SECONDS)
                    .build();

        }

        Retrofit retrofit = new Retrofit.Builder().baseUrl(mainUrl)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .build();

        return retrofit;
    }

}
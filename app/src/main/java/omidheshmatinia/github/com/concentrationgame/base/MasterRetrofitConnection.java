package omidheshmatinia.github.com.concentrationgame.base;

import android.support.annotation.Nullable;
import android.support.annotation.StringRes;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import omidheshmatinia.github.com.concentrationgame.interfaces.WebApiErrorInterface;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

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

    /**
     * get message from resource
     * @param resID
     * @return
     */
    protected String getMessageString(@StringRes int resID) {
        return MasterApplication.getInstance().getString(resID);
    }

    /**
     * make a retrofit object to communicate with apis
     * @param mainUrl
     * @param client if null passed, it would make a default one with 12 seconds time out time
     * @return
     */
    protected Retrofit initRetrofit(String mainUrl, @Nullable OkHttpClient client) {
        Gson gson = new GsonBuilder().setLenient().create();
        if (client == null) {
            client = new OkHttpClient.Builder()
                .connectTimeout(12, TimeUnit.SECONDS)
                .readTimeout(12, TimeUnit.SECONDS)
                .build();
        }

        return new Retrofit.Builder().baseUrl(mainUrl)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(client)
            .build();
    }

}
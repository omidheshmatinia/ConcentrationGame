package omidheshmatinia.github.com.concentrationgame.connection;

import android.provider.SyncStateContract;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import okhttp3.RequestBody;
import omidheshmatinia.github.com.concentrationgame.PublicConstants;
import omidheshmatinia.github.com.concentrationgame.R;
import omidheshmatinia.github.com.concentrationgame.base.MasterRetrofitConnection;
import omidheshmatinia.github.com.concentrationgame.interfaces.WebApiErrorInterface;
import omidheshmatinia.github.com.concentrationgame.interfaces.webapi.WebApiSearchPictureResponseInterface;
import omidheshmatinia.github.com.concentrationgame.model.PictureCard;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

/**
 * Created by Omid Heshmatinia on 8/3/17.
 */

public class SearchPictureConnection <T extends WebApiSearchPictureResponseInterface>
        extends MasterRetrofitConnection<T> implements Callback<JsonElement> {

    public void search(int number,String term){
        LinkedHashMap parameters = new LinkedHashMap();
        parameters.put("rpp",String.valueOf(number));
        parameters.put("consumer_key",PublicConstants.CONSUMER_KEY);
        parameters.put("term",term);
        Retrofit retrofit = initRetrofit(PublicConstants.BASE_API_URL, null);
        retrofit.create(SearchApi.class).search(parameters).enqueue(this);
    }

    @Override
    public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
        if(response.isSuccessful()){
            JsonObject serverResponse = response.body().getAsJsonObject();
            if(serverResponse.has("photos")){
                List<PictureCard> items= new ArrayList<>();
                int maxNumberOfItems = 0;
//                try {
                    JsonArray array = serverResponse.getAsJsonArray("photos");
                    for(int i=0;i<array.size();i++){
                        String url = array.get(i).getAsJsonObject().get("image_url").getAsString();
                        items.add(new PictureCard(url,i+1));
                    }
                    maxNumberOfItems= serverResponse.get("total_items").getAsInt();
                    mWebApiListener.searchComplete(maxNumberOfItems,items);
//                } catch (JSONException exp){
//                    mWebApiListener.errorHappened(getMessageString(R.string.error_webservice_parsing));
//                }
            } else {
                if(serverResponse.has("error")){
                    mWebApiListener.errorHappened(serverResponse.get("error").getAsString());
                } else {
                    mWebApiListener.errorHappened(getMessageString(R.string.error_webservice_unknown));
                }
            }
        } else {
            mWebApiListener.errorHappened(getMessageString(R.string.error_webservice_data));
        }
    }

    @Override
    public void onFailure(Call<JsonElement> call, Throwable t) {
        if(mWebApiListener!=null)
            mWebApiListener.errorHappened(getMessageString(R.string.error_webservice_connection));
    }


    interface SearchApi {
        @GET(Url.Px.Search)
        Call<JsonElement> search(@QueryMap LinkedHashMap<String,String> data);
    }

}

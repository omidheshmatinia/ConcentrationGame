package omidheshmatinia.github.com.concentrationgame.connection;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import omidheshmatinia.github.com.concentrationgame.PublicConstants;
import omidheshmatinia.github.com.concentrationgame.R;
import omidheshmatinia.github.com.concentrationgame.base.MasterRetrofitConnection;
import omidheshmatinia.github.com.concentrationgame.interfaces.webapi.WebApiSearchPictureResponseInterface;
import omidheshmatinia.github.com.concentrationgame.model.PictureCard;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * Used to retrieve data from 500 Px Api
 * @author omid
 */
public class SearchPictureConnection <T extends WebApiSearchPictureResponseInterface>
        extends MasterRetrofitConnection<T> implements Callback<JsonElement> {

    /**
     * search 500 px for desired pictures
     * @param number number fo returned images
     * @param term image titles or tags
     */
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
                JsonArray array = serverResponse.getAsJsonArray("photos");
                for(int i=0;i<array.size();i++){
                    String url = array.get(i).getAsJsonObject().get("image_url").getAsString();
                    items.add(new PictureCard(url,i+1));
                }
                maxNumberOfItems= serverResponse.get("total_items").getAsInt();
                mWebApiListener.searchComplete(maxNumberOfItems,items);
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


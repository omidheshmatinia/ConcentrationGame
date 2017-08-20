package omidheshmatinia.github.com.concentrationgame.base;

import android.app.Application;
import android.graphics.Bitmap;
import android.support.annotation.DrawableRes;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;

import omidheshmatinia.github.com.concentrationgame.R;
import omidheshmatinia.github.com.concentrationgame.dagger.ApplicationComponent;
import omidheshmatinia.github.com.concentrationgame.dagger.ApplicationModule;
import omidheshmatinia.github.com.concentrationgame.dagger.DaggerApplicationComponent;

public class MasterApplication extends Application {

    private static MasterApplication instance;
    private ApplicationComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        ImageLoader m= ImageLoader.getInstance();
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getApplicationContext())
                .threadPoolSize(3)
                .diskCacheExtraOptions(480, 320, null)
                .defaultDisplayImageOptions(getDisplayImageOption(R.drawable.icon_think))
                .build();
        m.init(config);
        appComponent = DaggerApplicationComponent.builder()
            .applicationModule(new ApplicationModule(this))
            .build();
        appComponent.inject(this);
    }

    public DisplayImageOptions getDisplayImageOption(@DrawableRes int icon){
        DisplayImageOptions options = new DisplayImageOptions
                .Builder()
                .showImageOnLoading(icon)
                .showImageForEmptyUri(icon)
                .showImageOnFail(icon)
                .cacheInMemory(false)
                .cacheOnDisk(true)
                .imageScaleType(ImageScaleType.EXACTLY)
                .considerExifParams(true)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .displayer(new FadeInBitmapDisplayer(1000))
                .build();
        return options;
    }

    public static MasterApplication getInstance(){
        return instance;
    }

    public ApplicationComponent getAppComponent() {
        return appComponent;
    }
}

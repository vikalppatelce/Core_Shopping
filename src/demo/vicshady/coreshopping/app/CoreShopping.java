package demo.vicshady.coreshopping.app;

import android.app.Application;

import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

public class CoreShopping extends Application{

	static CoreShopping coreShopping;
	static ImageLoaderConfiguration imageLoaderConfiguration;

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		coreShopping = this;
		imageLoaderConfiguration = new ImageLoaderConfiguration.Builder(coreShopping).build();//ADDED M0001
	}
	@Override
	public void onTerminate() {
		// TODO Auto-generated method stub
		super.onTerminate();
	}
	public static CoreShopping getApplication()
	{
		return coreShopping;
	}
	public static ImageLoaderConfiguration getImageLoaderConfiguration() {
		return imageLoaderConfiguration;
	}
}
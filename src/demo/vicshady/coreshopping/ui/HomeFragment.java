package demo.vicshady.coreshopping.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

import com.actionbarsherlock.app.SherlockFragment;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import demo.vicshady.coreshopping.R;
import demo.vicshady.coreshopping.app.AppConstants;
import demo.vicshady.coreshopping.async.CategoryTask;

public class HomeFragment extends SherlockFragment{
	GridView mainCategoryGridView;
	ImageLoader imageLoader;
	DisplayImageOptions options;
	
	public HomeFragment()
	{
	}
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.home, container, false);
		mainCategoryGridView = (GridView)rootView.findViewById(R.id.gridview);
		return rootView;
	}
	
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		
		imageLoader = ImageLoader.getInstance();
        // Initialize ImageLoader with configuration. Do it once.
        imageLoader.init(ImageLoaderConfiguration.createDefault(getActivity()));
        
        options = new DisplayImageOptions.Builder()
        .showImageOnLoading(R.drawable.ic_launcher) // resource or drawable
        .showImageForEmptyUri(R.drawable.ic_launcher) // resource or drawable
        .showImageOnFail(R.drawable.ic_launcher) //this is the image that will be displayed if download fails
        .cacheInMemory()
        .cacheOnDisc()
        .build();
    
		
		CategoryTask backgroundTask = new CategoryTask(getActivity(),imageLoader,options,mainCategoryGridView);
        backgroundTask.execute();
        
        mainCategoryGridView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View v, int position,long id) {
				// TODO Auto-generated method stub
//				Toast.makeText(CategoryActivity.this, "Item Clicked", Toast.LENGTH_SHORT).show();
				int categoryID =Integer.parseInt(v.getTag(R.id.TAG_CAT_ID).toString());

				switch(categoryID)
				{
				case 100000 :
					Intent i4 = new Intent(getActivity(), ProductFeatureActivity.class);
					i4.putExtra(AppConstants.JSONTAG.CategoryTag.CATEGORY_ID,"getFeaturedProducts");//1 - catId
					i4.putExtra(AppConstants.JSONTAG.CategoryTag.CATEGORY_NAME, v.getTag(R.id.TAG_CAT_NAME).toString());//2 - catName
					startActivity(i4);
					break;
				case 100001 :
					Intent i1 = new Intent(getActivity(), ProductFeatureActivity.class);
					i1.putExtra(AppConstants.JSONTAG.CategoryTag.CATEGORY_ID,"getNewlyAdedProducts");//1 - catId
					i1.putExtra(AppConstants.JSONTAG.CategoryTag.CATEGORY_NAME, v.getTag(R.id.TAG_CAT_NAME).toString());//2 - catName
					startActivity(i1);
					break;
				case 100002 :
					Intent i2 = new Intent(getActivity(), ProductFeatureActivity.class);
					i2.putExtra(AppConstants.JSONTAG.CategoryTag.CATEGORY_ID, "getPopularProducts");//1 - catId
					i2.putExtra(AppConstants.JSONTAG.CategoryTag.CATEGORY_NAME, v.getTag(R.id.TAG_CAT_NAME).toString());//2 - catName
					startActivity(i2);
					break;
				case 100003 :
					Intent i3 = new Intent(getActivity(), ProductFeatureActivity.class);
					i3.putExtra(AppConstants.JSONTAG.CategoryTag.CATEGORY_ID, "getDealofDay");//1 - catId
					i3.putExtra(AppConstants.JSONTAG.CategoryTag.CATEGORY_NAME, v.getTag(R.id.TAG_CAT_NAME).toString());//2 - catName
					startActivity(i3);
					break;
				default:
					Intent i = new Intent(getActivity(), SubCategoryActivity.class);
					i.putExtra(AppConstants.JSONTAG.CategoryTag.CATEGORY_ID, v.getTag(R.id.TAG_CAT_ID).toString());//1 - catId
					i.putExtra(AppConstants.JSONTAG.CategoryTag.CATEGORY_NAME, v.getTag(R.id.TAG_CAT_NAME).toString());//2 - catName
					startActivity(i);
				break;
				}
			}
		});
	}

}

package demo.vicshady.coreshopping.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import demo.vicshady.coreshopping.R;
import demo.vicshady.coreshopping.app.AppConstants;
import demo.vicshady.coreshopping.async.SubCategoryTask;

public class SubCategoryActivity extends SherlockFragmentActivity {

	GridView mainCategoryGridView;
	ImageLoader imageLoader;
	DisplayImageOptions options;
	ActionBar actionBar;
	String urlCatId,urlCatName;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.home);
		
		imageLoader = ImageLoader.getInstance();
        // Initialize ImageLoader with configuration. Do it once.
        imageLoader.init(ImageLoaderConfiguration.createDefault(this));
        
        options = new DisplayImageOptions.Builder()
        .showImageOnLoading(R.drawable.ic_launcher) // resource or drawable
        .showImageForEmptyUri(R.drawable.ic_launcher) // resource or drawable
        .showImageOnFail(R.drawable.ic_launcher) //this is the image that will be displayed if download fails
        .cacheInMemory()
        .cacheOnDisc()
        .build();
        
        urlCatId = getIntent().getStringExtra(AppConstants.JSONTAG.CategoryTag.CATEGORY_ID);
		urlCatName = getIntent().getStringExtra(AppConstants.JSONTAG.CategoryTag.CATEGORY_NAME);
        
        
        actionBar = getSupportActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		actionBar.setDisplayShowTitleEnabled(true);
		actionBar.setTitle(urlCatName);
        
        mainCategoryGridView = (GridView)findViewById(R.id.gridview);
        
        SubCategoryTask backgroundTask = new SubCategoryTask(this,imageLoader,options,mainCategoryGridView,urlCatId);
        backgroundTask.execute();
        
        mainCategoryGridView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View v, int position,long id) {
				// TODO Auto-generated method stub
//				Toast.makeText(CategoryActivity.this, "Item Clicked", Toast.LENGTH_SHORT).show();
				Intent i = new Intent(SubCategoryActivity.this, ProductActivity.class);
				i.putExtra(AppConstants.JSONTAG.CategoryTag.CATEGORY_ID, v.getTag(R.id.TAG_CAT_ID).toString());//1 - catId
				i.putExtra(AppConstants.JSONTAG.CategoryTag.PARENT_CATEGORY_ID, v.getTag(R.id.TAG_CAT_SUB_ID).toString());//2 - catName
				i.putExtra(AppConstants.JSONTAG.CategoryTag.CATEGORY_NAME, v.getTag(R.id.TAG_CAT_NAME).toString());//2 - catName
				startActivity(i);
			}
		});
	}
	
	public void onBackPressed() {
        // TODO Auto-generated method stub
        super.onBackPressed();
        finish();
    }
}

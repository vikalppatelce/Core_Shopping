package demo.vicshady.coreshopping.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import demo.vicshady.coreshopping.R;
import demo.vicshady.coreshopping.app.AppConstants;
import demo.vicshady.coreshopping.async.ProductFeatureTask;

public class ProductFeatureActivity extends SherlockFragmentActivity {

	public ListView productListView;
	ImageLoader imageLoader;
	DisplayImageOptions options;
	String urlCatId,urlSubCatId,urlCatName;
	ActionBar actionBar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.product);
		
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
		
        productListView = (ListView)findViewById(R.id.listview);
        
        actionBar = getSupportActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		actionBar.setDisplayShowTitleEnabled(true);
		actionBar.setTitle(urlCatName);
		
		ProductFeatureTask backgroundTask = new ProductFeatureTask(this,imageLoader,options, productListView, urlCatId);
        backgroundTask.execute();
        
        productListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View v, int position,long id) {
				// TODO Auto-generated method stub
//				Toast.makeText(CategoryActivity.this, "Item Clicked", Toast.LENGTH_SHORT).show();
				Intent i = new Intent(ProductFeatureActivity.this, ProductDetailActivity.class);
				i.putExtra(AppConstants.JSONTAG.ProductDetailTag.PRODUCT_ID, v.getTag(R.id.TAG_PDT_ID).toString());//1 - catId
				i.putExtra(AppConstants.JSONTAG.ProductDetailTag.PRODUCT_VERSION_ID, v.getTag(R.id.TAG_PDT_V_ID).toString());//2 - catName
				i.putExtra(AppConstants.JSONTAG.ProductDetailTag.PRODUCT_NAME, v.getTag(R.id.TAG_PDT_NAME).toString());//2 - catName
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

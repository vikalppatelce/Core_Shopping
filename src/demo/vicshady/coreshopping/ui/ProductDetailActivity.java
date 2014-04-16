package demo.vicshady.coreshopping.ui;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import demo.vicshady.coreshopping.R;
import demo.vicshady.coreshopping.app.AppConstants;
import demo.vicshady.coreshopping.async.ProductDetailTask;
import demo.vicshady.coreshopping.ui.utils.ScrollableGridView;

public class ProductDetailActivity extends SherlockFragmentActivity {

	public ScrollableGridView similarProductListView;
	ImageLoader imageLoader;
	DisplayImageOptions options;
	String urlProductId,urlVersionId,urlProductName;
	ActionBar actionBar;
	ImageView productDetailImageView;
	TextView productName,productType,productBrandDetail;
	TextView productFeaturePrice,productFeatureDecimalPrice,productPrice,productDecimalPrice;
	TextView productAvailable,productDesc;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.product_detail);
		
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
        
        urlProductId = getIntent().getStringExtra(AppConstants.JSONTAG.ProductDetailTag.PRODUCT_ID);
		urlVersionId = getIntent().getStringExtra(AppConstants.JSONTAG.ProductDetailTag.PRODUCT_VERSION_ID);
		urlProductName = getIntent().getStringExtra(AppConstants.JSONTAG.ProductDetailTag.PRODUCT_NAME);
		
        similarProductListView = (ScrollableGridView)findViewById(R.id.list_similarproducts);
		similarProductListView.setExpanded(true);
		
		productDetailImageView = (ImageView)findViewById(R.id.product_detail_image);
		productName = (TextView)findViewById(R.id.product_name_detail);
		productType = (TextView)findViewById(R.id.product_type);
		productBrandDetail= (TextView)findViewById(R.id.product_brand_detail);
		productFeaturePrice = (TextView)findViewById(R.id.product_feature_price);
		productFeatureDecimalPrice = (TextView)findViewById(R.id.product_feature_decimal_price);
		productPrice = (TextView)findViewById(R.id.product_price);
		productDecimalPrice = (TextView)findViewById(R.id.product_decimal_price);
		productAvailable = (TextView)findViewById(R.id.product_availability_detail);
		productDesc = (TextView)findViewById(R.id.product_description);
		
		
		
        actionBar = getSupportActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		actionBar.setDisplayShowTitleEnabled(true);
		actionBar.setTitle(urlProductName);
		
        ProductDetailTask backgroundTask = new ProductDetailTask(this,imageLoader,options, similarProductListView, urlProductId, urlVersionId,
        		productName,productType,productBrandDetail,
    	productFeaturePrice,productFeatureDecimalPrice,productPrice,productDecimalPrice,
    	productAvailable,productDesc,productDetailImageView);
        backgroundTask.execute();
        
//        productListView.setOnItemClickListener(new OnItemClickListener() {
//
//			@Override
//			public void onItemClick(AdapterView<?> parent, View v, int position,long id) {
//				// TODO Auto-generated method stub
////				Toast.makeText(CategoryActivity.this, "Item Clicked", Toast.LENGTH_SHORT).show();
////				Intent i = new Intent(ProductActivity.this, SubCategoryActivity.class);
////				i.putExtra(AppConstants.JSONTAG.CategoryTag.CATEGORY_ID, v.getTag(R.id.TAG_CAT_ID).toString());//1 - catId
////				i.putExtra(AppConstants.JSONTAG.CategoryTag.CATEGORY_NAME, v.getTag(R.id.TAG_CAT_NAME).toString());//2 - catName
////				startActivity(i);
//			}
//		});
	}
	public void onBackPressed() {
        // TODO Auto-generated method stub
        super.onBackPressed();
        finish();
    }
}

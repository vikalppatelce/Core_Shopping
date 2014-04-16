package demo.vicshady.coreshopping.ui;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import demo.vicshady.coreshopping.R;
import demo.vicshady.coreshopping.app.AppConstants;
import demo.vicshady.coreshopping.async.ProductSearchTask;

public class SearchProductActivity extends SherlockFragmentActivity {

	public ListView productListView;
	public EditText searchText;
	ImageLoader imageLoader;
	DisplayImageOptions options;
	String urlSearchText;
	ActionBar actionBar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.product_search);
		
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
		
        productListView = (ListView)findViewById(R.id.listview);
        searchText = (EditText)findViewById(R.id.search_field);
        
        actionBar = getSupportActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		actionBar.setDisplayShowTitleEnabled(true);
		actionBar.setTitle(getString(R.string.action_search));
        
        searchText.setOnEditorActionListener(
                new EditText.OnEditorActionListener() {
                    @Override
                    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                        if (actionId == EditorInfo.IME_ACTION_SEARCH ||
                                actionId == EditorInfo.IME_ACTION_DONE ||
                                event.getAction() == KeyEvent.ACTION_DOWN &&
                                event.getKeyCode() == KeyEvent.KEYCODE_ENTER) {
                            if(!TextUtils.isEmpty(searchText.getText().toString()) && searchText.getText().toString().length() > 2)
                            {
                            	InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                        		imm.hideSoftInputFromWindow(searchText.getWindowToken(), 0);
                        		onSearchAction(searchText.getText().toString());
                            }
                            return true;
                        }
                        return false;
                    }
                });
        
        
        productListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View v, int position,long id) {
				// TODO Auto-generated method stub
//				Toast.makeText(CategoryActivity.this, "Item Clicked", Toast.LENGTH_SHORT).show();
				Intent i = new Intent(SearchProductActivity.this, ProductDetailActivity.class);
				i.putExtra(AppConstants.JSONTAG.ProductDetailTag.PRODUCT_ID, v.getTag(R.id.TAG_PDT_ID).toString());//1 - catId
				i.putExtra(AppConstants.JSONTAG.ProductDetailTag.PRODUCT_VERSION_ID, v.getTag(R.id.TAG_PDT_V_ID).toString());//2 - catName
				i.putExtra(AppConstants.JSONTAG.ProductDetailTag.PRODUCT_NAME, v.getTag(R.id.TAG_PDT_NAME).toString());//2 - catName
				startActivity(i);
			}
		});
	}
	
	public void onSearchAction(String searchText){

		try {
			searchText.replaceAll(" ", "%20");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ProductSearchTask backgroundTask = new ProductSearchTask(this,imageLoader,options, productListView, searchText);
        backgroundTask.execute();		
	}
	
	public void onBackPressed() {
        // TODO Auto-generated method stub
        super.onBackPressed();
        finish();
    }
}

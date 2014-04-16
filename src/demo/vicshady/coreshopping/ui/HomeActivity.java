package demo.vicshady.coreshopping.ui;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import demo.vicshady.coreshopping.R;

public class HomeActivity extends SherlockFragmentActivity {

	GridView mainCategoryGridView;
//	ImageLoader imageLoader;
//	DisplayImageOptions options;
	
	private DrawerLayout mDrawerLayout;
	private ListView mDrawerList;
	private ActionBarDrawerToggle mDrawerToggle;

	private CharSequence mDrawerTitle;
	private CharSequence mTitle;
	private String[] mPlanetTitles;
//	static Typeface stylefont;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
//		
//		imageLoader = ImageLoader.getInstance();
//        // Initialize ImageLoader with configuration. Do it once.
//        imageLoader.init(ImageLoaderConfiguration.createDefault(this));
//        
//        options = new DisplayImageOptions.Builder()
//        .showImageOnLoading(R.drawable.ic_launcher) // resource or drawable
//        .showImageForEmptyUri(R.drawable.ic_launcher) // resource or drawable
//        .showImageOnFail(R.drawable.ic_launcher) //this is the image that will be displayed if download fails
//        .cacheInMemory()
//        .cacheOnDisc()
//        .build();
//        
        
        mTitle = mDrawerTitle = getTitle();
		mPlanetTitles = getResources().getStringArray(R.array.planets_array);
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		mDrawerList = (ListView) findViewById(R.id.left_drawer);

		// set a custom shadow that overlays the main content when the drawer
		// opens
		mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow,GravityCompat.START);
		// set up the drawer's list view with items and click listener
		MySimpleArrayAdapter adapter = new MySimpleArrayAdapter(this, mPlanetTitles);
		mDrawerList.setAdapter(adapter);
//		mDrawerList.setAdapter(new ArrayAdapter<String>(this,
//				R.layout.drawer_list_item, mPlanetTitles));
		mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

		// enable ActionBar app icon to behave as action to toggle nav drawer

		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setHomeButtonEnabled(true);

		// ActionBarDrawerToggle ties together the the proper interactions
		// between the sliding drawer and the action bar app icon
		mDrawerToggle = new ActionBarDrawerToggle(this, /* host Activity */
		mDrawerLayout, /* DrawerLayout object */
		R.drawable.ic_drawer, /* nav drawer image to replace 'Up' caret */
		R.string.drawer_open, /* "open drawer" description for accessibility */
		R.string.drawer_close /* "close drawer" description for accessibility */
		) {
			@Override
			public void onDrawerClosed(View view) {
				getSupportActionBar().setTitle(mTitle);
				supportInvalidateOptionsMenu();
				// invalidateOptionsMenu(); // creates call to
				// onPrepareOptionsMenu()
			}

			@Override
			public void onDrawerOpened(View drawerView) {
				getSupportActionBar().setTitle(mDrawerTitle);
				supportInvalidateOptionsMenu();
				// invalidateOptionsMenu(); // creates call to
				// onPrepareOptionsMenu()
			}
		};
		mDrawerLayout.setDrawerListener(mDrawerToggle);

		if (savedInstanceState == null) {
			selectItem(0);
		}

        
        
        
//        mainCategoryGridView = (GridView)findViewById(R.id.gridview);
//        
//        CategoryTask backgroundTask = new CategoryTask(this,imageLoader,options,mainCategoryGridView);
//        backgroundTask.execute();
//        
//        mainCategoryGridView.setOnItemClickListener(new OnItemClickListener() {
//
//			@Override
//			public void onItemClick(AdapterView<?> parent, View v, int position,long id) {
//				// TODO Auto-generated method stub
////				Toast.makeText(CategoryActivity.this, "Item Clicked", Toast.LENGTH_SHORT).show();
//				Intent i = new Intent(HomeActivity.this, SubCategoryActivity.class);
//				i.putExtra(AppConstants.JSONTAG.CategoryTag.CATEGORY_ID, v.getTag(R.id.TAG_CAT_ID).toString());//1 - catId
//				i.putExtra(AppConstants.JSONTAG.CategoryTag.CATEGORY_NAME, v.getTag(R.id.TAG_CAT_NAME).toString());//2 - catName
//				startActivity(i);
//			}
//		});
	}
	
	
	public class MySimpleArrayAdapter extends ArrayAdapter<String> {
		  private final Context context;
		  private final String[] values;

		  public MySimpleArrayAdapter(Context context, String[] values) {
		    super(context, R.layout.drawer_list_item, values);
		    this.context = context;
		    this.values = values;
		  }

		  @Override
		  public View getView(int position, View convertView, ViewGroup parent) {
		    LayoutInflater inflater = (LayoutInflater) context
		        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		    View rowView = inflater.inflate(R.layout.drawer_list_item, parent, false);
		    TextView textView = (TextView) rowView.findViewById(R.id.text1);
		    ImageView imageView = (ImageView) rowView.findViewById(R.id.imageView1);
		    textView.setText(values[position]);
		    
//		    textView.setTypeface(stylefont);
		    // Change the icon for Windows and iPhone
		    String s = values[position];
			switch (position) {
			case 0:
				imageView.setImageResource(R.drawable.ic_launcher);
				break;
			case 1:
				imageView.setImageResource(android.R.drawable.ic_menu_today);
				break;
			case 2:
				imageView.setImageResource(android.R.drawable.ic_menu_gallery);
				break;
			case 3:
				imageView.setImageResource(android.R.drawable.ic_menu_info_details);
				break;
			case 4:
				imageView.setImageResource(android.R.drawable.ic_menu_agenda);
				break;
			case 5:
				imageView.setImageResource(android.R.drawable.ic_menu_mapmode);
				break;
			case 6:
				imageView.setImageResource(android.R.drawable.ic_menu_myplaces);
				break;
			case 7:
				imageView.setImageResource(R.drawable.ic_drawer);
				break;
			default:
				break;
			}
		    return rowView;
		  }
		} 
	
	
	/* The click listner for ListView in the navigation drawer */
	private class DrawerItemClickListener implements
			ListView.OnItemClickListener {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			selectItem(position);
		}
	}

	
	private void selectItem(int position) {
		// update the main content by replacing fragments
		Fragment fragment = null;
		
//		PlanetFragment fragment = new PlanetFragment();
		Bundle args = new Bundle();
//		args.putInt(PlanetFragment.ARG_PLANET_NUMBER, position);
//		fragment.setArguments(args);

		
        switch (position) {
        case 0:
            fragment = new HomeFragment();
            break;
        case 1:
            break;
        case 2:
            break;
        case 3:
            break;
        case 4:
            break;
        case 5:
            break;
        case 6:
            break;
        case 7:
//            fragment = new SendFeedbackFragment();
//            showFeedbackActivity();
            break;
        default:
            break;
        }
        fragment.setArguments(args);
		android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
		fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();

		// update selected item and title, then close the drawer
		mDrawerList.setItemChecked(position, true);
		setTitle(mPlanetTitles[position]);
		mDrawerLayout.closeDrawer(mDrawerList);
	}

	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.

		MenuInflater inflater = getSupportMenuInflater();
		inflater.inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			try {
				if (mDrawerLayout.isDrawerOpen(mDrawerList)) {
					mDrawerLayout.closeDrawer(mDrawerList);
				} else {
					mDrawerLayout.openDrawer(mDrawerList);
				}
			} catch (Exception e) {
			}
			return true;
		case R.id.action_search:
			Intent searchIntent = new Intent(this,SearchProductActivity.class);
			startActivity(searchIntent);
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}
	
	
	public void onBackPressed() {
        // TODO Auto-generated method stub
        super.onBackPressed();
        finish();
    }
}

package demo.vicshady.coreshopping.async;

import java.io.IOException;
import java.util.ArrayList;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.GridView;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import demo.vicshady.coreshopping.DTO.CategoryDTO;
import demo.vicshady.coreshopping.adapter.MainCategoryAdapter;
import demo.vicshady.coreshopping.app.AppConstants;
import demo.vicshady.coreshopping.network.RestClient;

public class CategoryTask extends AsyncTask<Void, Void, String>
{
	  private Context context;
	  private ProgressDialog progressDialog;
	  public ImageLoader imageLoader;
	  public GridView mainCategoryGridView;
	  public DisplayImageOptions options;
	  public CategoryDTO categoryDTO;
	  ArrayList<CategoryDTO> CategoryListDTO;

	  public CategoryTask(Context context, ImageLoader imageLoader, DisplayImageOptions options,GridView mainCategoryGridView)
	  {
	    this.context = context;
	    this.imageLoader = imageLoader;
	    this.options = options;
	    this.mainCategoryGridView= mainCategoryGridView;
		categoryDTO = new CategoryDTO();
		CategoryListDTO = new ArrayList<CategoryDTO>();
	  }
	  protected void onPreExecute()
	  {
	    super.onPreExecute();
	    {
	      progressDialog = new ProgressDialog(context);
	      progressDialog.setMessage("Loading...");
	      progressDialog.show();
	    }
	  }
	  @Override
		protected String doInBackground(Void... params) {
			// TODO Auto-generated method stub
		  String n = null;
		  try {
				parseJSON();
			} catch (JsonParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		  Log.i("TAG", "doInBackGround-Finish");
		return n;
		}
	  
	  protected void onPostExecute(String result)
	  {
	    super.onPostExecute(result);
		progressDialog.dismiss();
	    Log.i("TAG", "onPostExcecute");
		mainCategoryGridView.setAdapter(new MainCategoryAdapter(context, CategoryListDTO, imageLoader, options));
	  }
	  
	  /*
	   * Parse JSON with JackSON Parser directly into Java Objects
	   */
	  
	  @SuppressWarnings("deprecation")
	public void parseJSON() throws JsonParseException, IOException {
			JsonFactory f = new JsonFactory();
			boolean breakLoop=false;
			JsonParser jp = null;
			try {
				jp = f.createJsonParser(RestClient.connect(AppConstants.URLS.CATEGORY_URL));
			} catch (JsonParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			while (jp.nextToken() != JsonToken.END_ARRAY) 
			{
				if(breakLoop)
				{
					break;
				}
				String fieldname = jp.getCurrentName();
				if ("MainCategories".equals(fieldname)) 
				{
					while (jp.nextToken() != JsonToken.END_ARRAY) 
					{
						if ("}".equals(jp.getText())) {
							CategoryListDTO.add(categoryDTO);
							categoryDTO = new CategoryDTO();
						}
						if (jp.getText().equals(JsonToken.END_OBJECT)) {
							break;
						}
						
						String responsefield = jp.getCurrentName();
						jp.nextToken(); // move to value
						
						if ("catId".equals(responsefield)) {
							categoryDTO.setCatId(jp.getText());
						} else if ("catName".equals(responsefield)) {
							categoryDTO.setCatName(jp.getText());
						} else if ("parentId".equals(responsefield)) {
							categoryDTO.setParentId(jp.getText());
						} else if ("imagePath".equals(responsefield)) {
							categoryDTO.setImagePath(jp.getText());
						} else if (jp.getText().equals("]")) {
							Log.i("Jackson", "Outta MainCategories");
							breakLoop=true;
							break;
						} else {
						Log.i("Jackson", "Unrecognized Token");
						}
					}
				}
			}
			jp.close();
	  	}
	}
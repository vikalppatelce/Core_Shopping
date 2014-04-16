package demo.vicshady.coreshopping.async;

import java.io.IOException;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

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

import demo.vicshady.coreshopping.DTO.SubCategoryDTO;
import demo.vicshady.coreshopping.adapter.SubCategoryAdapter;
import demo.vicshady.coreshopping.app.AppConstants;
import demo.vicshady.coreshopping.network.RestClient;

public class SubCategoryTask extends AsyncTask<Void, Void, String>
{
	  private Context context;
	  private ProgressDialog progressDialog;
	  public ImageLoader imageLoader;
	  public GridView mainCategoryGridView;
	  public DisplayImageOptions options;
	  public SubCategoryDTO subCategoryDTO;
	  public SubCategoryDTO subCategoryParentDTO;
	  ArrayList<SubCategoryDTO> subCategoryListDTO;
	  public String urlCatId;
	  

	  public SubCategoryTask(Context context, ImageLoader imageLoader, DisplayImageOptions options,GridView mainCategoryGridView, String urlCatId)
	  {
	    this.context = context;
	    this.imageLoader = imageLoader;
	    this.options = options;
	    this.mainCategoryGridView= mainCategoryGridView;
	    subCategoryDTO = new SubCategoryDTO();
	    subCategoryParentDTO = new SubCategoryDTO();
		subCategoryListDTO = new ArrayList<SubCategoryDTO>();
		this.urlCatId = urlCatId;
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
//			  parseWithSimpleJSON();
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
		mainCategoryGridView.setAdapter(new SubCategoryAdapter(context, subCategoryListDTO, imageLoader, options));
	  }

	  public void parseWithSimpleJSON()
	  {
		  try
		  {
			  JSONArray SubCategoriesarray , BotCategoriesarray;
			  String jsonStr = RestClient.connect(AppConstants.URLS.SUBCATEGORY_URL+urlCatId);
			  JSONObject jsonObject = new JSONObject(jsonStr);
			  SubCategoriesarray = jsonObject.getJSONArray("SubCategories");
			  
			  for (int i = 0; i < SubCategoriesarray.length(); i++) {
                  JSONObject c = SubCategoriesarray.getJSONObject(i);
				  subCategoryDTO = new SubCategoryDTO();
				  subCategoryDTO.setCatId(c.getString("catId"));
				  subCategoryDTO.setCatName(c.getString("catName"));
				  subCategoryDTO.setParentId(c.getString("parentId"));
				  subCategoryDTO.setImagePath(c.getString("imagePath"));

				  BotCategoriesarray = jsonObject.getJSONArray("BotCategoriesarray");
				  for (int j = 0; i < BotCategoriesarray.length(); i++) {
	                  JSONObject c1 = BotCategoriesarray.getJSONObject(i);
					  subCategoryDTO = new SubCategoryDTO();
					  subCategoryDTO.setCatId(c1.getString("catId"));
					  subCategoryDTO.setCatName(c1.getString("catName"));
					  subCategoryDTO.setParentId(c1.getString("parentId"));
					  subCategoryDTO.setImagePath(c1.getString("imagePath"));
					  subCategoryListDTO.add(subCategoryDTO);
	              }
					
				  subCategoryListDTO.add(subCategoryDTO);
              }
		  }
		  catch(Exception e)
		  {
			  
		  }
	  }
	  
	  
	  /*
	   * Parse JSON with JackSON Parser directly into Java Objects
	   */
	  @SuppressWarnings("deprecation")
	public void parseJSON() throws JsonParseException, IOException {
			JsonFactory f = new JsonFactory();
			boolean breakLoop=false;
			boolean breakOuterLoop=false;
			JsonParser jp = null;
			try {
//				jp = f.createJsonParser(context.getAssets().open("kadajsondata.txt"));
				jp = f.createJsonParser(RestClient.connect(AppConstants.URLS.SUBCATEGORY_URL+urlCatId));
			} catch (JsonParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			while (jp.nextToken() != JsonToken.END_ARRAY) 
			{
				if(breakOuterLoop)
				{
					break;
				}
				String fieldname = jp.getCurrentName();
				if ("SubCategories".equals(fieldname)) 
				{
					while (jp.nextToken() != JsonToken.END_ARRAY) 
					{
						if ("}".equals(jp.getText())) {
							subCategoryListDTO.add(subCategoryParentDTO);
							subCategoryParentDTO = new SubCategoryDTO();
						}
						if (jp.getText().equals(JsonToken.END_OBJECT)) {
							break;
						}
						
						String responsefield = jp.getCurrentName();
						jp.nextToken(); // move to value
						
						if ("catId".equals(responsefield)) {
							subCategoryParentDTO.setCatId(jp.getText());
						} else if ("catName".equals(responsefield)) {
							subCategoryParentDTO.setCatName(jp.getText());
						} else if ("parentId".equals(responsefield)) {
							subCategoryParentDTO.setParentId(jp.getText());
						} else if ("imagePath".equals(responsefield)) {
							subCategoryParentDTO.setImagePath(jp.getText());
						}
						else if("BotCategories".equals(responsefield))
						{
							while (jp.nextToken() != JsonToken.END_ARRAY) 
							{
								if ("}".equals(jp.getText())) {
									subCategoryListDTO.add(subCategoryDTO);
									subCategoryDTO = new SubCategoryDTO();
								}
								if (jp.getText().equals(JsonToken.END_OBJECT)) {
									break;
								}
								
								String responsefield1 = jp.getCurrentName();
//								jp.nextToken(); // move to value
								
								if ("catId".equals(responsefield1)) {
									subCategoryDTO.setCatId(jp.getText());
								} else if ("catName".equals(responsefield1)) {
									subCategoryDTO.setCatName(jp.getText());
								} else if ("parentId".equals(responsefield1)) {
									subCategoryDTO.setParentId(jp.getText());
								} else if ("imagePath".equals(responsefield1)) {
									subCategoryDTO.setImagePath(jp.getText());
								} else if (jp.getText().equals("]")) {
									Log.i("Jackson", "Outta SubCategories");
									breakLoop=true;
									break;
								} else {
								Log.i("Jackson", "Unrecognized Token");
								}
							}
						}
						
						
						else if (jp.getText().equals("]")) {
							Log.i("Jackson", "Outta SubCategories");
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
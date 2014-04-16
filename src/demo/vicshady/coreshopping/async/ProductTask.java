package demo.vicshady.coreshopping.async;

import java.io.IOException;
import java.util.ArrayList;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ListView;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import demo.vicshady.coreshopping.DTO.ProductDTO;
import demo.vicshady.coreshopping.adapter.ProductListAdapter;
import demo.vicshady.coreshopping.app.AppConstants;
import demo.vicshady.coreshopping.network.RestClient;

public class ProductTask extends AsyncTask<Void, Void, String>
{
	  private Context context;
	  private ProgressDialog progressDialog;
	  public ImageLoader imageLoader;
	  public ListView productListView;
	  public DisplayImageOptions options;
	  public ProductDTO productDTO;
	  ArrayList<ProductDTO> productListDTO;
	  public String urlCatId;
	  public String urlSubCatId;
	  

	  public ProductTask(Context context, ImageLoader imageLoader, DisplayImageOptions options,ListView productListView, String urlCatId,String urlSubCatId)
	  {
	    this.context = context;
	    this.imageLoader = imageLoader;
	    this.options = options;
	    this.productListView= productListView;
	    productDTO = new ProductDTO();
	    productListDTO = new ArrayList<ProductDTO>();
		this.urlCatId = urlCatId;
		this.urlSubCatId=urlSubCatId;
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
	    productListView.setAdapter(new ProductListAdapter(context, productListDTO, imageLoader, options));
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
				jp = f.createJsonParser(RestClient.connect(AppConstants.URLS.PRODUCT_LIST_URL+urlCatId+"&subcatid="+urlSubCatId));
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
				if ("Products".equals(fieldname)) 
				{
					while (jp.nextToken() != JsonToken.END_ARRAY) 
					{
						if ("}".equals(jp.getText())) {
							productListDTO.add(productDTO);
							productDTO = new ProductDTO();
						}
						if (jp.getText().equals(JsonToken.END_OBJECT)) {
							break;
						}
						
						String responsefield = jp.getCurrentName();
						jp.nextToken(); // move to value
						
						if ("pdtId".equals(responsefield)) {
							productDTO.setPdtId(jp.getText());
						} else if ("versionId".equals(responsefield)) {
							productDTO.setVersionId(jp.getText());
						} else if ("pdtName".equals(responsefield)) {
							productDTO.setPdtName(jp.getText());
						} else if ("isFeatured".equals(responsefield)) {
							productDTO.setIsFeatured(jp.getText());
						}else if ("pdtFeaturedPrice".equals(responsefield)) {
							productDTO.setPdtFeaturedPrice(jp.getText());
						}else if ("pdtPrice".equals(responsefield)) {
							productDTO.setPdtPrice(jp.getText());
						}else if ("isStockout".equals(responsefield)) {
							productDTO.setIsStockout(jp.getText());
						}else if ("isLimited".equals(responsefield)) {
							productDTO.setIsLimited(jp.getText());
						}else if ("isFoodCoupon".equals(responsefield)) {
							productDTO.setIsFoodCoupon(jp.getText());
						} else if ("stockAvailable".equals(responsefield)) {
							productDTO.setStockAvailable(jp.getText());
						} else if ("imagePath".equals(responsefield)) {
							productDTO.setImagePath(jp.getText());
						}  
						else if (jp.getText().equals("]")) {
							Log.i("Jackson", "Outta Products");
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
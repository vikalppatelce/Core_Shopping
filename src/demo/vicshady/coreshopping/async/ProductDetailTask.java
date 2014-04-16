package demo.vicshady.coreshopping.async;

import java.io.IOException;
import java.util.ArrayList;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import demo.vicshady.coreshopping.DTO.ProductDetailDTO;
import demo.vicshady.coreshopping.DTO.SimilarProductDTO;
import demo.vicshady.coreshopping.adapter.SimilarProductListAdapter;
import demo.vicshady.coreshopping.app.AppConstants;
import demo.vicshady.coreshopping.network.RestClient;
import demo.vicshady.coreshopping.ui.ProductDetailActivity;
import demo.vicshady.coreshopping.ui.utils.ScrollableGridView;

public class ProductDetailTask extends AsyncTask<Void, Void, String>
{
	  private Context context;
	  private ProgressDialog progressDialog;
	  public ImageLoader imageLoader;
	  public ScrollableGridView similarProductListView;
	  public TextView productName,productType,productBrandDetail,
  					  productFeaturePrice,productFeatureDecimalPrice,productPrice,productDecimalPrice,
  					  productAvailable,productDesc;
	  public ImageView productImageView;
	  public DisplayImageOptions options;
	  public ProductDetailDTO productDetailDTO;
	  public SimilarProductDTO similarProductDTO;
	  ArrayList<SimilarProductDTO> similarProductListDTO;
	  public String urlProductId,urlVersionId;
	  

	  public ProductDetailTask(Context context, ImageLoader imageLoader, DisplayImageOptions options,ScrollableGridView similarProductListView, String urlProductId, String urlVersionId,
			  TextView productName,TextView productType,TextView productBrandDetail,
			  TextView productFeaturePrice,TextView productFeatureDecimalPrice,TextView productPrice,TextView productDecimalPrice,
			  TextView productAvailable,TextView productDesc,ImageView productImageView)
	  {
	    this.context = context;
	    this.imageLoader = imageLoader;
	    this.options = options;
	    this.similarProductListView = similarProductListView;
	    productDetailDTO = new ProductDetailDTO();
	    similarProductDTO = new SimilarProductDTO();
		similarProductListDTO = new ArrayList<SimilarProductDTO>();
		this.urlProductId = urlProductId;
		this.urlVersionId = urlVersionId;
		this.productName = productName;
		this.productType = productType;
		this.productBrandDetail = productBrandDetail;
		this.productFeaturePrice = productFeaturePrice;
		this.productFeatureDecimalPrice = productFeatureDecimalPrice;
		this.productPrice = productPrice;
		this.productDecimalPrice = productDecimalPrice;
		this.productAvailable = productAvailable;
		this.productDesc = productDesc;
		this.productImageView = productImageView;
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
	    Log.i("TAG", String.valueOf(similarProductListDTO.size()));
	    Log.i("TAG", String.valueOf(productDetailDTO.imagePath));
	    
	    similarProductListView.setAdapter(new SimilarProductListAdapter(context, similarProductListDTO, imageLoader, options));
	    
	    productName.setText(productDetailDTO.getPdtName());
	    productType.setText(productDetailDTO.getPdtType());
	    productBrandDetail.setText(productDetailDTO.getPdtBrand());
		productFeaturePrice.setText(productDetailDTO.getPdtFeaturedPrice());
		productFeatureDecimalPrice.setText("00");
		productPrice.setText(productDetailDTO.getPdtPrice());
		productDecimalPrice.setText("00");
		productAvailable.setText(productDetailDTO.getIsStockout());
		productDesc.setText(productDetailDTO.getPdtDesc());
	    
		imageLoader.displayImage(productDetailDTO.getImagePath(), productImageView, options);
		
//		((Activity) context).runOnUiThread(new Runnable(){
//		    public void run(){
//		        imageLoader.displayImage(productDetailDTO.getImagePath(), productImageView, options);
//		        Log.i("TAG", "ImagePath"+":"+String.valueOf(productDetailDTO.imagePath)+ " "
//		        +"ImageView"+ productImageView
//		        		);
//		    }
//		});
	  }
	  /*
	   * Parse JSON with JackSON Parser directly into Java Objects
	   */
	  @SuppressWarnings("deprecation")
	public void parseJSON() throws JsonParseException, IOException {
			JsonFactory f = new JsonFactory();
			boolean breakLoop=false;
			boolean breakOuterLoop=false;
			boolean emptySimilarProduct = true;
			JsonParser jp = null;
			try {
//				jp = f.createJsonParser(context.getAssets().open("kadajsondata.txt"));
				jp = f.createJsonParser(RestClient.connect(AppConstants.URLS.PRODUCT_DETAIL_URL+urlProductId+"&intProductVersionId="+urlVersionId));
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
				if ("ProductDetails".equals(fieldname)) 
				{
					while (jp.nextToken() != JsonToken.END_ARRAY) 
					{
//						if ("}".equals(jp.getText())) {
//							productDetailDTO = new ProductDetailDTO();
//						}
						if (jp.getText().equals(JsonToken.END_OBJECT)) {
							break;
						}
						
						String responsefield = jp.getCurrentName();
						jp.nextToken(); // move to value
						
						if ("pdtId".equals(responsefield)) {
							productDetailDTO.setPdtId(jp.getText());
						} else if ("versionId".equals(responsefield)) {
							productDetailDTO.setVersionId(jp.getText());
						} else if ("pdtName".equals(responsefield)) {
							productDetailDTO.setPdtName(jp.getText());
						} else if ("isFeatured".equals(responsefield)) {
							productDetailDTO.setIsFeatured(jp.getText());
						}else if ("pdtFeaturedPrice".equals(responsefield)) {
							productDetailDTO.setPdtFeaturedPrice(jp.getText());
						}else if ("pdtPrice".equals(responsefield)) {
							productDetailDTO.setPdtPrice(jp.getText());
						}else if ("isStockout".equals(responsefield)) {
							productDetailDTO.setIsStockout(jp.getText());
						}else if ("isLimited".equals(responsefield)) {
							productDetailDTO.setIsLimited(jp.getText());
						}else if ("pdtBrand".equals(responsefield)) {
							productDetailDTO.setPdtBrand(jp.getText());
						}else if ("pdtDesc".equals(responsefield)) {
							productDetailDTO.setPdtDesc(jp.getText());
						}else if ("pdtType".equals(responsefield)) {
							productDetailDTO.setPdtType(jp.getText());
						}else if ("isFoodCoupon".equals(responsefield)) {
							productDetailDTO.setIsFoodCoupon(jp.getText());
						}else if ("stockAvailable".equals(responsefield)) {
							productDetailDTO.setStockAvailable(jp.getText());
						}else if ("imagePath".equals(responsefield)) {
							productDetailDTO.setImagePath(jp.getText());
						}
						else if("SimilarProducts".equals(responsefield))
						{
							jp.nextToken();
							if (jp.getText().equals("]")) {
								emptySimilarProduct= false;
								jp.nextToken();
								String responseImagePath = jp.getCurrentName();
								jp.nextToken(); // move to value
								if ("imagePath".equals(responseImagePath)) {
									productDetailDTO.setImagePath(jp.getText());
								}
							}
							while (jp.nextToken() != JsonToken.END_ARRAY && emptySimilarProduct) 
							{
								if ("}".equals(jp.getText())) {
									similarProductListDTO.add(similarProductDTO);
									similarProductDTO = new SimilarProductDTO();
								}
								if (jp.getText().equals(JsonToken.END_OBJECT)) {
									break;
								}
								
								String responsefield1 = jp.getCurrentName();
								jp.nextToken(); // move to value
								
								if ("pdtId".equals(responsefield1)) {
									similarProductDTO.setPdtId(jp.getText());
								} else if ("versionId".equals(responsefield1)) {
									similarProductDTO.setVersionId(jp.getText());
								} else if ("pdtName".equals(responsefield1)) {
									similarProductDTO.setPdtName(jp.getText());
								} else if ("isFeatured".equals(responsefield1)) {
									similarProductDTO.setIsFeatured(jp.getText());
								}else if ("pdtFeaturedPrice".equals(responsefield1)) {
									similarProductDTO.setPdtFeaturedPrice(jp.getText());
								}else if ("pdtPrice".equals(responsefield1)) {
									similarProductDTO.setPdtPrice(jp.getText());
								}else if ("isStockout".equals(responsefield1)) {
									similarProductDTO.setIsStockout(jp.getText());
								}else if ("isLimited".equals(responsefield1)) {
									similarProductDTO.setIsLimited(jp.getText());
								}else if ("isFoodCoupon".equals(responsefield1)) {
									similarProductDTO.setIsFoodCoupon(jp.getText());
								}else if ("stockAvailable".equals(responsefield1)) {
									similarProductDTO.setStockAvailable(jp.getText());
								}else if ("imagePath".equals(responsefield1)) {
									similarProductDTO.setImagePath(jp.getText());
								}  
								else if (jp.getText().equals("]")) {
									Log.i("Jackson", "Outta Similar Products");
									breakLoop=true;
									break;
								} else {
								Log.i("Jackson", "Unrecognized Token");
								}
							}
						}
						
						
						else if (jp.getText().equals("]")) {
							Log.i("Jackson", "Outta ProductDetail");
							breakOuterLoop=true;
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
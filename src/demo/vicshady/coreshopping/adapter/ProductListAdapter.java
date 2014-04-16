package demo.vicshady.coreshopping.adapter;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import demo.vicshady.coreshopping.R;
import demo.vicshady.coreshopping.DTO.ProductDTO;
import demo.vicshady.coreshopping.app.AppConstants;

public class ProductListAdapter extends BaseAdapter {

	Context context = null;
    ArrayList<ProductDTO> product = null;
    DisplayImageOptions options;
    ImageLoader imageLoader;
    Typeface fontStyle;
//    Random rand;
//    int max,min;
    
    public ProductListAdapter(Context context, ArrayList<ProductDTO> product, ImageLoader imageLoader, DisplayImageOptions options) {
        this.context = context;
        this.product=product;
        this.options = options;
        this.imageLoader = imageLoader;
//        rand=new Random();
//        max=99;min=0;
        fontStyle = Typeface.createFromAsset(context.getAssets(), AppConstants.fontStyle);
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return product.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
//        return coupons.get(position).getTitle();
    	return product.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return 0;
    }
    
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub

        View v;
        final TextView productTitle,productFeaturePrice,productPrice,productAvailability;
        final ImageView productImage;
        if (convertView == null) {
            LayoutInflater li = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = li.inflate(R.layout.item_product, null);

        } else {
            v = convertView;
        }
        
        productImage = (ImageView) v.findViewById(R.id.product_imgview);
        productTitle = (TextView) v.findViewById(R.id.product_title);
        productFeaturePrice = (TextView) v.findViewById(R.id.product_featured_price);
        productPrice = (TextView) v.findViewById(R.id.product_price);
        productAvailability = (TextView) v.findViewById(R.id.product_availability);
        
        productTitle.setTypeface(fontStyle);
        productFeaturePrice.setTypeface(fontStyle);
        productPrice.setTypeface(fontStyle);
        productAvailability.setTypeface(fontStyle);

        productTitle.setText(product.get(position).getPdtName());
        productFeaturePrice.setText(Html.fromHtml(context.getString(R.string.rupee_symbol))+" "+product.get(position).getPdtFeaturedPrice());
        productPrice.setText(Html.fromHtml(context.getString(R.string.rupee_symbol))+" "+product.get(position).getPdtPrice());
        productPrice.setPaintFlags(productPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        
        productAvailability.setText("Availability : " + (Integer.parseInt(product.get(position).getStockAvailable()) > 0 ? "In Stock ": "Out of Stock"));
        productAvailability.setTextColor(Integer.parseInt(product.get(position).getStockAvailable()) > 0 ? Color.GREEN: Color.RED);
        ((Activity) context).runOnUiThread(new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                imageLoader.displayImage(product.get(position).getImagePath(), productImage, options);
            }
        });

        v.setTag(R.id.TAG_PDT_ID, product.get(position).getPdtId());
        v.setTag(R.id.TAG_PDT_V_ID, product.get(position).getVersionId());
        v.setTag(R.id.TAG_PDT_NAME, product.get(position).getPdtName());
        return v;
    }
}


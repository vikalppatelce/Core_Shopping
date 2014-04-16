package demo.vicshady.coreshopping.adapter;

import java.util.ArrayList;
import java.util.Random;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import demo.vicshady.coreshopping.R;
import demo.vicshady.coreshopping.DTO.CategoryDTO;
import demo.vicshady.coreshopping.DTO.ProductDTO;
import demo.vicshady.coreshopping.DTO.SimilarProductDTO;
import demo.vicshady.coreshopping.DTO.SubCategoryDTO;

public class SimilarProductListAdapter extends BaseAdapter {

	Context context = null;
    ArrayList<SimilarProductDTO> product = null;
    DisplayImageOptions options;
    ImageLoader imageLoader;
//    Random rand;
//    int max,min;
    
    public SimilarProductListAdapter(Context context, ArrayList<SimilarProductDTO> product, ImageLoader imageLoader, DisplayImageOptions options) {
        this.context = context;
        this.product=product;
        this.options = options;
        this.imageLoader = imageLoader;
//        rand=new Random();
//        max=99;min=0;
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

        productTitle.setText(product.get(position).getPdtName());
        productFeaturePrice.setText(product.get(position).getPdtFeaturedPrice());
        productPrice.setText(product.get(position).getPdtPrice());
        productAvailability.setText(product.get(position).getStockAvailable());
        ((Activity) context).runOnUiThread(new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                imageLoader.displayImage(product.get(position).getImagePath(), productImage, options);
            }
        });

        return v;
    }
}


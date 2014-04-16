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
import demo.vicshady.coreshopping.app.AppConstants;

public class MainCategoryAdapter extends BaseAdapter {

	Context context = null;
    ArrayList<CategoryDTO> category = null;
    DisplayImageOptions options;
    ImageLoader imageLoader;
//    Random rand;
//    int max,min;
    
    public MainCategoryAdapter(Context context, ArrayList<CategoryDTO> category, ImageLoader imageLoader, DisplayImageOptions options) {
        this.context = context;
        this.category=category;
        this.options = options;
        this.imageLoader = imageLoader;
//        rand=new Random();
//        max=99;min=0;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return category.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
//        return coupons.get(position).getTitle();
    	return category.get(position);
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
        final TextView categoryTitle;
        final ImageView categoryImage;
        if (convertView == null) {
            LayoutInflater li = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = li.inflate(R.layout.item_main_category, null);

        } else {
            v = convertView;
        }
        
        categoryImage = (ImageView) v.findViewById(R.id.category_imgview);
        categoryTitle = (TextView) v.findViewById(R.id.category_title);
        
//        int r = min + rand.nextInt(max - min + 1);
//        int g = min + rand.nextInt(max - min + 1);
//        int b = min + rand.nextInt(max - min + 1);
//        
//        String colorString = "#"+String.valueOf(r)+String.valueOf(g)+String.valueOf(b);
        categoryImage.setBackgroundColor(Color.GRAY);
//        categoryImage.setBackgroundColor(Color.parseColor(colorString));
        ((Activity) context).runOnUiThread(new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                categoryTitle.setText(category.get(position).getCatName());
                imageLoader.displayImage(category.get(position).getImagePath(), categoryImage, options);
            }
        });
        v.setTag(R.id.TAG_CAT_ID, category.get(position).getCatId().toString());//1 - catId
        v.setTag(R.id.TAG_CAT_NAME, category.get(position).getCatName().toString());//2 - catName
        return v;
    }
}


package com.example.androidstore.network.services;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.toolbox.NetworkImageView;
import com.example.androidstore.R;
import com.example.androidstore.dto.ProductImageDTO;
import com.example.androidstore.network.ImageRequester;

import java.util.List;

public class ListViewAdapter extends ArrayAdapter<ProductImageDTO> {
    private List<ProductImageDTO> images;
    private Activity context;
    ImageRequester imageRequester=ImageRequester.getInstance();;
    public static int count=0;

    public ListViewAdapter(Activity context,List<ProductImageDTO>images)
    {
        super(context, R.layout.row_item,images);
        this.context = context;
        this.images = images;


    }
    public ListViewAdapter(Activity context)
    {
        super(context, R.layout.row_item);
        this.context = context;

    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View row=convertView;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if(convertView==null)
            row = inflater.inflate(R.layout.row_item, parent, false);
        NetworkImageView imageFlag = (NetworkImageView) row.findViewById(R.id.imageViewFlag);

        imageRequester.setImageFromUrl(imageFlag,images.get(count).getPath());
        System.out.println("sssssss");
        count++;
        row.setOnClickListener(new View.OnClickListener(){
            @Override
            public  void onClick(View view){
                Toast.makeText(getContext(),"Clicked",Toast.LENGTH_SHORT).show();
            }
        });
        return row;
    }

    public void setImages(List<ProductImageDTO> images) {
        this.images = images;
    }
}

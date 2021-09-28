package com.example.androidstore.network.services;

import android.content.Context;
import android.renderscript.ScriptIntrinsicYuvToRGB;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.toolbox.NetworkImageView;
import com.example.androidstore.R;
import com.example.androidstore.constans.Urls;
import com.example.androidstore.dto.ProductDTO;
import com.example.androidstore.dto.ProductImageDTO;
import com.example.androidstore.network.ImageRequester;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ItemViewHolder>{
   private  static  int viewHolderCount;
   private List<ProductImageDTO> numberItems;

    public ProductAdapter(List<ProductImageDTO> body) {
        this.numberItems = body;
        viewHolderCount=0;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context=parent.getContext();
        int layoutIdForListItem=R.layout.recycler_list_item;
        LayoutInflater inflater=LayoutInflater.from(context);

        View view=inflater.inflate((layoutIdForListItem),parent,false);
        ItemViewHolder viewHolder=new ItemViewHolder(view);
       //String url ="https://i.pinimg.com/originals/82/e6/7c/82e67c686a6f80321455b3d016def273.jpg";
        viewHolder.imageRequester.setImageFromUrl(viewHolder.listItemsView,numberItems.get(viewHolderCount).getPath());
        viewHolderCount++;
        return  viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return this.numberItems.size();
    }

    class ItemViewHolder extends RecyclerView.ViewHolder{
        NetworkImageView listItemsView;
        ImageRequester imageRequester;


        public ItemViewHolder(View itemView)
        {
           super(itemView);
           listItemsView=itemView.findViewById(R.id.recycler_item);
           imageRequester = ImageRequester.getInstance();
        }

        void bind(String url){

        }
    }
}

package com.example.androidstore.network.services;

import android.content.Context;
import android.renderscript.ScriptIntrinsicYuvToRGB;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidstore.R;
import com.example.androidstore.dto.ProductDTO;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ItemViewHolder>{

   private  static  int viewHolderCount;
   private List<ProductDTO> numberItems;

    public ProductAdapter(List<ProductDTO> body) {
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

        viewHolder.viewHolderPrice.setText("Price: "+(numberItems.get(viewHolderCount).getPrice()));
        viewHolderCount++;
        return  viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        holder.bind(numberItems.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return numberItems.size();
    }

    class ItemViewHolder extends RecyclerView.ViewHolder{
        TextView listItemsView;
        TextView viewHolderPrice;

        public ItemViewHolder(View itemView)
        {
           super(itemView);
           listItemsView=itemView.findViewById(R.id.recycler_item);
           viewHolderPrice=itemView.findViewById(R.id.holder_item);
        }

        void bind(String name){
            listItemsView.setText(name);
        }
    }
}

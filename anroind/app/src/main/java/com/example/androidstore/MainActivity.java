package com.example.androidstore;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;
import com.example.androidstore.constans.Urls;
import com.example.androidstore.dto.ProductDTO;
import com.example.androidstore.dto.ProductImageDTO;
import com.example.androidstore.network.ImageRequester;
import com.example.androidstore.network.services.ListViewAdapter;
import com.example.androidstore.network.services.ProductAdapter;
import com.example.androidstore.network.services.ProductService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Spliterator;
import java.util.function.UnaryOperator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

private RecyclerView itemList;
private ProductAdapter adapter;
private ListViewAdapter listAdapter;
private ListView listView;
public static Activity context;
public EditText index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        itemList=findViewById(R.id.recycler);
        listView=(ListView) findViewById(R.id.listViewitems);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        itemList.setLayoutManager(layoutManager);
        itemList.setHasFixedSize(true);
        context=this;
        index=findViewById((R.id.index));


    }

    public void recyclerClick(View v)
    {
        ProductService.getInstance()
                .getProductsApi()
                .getPostWithID(1)
                .enqueue( new Callback<List<ProductImageDTO>>(){
                    @Override
                    public void onResponse(Call<List<ProductImageDTO>> call, Response<List<ProductImageDTO>> response) {
                        if(response.isSuccessful())
                        {
                            System.out.println("ss");
                            List<ProductImageDTO> body=response.body();
                            adapter=new ProductAdapter(body);
                            itemList.setAdapter(adapter);
                        }
                    }
                    @Override
                    public void onFailure(Call<List<ProductImageDTO>> call, Throwable t) {
                        System.out.println(t.getMessage());
                    }
                });
    }

    public void listViewClick(View v)
    {
        int i=Integer.parseInt(index.getText().toString());
        if(i<0)
        {
            i=0;
        }
        ProductService.getInstance()
                .getProductsApi()
                .getPostWithID(i)
                .enqueue( new Callback<List<ProductImageDTO>>(){
                    @Override
                    public void onResponse(Call<List<ProductImageDTO>> call, Response<List<ProductImageDTO>> response) {
                        if(response.isSuccessful())
                        {
                            List<ProductImageDTO> body=response.body();
                        listAdapter=new ListViewAdapter(context,body);
                        listView.setAdapter(listAdapter);
                        }
                    }
                    @Override
                    public void onFailure(Call<List<ProductImageDTO>> call, Throwable t) {
                        System.out.println(t.getMessage());
                    }
                });
    }
}
package com.example.androidstore;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.androidstore.dto.ProductDTO;
import com.example.androidstore.network.services.ProductAdapter;
import com.example.androidstore.network.services.ProductService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

private RecyclerView itemList;
private ProductAdapter adapter;

private ListView listView;

    //private TextView txtinfo;
    //private ImageRequester imageRequester;
    //private NetworkImageView myImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        itemList=findViewById(R.id.recycler);
        listView=(ListView) findViewById(R.id.listViewitems);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        itemList.setLayoutManager(layoutManager);
        itemList.setHasFixedSize(true);



       // String url = Urls.BASE + "/images/manul.jpg";
       // imageRequester = ImageRequester.getInstance();
       // myImage = findViewById(R.id.myimg);
       // imageRequester.setImageFromUrl(myImage, url);

        //txtinfo=findViewById(R.id.txtinfo);


    }

    public void recyclerClick(View v)
    {
        ProductService.getInstance()
                .getProductsApi()
                .all()
                .enqueue( new Callback<List<ProductDTO>>(){
                    @Override
                    public void onResponse(Call<List<ProductDTO>> call, Response<List<ProductDTO>> response) {
                        System.out.println("ss");
                        if(response.isSuccessful())
                        {
                            List<ProductDTO> body=response.body();
                            adapter=new ProductAdapter(body);
                            itemList.setAdapter(adapter);
                        }
                    }
                    @Override
                    public void onFailure(Call<List<ProductDTO>> call, Throwable t) {
                        System.out.println(t.getMessage());
                    }
                });
    }

    public void listViewClick(View v)
    {
        ProductService.getInstance()
                .getProductsApi()
                .all()
                .enqueue( new Callback<List<ProductDTO>>(){
                    @Override
                    public void onResponse(Call<List<ProductDTO>> call, Response<List<ProductDTO>> response) {
                        System.out.println("ss");
                        if(response.isSuccessful())
                        {
                            List<ProductDTO> body=response.body();
                            ArrayList<String> items=new ArrayList<>() ;

                            for (int i=0;i<body.size();i++)
                            {
                                items.add(body.get(i).getName()+" Price: "+body.get(i).getPrice());
                            }

                            ArrayAdapter adapter=new ArrayAdapter(v.getContext(), android.R.layout.simple_list_item_1,items);
                            listView.setAdapter(adapter);
                        }
                    }
                    @Override
                    public void onFailure(Call<List<ProductDTO>> call, Throwable t) {
                        System.out.println(t.getMessage());
                    }
                });
    }
}
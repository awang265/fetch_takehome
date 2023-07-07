package com.example.fetch;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {
    RecyclerView recycler_view;
    ItemAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recycler_view = findViewById(R.id.recycler_view);

        setRecyclerView();
    }

    private void setRecyclerView() {
        recycler_view.setHasFixedSize(true);
        recycler_view.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ItemAdapter(this, getList());
        recycler_view.setAdapter(adapter);
    }

    private List<ItemModel> getList() {

        //get and sort data
        try {
            //get JSONArray from JSON file
            String jsonUrl = "https://fetch-hiring.s3.amazonaws.com/hiring.json";
            JsonScrapingTask scrapingTask = new JsonScrapingTask(jsonUrl);
            String json = scrapingTask.execute().get();

            JSONArray itemArray = new JSONArray(json);
            List<ItemModel> itemList = new ArrayList<>();
            // implement for loop to put the data into a sortable list
            for (int i = 0; i < itemArray.length(); i++) {
                // add to JSONObject list
                if (!itemArray.getJSONObject(i).getString("name").equals("null") &&
                        !itemArray.getJSONObject(i).getString("name").equals("")) { //filter out null and blank values
                    JSONObject item = itemArray.getJSONObject(i);
                    itemList.add(new ItemModel(item.getString("listId"), item.getString("name"), item.getString("id")));
                }
            }
//
//            //create listId comparator
            class ItemComparator implements Comparator<ItemModel> {
                public int compare(ItemModel a, ItemModel b) {
                        //first sort by ListId
                        String valA = a.getlistId();
                        String valB = b.getlistId();
                        int listIdComp = valA.compareTo(valB);
                        if(listIdComp != 0) {
                            return(listIdComp);
                        }

                        //then sort by name
                        String stringA = a.getName();
                        String stringB = b.getName();
                        return(stringA.compareTo(stringB));
                }
            }
//            //sort by listId
            Collections.sort(itemList, new ItemComparator());
            return itemList;

        } catch (JSONException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}

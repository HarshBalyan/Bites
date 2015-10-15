package harsh.volleytest;

import android.app.Activity;
import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    private String fetch_rest_all_url ="http://10.0.2.2/Dineapp/fetch_rest_all.php";
    private String fetch_city_url ="http://10.0.2.2/Dineapp/fetch_city.php";
    List<Restaurant> restList=new ArrayList<Restaurant>();
    List<String> cityList=new ArrayList<String>();
    CustomListAdapter listAdapter;
    ProgressDialog pDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final RequestQueue requestQueue= Volley.newRequestQueue(this);

        Spinner spinner=(Spinner)findViewById(R.id.spinner);
        final ArrayAdapter spinnerAdapter= new ArrayAdapter(this,R.layout.dropdown_item,cityList);
        spinner.setAdapter(spinnerAdapter);
        try{spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                populateList(parent.getItemAtPosition(position).toString(),requestQueue);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });}
        catch(Exception e){Log.e("spinner",e.getMessage());}
        JsonObjectRequest jsonSpinnerObjectRequest=new JsonObjectRequest(Request.Method.GET,fetch_city_url,null,new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {

                    JSONArray jsonArray = response.getJSONArray("cities");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject city = jsonArray.getJSONObject(i);
                        Log.i("onResponseSpinner", city.getInt("cid") + " " + city.getString("cname"));
                        cityList.add(city.getString("cname"));
                        //cityList.add(new City(city.getInt("cid"),city.getString("cname")));
                    }
                    spinnerAdapter.notifyDataSetChanged();

                }catch(JSONException jex)
                {
                    jex.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.e("VOLLEY","ERROR in response"+error.getMessage());
            }
        });
        requestQueue.add(jsonSpinnerObjectRequest);
        ListView listView=(ListView)findViewById(R.id.listView);

        listAdapter = new CustomListAdapter(this,restList,requestQueue);
        listView.setAdapter(listAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), "Good Choice", Toast.LENGTH_SHORT).show();
            }
        });
        pDialog=new ProgressDialog(this);
        pDialog.setMessage("Loading...");
        pDialog.show();
        //final String selection="Lucknow";

    }


    protected void populateList(final String selection, RequestQueue requestQueue)
    {
        JsonObjectRequest jsonListObjectRequest=new JsonObjectRequest(Request.Method.GET,fetch_rest_all_url,null,new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    if (pDialog != null) {
                        pDialog.dismiss();
                        pDialog = null;
                    }
                    restList.clear();
                    JSONArray jsonArray = response.getJSONArray("restncity");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject rest = jsonArray.getJSONObject(i);
                        String rname=rest.getString("rname");
                        String cname=rest.getString("cname");
                        String url=rest.getString("url");
                        Log.i("POST RESPONSE", rname +cname +url);
                        if(rest.getString("cname").compareTo(selection)==0)
                            restList.add(new Restaurant(url,rname,cname));

                    }
                }catch(JSONException jex)
                {
                    jex.printStackTrace();
                }
                Log.i("LIST RESPONSE",restList.size()+"");
                listAdapter.notifyDataSetChanged();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.e("VOLLEY POST","ERROR in post response"+error.getMessage());
                if (pDialog != null) {
                    pDialog.dismiss();
                    pDialog = null;
                }
            }
        });
        requestQueue.add(jsonListObjectRequest);
    }
   }

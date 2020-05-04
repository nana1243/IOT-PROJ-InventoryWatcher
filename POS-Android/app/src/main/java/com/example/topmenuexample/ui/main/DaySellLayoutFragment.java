package com.example.topmenuexample.ui.main;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.topmenuexample.MainActivity;
import com.example.topmenuexample.R;
import com.example.topmenuexample.adapter.SellListAdapter;
import com.example.topmenuexample.frame.Sales;
import com.example.topmenuexample.frame.SalesVO;
import com.example.topmenuexample.network.getDailySalesHttpHandler;
import com.example.topmenuexample.network.getSalesHttpHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/*
일일 판매량 나타내는 Fragment , DB 에서 판매량을 일별로 묶어서 가져온 후 한줄씩 표시할 수 있도록 수정해야한다.
 */

public class DaySellLayoutFragment extends Fragment {

    private String title;
    private int page;
    private Sales sales = new Sales();
    private SalesVO sdb = new SalesVO();
    private ArrayList<Sales> salesList = new ArrayList<Sales>();
    private ArrayList<SalesVO> sdbList = new ArrayList<SalesVO>();
    private ArrayList<SalesVO> sdbList2 = new ArrayList<SalesVO>();
    RecyclerView list_sellView;
    private SellListAdapter sellListAdapter;
    Button button_closedaysell;
    TextView txt_startday,txt_lastday;
    String startDay,lastDay;


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {

        super.setUserVisibleHint(isVisibleToUser);
        getFragmentManager().beginTransaction().detach(this).attach(this).commit();

        Log.d("---", "daySellLayout isvisible ? :" + isVisibleToUser);
        if (isVisibleToUser) {
            if (sales != null && salesList != null) {
                sales = ((MainActivity) getActivity()).tempSales;
                salesList = ((MainActivity) getActivity()).tempSalesList;

               // getSalesData();
                getDailySalesData();

                Log.d("---", "sales : " + sales.toString());
                Log.d("---", "salesList : " + salesList.toString());
                sellView(this.getView());
            }


        } else {
            Log.d("---", "not Visible to User");

        }

    }

    // newInstance constructor for creating fragment with arguments
    public static DaySellLayoutFragment newInstance(int page, String title) {
        DaySellLayoutFragment fragment = new DaySellLayoutFragment();
        Bundle args = new Bundle();
        args.putInt("someInt", page);
        args.putString("someTitle", title);
        Log.d("---", "page:" + page + "title" + title);
        fragment.setArguments(args);
        return fragment;
    }

    // Store instance variables based on arguments passed
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        page = getArguments().getInt("someInt", 0);
        title = getArguments().getString("someTitle");

    }

    public void sellView(View view) {

        list_sellView = view.findViewById(R.id.list_sellView);
        LinearLayoutManager sllm = new LinearLayoutManager(getContext());
        list_sellView.setLayoutManager(sllm);


        sellListAdapter = new SellListAdapter(sdbList2);
        list_sellView.setAdapter(sellListAdapter);

        button_closedaysell = view.findViewById(R.id.button_closedaysell);
        button_closedaysell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).setPageNum(0);
                ((MainActivity) getActivity()).viewPager.setCurrentItem(0);
            }
        });

        txt_lastday = view.findViewById(R.id.txt_lastday);
        txt_startday = view.findViewById(R.id.txt_startday);


    }

    public void getSalesData() {

        getDataFromHttp task = new getDataFromHttp();
        task.execute();
    }

    public void getDailySalesData() {

        getDailyDataFromHttp task = new getDailyDataFromHttp();
        task.execute();
    }


    class getDataFromHttp extends AsyncTask<Void, Void, String> {

        URL url;
        JSONObject jo;
        JSONArray ja;

        public getDataFromHttp() {
            try {
                url = new URL("http://" + MainActivity.IP + "/top/pos.top");
                this.jo = jo;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }

        }

        // background 에서 http 요청
        @Override
        protected String doInBackground(Void... voids) {
            return getSalesHttpHandler.requestData(url);
        }


        // 받아온 값 정제 //
        @Override
        protected void onPostExecute(String result) {
            try {
                ja = new JSONArray(result);
                sdbList = new ArrayList<SalesVO>();
                for (int i = 0; i < ja.length(); i++) {
                    jo = ja.getJSONObject(i);
                    sdb = new SalesVO();
                    sdb.setChainID(jo.getString("chainID"));

                    sdb.setSalesID(jo.getString("salesID"));

                    sdb.setSalesRegDate(jo.getString("salesRegDate"));
                    sdb.setTotSales(Integer.parseInt(jo.getString("totSales")));
                    sdbList.add(sdb);

                }
                // 사실 ChainID는 태블릿마다 지정되기때문에, DB에서 굳이 안받아와도 된다. //
                String chainID = sdbList.get(0).getChainID();
                ((MainActivity)getActivity()).tempchainID = chainID;
                Log.d("---", "salesDBList" + sdbList.toString());
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

        class getDailyDataFromHttp extends AsyncTask<Void, Void, String> {

            URL url;
            JSONObject jo;
            JSONArray ja;

            public getDailyDataFromHttp() {
                try {
                    url = new URL("http://" + MainActivity.IP + "/top/posgetdailydata.top");
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }

            }

            // background 에서 http 요청
            @Override
            protected String doInBackground(Void... voids) {
                return getDailySalesHttpHandler.requestData(url);
            }


            // 받아온 값 정제 //
            @Override
            protected void onPostExecute(String result) {
                try {
                    ja = new JSONArray(result);
                    sdbList2 = new ArrayList<SalesVO>();
                    if(ja.length() != 0 ){
                    for(int i = 0; i<ja.length();i++){
                        jo = ja.getJSONObject(i);
                        sdb = new SalesVO();
                        sdb.setDailySales(jo.getString("dailySales"));
                        sdb.setRevenue(jo.getString("revenue"));


                        sdbList2.add(sdb);
                    }
                    lastDay = sdbList2.get(0).getDailySales();
                    startDay = sdbList2.get(sdbList2.size()-1).getDailySales();
                    txt_lastday.setText(lastDay);
                    txt_startday.setText(startDay);
                    Log.d("---", "lastDay : " + lastDay + " startDay" + startDay);
                    Log.d("---","salesDBList" + sdbList2.toString());}
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

    }


    // Inflate the view for the fragment based on layout XML
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.daysell_layout, container, false);

        sellView(view);
        return view;
    }


}

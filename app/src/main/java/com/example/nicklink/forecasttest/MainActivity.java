package com.example.nicklink.forecasttest;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nicklink.forecasttest.adapter.RecyclerClick;
import com.example.nicklink.forecasttest.adapter.WeatherAdapter;
import com.example.nicklink.forecasttest.database.WeatherDatabase;
import com.example.nicklink.forecasttest.database.WeatherDatabaseImpl;
import com.example.nicklink.forecasttest.domain.WeatherResponse;
import com.example.nicklink.forecasttest.fragments.RetainDataFragment;
import com.example.nicklink.forecasttest.presenter.MainPresenter;
import com.example.nicklink.forecasttest.presenter.MainPresenterImpl;
import com.example.nicklink.forecasttest.presenter.MainView;
import com.example.nicklink.forecasttest.rest.ApiClient;
import com.example.nicklink.forecasttest.rest.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, RecyclerClick, MainView {

//    WeatherResponse responce;
//    WeatherDatabaseImpl database;




    private static final String TAG = MainActivity.class.getSimpleName();
//    private CoordinatorLayout coordinatorLayout;
//    private Toolbar toolbar;
//    private BottomNavigationView navigationView;
    private MainPresenter presenter;
    private RetainDataFragment dataFragment;
    private RecyclerView recyclerView;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.root);
//        toolbar = (Toolbar) findViewById(R.id.toolbar);
//        navigationView = (BottomNavigationView) findViewById(R.id.navigation);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
//        setSupportActionBar(toolbar);
        LinearLayoutManager layoutManager =
                new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        recyclerView.setLayoutManager(layoutManager);
        DividerItemDecoration mDividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(mDividerItemDecoration);

        presenter = new MainPresenterImpl(this);

//        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView
//                .OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                presenter.navigationItemClick(item.getItemId());
//                return true;
//            }
//        });

        presenter.loadData();

//        database = new WeatherDatabaseImpl(this);

//        Button inet = (Button) findViewById(R.id.inet);
//        Button button = (Button) findViewById(R.id.button);
//        inet.setOnClickListener(this);
//        button.setOnClickListener(this);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
//        final MenuItem alertMenuItem = menu.findItem(R.id.refresh);
//        FrameLayout rootView = (FrameLayout) alertMenuItem.getActionView();
//        redCircle = (FrameLayout) rootView.findViewById(R.id.view_alert_red_circle);
//        countTextView = (TextView) rootView.findViewById(R.id.view_alert_count_textview);
//        rootView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                onOptionsItemSelected(alertMenuItem);
//            }
//        });
//        presenter.setMenuStatus();
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.forecast, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        presenter.onOptionsItemClick(item.getItemId());
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        Log.e(TAG, "-> onClick " + view.getId());

//        if(view.getId() == R.id.button){
//
//            responce = database.getWeather(CITY, DAYS);
//            Log.e(TAG, "Success " + responce.toString());
//        }
//
//        if(view.getId() == R.id.inet){
//
//            ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
//            Call<WeatherResponse> call = apiInterface.getWeatherResponce(CITY, APPID, DAYS, UNITS);
//            call.enqueue(new Callback<WeatherResponse>() {
//                @Override
//                public void onResponse(Call<WeatherResponse> call, Response<WeatherResponse> response) {
//                    Log.e(TAG, "-> onResponse");
//                    responce = response.body();
//                    Log.e(TAG, "Success " + responce.toString());
//                    database.saveWeather(CITY, DAYS, responce);
//                }
//
//                @Override
//                public void onFailure(Call<WeatherResponse> call, Throwable t) {
//                    Log.e(TAG, "Error " + t.toString());
//                }
//            });
//
//        }
    }


    @Override
    public void onClick(int position, int id) {

    }

    @Override
    public void showLoadingIndicator() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoadingIndicator() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void setAdapter(WeatherAdapter adapter) {
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void showSnack(String message) {
//        Snackbar.make(coordinatorLayout, message, Snackbar.LENGTH_INDEFINITE)
//                .setAction("One more", new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        Toast.makeText(getApplicationContext(), "Respect!", Toast.LENGTH_LONG).show();
//                    }
//                }).show();
    }

    @Override
    public void updateAlertIcon(String count, int visible) {

    }

    @Override
    public void showFragment(Fragment fragment) {

    }

    public RetainDataFragment getDataFragment(){
        if (dataFragment == null) {
            dataFragment = new RetainDataFragment();
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(dataFragment, "retain")
                    .commit();

        }
        return dataFragment;
    }

}

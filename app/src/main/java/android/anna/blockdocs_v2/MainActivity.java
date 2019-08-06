package android.anna.blockdocs_v2;

import android.anna.blockdocs_v2.helpers.AsyncTasks.EthGetDocumentsTask;
import android.anna.blockdocs_v2.helpers.DocArrayAdapter;
import android.anna.blockdocs_v2.model.Doc;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.Web3jFactory;
import org.web3j.protocol.core.methods.response.Web3ClientVersion;
import org.web3j.protocol.http.HttpService;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;


public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    TextView welcome;
    private View mProgressView;
    private View mMainFormView;
    private SwipeRefreshLayout mSwipeRefreshLayout;

    String TAG = "MAIN";
    List<Doc> docs = new ArrayList<>();
    Credentials credentials;
    BlockdocsApplication app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "In onCreate()");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        welcome = findViewById(R.id.main_welcomeText);
        mMainFormView = findViewById(R.id.main_docsListView);
        mProgressView = findViewById(R.id.main_progressBar);
        mProgressView.setTag("progressBar");
        mSwipeRefreshLayout = findViewById(R.id.swiperefresh);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setColorSchemeColors(Color.parseColor("#673AB7"));

        app = (BlockdocsApplication) getApplicationContext();
        credentials = app.getCredentials();


    }

    @Override
    protected void onStart() {
        super.onStart();
        try {
            app.showProgress(true, mMainFormView, mProgressView);
            docs = new EthGetDocumentsTask(credentials, app.getContractAddress()).execute().get();
            Intent intent = getIntent();
            welcome.setText("Welcome " +
                    intent.getStringExtra("login"));
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            Intent intent = getIntent();
            welcome.setText("Welcome " +
                    intent.getStringExtra("login")
                    + "\n Something went wrong");
        } finally {
            app.showProgress(false, mMainFormView, mProgressView);
        }

        ArrayAdapter arrayAdapter = new DocArrayAdapter(this, 0, docs);
        ListView listView = findViewById(R.id.main_docsListView);
        listView.setAdapter(arrayAdapter);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);
    }

    public void onBlockClick(View view) {
        Log.d(TAG, "In onClick for ConstraintLayout");
        try {
            Web3j web3 = Web3jFactory.build(new HttpService("http://172.18.4.207:8545"));
            Web3ClientVersion web3ClientVersion = web3.web3ClientVersion().sendAsync().get();
            String clientVersion = web3ClientVersion.getWeb3ClientVersion();
            Log.d(TAG, "clientVersion " + clientVersion);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

    }

    public void onAddDocClick(View view) {
        Intent intent = new Intent(MainActivity.this, EntryActivity.class);
        intent.putExtras(getIntent());
        startActivity(intent);
    }

    @Override
    public void onRefresh() {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Отменяем анимацию обновления
                mSwipeRefreshLayout.setRefreshing(false);
                try {
                    docs = new EthGetDocumentsTask(credentials, app.getContractAddress()).execute().get();
                    Intent intent = getIntent();
                    welcome.setText("Welcome " +
                            intent.getStringExtra("login"));
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                    Intent intent = getIntent();
                    welcome.setText("Welcome " +
                            intent.getStringExtra("login")
                            + "\n Something went wrong");

                }
            }
        }, 1000);
    }
}

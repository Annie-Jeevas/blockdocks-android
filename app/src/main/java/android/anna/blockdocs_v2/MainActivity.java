package android.anna.blockdocs_v2;

import android.anna.blockdocs_v2.helpers.AsyncTasks.EthDeployTask;
import android.anna.blockdocs_v2.helpers.AsyncTasks.EthGetDocumentsTask;
import android.anna.blockdocs_v2.helpers.EthHelper;
import android.anna.blockdocs_v2.helpers.DocArrayAdapter;
import android.anna.blockdocs_v2.model.Doc;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
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


public class MainActivity extends AppCompatActivity {

    TextView welcome;
    ConstraintLayout docBlock;
    String TAG = "MAIN";
    List<Doc> docs = new ArrayList<>();
    Credentials credentials;

    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG,"In onCreate()");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        welcome = findViewById(R.id.welcomeText);
        BlockdocsApplication app = (BlockdocsApplication) getApplicationContext();
        credentials = app.getCredentials();
        Intent intent = getIntent();
        welcome.setText("Welcome " +
                intent.getStringExtra("login"));
        try {
            docs = new EthGetDocumentsTask(credentials, app.getContractAddress()).execute().get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        ArrayAdapter arrayAdapter = new DocArrayAdapter(this, 0, docs);
        ListView listView = findViewById(R.id.docsListView);
        listView.setAdapter(arrayAdapter);
    }

    public void onBlockClick(View view) {
        Log.d(TAG,"In onClick for ConstraintLayout");
        try {
            Web3j web3 = Web3jFactory.build(new HttpService("http://192.168.1.65:8545"));  // defaults to http://localhost:8545/
            Web3ClientVersion web3ClientVersion = web3.web3ClientVersion().sendAsync().get();
            String clientVersion = web3ClientVersion.getWeb3ClientVersion();
            Log.d(TAG,"clientVersion " + clientVersion);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

    }

    public void onAddDocClick(View view) {
        Intent intent = new Intent(MainActivity.this, EntryActivity.class);
        intent.putExtras(getIntent());
        startActivity(intent);
    }
}

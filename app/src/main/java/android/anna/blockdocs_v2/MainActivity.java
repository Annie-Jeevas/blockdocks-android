package android.anna.blockdocs_v2;

import android.anna.blockdocs_v2.helpers.EthHelper;
import android.anna.blockdocs_v2.model.Doc;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.web3j.crypto.CipherException;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.Web3jFactory;
import org.web3j.protocol.core.methods.response.Web3ClientVersion;
import org.web3j.protocol.http.HttpService;

import java.io.File;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.logging.Logger;


public class MainActivity extends AppCompatActivity {

    TextView welcome;
    TextView contractAddress;
    ConstraintLayout docBlock;
    Logger log = Logger.getLogger("Main");
    ArrayList<Doc> docs = new ArrayList<>();
    private Context context = this;
    Credentials credentials;

    class DocArrayAdapter extends ArrayAdapter<Doc> {
        private Context context;
        private List<Doc> docList;

        //constructor, call on creation
        public DocArrayAdapter(Context context, int resource, ArrayList<Doc> objects) {
            super(context, resource, objects);
            this.context = context;
            this.docList = objects;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            //get the property we are displaying
            log.info("In getView() with position " + position);
            Doc doc = docList.get(position);

            //get the inflater and inflate the XML layout for each item
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate(R.layout.docblock_layout, null);

            TextView number = (TextView) view.findViewById(R.id.number);
            TextView specialization = (TextView) view.findViewById(R.id.specialization);
            TextView qualification = (TextView) view.findViewById(R.id.qualification);
            TextView graduationDate = (TextView) view.findViewById(R.id.graduation);

            number.setText(String.valueOf(doc.getNumber()));
            specialization.setText(doc.getSpecialization());
            qualification.setText(doc.getQualification());
            graduationDate.setText(doc.getGraduationDate());

            return view;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        log.info("In onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        welcome = (TextView) findViewById(R.id.welcomeText);
        BlockdocsApplication app = (BlockdocsApplication) getApplicationContext();
        credentials = app.getCredentials();
        Intent intent = getIntent();
        welcome.setText("Welcome " +
                intent.getStringExtra("login"));
        EthHelper.fillDocList(docs);

        ArrayAdapter arrayAdapter = new DocArrayAdapter(this, 0, docs);
        ListView listView = (ListView) findViewById(R.id.docsListView);
        listView.setAdapter(arrayAdapter);
    }

    public void onBlockClick(View view) {
        log.info("In onClick for ConstraintLayout");
        try {
            Web3j web3 = Web3jFactory.build(new HttpService("http://192.168.1.65:8545"));  // defaults to http://localhost:8545/
            Web3ClientVersion web3ClientVersion = web3.web3ClientVersion().sendAsync().get();
            String clientVersion = web3ClientVersion.getWeb3ClientVersion();
            log.info("clientVersion " + clientVersion);
            EthHelper.getDocuments(credentials);
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

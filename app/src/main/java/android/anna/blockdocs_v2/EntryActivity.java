package android.anna.blockdocs_v2;

import android.anna.blockdocs_v2.helpers.EthHelper;
import android.anna.blockdocs_v2.model.Doc;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import org.web3j.crypto.Credentials;

public class EntryActivity extends AppCompatActivity {


    private static final String TAG = "EntryActivity";
    EditText docNumber;
    EditText docSpec;
    EditText docQual;
    EditText docDate;

    Credentials credentials;
    BlockdocsApplication app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry);
        docNumber = findViewById(R.id.docNumber);
        docSpec = findViewById(R.id.docSpec);
        docQual = findViewById(R.id.docQual);
        docDate = findViewById(R.id.docDate);
        app = (BlockdocsApplication) getApplicationContext();
        credentials = app.getCredentials();
    }

    public void onSaveClick(View view) {
        Doc d = new Doc(
                Integer.valueOf(docNumber.getText().toString()),
                docDate.getText().toString(),
                docQual.getText().toString(),
                docSpec.getText().toString(),
                app.getFIO());
        Log.d(TAG, EthHelper.saveDoc(d, credentials, app.getContractAddress()).toString());
        Intent intent = new Intent(EntryActivity.this, MainActivity.class);
        intent.putExtras(getIntent());
        startActivity(intent);
    }

}

package android.anna.blockdocs_v2.helpers.AsyncTasks;

import android.anna.blockdocs_v2.Documents;
import android.anna.blockdocs_v2.R;
import android.anna.blockdocs_v2.model.Doc;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.util.Log;

import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.Web3jFactory;
import org.web3j.protocol.http.HttpService;

import java.math.BigInteger;

/**
 * Created by Анюта on 03.12.2017.
 */

public class EthAddDocumentTask extends AsyncTask<Void, Void, Boolean> {

    private Credentials credentials;
    private String contractAddress;
    private Doc doc;
    String TAG = "TASK-EthAddDocumentTask";

    public EthAddDocumentTask(Credentials credentials, String contractAddress, Doc doc) {
        this.credentials = credentials;
        this.contractAddress = contractAddress;
        this.doc = doc;
    }

    @Override
    protected Boolean doInBackground(Void... voids) {
        Web3j web3 = Web3jFactory.build(new HttpService("http://172.18.4.207:8545"));
        Documents docs = Documents.load(contractAddress,
                web3,
                credentials,
                new BigInteger("100000000000"),
                new BigInteger("4712388"));
        Log.d(TAG, "Try to add doc. Before try");
        try {
            Log.d(TAG, "Try to add doc");
            docs.addDocument(doc.toJson(), doc.getFIO())
                    .send();
            return true;
        } catch (Exception e) {
            Log.d(TAG, "Try to add doc failed. In catch");
            e.printStackTrace();
        }
        return false;
    }

    @Override
    protected void onPostExecute(Boolean result) {
        Log.d(TAG, result.toString());

    }
}

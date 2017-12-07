package android.anna.blockdocs_v2.helpers.AsyncTasks;

import android.anna.blockdocs_v2.Documents;
import android.anna.blockdocs_v2.model.Doc;
import android.os.AsyncTask;
import android.util.Log;

import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.Web3jFactory;
import org.web3j.protocol.http.HttpService;
import org.web3j.tuples.generated.Tuple4;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Анюта on 03.12.2017.
 */


public class EthGetDocumentsTask extends AsyncTask<Void, Void, List<Doc>> {

    private Credentials credentials;
    private String contractAddress;
    String TAG = "TASK-EthGetDocumentsTask";

    public EthGetDocumentsTask(Credentials credentials, String contractAddress) {
        this.credentials = credentials;
        this.contractAddress = contractAddress;
    }

    @Override
    protected List<Doc> doInBackground(Void... voids) {
        Web3j web3 = Web3jFactory.build(new HttpService("http://192.168.1.65:8545"));
        Documents docs = Documents.load(contractAddress,
                web3,
                credentials,
                new BigInteger("100000000000"),
                new BigInteger("4712388"));


        BigInteger myDocsNumber = null;
        try {
            myDocsNumber = docs.getThisAddresDocNumber().send();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
        long docNumber = myDocsNumber.longValue();
            List<Doc> result = new ArrayList<>();
            for (long i = 0; i <= docNumber; i++) {
                Tuple4<BigInteger,  //ID
                        String,     //owner address
                        String,     //data
                        String      //fio
                        > docTuple4 = null;
                try {
                    docTuple4 = docs.getThisAddresDocById(new BigInteger(String.valueOf(i))).send();
                } catch (Exception e) {
                    e.printStackTrace();
                    continue;
                }
                Log.d(TAG, "ID: " + docTuple4.getValue1());
                Log.d(TAG, "Owner: " + docTuple4.getValue2());
                Log.d(TAG, "Data: " + docTuple4.getValue3());
                Log.d(TAG, "FIO: " + docTuple4.getValue4());
                Doc doc = null;
                try {
                    doc = Doc.valueOfJson(docTuple4.getValue3());
                } catch (IOException e) {
                    e.printStackTrace();
                    continue; //поврежденный документ, пропускаем
                }
                result.add(doc);
            }
            return result;

    }

    @Override
    protected void onPostExecute(List<Doc> result) {
        Log.d(TAG, result == null ? "Can't get doc list" : "Doc number: " + result.size());

    }
}

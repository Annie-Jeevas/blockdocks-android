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

        try {
            BigInteger lastDoc = docs.getDocumentsNumber().send();
            List<Doc> result = new ArrayList<>();
            for (BigInteger i = new BigInteger("0"); i.compareTo(lastDoc) < 0; i.add(BigInteger.ONE)) {
                Tuple4<BigInteger,  //ID
                        String,     //owner address
                        String,     //data
                        String      //fio
                        > docTuple4 = docs.getDocById(i).send();
                Doc doc = new Doc(docTuple4.getValue1().intValue(),
                        0,
                        "gradDate",
                        docTuple4.getValue2(),
                        docTuple4.getValue3(),
                        docTuple4.getValue4());
                //TODO сделать расшифровку JSON
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(List<Doc> result) {
        Log.d(TAG, result==null?"Can't get doc list":"Doc number: " + result.size());

    }
}

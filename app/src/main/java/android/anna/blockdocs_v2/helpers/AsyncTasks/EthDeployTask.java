package android.anna.blockdocs_v2.helpers.AsyncTasks;

import android.anna.blockdocs_v2.Documents;
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


public class EthDeployTask extends AsyncTask<Void, Void, String> {
    Credentials credentials;
    String TAG = "TASK-EthDeployTask";

    public EthDeployTask(Credentials credentials) {
        Log.d(TAG, "In EthTask constructor");
        this.credentials = credentials;
    }

    @Override
    protected String doInBackground(Void... params) {
        Log.d(TAG, "In doInBackground");
        Web3j web3j = Web3jFactory.build(new HttpService("http://192.168.1.65:8545"));
        try {
            Documents docContract = Documents.deploy(
                    web3j,
                    credentials,
                    new BigInteger("100000000000"),
                    new BigInteger("4712388")
            ).send();
            Log.d("Contract Address: ", docContract.getContractAddress());
            return docContract.getContractAddress();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}


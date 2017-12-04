package android.anna.blockdocs_v2.helpers;

import android.anna.blockdocs_v2.BlockdocsApplication;
import android.anna.blockdocs_v2.helpers.AsyncTasks.EthAddDocumentTask;
import android.anna.blockdocs_v2.helpers.AsyncTasks.EthDeployTask;
import android.anna.blockdocs_v2.helpers.AsyncTasks.EthGetDocumentsTask;
import android.anna.blockdocs_v2.model.Doc;
import android.util.Log;

import org.web3j.crypto.Credentials;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * Created by anya on 19.11.2017.
 */

public class EthHelper {
    static String TAG = "EthHelper";

    public static String deployDocuments(Credentials credentials) {
        Log.d(TAG, "In deployDocuments()");
        try {
            return new EthDeployTask(credentials).execute().get(10000, TimeUnit.MILLISECONDS);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static List<Doc> getDocuments(Credentials credentials, String contractAddress) {
        Log.d(TAG, "In getDocuments()");
        try {
            return new EthGetDocumentsTask(credentials, contractAddress).execute().get(1,TimeUnit.SECONDS);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            e.printStackTrace();
            return null;
        }

    }

    public static Boolean saveDoc(Doc d, Credentials credentials, String contractAddress) {
        Log.d(TAG, "In saveDoc()");
        new EthAddDocumentTask(credentials, contractAddress, d).execute();
        return true;
    }
}

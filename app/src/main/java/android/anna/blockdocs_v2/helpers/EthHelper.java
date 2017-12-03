package android.anna.blockdocs_v2.helpers;

import android.anna.blockdocs_v2.Documents;
import android.anna.blockdocs_v2.model.Doc;
import android.os.AsyncTask;
import android.util.Log;

import org.web3j.crypto.CipherException;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.Web3jFactory;
import org.web3j.protocol.http.HttpService;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.security.InvalidAlgorithmParameterException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * Created by anya on 19.11.2017.
 */

public class EthHelper {
    static String TAG = "EthHelper";
    static List<Doc> staticDocs = new ArrayList<>();

    static {
        staticDocs.add(new Doc(1, 1234, "14.12.2017", "Qual", "Spec"));
    }

    public static class EthDeployTask extends AsyncTask<Void, Void, String> {
        Credentials credentials;

        public EthDeployTask(Credentials credentials) {
            Log.d(TAG, "In EthTask constructor");
            this.credentials = credentials;
        }

        @Override
        protected String doInBackground(Void... params) {
            Log.d(TAG, "In doInBackground");
            Web3j web3j = Web3jFactory.build(new HttpService("http://192.168.1.65:8545"));
            try {
                Documents docContract = Documents.deploy(web3j, credentials, new BigInteger("100000000000"), new BigInteger("4712388")).send();
                Log.d("Contract Address: ", docContract.getContractAddress());
                return docContract.getContractAddress();
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
    }

    public static class EthGetDocumentsTask extends AsyncTask<Void, Void, BigInteger> {

        private Credentials credentials;
        private String contractAddress;

        public EthGetDocumentsTask(Credentials credentials, String contractAddress) {
            this.credentials = credentials;
            this.contractAddress = contractAddress;
        }

        @Override
        protected BigInteger doInBackground(Void... voids) {
            Web3j web3 = Web3jFactory.build(new HttpService("http://192.168.1.65:8545"));
            Documents docs = Documents.load(contractAddress,
                    web3,
                    credentials,
                    new BigInteger("100000000000"),
                    new BigInteger("4712388"));

            try {
                BigInteger lastDoc = docs.getDocumentsNumber().send();
                return lastDoc;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(BigInteger result) {
            Log.d(TAG, "Doc number: " + result.toString());

        }
    }

    public static Credentials loadCredentialsFromFile(String password, String filePath) throws CipherException {

        if (filePath.isEmpty()) {
            String privateKey = "0xf7f7e25c1f51d00a9c0708633c87d75ab21ca61cf28f5f5dbdb0c2845dd63475";
            return Credentials.create(privateKey);
        }
        File walletDir = new File(filePath);
        String fileName = "";
        if (!walletDir.exists()) {
            walletDir.mkdir();
            try {
                fileName = WalletUtils.generateNewWalletFile(password, walletDir, false);
            } catch (CipherException | IOException | NoSuchAlgorithmException | InvalidAlgorithmParameterException | NoSuchProviderException e) {
                e.printStackTrace();
            }

        } else {
            File[] files = walletDir.listFiles();
            if (files.length == 0) {
                try {
                    fileName = WalletUtils.generateNewWalletFile(password, walletDir, false);
                } catch (CipherException | IOException | NoSuchAlgorithmException | InvalidAlgorithmParameterException | NoSuchProviderException e) {
                    e.printStackTrace();
                }
            } else fileName = files[0].getName();

        }
        Log.d("loadCredentialsFromFile", "filename: " + fileName);
        org.web3j.crypto.Credentials result = null;
        try {
            result = WalletUtils.loadCredentials(password, filePath + "/" + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.d("loadCredentialsFromFile", "address: " + ((result == null) ? "null" : result.getAddress()));
        return result;
    }

    public static void fillDocList(ArrayList<Doc> docs) {
        docs.addAll(staticDocs);
    }

    public static String deployDocuments(Credentials credentials) {
        Log.d(TAG, "In deployDocuments()");
        try {
            return new EthDeployTask(credentials).execute().get(10000, TimeUnit.MILLISECONDS);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void getDocuments(Credentials credentials) {
        Log.d(TAG, "In deployDocuments()");

        new EthGetDocumentsTask(credentials, deployDocuments(credentials)).execute();
    }

    public static String[] getAccounts() {
        return new String[]{"Anya:pass"};
    }

    public static void saveDoc(Doc d) {
        staticDocs.add(d);
    }
}

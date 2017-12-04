package android.anna.blockdocs_v2;

import android.anna.blockdocs_v2.helpers.AsyncTasks.EthDeployTask;
import android.anna.blockdocs_v2.helpers.EthHelper;
import android.app.Application;

import org.web3j.crypto.Credentials;

import java.util.concurrent.ExecutionException;


/**
 * Created by Анюта on 26.11.2017.
 */

public class BlockdocsApplication extends Application {
    private Credentials credentials;
    private String contractAddress = "";

    public Credentials getCredentials() {
        return credentials;
    }

    public void setCredentials(Credentials credentials) {
        this.credentials = credentials;
    }

    public String getContractAddress() {
        if (contractAddress.isEmpty()){
            contractAddress = EthHelper.deployDocuments(getCredentials());
        }
        return contractAddress;
    }
}

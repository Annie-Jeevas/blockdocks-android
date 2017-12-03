package android.anna.blockdocs_v2;

import android.app.Application;

import org.web3j.crypto.Credentials;


/**
 * Created by Анюта on 26.11.2017.
 */

public class BlockdocsApplication extends Application {
    private Credentials credentials;

    public Credentials getCredentials() {
        return credentials;
    }

    public void setCredentials(Credentials credentials) {
        this.credentials = credentials;
    }
}

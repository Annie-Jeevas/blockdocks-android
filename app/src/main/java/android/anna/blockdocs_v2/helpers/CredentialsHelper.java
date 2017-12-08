package android.anna.blockdocs_v2.helpers;

import android.anna.blockdocs_v2.model.Doc;
import android.util.Log;

import org.web3j.crypto.CipherException;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;

import java.io.File;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Анюта on 03.12.2017.
 */

public class CredentialsHelper {
    static Map<String,String> privateKeys = new HashMap<>();
    static {
        privateKeys.put("anya","56b263491870509f6fb984d0d3530c4b292626a920db079ddfa14cba8487c303");
        privateKeys.put("alice","4d71cf983187f9876d4a1e3436c5623ba2cb801f17460621b76ae58eff630965");
        privateKeys.put("bob","0464ab1e2de80ef96a5f72a58651e0bd7675ccbfb779910fde084672a4ef9ae5");
    }

    public static Credentials loadCredentialsFromFile(String password, String filePath) throws CipherException {

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

    public static Credentials loadExistCredentials(String login){
        String privateKey = privateKeys.get(login);
        if (privateKey == null) return null;
        return Credentials.create(privateKey);
    }
}

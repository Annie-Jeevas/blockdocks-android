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
        privateKeys.put("alice","4589dab39424c2e86d540396cf02b4cd578f65374045f69448cda55db278752d");
        privateKeys.put("bob","44356e21c3626663bca85327896c6f8097fe1bc18a8925a3405aa32387fb6a52");
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

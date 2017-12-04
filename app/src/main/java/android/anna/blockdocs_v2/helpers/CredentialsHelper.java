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
        privateKeys.put("anya","a5b7a8c90562286f2749ca196f8366284c7753141c7d97338371e5e4cf589e6c");
        privateKeys.put("alice","898de04d60a089589c5d56748ac8b8683f2aa0383cd6e82470afb0a6bb046e3c");
        privateKeys.put("bob","1850a3a141b5011dd7381fae989d881d031bbace49dc9fe0be79a43e2d0ddc9e");
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

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
        privateKeys.put("anya","266d9959abc858de1ede331156082a2e897201a170a7c7f99f72599407aca283");
        privateKeys.put("alice","9f91c4d3dabfa7298bfee5e6d8fa7a1502f363f938465b570d1a32ceb1d692c0");
        privateKeys.put("bob","c4fb9b2e6ac80bfba2e2639e86adc12e09d1bb5495bf4764c12264ef374a614a");
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

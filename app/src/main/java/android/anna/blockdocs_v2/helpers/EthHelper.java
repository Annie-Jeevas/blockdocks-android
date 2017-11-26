package android.anna.blockdocs_v2.helpers;

import android.anna.blockdocs.Documents;
import android.anna.blockdocs_v2.model.Doc;
import android.util.Log;

import org.web3j.crypto.CipherException;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.security.InvalidAlgorithmParameterException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.ArrayList;

/**
 * Created by anya on 19.11.2017.
 */

public class EthHelper {
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

    public static void fillDocList(ArrayList<Doc> docs) {
        docs.add(new Doc(1, 1716480277, "2016-12-12", "Bachelor", "09.02.03"));
        docs.add(new Doc(2, 1720456993, "2020-12-14", "Master", "09.02.03"));
    }

    public static void deployDocuments(Web3j web3j, Credentials credentials) {
        try {
            Documents docContract = Documents.deploy(web3j, credentials, BigInteger.ONE, BigInteger.TEN).send();
            Log.d("Contract Address: ",docContract.getContractAddress());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

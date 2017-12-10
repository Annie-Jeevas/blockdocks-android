package android.anna.blockdocs_v2;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tuples.generated.Tuple3;
import org.web3j.tuples.generated.Tuple4;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 3.1.1.
 */
public final class Documents extends Contract {
    private static final String BINARY = "6060604052341561000f57600080fd5b610dbd8061001e6000396000f300606060405260043610610078576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff168063239cce0b1461007d57806323d73275146100a6578063452513ad146100cf5780634d2b197814610211578063c2ed2b05146102b1578063eefa810714610426575b600080fd5b341561008857600080fd5b610090610568565b6040518082815260200191505060405180910390f35b34156100b157600080fd5b6100b9610574565b6040518082815260200191505060405180910390f35b34156100da57600080fd5b6100f06004808035906020019091905050610620565b604051808581526020018473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020018060200180602001838103835285818151815260200191508051906020019080838360005b8381101561016c578082015181840152602081019050610151565b50505050905090810190601f1680156101995780820380516001836020036101000a031916815260200191505b50838103825284818151815260200191508051906020019080838360005b838110156101d25780820151818401526020810190506101b7565b50505050905090810190601f1680156101ff5780820380516001836020036101000a031916815260200191505b50965050505050505060405180910390f35b341561021c57600080fd5b6102af600480803590602001908201803590602001908080601f0160208091040260200160405190810160405280939291908181526020018383808284378201915050505050509190803590602001908201803590602001908080601f016020809104026020016040519081016040528093929190818152602001838380828437820191505050505050919050506107fc565b005b34156102bc57600080fd5b6102d260048080359060200190919050506108df565b604051808473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200180602001806020018381038352858181546001816001161561010002031660029004815260200191508054600181600116156101000203166002900480156103915780601f1061036657610100808354040283529160200191610391565b820191906000526020600020905b81548152906001019060200180831161037457829003601f168201915b50508381038252848181546001816001161561010002031660029004815260200191508054600181600116156101000203166002900480156104145780601f106103e957610100808354040283529160200191610414565b820191906000526020600020905b8154815290600101906020018083116103f757829003601f168201915b50509550505050505060405180910390f35b341561043157600080fd5b6104476004808035906020019091905050610936565b604051808581526020018473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020018060200180602001838103835285818151815260200191508051906020019080838360005b838110156104c35780820151818401526020810190506104a8565b50505050905090810190601f1680156104f05780820380516001836020036101000a031916815260200191505b50838103825284818151815260200191508051906020019080838360005b8381101561052957808201518184015260208101905061050e565b50505050905090810190601f1680156105565780820380516001836020036101000a031916815260200191505b50965050505050505060405180910390f35b60008080549050905090565b6000806000809150600090505b600080549050811015610618573373ffffffffffffffffffffffffffffffffffffffff166000828154811015156105b457fe5b906000526020600020906003020160000160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16141561060b5781806001019250505b8080600101915050610581565b819250505090565b60008061062b610bf8565b610633610bf8565b8460008681548110151561064357fe5b906000526020600020906003020160000160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1660008781548110151561068457fe5b90600052602060002090600302016001016000888154811015156106a457fe5b9060005260206000209060030201600201818054600181600116156101000203166002900480601f01602080910402602001604051908101604052809291908181526020018280546001816001161561010002031660029004801561074a5780601f1061071f5761010080835404028352916020019161074a565b820191906000526020600020905b81548152906001019060200180831161072d57829003601f168201915b50505050509150808054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156107e65780601f106107bb576101008083540402835291602001916107e6565b820191906000526020600020905b8154815290600101906020018083116107c957829003601f168201915b5050505050905093509350935093509193509193565b600080548060010182816108109190610c0c565b916000526020600020906003020160006060604051908101604052803373ffffffffffffffffffffffffffffffffffffffff16815260200186815260200185815250909190915060008201518160000160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555060208201518160010190805190602001906108ba929190610c3e565b5060408201518160020190805190602001906108d7929190610c3e565b505050505050565b6000818154811015156108ee57fe5b90600052602060002090600302016000915090508060000160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1690806001019080600201905083565b600080610941610bf8565b610949610bf8565b60008060009150600090505b600080549050811015610bbb573373ffffffffffffffffffffffffffffffffffffffff1660008281548110151561098857fe5b906000526020600020906003020160000160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff161415610bae5786821415610ba557806000828154811015156109ee57fe5b906000526020600020906003020160000160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff16600083815481101515610a2f57fe5b9060005260206000209060030201600101600084815481101515610a4f57fe5b9060005260206000209060030201600201818054600181600116156101000203166002900480601f016020809104026020016040519081016040528092919081815260200182805460018160011615610100020316600290048015610af55780601f10610aca57610100808354040283529160200191610af5565b820191906000526020600020905b815481529060010190602001808311610ad857829003601f168201915b50505050509150808054600181600116156101000203166002900480601f016020809104026020016040519081016040528092919081815260200182805460018160011615610100020316600290048015610b915780601f10610b6657610100808354040283529160200191610b91565b820191906000526020600020905b815481529060010190602001808311610b7457829003601f168201915b505050505090509550955095509550610bef565b81806001019250505b8080600101915050610955565b6000808191508090506020604051908101604052806000815250602060405190810160405280600081525095509550955095505b50509193509193565b602060405190810160405280600081525090565b815481835581811511610c3957600302816003028360005260206000209182019101610c389190610cbe565b5b505050565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f10610c7f57805160ff1916838001178555610cad565b82800160010185558215610cad579182015b82811115610cac578251825591602001919060010190610c91565b5b509050610cba9190610d24565b5090565b610d2191905b80821115610d1d57600080820160006101000a81549073ffffffffffffffffffffffffffffffffffffffff0219169055600182016000610d049190610d49565b600282016000610d149190610d49565b50600301610cc4565b5090565b90565b610d4691905b80821115610d42576000816000905550600101610d2a565b5090565b90565b50805460018160011615610100020316600290046000825580601f10610d6f5750610d8e565b601f016020900490600052602060002090810190610d8d9190610d24565b5b505600a165627a7a72305820af68e9d508de7a74b688ecc0a87454ecf22dfa17de5ae9f68cba7d1085a543d30029";

    private Documents(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    private Documents(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public RemoteCall<BigInteger> getDocumentsNumber() {
        Function function = new Function("getDocumentsNumber", 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<BigInteger> getThisAddresDocNumber() {
        Function function = new Function("getThisAddresDocNumber", 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<Tuple4<BigInteger, String, String, String>> getDocById(BigInteger id) {
        final Function function = new Function("getDocById", 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(id)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Address>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}));
        return new RemoteCall<Tuple4<BigInteger, String, String, String>>(
                new Callable<Tuple4<BigInteger, String, String, String>>() {
                    @Override
                    public Tuple4<BigInteger, String, String, String> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);;
                        return new Tuple4<BigInteger, String, String, String>(
                                (BigInteger) results.get(0).getValue(), 
                                (String) results.get(1).getValue(), 
                                (String) results.get(2).getValue(), 
                                (String) results.get(3).getValue());
                    }
                });
    }

    public RemoteCall<TransactionReceipt> addDocument(String data, String fio) {
        Function function = new Function(
                "addDocument", 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(data), 
                new org.web3j.abi.datatypes.Utf8String(fio)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<Tuple3<String, String, String>> documents(BigInteger param0) {
        final Function function = new Function("documents", 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}));
        return new RemoteCall<Tuple3<String, String, String>>(
                new Callable<Tuple3<String, String, String>>() {
                    @Override
                    public Tuple3<String, String, String> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);;
                        return new Tuple3<String, String, String>(
                                (String) results.get(0).getValue(), 
                                (String) results.get(1).getValue(), 
                                (String) results.get(2).getValue());
                    }
                });
    }

    public RemoteCall<Tuple4<BigInteger, String, String, String>> getThisAddresDocById(BigInteger id) {
        final Function function = new Function("getThisAddresDocById", 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(id)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Address>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}));
        return new RemoteCall<Tuple4<BigInteger, String, String, String>>(
                new Callable<Tuple4<BigInteger, String, String, String>>() {
                    @Override
                    public Tuple4<BigInteger, String, String, String> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);;
                        return new Tuple4<BigInteger, String, String, String>(
                                (BigInteger) results.get(0).getValue(), 
                                (String) results.get(1).getValue(), 
                                (String) results.get(2).getValue(), 
                                (String) results.get(3).getValue());
                    }
                });
    }

    public static RemoteCall<Documents> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(Documents.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    public static RemoteCall<Documents> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(Documents.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }

    public static Documents load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new Documents(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    public static Documents load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new Documents(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }
}

package com.dechain.utils;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 4.3.0.
 */
public class GoodsSol extends Contract {
    private static final String BINARY = "608060405260006003556000600455600060055534801561001f57600080fd5b5060405161153a38038061153a83398101604081905261003e91610088565b600080546001600160a01b039586166001600160a01b03199182161790915560028054821633179055600180549490951693169290921790925560048290556003556005556100e7565b6000806000806080858703121561009d578384fd5b84516100a8816100cf565b60208601519094506100b9816100cf565b6040860151606090960151949790965092505050565b6001600160a01b03811681146100e457600080fd5b50565b611444806100f66000396000f3fe60806040526004361061009c5760003560e01c80639a45c48d116100645780639a45c48d1461015a578063a035b1fe1461017a578063aa528e761461018f578063aa8c217c146101a2578063bc7f253d146101b7578063e7b0272e146101ec5761009c565b806315eb3f30146100a157806325e7514b146100dc578063893d20e8146100fe5780638da5cb5b146101205780639551ae4414610135575b600080fd5b3480156100ad57600080fd5b506100c16100bc366004610fc1565b61020c565b6040516100d39695949392919061119a565b60405180910390f35b3480156100e857600080fd5b506100f161038e565b6040516100d39190611321565b34801561010a57600080fd5b50610113610394565b6040516100d391906110a8565b34801561012c57600080fd5b506101136103a3565b34801561014157600080fd5b5061014a6103b2565b6040516100d39493929190611169565b61016d61016836600461103f565b6103d0565b6040516100d3919061118f565b34801561018657600080fd5b506100f1610472565b61016d61019d366004610ffc565b610478565b3480156101ae57600080fd5b506100f16108f0565b3480156101c357600080fd5b506101d76101d2366004610fc1565b6108f6565b6040516100d3999897969594939291906110e0565b3480156101f857600080fd5b5061016d610207366004610f18565b610a6a565b6060806000806000806000600688604051610227919061108c565b908152602001604051809103902090508060010181600201826003015483600401548460050160009054906101000a900460ff16856006015485805461026c906113a5565b80601f0160208091040260200160405190810160405280929190818152602001828054610298906113a5565b80156102e55780601f106102ba576101008083540402835291602001916102e5565b820191906000526020600020905b8154815290600101906020018083116102c857829003601f168201915b505050505095508480546102f8906113a5565b80601f0160208091040260200160405190810160405280929190818152602001828054610324906113a5565b80156103715780601f1061034657610100808354040283529160200191610371565b820191906000526020600020905b81548152906001019060200180831161035457829003601f168201915b505050505094509650965096509650965096505091939550919395565b60055481565b6002546001600160a01b031690565b6002546001600160a01b031681565b6002546003546005546004546001600160a01b039093169290919293565b600080546001600160a01b03163314806103f457506002546001600160a01b031633145b6104195760405162461bcd60e51b815260040161041090611288565b60405180910390fd5b600083116104395760405162461bcd60e51b815260040161041090611207565b600082116104595760405162461bcd60e51b8152600401610410906112ad565b5060048290556005829055600381905560015b92915050565b60035481565b600080546001600160a01b031633146104a35760405162461bcd60e51b815260040161041090611288565b6006836040516104b3919061108c565b9081526040519081900360200190206008015460ff166104e55760405162461bcd60e51b8152600401610410906112f8565b60026006846040516104f7919061108c565b908152602001604051809103902060070154106105265760405162461bcd60e51b815260040161041090611251565b81600684604051610537919061108c565b908152602001604051809103902060070181905550600060068460405161055e919061108c565b908152604051908190036020019020546001600160a01b031690506002831480156105ab5750600684604051610594919061108c565b9081526040519081900360200190206005015460ff165b80156105d8575060006006856040516105c4919061108c565b908152602001604051809103902060060154115b15610670576001546002546040516001600160a01b039283169263a9059cbb92169060069061060890899061108c565b908152604051908190036020018120600601546001600160e01b031960e085901b1682526106399291600401611150565b600060405180830381600087803b15801561065357600080fd5b505af1158015610667573d6000803e3d6000fd5b505050506108e6565b8260031480156106a2575060068460405161068b919061108c565b9081526040519081900360200190206005015460ff165b80156106cf575060006006856040516106bb919061108c565b908152602001604051809103902060060154115b1561077957600160009054906101000a90046001600160a01b03166001600160a01b031663a9059cbb826001600160a01b031663893d20e86040518163ffffffff1660e01b815260040160206040518083038186803b15801561073157600080fd5b505afa158015610745573d6000803e3d6000fd5b505050506040513d601f19601f820116820180604052508101906107699190610efc565b600687604051610608919061108c565b8260041480156107ab5750600684604051610794919061108c565b9081526040519081900360200190206005015460ff165b80156107d8575060006006856040516107c4919061108c565b908152602001604051809103902060060154115b156108e657600160009054906101000a90046001600160a01b03166001600160a01b031663a9059cbb826001600160a01b031663893d20e86040518163ffffffff1660e01b815260040160206040518083038186803b15801561083a57600080fd5b505afa15801561084e573d6000803e3d6000fd5b505050506040513d601f19601f820116820180604052508101906108729190610efc565b600687604051610882919061108c565b908152604051908190036020018120600601546001600160e01b031960e085901b1682526108b39291600401611150565b600060405180830381600087803b1580156108cd57600080fd5b505af11580156108e1573d6000803e3d6000fd5b505050505b5060019392505050565b60045481565b8051602081830181018051600682529282019190930120915280546001820180546001600160a01b03909216929161092d906113a5565b80601f0160208091040260200160405190810160405280929190818152602001828054610959906113a5565b80156109a65780601f1061097b576101008083540402835291602001916109a6565b820191906000526020600020905b81548152906001019060200180831161098957829003601f168201915b5050505050908060020180546109bb906113a5565b80601f01602080910402602001604051908101604052809291908181526020018280546109e7906113a5565b8015610a345780601f10610a0957610100808354040283529160200191610a34565b820191906000526020600020905b815481529060010190602001808311610a1757829003601f168201915b505050600384015460048501546005860154600687015460078801546008909801549697939692955060ff918216945092911689565b6002546000906001600160a01b03163314610a975760405162461bcd60e51b815260040161041090611288565b600687604051610aa7919061108c565b9081526040519081900360200190206008015460ff1615610ada5760405162461bcd60e51b81526004016104109061122c565b60008511610afa5760405162461bcd60e51b8152600401610410906112d3565b84600688604051610b0b919061108c565b90815260200160405180910390206003018190555086600688604051610b31919061108c565b90815260200160405180910390206001019080519060200190610b55929190610de2565b5082600688604051610b67919061108c565b908152604051908190036020018120600501805492151560ff19909316929092179091558890600690610b9b908a9061108c565b90815260405190819003602001812080546001600160a01b03939093166001600160a01b0319909316929092179091558690600690610bdb908a9061108c565b90815260200160405180910390206002019080519060200190610bff929190610de2565b5083600688604051610c11919061108c565b90815260200160405180910390206004018190555081600688604051610c37919061108c565b9081526020016040518091039020600601819055506000600688604051610c5e919061108c565b908152604051908190036020019020600701558215610cde576001546040516323b872dd60e01b81526001600160a01b03909116906323b872dd90610cab903390309087906004016110bc565b600060405180830381600087803b158015610cc557600080fd5b505af1158015610cd9573d6000803e3d6000fd5b505050505b6001600688604051610cf0919061108c565b908152604051908190036020019020600801805491151560ff199092169190911790556000546003546001600160a01b0390911690819063cbcfd2a9908a90610d3a908a90610d98565b6040518363ffffffff1660e01b8152600401610d579291906111e5565b600060405180830381600087803b158015610d7157600080fd5b505af1158015610d85573d6000803e3d6000fd5b5060019c9b505050505050505050505050565b600082610da75750600061046c565b6000610db3838561134a565b905082610dc0858361132a565b14610ddb57634e487b7160e01b600052600160045260246000fd5b9392505050565b828054610dee906113a5565b90600052602060002090601f016020900481019282610e105760008555610e56565b82601f10610e2957805160ff1916838001178555610e56565b82800160010185558215610e56579182015b82811115610e56578251825591602001919060010190610e3b565b50610e62929150610e66565b5090565b5b80821115610e625760008155600101610e67565b600082601f830112610e8b578081fd5b813567ffffffffffffffff80821115610ea657610ea66113e0565b604051601f8301601f191681016020018281118282101715610eca57610eca6113e0565b604052828152848301602001861015610ee1578384fd5b82602086016020830137918201602001929092529392505050565b600060208284031215610f0d578081fd5b8151610ddb816113f6565b600080600080600080600060e0888a031215610f32578283fd5b8735610f3d816113f6565b9650602088013567ffffffffffffffff80821115610f59578485fd5b610f658b838c01610e7b565b975060408a0135915080821115610f7a578485fd5b50610f878a828b01610e7b565b955050606088013593506080880135925060a08801358015158114610faa578283fd5b8092505060c0880135905092959891949750929550565b600060208284031215610fd2578081fd5b813567ffffffffffffffff811115610fe8578182fd5b610ff484828501610e7b565b949350505050565b6000806040838503121561100e578182fd5b823567ffffffffffffffff811115611024578283fd5b61103085828601610e7b565b95602094909401359450505050565b60008060408385031215611051578182fd5b50508035926020909101359150565b60008151808452611078816020860160208601611375565b601f01601f19169290920160200192915050565b6000825161109e818460208701611375565b9190910192915050565b6001600160a01b0391909116815260200190565b6001600160a01b039384168152919092166020820152604081019190915260600190565b6001600160a01b038a168152610120602082018190526000906111058382018c611060565b90508281036040840152611119818b611060565b60608401999099525050608081019590955292151560a085015260c084019190915260e08301521515610100909101529392505050565b6001600160a01b03929092168252602082015260400190565b6001600160a01b0394909416845260208401929092526040830152606082015260800190565b901515815260200190565b600060c082526111ad60c0830189611060565b82810360208401526111bf8189611060565b604084019790975250506060810193909352901515608083015260a09091015292915050565b6000604082526111f86040830185611060565b90508260208301529392505050565b6020808252600b908201526a2fb73ab6b99032b93937b960a91b604082015260600190565b6020808252600b908201526a1bdc99195c88195e1a5cdd60aa1b604082015260600190565b60208082526017908201527f6f72646572207374617475732063616e206e6f74206f70000000000000000000604082015260600190565b6020808252600b908201526a0796f752063616e74206f760ac1b604082015260600190565b6020808252600c908201526b2fb83934b1b29032b93937b960a11b604082015260600190565b6020808252600b908201526a36bca73ab69032b93937b960a91b604082015260600190565b6020808252600f908201526e1bdc99195c881b9bdd08195e1a5cdd608a1b604082015260600190565b90815260200190565b60008261134557634e487b7160e01b81526012600452602481fd5b500490565b600081600019048311821515161561137057634e487b7160e01b81526011600452602481fd5b500290565b60005b83811015611390578181015183820152602001611378565b8381111561139f576000848401525b50505050565b6002810460018216806113b957607f821691505b602082108114156113da57634e487b7160e01b600052602260045260246000fd5b50919050565b634e487b7160e01b600052604160045260246000fd5b6001600160a01b038116811461140b57600080fd5b5056fea2646970667358221220317e3df8dac55588775a96c0fafcef90864d455fecbe9f8d03b54956e9aa943e64736f6c63430008000033";

    public static final String FUNC_AMOUNT = "amount";

    public static final String FUNC_CHANGENUMANDPRICE = "changeNumAndPrice";

    public static final String FUNC_CREATEORDER = "createOrder";

    public static final String FUNC_DOORDERSTATUS = "doOrderStatus";

    public static final String FUNC_GETBASEINFO = "getBaseInfo";

    public static final String FUNC_GETORDERINFO = "getOrderInfo";

    public static final String FUNC_GETOWNER = "getOwner";

    public static final String FUNC_ORDERMAP = "orderMap";

    public static final String FUNC_OWNER = "owner";

    public static final String FUNC_PRICE = "price";

    public static final String FUNC_REMAINAMOUNT = "remainAmount";

    @Deprecated
    protected GoodsSol(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected GoodsSol(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected GoodsSol(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected GoodsSol(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public RemoteCall<TransactionReceipt> amount() {
        final Function function = new Function(
                FUNC_AMOUNT,
                Arrays.<Type>asList(),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> changeNumAndPrice(BigInteger _nums, BigInteger _price) {
        final Function function = new Function(
                FUNC_CHANGENUMANDPRICE,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_nums),
                new org.web3j.abi.datatypes.generated.Uint256(_price)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> createOrder(String _otherGoods, String _myOrderId, String _otherOrderId, BigInteger _myNum, BigInteger _otherNum, Boolean _myPay, BigInteger _payAmount) {
        final Function function = new Function(
                FUNC_CREATEORDER,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(_otherGoods),
                new org.web3j.abi.datatypes.Utf8String(_myOrderId),
                new org.web3j.abi.datatypes.Utf8String(_otherOrderId),
                new org.web3j.abi.datatypes.generated.Uint256(_myNum),
                new org.web3j.abi.datatypes.generated.Uint256(_otherNum),
                new org.web3j.abi.datatypes.Bool(_myPay),
                new org.web3j.abi.datatypes.generated.Uint256(_payAmount)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> doOrderStatus(String _myOrderId, BigInteger _orderStatus) {
        final Function function = new Function(
                FUNC_DOORDERSTATUS,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_myOrderId),
                new org.web3j.abi.datatypes.generated.Uint256(_orderStatus)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> getBaseInfo() {
        final Function function = new Function(
                FUNC_GETBASEINFO,
                Arrays.<Type>asList(),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> getOrderInfo(String _myOrderId) {
        final Function function = new Function(
                FUNC_GETORDERINFO,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_myOrderId)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> getOwner() {
        final Function function = new Function(
                FUNC_GETOWNER,
                Arrays.<Type>asList(),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> orderMap(String param0) {
        final Function function = new Function(
                FUNC_ORDERMAP,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(param0)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> owner() {
        final Function function = new Function(
                FUNC_OWNER,
                Arrays.<Type>asList(),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> price() {
        final Function function = new Function(
                FUNC_PRICE,
                Arrays.<Type>asList(),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> remainAmount() {
        final Function function = new Function(
                FUNC_REMAINAMOUNT,
                Arrays.<Type>asList(),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    @Deprecated
    public static GoodsSol load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new GoodsSol(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static GoodsSol load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new GoodsSol(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static GoodsSol load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new GoodsSol(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static GoodsSol load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new GoodsSol(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<GoodsSol> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider, String _master, String _token, BigInteger _amount, BigInteger _price) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(_master),
                new org.web3j.abi.datatypes.Address(_token),
                new org.web3j.abi.datatypes.generated.Uint256(_amount),
                new org.web3j.abi.datatypes.generated.Uint256(_price)));
        return deployRemoteCall(GoodsSol.class, web3j, credentials, contractGasProvider, BINARY, encodedConstructor);
    }

    public static RemoteCall<GoodsSol> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider, String _master, String _token, BigInteger _amount, BigInteger _price) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(_master),
                new org.web3j.abi.datatypes.Address(_token),
                new org.web3j.abi.datatypes.generated.Uint256(_amount),
                new org.web3j.abi.datatypes.generated.Uint256(_price)));
        return deployRemoteCall(GoodsSol.class, web3j, transactionManager, contractGasProvider, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<GoodsSol> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, String _master, String _token, BigInteger _amount, BigInteger _price) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(_master),
                new org.web3j.abi.datatypes.Address(_token),
                new org.web3j.abi.datatypes.generated.Uint256(_amount),
                new org.web3j.abi.datatypes.generated.Uint256(_price)));
        return deployRemoteCall(GoodsSol.class, web3j, credentials, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<GoodsSol> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, String _master, String _token, BigInteger _amount, BigInteger _price) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(_master),
                new org.web3j.abi.datatypes.Address(_token),
                new org.web3j.abi.datatypes.generated.Uint256(_amount),
                new org.web3j.abi.datatypes.generated.Uint256(_price)));
        return deployRemoteCall(GoodsSol.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, encodedConstructor);
    }
}

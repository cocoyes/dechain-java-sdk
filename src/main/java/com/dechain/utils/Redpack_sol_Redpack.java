package com.dechain.utils;

import io.reactivex.Flowable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.DynamicArray;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tuples.generated.Tuple3;
import org.web3j.tuples.generated.Tuple4;
import org.web3j.tuples.generated.Tuple5;
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
 * <p>Generated with web3j version 4.0.1.
 */
public class Redpack_sol_Redpack extends Contract {
    private static final String BINARY = "6080604052670de0b6b3a764000060015569021e19e0c9bab240000060025560016003556127106004556000600555600060065534801561003f57600080fd5b50604051602080611f738339810180604052602081101561005f57600080fd5b5051600080546001600160a01b03199081163317909155600880546001600160a01b0390931692909116919091179055611ed58061009e6000396000f3fe6080604052600436106100fe5760003560e01c8063ae361e4811610095578063e9e88dc311610064578063e9e88dc3146104b9578063f71d96cb1461056a578063fd1e5b1614610594578063fd6572dd14610664578063fd9ba834146107c3576100fe565b8063ae361e4814610368578063d1df81df14610418578063e06858561461048f578063e864fb7c146104a4576100fe565b80636f4bda17116100d15780636f4bda17146102165780638645bdb71461022b5780638da5cb5b14610240578063abb1dc4414610255576100fe565b80633e5a104b146101035780634e40aa881461012a5780635024136a1461013f5780635eab74fc146101e5575b600080fd5b34801561010f57600080fd5b506101186107d8565b60408051918252519081900360200190f35b34801561013657600080fd5b506101186107de565b6101e36004803603602081101561015557600080fd5b810190602081018135600160201b81111561016f57600080fd5b82018360208201111561018157600080fd5b803590602001918460018302840111600160201b831117156101a257600080fd5b91908080601f0160208091040260200160405190810160405280939291908181526020018383808284376000920191909152509295506107e4945050505050565b005b3480156101f157600080fd5b506101fa610b87565b604080516001600160a01b039092168252519081900360200190f35b34801561022257600080fd5b50610118610b96565b34801561023757600080fd5b50610118610b9c565b34801561024c57600080fd5b506101fa610baa565b34801561026157600080fd5b5061026a610bb9565b60405180858152602001846001600160a01b03166001600160a01b031681526020018060200180602001838103835285818151815260200191508051906020019080838360005b838110156102c95781810151838201526020016102b1565b50505050905090810190601f1680156102f65780820380516001836020036101000a031916815260200191505b50838103825284518152845160209182019186019080838360005b83811015610329578181015183820152602001610311565b50505050905090810190601f1680156103565780820380516001836020036101000a031916815260200191505b50965050505050505060405180910390f35b6101e36004803603606081101561037e57600080fd5b813591602081013591810190606081016040820135600160201b8111156103a457600080fd5b8201836020820111156103b657600080fd5b803590602001918460018302840111600160201b831117156103d757600080fd5b91908080601f016020809104026020016040519081016040528093929190818152602001838380828437600092019190915250929550610deb945050505050565b34801561042457600080fd5b5061042d611426565b6040518084815260200183815260200180602001828103825283818151815260200191508051906020019060200280838360005b83811015610479578181015183820152602001610461565b5050505090500194505050505060405180910390f35b34801561049b57600080fd5b5061011861149a565b3480156104b057600080fd5b506101186114a0565b3480156104c557600080fd5b506101e3600480360360208110156104dc57600080fd5b810190602081018135600160201b8111156104f657600080fd5b82018360208201111561050857600080fd5b803590602001918460018302840111600160201b8311171561052957600080fd5b91908080601f0160208091040260200160405190810160405280939291908181526020018383808284376000920191909152509295506114a6945050505050565b34801561057657600080fd5b506101fa6004803603602081101561058d57600080fd5b50356118c9565b3480156105a057600080fd5b50610650600480360360408110156105b757600080fd5b810190602081018135600160201b8111156105d157600080fd5b8201836020820111156105e357600080fd5b803590602001918460018302840111600160201b8311171561060457600080fd5b91908080601f016020809104026020016040519081016040528093929190818152602001838380828437600092019190915250929550505090356001600160a01b031691506118f09050565b604080519115158252519081900360200190f35b34801561067057600080fd5b506107156004803603602081101561068757600080fd5b810190602081018135600160201b8111156106a157600080fd5b8201836020820111156106b357600080fd5b803590602001918460018302840111600160201b831117156106d457600080fd5b91908080601f016020809104026020016040519081016040528093929190818152602001838380828437600092019190915250929550611a13945050505050565b604051808681526020018581526020018481526020018060200180602001838103835285818151815260200191508051906020019060200280838360005b8381101561076b578181015183820152602001610753565b50505050905001838103825284818151815260200191508051906020019060200280838360005b838110156107aa578181015183820152602001610792565b5050505090500197505050505050505060405180910390f35b3480156107cf57600080fd5b50610118611bcb565b60025481565b60015481565b60006009826040518082805190602001908083835b602083106108185780518252601f1990920191602091820191016107f9565b6001836020036101000a038019825116818451168082178552505050505050905001915050908152602001604051809103902060030154116108a45760408051600160e51b62461bcd02815260206004820152601060248201527f7265647061636b20697320656d70747900000000000000000000000000000000604482015290519081900360640190fd5b6009816040518082805190602001908083835b602083106108d65780518252601f1990920191602091820191016108b7565b51815160209384036101000a60001901801990921691161790529201948552506040519384900381018420600601548551909460099450869350918291908401908083835b6020831061093a5780518252601f19909201916020918201910161091b565b6001836020036101000a038019825116818451168082178552505050505050905001915050908152602001604051809103902060040154116109c65760408051600160e51b62461bcd02815260206004820152601960248201527f657863656564206e756d626572206f66207265647061636b7300000000000000604482015290519081900360640190fd5b6109d081336118f0565b15610a255760408051600160e51b62461bcd02815260206004820152600f60248201527f616c726561647920677261626265640000000000000000000000000000000000604482015290519081900360640190fd5b60006009826040518082805190602001908083835b60208310610a595780518252601f199092019160209182019101610a3a565b51815160209384036101000a6000190180199092169116179052920194855250604051938490038101842060060154865190955060009460099450879350918291908401908083835b60208310610ac15780518252601f199092019160209182019101610aa2565b51815160209384036101000a6000190180199092169116179052920194855250604051938490030190922060050180549092508491508110610aff57fe5b90600052602060002001549050610b168382611bd1565b806009846040518082805190602001908083835b60208310610b495780518252601f199092019160209182019101610b2a565b51815160209384036101000a600019018019909216911617905292019485525060405193849003019092206003018054939093039092555050505050565b6008546001600160a01b031681565b60065481565b69152d02c7e14af680000081565b6000546001600160a01b031681565b600080606080600860009054906101000a90046001600160a01b03166001600160a01b031663313ce5676040518163ffffffff1660e01b815260040160206040518083038186803b158015610c0d57600080fd5b505afa158015610c21573d6000803e3d6000fd5b505050506040513d6020811015610c3757600080fd5b505160085460408051600160e01b6306fdde0302815290516001600160a01b039092169182916306fdde03916004808301926000929190829003018186803b158015610c8257600080fd5b505afa158015610c96573d6000803e3d6000fd5b505050506040513d6000823e601f3d908101601f191682016040526020811015610cbf57600080fd5b810190808051600160201b811115610cd657600080fd5b82016020810184811115610ce957600080fd5b8151600160201b811182820187101715610d0257600080fd5b505060085460408051600160e01b6395d89b4102815290519295506001600160a01b0390911693506395d89b419250600480820192600092909190829003018186803b158015610d5157600080fd5b505afa158015610d65573d6000803e3d6000fd5b505050506040513d6000823e601f3d908101601f191682016040526020811015610d8e57600080fd5b810190808051600160201b811115610da557600080fd5b82016020810184811115610db857600080fd5b8151600160201b811182820187101715610dd157600080fd5b505060ff969096169a949950929750939550919350505050565b6009816040518082805190602001908083835b60208310610e1d5780518252601f199092019160209182019101610dfe565b51815160209384036101000a600019018019909216911617905292019485525060405193849003019092206008015460ff16159150610e5d905057600080fd5b816009826040518082805190602001908083835b60208310610e905780518252601f199092019160209182019101610e71565b51815160209384036101000a6000190180199092169116179052920194855250604051938490038101842060020194909455505082518492600992859290918291908401908083835b60208310610ef85780518252601f199092019160209182019101610ed9565b51815160209384036101000a6000190180199092169116179052920194855250604051938490038101842060030194909455505082518592600992859290918291908401908083835b60208310610f605780518252601f199092019160209182019101610f41565b51815160209384036101000a6000190180199092169116179052920194855250604051938490038101842060040194909455505082518392600992849290918291908401908083835b60208310610fc85780518252601f199092019160209182019101610fa9565b51815160209384036101000a600019018019909216911617905292019485525060405193849003810190932084516110099591949190910192509050611dd4565b5082604051908082528060200260200182016040528015611034578160200160208202803883390190505b506009826040518082805190602001908083835b602083106110675780518252601f199092019160209182019101611048565b51815160209384036101000a600019018019909216911617905292019485525060405193849003810190932084516110ac956005909201949190910192509050611e52565b5060016402540be4006000845b8684101561124e5783870383810282039250600280820491116110db57508387035b8083816110e457fe5b60408051426020808301919091523360601b8284015260548083018b90528351808403909101815260749092019092528051910120919004935060009084908161112a57fe5b069050806009886040518082805190602001908083835b602083106111605780518252601f199092019160209182019101611141565b6001836020036101000a038019825116818451168082178552505050505050905001915050908152602001604051809103902060050160018803815481106111a457fe5b60009182526020909120015560019095019491829003918886141561124757826009886040518082805190602001908083835b602083106111f65780518252601f1990920191602091820191016111d7565b6001836020036101000a038019825116818451168082178552505050505050905001915050908152602001604051809103902060050160018b038154811061123a57fe5b6000918252602090912001555b50506110b9565b600580548701905560068054600190810190915560078054918201815560009081527fa66cc928b5edb82af9bd49922954155ab7b0942694bea4ce44661d9a8736c68890910180546001600160a01b0319163390811790915560085460408051600160e01b6323b872dd0281526004810193909352306024840152604483018a9052516001600160a01b0391909116926323b872dd926064808201939182900301818387803b15801561130057600080fd5b505af1158015611314573d6000803e3d6000fd5b5050505060016009866040518082805190602001908083835b6020831061134c5780518252601f19909201916020918201910161132d565b51815160209384036101000a6000190180199092169116179052920194855250604080519485900382018520600801805460ff19169615159690961790955580845289518482015289517f312a96a6b31705364f7cc9dff187aaf6bd3a835e164c7e9a3f865a14b5f00e4b958b95945084935083019185019080838360005b838110156113e35781810151838201526020016113cb565b50505050905090810190601f1680156114105780820380516001836020036101000a031916815260200191505b509250505060405180910390a150505050505050565b600080606060055460065460078080548060200260200160405190810160405280929190818152602001828054801561148857602002820191906000526020600020905b81546001600160a01b0316815260019091019060200180831161146a575b50505050509050925092509250909192565b60055481565b60035481565b6009816040518082805190602001908083835b602083106114d85780518252601f1990920191602091820191016114b9565b51815160209384036101000a60001901801990921691161790529201948552506040519384900301909220600101546001600160a01b03163314915061156a90505760408051600160e51b62461bcd02815260206004820152600e60248201527f6e6f7420746865206f776e65722e000000000000000000000000000000000000604482015290519081900360640190fd5b60006009826040518082805190602001908083835b6020831061159e5780518252601f19909201916020918201910161157f565b6001836020036101000a0380198251168184511680821785525050505050509050019150509081526020016040518091039020600301541161162a5760408051600160e51b62461bcd02815260206004820152600d60248201527f62616c616e636520697320302e00000000000000000000000000000000000000604482015290519081900360640190fd5b6005546009826040518082805190602001908083835b6020831061165f5780518252601f199092019160209182019101611640565b6001836020036101000a03801982511681845116808217855250505050505090500191505090815260200160405180910390206003015411156116ec5760408051600160e51b62461bcd02815260206004820152601260248201527f6e6f7420656e6f756768206275646765742e0000000000000000000000000000604482015290519081900360640190fd5b60085460405182516001600160a01b039092169163a9059cbb9133916009918691819060208401908083835b602083106117375780518252601f199092019160209182019101611718565b51815160209384036101000a6000190180199092169116179052920194855250604080519485900390910184206003015463ffffffff871660e01b85526001600160a01b03959095166004850152602484019490945250509051604480830192600092919082900301818387803b1580156117b157600080fd5b505af11580156117c5573d6000803e3d6000fd5b505050506009816040518082805190602001908083835b602083106117fb5780518252601f1990920191602091820191016117dc565b51815160209384036101000a60001901801990921691161790529201948552506040519384900381018420600301546005805491909103905584517f428e401f2eb017cce18a2c895dc33cd6c58314cd791214e96cd1143369cb6e2a9460099450869350918291908401908083835b602083106118895780518252601f19909201916020918201910161186a565b51815160209384036101000a600019018019909216911617905292019485525060408051948590038201852060030154855251938490030192915050a150565b600781815481106118d657fe5b6000918252602090912001546001600160a01b0316905081565b6000805b6009846040518082805190602001908083835b602083106119265780518252601f199092019160209182019101611907565b51815160209384036101000a60001901801990921691161790529201948552506040519384900301909220600601548310159150611a07905057826001600160a01b03166009856040518082805190602001908083835b6020831061199c5780518252601f19909201916020918201910161197d565b51815160209384036101000a60001901801990921691161790529201948552506040519384900301909220600601805490925084915081106119da57fe5b6000918252602090912001546001600160a01b031614156119ff576001915050611a0d565b6001016118f4565b50600090505b92915050565b600080600060608060006009876040518082805190602001908083835b60208310611a4f5780518252601f199092019160209182019101611a30565b51815160209384036101000a6000190180199092169116179052920194855250604080519485900382018520600681015480875280840287019093019091529450606093925090508015611aad578160200160208202803883390190505b50905060608260060180549050604051908082528060200260200182016040528015611ae3578160200160208202803883390190505b50905060005b6006840154811015611ba957836006018181548110611b0457fe5b9060005260206000200160009054906101000a90046001600160a01b0316838281518110611b2e57fe5b60200260200101906001600160a01b031690816001600160a01b031681525050836007016000856006018381548110611b6357fe5b60009182526020808320909101546001600160a01b031683528201929092526040019020548251839083908110611b9657fe5b6020908102919091010152600101611ae9565b5060028301546003840154600490940154909a93995097509095509350915050565b60045481565b600554811115611c2b5760408051600160e51b62461bcd02815260206004820152601860248201527f677261623a206e6f7420656e6f756768206275646765742e0000000000000000604482015290519081900360640190fd5b60085460408051600160e01b63a9059cbb0281523360048201526024810184905290516001600160a01b039092169163a9059cbb9160448082019260009290919082900301818387803b158015611c8157600080fd5b505af1158015611c95573d6000803e3d6000fd5b5050600580548490039055505060405182516009918491819060208401908083835b60208310611cd65780518252601f199092019160209182019101611cb7565b51815160001960209485036101000a01908116901991909116179052920194855250604051938490038101842060060180546001810182556000918252908290200180546001600160a01b031916331790558551859460099450879350918291908401908083835b60208310611d5d5780518252601f199092019160209182019101611d3e565b51815160209384036101000a6000190180199092169116179052920194855250604080519485900382018520336000908152600790910183528190209590955585845293517f4e5f3fe5ac93c6f5c31989b385e3eb238bc744a671145ccd1a6951fd81aadb1d949381900390930192915050a15050565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f10611e1557805160ff1916838001178555611e42565b82800160010185558215611e42579182015b82811115611e42578251825591602001919060010190611e27565b50611e4e929150611e8c565b5090565b828054828255906000526020600020908101928215611e425791602002820182811115611e42578251825591602001919060010190611e27565b611ea691905b80821115611e4e5760008155600101611e92565b9056fea165627a7a7230582069a4271a2f776a482bbde16f868cd1af4ce722e2a7887188c2bd4f2b03962bfa0029";

    public static final String FUNC_MAXPACKAMOUNT = "maxPackAmount";

    public static final String FUNC_MINPACKAMOUNT = "minPackAmount";

    public static final String FUNC_HUNTING = "hunting";

    public static final String FUNC_MYTOKEN = "mytoken";

    public static final String FUNC_NUMBEROFPLAYERS = "numberOfPlayers";

    public static final String FUNC_LIMIT_AMOUNT_OF_PACK = "LIMIT_AMOUNT_OF_PACK";

    public static final String FUNC_OWNER = "owner";

    public static final String FUNC_GETTOKENINFO = "getTokenInfo";

    public static final String FUNC_TOLL = "toll";

    public static final String FUNC_GETPLAYERINFO = "getPlayerInfo";

    public static final String FUNC_TOTALPACKAMOUNTS = "totalPackAmounts";

    public static final String FUNC_MINPACKCOUNT = "minPackCount";

    public static final String FUNC_WITHDRAWBALANCE = "withdrawBalance";

    public static final String FUNC_PLAYERS = "players";

    public static final String FUNC_CHECKHUNTEREXISTS = "checkHunterExists";

    public static final String FUNC_GETPACKINFO = "getPackInfo";

    public static final String FUNC_MAXPACKCOUNT = "maxPackCount";

    public static final Event REDPACKCREATED_EVENT = new Event("redpackCreated",
            Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
    ;

    public static final Event REDPACKWITHDRAW_EVENT = new Event("redpackWithdraw",
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
    ;

    public static final Event REDPACKGRABBED_EVENT = new Event("redpackGrabbed",
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
    ;

    @Deprecated
    protected Redpack_sol_Redpack(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected Redpack_sol_Redpack(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected Redpack_sol_Redpack(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected Redpack_sol_Redpack(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public RemoteCall<BigInteger> maxPackAmount() {
        final Function function = new Function(FUNC_MAXPACKAMOUNT,
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<BigInteger> minPackAmount() {
        final Function function = new Function(FUNC_MINPACKAMOUNT,
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<TransactionReceipt> hunting(String id, BigInteger weiValue) {
        final Function function = new Function(
                FUNC_HUNTING,
                Arrays.<Type>asList(new Utf8String(id)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function, weiValue);
    }

    public RemoteCall<String> mytoken() {
        final Function function = new Function(FUNC_MYTOKEN,
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<BigInteger> numberOfPlayers() {
        final Function function = new Function(FUNC_NUMBEROFPLAYERS,
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<BigInteger> LIMIT_AMOUNT_OF_PACK() {
        final Function function = new Function(FUNC_LIMIT_AMOUNT_OF_PACK,
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<String> owner() {
        final Function function = new Function(FUNC_OWNER,
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<Tuple4<BigInteger, String, String, String>> getTokenInfo() {
        final Function function = new Function(FUNC_GETTOKENINFO,
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Address>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}));
        return new RemoteCall<Tuple4<BigInteger, String, String, String>>(
                new Callable<Tuple4<BigInteger, String, String, String>>() {
                    @Override
                    public Tuple4<BigInteger, String, String, String> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple4<BigInteger, String, String, String>(
                                (BigInteger) results.get(0).getValue(),
                                (String) results.get(1).getValue(),
                                (String) results.get(2).getValue(),
                                (String) results.get(3).getValue());
                    }
                });
    }

    public RemoteCall<TransactionReceipt> toll(BigInteger count, BigInteger value, String id, BigInteger weiValue) {
        final Function function = new Function(
                FUNC_TOLL,
                Arrays.<Type>asList(new Uint256(count),
                new Uint256(value),
                new Utf8String(id)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function, weiValue);
    }

    public RemoteCall<Tuple3<BigInteger, BigInteger, List<String>>> getPlayerInfo() {
        final Function function = new Function(FUNC_GETPLAYERINFO,
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<DynamicArray<Address>>() {}));
        return new RemoteCall<Tuple3<BigInteger, BigInteger, List<String>>>(
                new Callable<Tuple3<BigInteger, BigInteger, List<String>>>() {
                    @Override
                    public Tuple3<BigInteger, BigInteger, List<String>> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple3<BigInteger, BigInteger, List<String>>(
                                (BigInteger) results.get(0).getValue(),
                                (BigInteger) results.get(1).getValue(),
                                convertToNative((List<Address>) results.get(2).getValue()));
                    }
                });
    }

    public RemoteCall<BigInteger> totalPackAmounts() {
        final Function function = new Function(FUNC_TOTALPACKAMOUNTS,
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<BigInteger> minPackCount() {
        final Function function = new Function(FUNC_MINPACKCOUNT,
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<TransactionReceipt> withdrawBalance(String id) {
        final Function function = new Function(
                FUNC_WITHDRAWBALANCE,
                Arrays.<Type>asList(new Utf8String(id)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<String> players(BigInteger param0) {
        final Function function = new Function(FUNC_PLAYERS,
                Arrays.<Type>asList(new Uint256(param0)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<Boolean> checkHunterExists(String _id, String _hunter) {
        final Function function = new Function(FUNC_CHECKHUNTEREXISTS,
                Arrays.<Type>asList(new Utf8String(_id),
                new Address(_hunter)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteCall<Tuple5<BigInteger, BigInteger, BigInteger, List<String>, List<BigInteger>>> getPackInfo(String id) {
        final Function function = new Function(FUNC_GETPACKINFO,
                Arrays.<Type>asList(new Utf8String(id)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<DynamicArray<Address>>() {}, new TypeReference<DynamicArray<Uint256>>() {}));
        return new RemoteCall<Tuple5<BigInteger, BigInteger, BigInteger, List<String>, List<BigInteger>>>(
                new Callable<Tuple5<BigInteger, BigInteger, BigInteger, List<String>, List<BigInteger>>>() {
                    @Override
                    public Tuple5<BigInteger, BigInteger, BigInteger, List<String>, List<BigInteger>> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple5<BigInteger, BigInteger, BigInteger, List<String>, List<BigInteger>>(
                                (BigInteger) results.get(0).getValue(),
                                (BigInteger) results.get(1).getValue(),
                                (BigInteger) results.get(2).getValue(),
                                convertToNative((List<Address>) results.get(3).getValue()),
                                convertToNative((List<Uint256>) results.get(4).getValue()));
                    }
                });
    }

    public RemoteCall<BigInteger> maxPackCount() {
        final Function function = new Function(FUNC_MAXPACKCOUNT,
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public List<RedpackCreatedEventResponse> getRedpackCreatedEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = extractEventParametersWithLog(REDPACKCREATED_EVENT, transactionReceipt);
        ArrayList<RedpackCreatedEventResponse> responses = new ArrayList<RedpackCreatedEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            RedpackCreatedEventResponse typedResponse = new RedpackCreatedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.id = (String) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<RedpackCreatedEventResponse> redpackCreatedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new io.reactivex.functions.Function<Log, RedpackCreatedEventResponse>() {
            @Override
            public RedpackCreatedEventResponse apply(Log log) {
                EventValuesWithLog eventValues = extractEventParametersWithLog(REDPACKCREATED_EVENT, log);
                RedpackCreatedEventResponse typedResponse = new RedpackCreatedEventResponse();
                typedResponse.log = log;
                typedResponse.id = (String) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<RedpackCreatedEventResponse> redpackCreatedEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(REDPACKCREATED_EVENT));
        return redpackCreatedEventFlowable(filter);
    }

    public List<RedpackWithdrawEventResponse> getRedpackWithdrawEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = extractEventParametersWithLog(REDPACKWITHDRAW_EVENT, transactionReceipt);
        ArrayList<RedpackWithdrawEventResponse> responses = new ArrayList<RedpackWithdrawEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            RedpackWithdrawEventResponse typedResponse = new RedpackWithdrawEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.amount = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<RedpackWithdrawEventResponse> redpackWithdrawEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new io.reactivex.functions.Function<Log, RedpackWithdrawEventResponse>() {
            @Override
            public RedpackWithdrawEventResponse apply(Log log) {
                EventValuesWithLog eventValues = extractEventParametersWithLog(REDPACKWITHDRAW_EVENT, log);
                RedpackWithdrawEventResponse typedResponse = new RedpackWithdrawEventResponse();
                typedResponse.log = log;
                typedResponse.amount = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<RedpackWithdrawEventResponse> redpackWithdrawEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(REDPACKWITHDRAW_EVENT));
        return redpackWithdrawEventFlowable(filter);
    }

    public List<RedpackGrabbedEventResponse> getRedpackGrabbedEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = extractEventParametersWithLog(REDPACKGRABBED_EVENT, transactionReceipt);
        ArrayList<RedpackGrabbedEventResponse> responses = new ArrayList<RedpackGrabbedEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            RedpackGrabbedEventResponse typedResponse = new RedpackGrabbedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.amount = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<RedpackGrabbedEventResponse> redpackGrabbedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new io.reactivex.functions.Function<Log, RedpackGrabbedEventResponse>() {
            @Override
            public RedpackGrabbedEventResponse apply(Log log) {
                EventValuesWithLog eventValues = extractEventParametersWithLog(REDPACKGRABBED_EVENT, log);
                RedpackGrabbedEventResponse typedResponse = new RedpackGrabbedEventResponse();
                typedResponse.log = log;
                typedResponse.amount = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<RedpackGrabbedEventResponse> redpackGrabbedEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(REDPACKGRABBED_EVENT));
        return redpackGrabbedEventFlowable(filter);
    }

    @Deprecated
    public static Redpack_sol_Redpack load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new Redpack_sol_Redpack(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static Redpack_sol_Redpack load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new Redpack_sol_Redpack(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static Redpack_sol_Redpack load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new Redpack_sol_Redpack(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static Redpack_sol_Redpack load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new Redpack_sol_Redpack(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<Redpack_sol_Redpack> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider, String _token) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new Address(_token)));
        return deployRemoteCall(Redpack_sol_Redpack.class, web3j, credentials, contractGasProvider, BINARY, encodedConstructor);
    }

    public static RemoteCall<Redpack_sol_Redpack> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider, String _token) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new Address(_token)));
        return deployRemoteCall(Redpack_sol_Redpack.class, web3j, transactionManager, contractGasProvider, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<Redpack_sol_Redpack> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, String _token) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new Address(_token)));
        return deployRemoteCall(Redpack_sol_Redpack.class, web3j, credentials, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<Redpack_sol_Redpack> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, String _token) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new Address(_token)));
        return deployRemoteCall(Redpack_sol_Redpack.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    public static class RedpackCreatedEventResponse {
        public Log log;

        public String id;
    }

    public static class RedpackWithdrawEventResponse {
        public Log log;

        public BigInteger amount;
    }

    public static class RedpackGrabbedEventResponse {
        public Log log;

        public BigInteger amount;
    }
}

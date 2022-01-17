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
import org.web3j.abi.datatypes.generated.Uint8;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.Log;
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
 * <p>Generated with web3j version 4.0.1.
 */
public class PubTokenSol extends Contract {
    private static final String BINARY = "60048054600160a01b60ff021916905560c0604052600360808190527f322e30000000000000000000000000000000000000000000000000000000000060a0908152620000509160089190620000f6565b503480156200005e57600080fd5b50604051620021c4380380620021c483398101806040526200008491908101906200021a565b600480546001600160a01b03191633908117909155600185905560009081526002602090815260409091208590558351620000c69160059190860190620000f6565b506006805460ff191660ff84161790558051620000eb906007906020840190620000f6565b50505050506200033c565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f106200013957805160ff191683800117855562000169565b8280016001018555821562000169579182015b82811115620001695782518255916020019190600101906200014c565b50620001779291506200017b565b5090565b6200019891905b8082111562000177576000815560010162000182565b90565b600082601f830112620001ad57600080fd5b8151620001c4620001be82620002db565b620002b4565b91508082526020830160208301858383011115620001e157600080fd5b620001ee83828462000309565b50505092915050565b600062000205825162000198565b9392505050565b600062000205825162000303565b600080600080608085870312156200023157600080fd5b60006200023f8787620001f7565b94505060208501516001600160401b038111156200025c57600080fd5b6200026a878288016200019b565b93505060406200027d878288016200020c565b92505060608501516001600160401b038111156200029a57600080fd5b620002a8878288016200019b565b91505092959194509250565b6040518181016001600160401b0381118282101715620002d357600080fd5b604052919050565b60006001600160401b03821115620002f257600080fd5b506020601f91909101601f19160190565b60ff1690565b60005b83811015620003265781810151838201526020016200030c565b8381111562000336576000848401525b50505050565b611e78806200034c6000396000f3fe608060405234801561001057600080fd5b50600436106102275760003560e01c80635c975abb11610130578063a2b606e1116100b8578063dd62ed3e1161007c578063dd62ed3e14610461578063e611755714610474578063e6a4fbf614610487578063f2fde38b1461049a578063fbc4efb2146104ad57610227565b8063a2b606e114610402578063a9059cbb14610415578063b5e64ceb14610428578063c3bd89041461043b578063d73dd6231461044e57610227565b806370a08231116100ff57806370a08231146103b75780638456cb59146103ca5780638da5cb5b146103d25780639114ad3c146103e757806395d89b41146103fa57610227565b80635c975abb1461038157806364cceaef1461038957806365ba4e331461039c57806366188463146103a457610227565b806327e235e3116101b35780633f4ba83a116101825780633f4ba83a14610336578063410d59cc1461033e5780634ddb9c2a1461035357806354fd4d50146103665780635c6581651461036e57610227565b806327e235e3146102f3578063313ce5671461030657806336f281a91461031b5780633ef791da1461032357610227565b806310009b01116101fa57806310009b011461029257806312e63d71146102a557806318160ddd146102b857806319e0d8ee146102cd57806323b872dd146102e057610227565b806306fdde031461022c57806308cab3ef1461024a578063095ea7b31461025f5780630ab1bd701461027f575b600080fd5b6102346104b5565b6040516102419190611d3a565b60405180910390f35b61025d610258366004611ac5565b610543565b005b61027261026d366004611a60565b610612565b6040516102419190611d2c565b61023461028d366004611a90565b61063f565b61025d6102a0366004611ac5565b6106b3565b6102346102b3366004611a90565b610778565b6102c0610825565b6040516102419190611d4b565b6102346102db366004611a90565b61082c565b6102726102ee366004611a13565b61083e565b6102c06103013660046119bb565b61086b565b61030e61087d565b6040516102419190611d59565b610234610886565b610272610331366004611a90565b6108e1565b61025d610901565b610346610969565b6040516102419190611d1b565b61025d610361366004611ac5565b610a41565b610234610af6565b6102c061037c3660046119d9565b610b51565b610272610b6e565b610234610397366004611a90565b610b7e565b610346610bf2565b6102726103b2366004611a60565b610cc1565b6102c06103c53660046119bb565b610ce5565b61025d610d00565b6103da610d6f565b6040516102419190611d0d565b6103466103f5366004611b42565b610d7e565b610234610ed9565b610346610410366004611b24565b610f34565b610272610423366004611a60565b61107e565b610234610436366004611a90565b6110a2565b610234610449366004611a90565b611116565b61027261045c366004611a60565b611128565b6102c061046f3660046119d9565b61114c565b610346610482366004611b42565b611177565b61025d610495366004611a90565b6112c7565b61025d6104a83660046119bb565b6112f1565b610346611377565b6005805460408051602060026001851615610100026000190190941693909304601f8101849004840282018401909252818152929183018282801561053b5780601f106105105761010080835404028352916020019161053b565b820191906000526020600020905b81548152906001019060200180831161051e57829003601f168201915b505050505081565b6004546001600160a01b0316331461055a57600080fd5b6000600e8360405161056c9190611cee565b90815260405190819003602001902054600260001961010060018416150201909116041161060e5780600e836040516105a59190611cee565b908152602001604051809103902090805190602001906105c69291906118ab565b50600f805460018101808355600092909252835161060b917f8d1108e10bcb7c27dddfc02ed9d693a074039d026cf4ea4240b40f7d581ac802019060208601906118ab565b50505b5050565b600454600090600160a01b900460ff161561062c57600080fd5b6106368383611446565b90505b92915050565b8051602081830181018051600c8252928201938201939093209190925280546040805160026001841615610100026000190190931692909204601f8101859004850283018501909152808252909283018282801561053b5780601f106105105761010080835404028352916020019161053b565b6004546001600160a01b031633146106ca57600080fd5b600c826040516106da9190611cee565b908152604051908190036020019020546002600019610100600184161502019091160461060e5780600c836040516107129190611cee565b908152602001604051809103902090805190602001906107339291906118ab565b50600d805460018101808355600092909252835161060b917fd7b6990105719101dabeb77144f2a3385c8033acd3af97e9423a695e81ad1eb5019060208601906118ab565b606060098260405161078a9190611cee565b9081526040805160209281900383018120805460026001821615610100026000190190911604601f810185900485028301850190935282825290929091908301828280156108195780601f106107ee57610100808354040283529160200191610819565b820191906000526020600020905b8154815290600101906020018083116107fc57829003601f168201915b50505050509050919050565b6001545b90565b6060600e8260405161078a9190611cee565b600454600090600160a01b900460ff161561085857600080fd5b6108638484846114c1565b949350505050565b60026020526000908152604090205481565b60065460ff1681565b6010805460408051602060026001851615610100026000190190941693909304601f8101849004840282018401909252818152929183018282801561053b5780601f106105105761010080835404028352916020019161053b565b8051602081830181018051600b8252928201919093012091525460ff1681565b6004546001600160a01b0316331461091857600080fd5b600454600160a01b900460ff1661092e57600080fd5b60048054600160a01b60ff02191690556040517f7805862f689e2f13df9f062ff482ad3ad112aca9e0847911ed832e158c525b3390600090a1565b6060600a805480602002602001604051908101604052809291908181526020016000905b82821015610a385760008481526020908190208301805460408051601f6002600019610100600187161502019094169390930492830185900485028101850190915281815292830182828015610a245780601f106109f957610100808354040283529160200191610a24565b820191906000526020600020905b815481529060010190602001808311610a0757829003601f168201915b50505050508152602001906001019061098d565b50505050905090565b6004546001600160a01b03163314610a5857600080fd5b80600983604051610a699190611cee565b90815260200160405180910390209080519060200190610a8a9291906118ab565b50600b82604051610a9b9190611cee565b9081526040519081900360200190205460ff1661060e57600a805460018101808355600092909252835161060b917fc65a7bb8d6351c1cf70c95a316cc6a92839c986682d98bc35f958f4883f9d2a8019060208601906118ab565b6008805460408051602060026001851615610100026000190190941693909304601f8101849004840282018401909252818152929183018282801561053b5780601f106105105761010080835404028352916020019161053b565b600360209081526000928352604080842090915290825290205481565b600454600160a01b900460ff1681565b805160208183018101805160098252928201938201939093209190925280546040805160026001841615610100026000190190931692909204601f8101859004850283018501909152808252909283018282801561053b5780601f106105105761010080835404028352916020019161053b565b6060600d805480602002602001604051908101604052809291908181526020016000905b82821015610a385760008481526020908190208301805460408051601f6002600019610100600187161502019094169390930492830185900485028101850190915281815292830182828015610cad5780601f10610c8257610100808354040283529160200191610cad565b820191906000526020600020905b815481529060010190602001808311610c9057829003601f168201915b505050505081526020019060010190610c16565b600454600090600160a01b900460ff1615610cdb57600080fd5b610636838361163d565b6001600160a01b031660009081526002602052604090205490565b6004546001600160a01b03163314610d1757600080fd5b600454600160a01b900460ff1615610d2e57600080fd5b60048054600160a01b60ff021916600160a01b1790556040517f6985a02210a168e66602d3235cb6db0e70f92b3ba4d376a33c0f3d9434bff62590600090a1565b6004546001600160a01b031681565b600a5460609060009060ff841610610d995750600a54610d9f565b5060ff82165b606081604051908082528060200260200182016040528015610dd557816020015b6060815260200190600190039081610dc05790505b50600a549091508281039060005b82821115610ece576009600a6001840381548110610dfd57fe5b90600052602060002001604051610e149190611d01565b9081526040805160209281900383018120805460026001821615610100026000190190911604601f81018590048502830185019093528282529092909190830182828015610ea35780601f10610e7857610100808354040283529160200191610ea3565b820191906000526020600020905b815481529060010190602001808311610e8657829003601f168201915b5050505050848281518110610eb457fe5b602090810291909101015260001990910190600101610de3565b509195945050505050565b6007805460408051602060026001851615610100026000190190941693909304601f8101849004840282018401909252818152929183018282801561053b5780601f106105105761010080835404028352916020019161053b565b600f546060906000908310610f4c5750600f54610f4f565b50815b606081604051908082528060200260200182016040528015610f8557816020015b6060815260200190600190039081610f705790505b50600f549091508281039060005b82821115610ece57600e600f6001840381548110610fad57fe5b90600052602060002001604051610fc49190611d01565b9081526040805160209281900383018120805460026001821615610100026000190190911604601f810185900485028301850190935282825290929091908301828280156110535780601f1061102857610100808354040283529160200191611053565b820191906000526020600020905b81548152906001019060200180831161103657829003601f168201915b505050505084828151811061106457fe5b602090810291909101015260001990910190600101610f93565b600454600090600160a01b900460ff161561109857600080fd5b610636838361171e565b8051602081830181018051600e8252928201938201939093209190925280546040805160026001841615610100026000190190931692909204601f8101859004850283018501909152808252909283018282801561053b5780601f106105105761010080835404028352916020019161053b565b6060600c8260405161078a9190611cee565b600454600090600160a01b900460ff161561114257600080fd5b61063683836117fe565b6001600160a01b03918216600090815260036020908152604080832093909416825291909152205490565b600d5460609060009060ff8416106111925750600d54611198565b5060ff82165b6060816040519080825280602002602001820160405280156111ce57816020015b60608152602001906001900390816111b95790505b50600d549091508281039060005b82821115610ece57600c600d60018403815481106111f657fe5b9060005260206000200160405161120d9190611d01565b9081526040805160209281900383018120805460026001821615610100026000190190911604601f8101859004850283018501909352828252909290919083018282801561129c5780601f106112715761010080835404028352916020019161129c565b820191906000526020600020905b81548152906001019060200180831161127f57829003601f168201915b50505050508482815181106112ad57fe5b6020908102919091010152600019909101906001016111dc565b6004546001600160a01b031633146112de57600080fd5b805161060e9060109060208401906118ab565b6004546001600160a01b0316331461130857600080fd5b6001600160a01b03811661131b57600080fd5b6004546040516001600160a01b038084169216907f8be0079c531659141344cd1fd0a4f28419497f9722a3daafe3b4186f6b6457e090600090a3600480546001600160a01b0319166001600160a01b0392909216919091179055565b6060600f805480602002602001604051908101604052809291908181526020016000905b82821015610a385760008481526020908190208301805460408051601f60026000196101006001871615020190941693909304928301859004850281018501909152818152928301828280156114325780601f1061140757610100808354040283529160200191611432565b820191906000526020600020905b81548152906001019060200180831161141557829003601f168201915b50505050508152602001906001019061139b565b6000604436101561145657600080fd5b3360008181526003602090815260408083206001600160a01b03881680855292529182902085905590519091907f8c5be1e5ebec7d5bd14f71427d1e84f3dd0314c0f7b2291e5b200ac8c7c3b925906114b0908690611d4b565b60405180910390a350600192915050565b60006001600160a01b0383166114d657600080fd5b6001600160a01b0384166000908152600260205260409020548211156114fb57600080fd5b6001600160a01b038416600090815260036020908152604080832033845290915290205482111561152b57600080fd5b6001600160a01b038416600090815260026020526040902054611554908363ffffffff61188a16565b6001600160a01b038086166000908152600260205260408082209390935590851681522054611589908363ffffffff61189c16565b6001600160a01b0380851660009081526002602090815260408083209490945591871681526003825282812033825290915220546115cd908363ffffffff61188a16565b6001600160a01b0380861660008181526003602090815260408083203384529091529081902093909355915190851691907fddf252ad1be2c89b69c2b068fc378daa952ba7f163c4a11628f55a4df523b3ef9061162b908690611d4b565b60405180910390a35060019392505050565b3360009081526003602090815260408083206001600160a01b038616845290915281205480831115611692573360009081526003602090815260408083206001600160a01b03881684529091528120556116c7565b6116a2818463ffffffff61188a16565b3360009081526003602090815260408083206001600160a01b03891684529091529020555b3360008181526003602090815260408083206001600160a01b0389168085529252918290205491519092917f8c5be1e5ebec7d5bd14f71427d1e84f3dd0314c0f7b2291e5b200ac8c7c3b9259161162b9190611d4b565b6000604436101561172e57600080fd5b6001600160a01b03831661174157600080fd5b3360009081526002602052604090205482111561175d57600080fd5b3360009081526002602052604090205461177d908363ffffffff61188a16565b33600090815260026020526040808220929092556001600160a01b038516815220546117af908363ffffffff61189c16565b6001600160a01b0384166000818152600260205260409081902092909255905133907fddf252ad1be2c89b69c2b068fc378daa952ba7f163c4a11628f55a4df523b3ef906114b0908690611d4b565b3360009081526003602090815260408083206001600160a01b0386168452909152812054611832908363ffffffff61189c16565b3360008181526003602090815260408083206001600160a01b038916808552925291829020849055905190927f8c5be1e5ebec7d5bd14f71427d1e84f3dd0314c0f7b2291e5b200ac8c7c3b925916114b09190611d4b565b60008282111561189657fe5b50900390565b60008282018381101561063657fe5b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f106118ec57805160ff1916838001178555611919565b82800160010185558215611919579182015b828111156119195782518255916020019190600101906118fe565b50611925929150611929565b5090565b61082991905b80821115611925576000815560010161192f565b60006106368235611dda565b600082601f83011261196057600080fd5b813561197361196e82611d8e565b611d67565b9150808252602083016020830185838301111561198f57600080fd5b61199a838284611dfc565b50505092915050565b60006106368235610829565b60006106368235611df6565b6000602082840312156119cd57600080fd5b60006108638484611943565b600080604083850312156119ec57600080fd5b60006119f88585611943565b9250506020611a0985828601611943565b9150509250929050565b600080600060608486031215611a2857600080fd5b6000611a348686611943565b9350506020611a4586828701611943565b9250506040611a56868287016119a3565b9150509250925092565b60008060408385031215611a7357600080fd5b6000611a7f8585611943565b9250506020611a09858286016119a3565b600060208284031215611aa257600080fd5b813567ffffffffffffffff811115611ab957600080fd5b6108638482850161194f565b60008060408385031215611ad857600080fd5b823567ffffffffffffffff811115611aef57600080fd5b611afb8582860161194f565b925050602083013567ffffffffffffffff811115611b1857600080fd5b611a098582860161194f565b600060208284031215611b3657600080fd5b600061086384846119a3565b600060208284031215611b5457600080fd5b600061086384846119af565b60006106368383611bee565b611b7581611dda565b82525050565b6000611b8682611dc8565b611b908185611dcc565b935083602082028501611ba285611db6565b60005b84811015611bd9578383038852611bbd838351611b60565b9250611bc882611db6565b602098909801979150600101611ba5565b50909695505050505050565b611b7581611de5565b6000611bf982611dc8565b611c038185611dcc565b9350611c13818560208601611e08565b611c1c81611e34565b9093019392505050565b6000611c3182611dc8565b611c3b8185611dd5565b9350611c4b818560208601611e08565b9290920192915050565b600081546001811660008114611c725760018114611c9557611cd4565b607f6002830416611c838187611dd5565b60ff1984168152955085019250611cd4565b60028204611ca38187611dd5565b9550611cae85611dbc565b60005b82811015611ccd57815488820152600190910190602001611cb1565b5050850192505b505092915050565b611b7581610829565b611b7581611df6565b6000611cfa8284611c26565b9392505050565b6000611cfa8284611c55565b602081016106398284611b6c565b602080825281016106368184611b7b565b602081016106398284611be5565b602080825281016106368184611bee565b602081016106398284611cdc565b602081016106398284611ce5565b60405181810167ffffffffffffffff81118282101715611d8657600080fd5b604052919050565b600067ffffffffffffffff821115611da557600080fd5b506020601f91909101601f19160190565b60200190565b60009081526020902090565b5190565b90815260200190565b919050565b600061063982611dea565b151590565b6001600160a01b031690565b60ff1690565b82818337506000910152565b60005b83811015611e23578181015183820152602001611e0b565b8381111561060b5750506000910152565b601f01601f19169056fea265627a7a7230582079767aeba26d27771a628825796c78f4270db74be661a752117681772fe569c96c6578706572696d656e74616cf50037";

    public static final String FUNC_NAME = "name";

    public static final String FUNC_CREATENOTICE = "createNotice";

    public static final String FUNC_APPROVE = "approve";

    public static final String FUNC_NEWS = "news";

    public static final String FUNC_CREATENEWS = "createNews";

    public static final String FUNC_GETREPORT = "getReport";

    public static final String FUNC_TOTALSUPPLY = "totalSupply";

    public static final String FUNC_GETNOTICE = "getNotice";

    public static final String FUNC_TRANSFERFROM = "transferFrom";

    public static final String FUNC_BALANCES = "balances";

    public static final String FUNC_DECIMALS = "decimals";

    public static final String FUNC_COMPANYINFO = "companyInfo";

    public static final String FUNC_ITEMMAP = "itemMap";

    public static final String FUNC_UNPAUSE = "unpause";

    public static final String FUNC_GETITEMS = "getItems";

    public static final String FUNC_CREATEREPORT = "createReport";

    public static final String FUNC_VERSION = "version";

    public static final String FUNC_ALLOWED = "allowed";

    public static final String FUNC_PAUSED = "paused";

    public static final String FUNC_REPORTS = "reports";

    public static final String FUNC_GETNEWSITEMS = "getNewsItems";

    public static final String FUNC_DECREASEAPPROVAL = "decreaseApproval";

    public static final String FUNC_BALANCEOF = "balanceOf";

    public static final String FUNC_PAUSE = "pause";

    public static final String FUNC_OWNER = "owner";

    public static final String FUNC_GETLASTREPORT = "getLastReport";

    public static final String FUNC_SYMBOL = "symbol";

    public static final String FUNC_GETLASTNOTICE = "getLastNotice";

    public static final String FUNC_TRANSFER = "transfer";

    public static final String FUNC_NOTICE = "notice";

    public static final String FUNC_GETNEWS = "getNews";

    public static final String FUNC_INCREASEAPPROVAL = "increaseApproval";

    public static final String FUNC_ALLOWANCE = "allowance";

    public static final String FUNC_GETLASTNEWS = "getLastNews";

    public static final String FUNC_UPDATECOMPANYINFO = "updateCompanyInfo";

    public static final String FUNC_TRANSFEROWNERSHIP = "transferOwnership";

    public static final String FUNC_GETNOTICEITEMS = "getNoticeItems";

    public static final Event PAUSE_EVENT = new Event("Pause",
            Arrays.<TypeReference<?>>asList());
    ;

    public static final Event UNPAUSE_EVENT = new Event("Unpause",
            Arrays.<TypeReference<?>>asList());
    ;

    public static final Event OWNERSHIPRENOUNCED_EVENT = new Event("OwnershipRenounced",
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}));
    ;

    public static final Event OWNERSHIPTRANSFERRED_EVENT = new Event("OwnershipTransferred",
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}));
    ;

    public static final Event APPROVAL_EVENT = new Event("Approval",
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event TRANSFER_EVENT = new Event("Transfer",
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Uint256>() {}));
    ;

    @Deprecated
    protected PubTokenSol(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected PubTokenSol(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected PubTokenSol(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected PubTokenSol(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public RemoteCall<String> name() {
        final Function function = new Function(FUNC_NAME,
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<TransactionReceipt> createNotice(String item, String json) {
        final Function function = new Function(
                FUNC_CREATENOTICE,
                Arrays.<Type>asList(new Utf8String(item),
                new Utf8String(json)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> approve(String _spender, BigInteger _value) {
        final Function function = new Function(
                FUNC_APPROVE,
                Arrays.<Type>asList(new Address(_spender),
                new Uint256(_value)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<String> news(String param0) {
        final Function function = new Function(FUNC_NEWS,
                Arrays.<Type>asList(new Utf8String(param0)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<TransactionReceipt> createNews(String item, String json) {
        final Function function = new Function(
                FUNC_CREATENEWS,
                Arrays.<Type>asList(new Utf8String(item),
                new Utf8String(json)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<String> getReport(String item) {
        final Function function = new Function(FUNC_GETREPORT,
                Arrays.<Type>asList(new Utf8String(item)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<BigInteger> totalSupply() {
        final Function function = new Function(FUNC_TOTALSUPPLY,
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<String> getNotice(String item) {
        final Function function = new Function(FUNC_GETNOTICE,
                Arrays.<Type>asList(new Utf8String(item)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<TransactionReceipt> transferFrom(String _from, String _to, BigInteger _value) {
        final Function function = new Function(
                FUNC_TRANSFERFROM,
                Arrays.<Type>asList(new Address(_from),
                new Address(_to),
                new Uint256(_value)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<BigInteger> balances(String param0) {
        final Function function = new Function(FUNC_BALANCES,
                Arrays.<Type>asList(new Address(param0)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<BigInteger> decimals() {
        final Function function = new Function(FUNC_DECIMALS,
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint8>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<String> companyInfo() {
        final Function function = new Function(FUNC_COMPANYINFO,
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<Boolean> itemMap(String param0) {
        final Function function = new Function(FUNC_ITEMMAP,
                Arrays.<Type>asList(new Utf8String(param0)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteCall<TransactionReceipt> unpause() {
        final Function function = new Function(
                FUNC_UNPAUSE,
                Arrays.<Type>asList(),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<List> getItems() {
        final Function function = new Function(FUNC_GETITEMS,
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<DynamicArray<Utf8String>>() {}));
        return new RemoteCall<List>(
                new Callable<List>() {
                    @Override
                    @SuppressWarnings("unchecked")
                    public List call() throws Exception {
                        List<Type> result = (List<Type>) executeCallSingleValueReturn(function, List.class);
                        return convertToNative(result);
                    }
                });
    }

    public RemoteCall<TransactionReceipt> createReport(String item, String json) {
        final Function function = new Function(
                FUNC_CREATEREPORT,
                Arrays.<Type>asList(new Utf8String(item),
                new Utf8String(json)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<String> version() {
        final Function function = new Function(FUNC_VERSION,
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<BigInteger> allowed(String param0, String param1) {
        final Function function = new Function(FUNC_ALLOWED,
                Arrays.<Type>asList(new Address(param0),
                new Address(param1)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<Boolean> paused() {
        final Function function = new Function(FUNC_PAUSED,
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteCall<String> reports(String param0) {
        final Function function = new Function(FUNC_REPORTS,
                Arrays.<Type>asList(new Utf8String(param0)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<List> getNewsItems() {
        final Function function = new Function(FUNC_GETNEWSITEMS,
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<DynamicArray<Utf8String>>() {}));
        return new RemoteCall<List>(
                new Callable<List>() {
                    @Override
                    @SuppressWarnings("unchecked")
                    public List call() throws Exception {
                        List<Type> result = (List<Type>) executeCallSingleValueReturn(function, List.class);
                        return convertToNative(result);
                    }
                });
    }

    public RemoteCall<TransactionReceipt> decreaseApproval(String _spender, BigInteger _subtractedValue) {
        final Function function = new Function(
                FUNC_DECREASEAPPROVAL,
                Arrays.<Type>asList(new Address(_spender),
                new Uint256(_subtractedValue)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<BigInteger> balanceOf(String _owner) {
        final Function function = new Function(FUNC_BALANCEOF,
                Arrays.<Type>asList(new Address(_owner)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<TransactionReceipt> pause() {
        final Function function = new Function(
                FUNC_PAUSE,
                Arrays.<Type>asList(),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<String> owner() {
        final Function function = new Function(FUNC_OWNER,
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<List> getLastReport(BigInteger _size) {
        final Function function = new Function(FUNC_GETLASTREPORT,
                Arrays.<Type>asList(new Uint8(_size)),
                Arrays.<TypeReference<?>>asList(new TypeReference<DynamicArray<Utf8String>>() {}));
        return new RemoteCall<List>(
                new Callable<List>() {
                    @Override
                    @SuppressWarnings("unchecked")
                    public List call() throws Exception {
                        List<Type> result = (List<Type>) executeCallSingleValueReturn(function, List.class);
                        return convertToNative(result);
                    }
                });
    }

    public RemoteCall<String> symbol() {
        final Function function = new Function(FUNC_SYMBOL,
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<List> getLastNotice(BigInteger _size) {
        final Function function = new Function(FUNC_GETLASTNOTICE,
                Arrays.<Type>asList(new Uint256(_size)),
                Arrays.<TypeReference<?>>asList(new TypeReference<DynamicArray<Utf8String>>() {}));
        return new RemoteCall<List>(
                new Callable<List>() {
                    @Override
                    @SuppressWarnings("unchecked")
                    public List call() throws Exception {
                        List<Type> result = (List<Type>) executeCallSingleValueReturn(function, List.class);
                        return convertToNative(result);
                    }
                });
    }

    public RemoteCall<TransactionReceipt> transfer(String _to, BigInteger _value) {
        final Function function = new Function(
                FUNC_TRANSFER,
                Arrays.<Type>asList(new Address(_to),
                new Uint256(_value)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<String> notice(String param0) {
        final Function function = new Function(FUNC_NOTICE,
                Arrays.<Type>asList(new Utf8String(param0)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<String> getNews(String item) {
        final Function function = new Function(FUNC_GETNEWS,
                Arrays.<Type>asList(new Utf8String(item)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<TransactionReceipt> increaseApproval(String _spender, BigInteger _addedValue) {
        final Function function = new Function(
                FUNC_INCREASEAPPROVAL,
                Arrays.<Type>asList(new Address(_spender),
                new Uint256(_addedValue)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<BigInteger> allowance(String _owner, String _spender) {
        final Function function = new Function(FUNC_ALLOWANCE,
                Arrays.<Type>asList(new Address(_owner),
                new Address(_spender)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<List> getLastNews(BigInteger _size) {
        final Function function = new Function(FUNC_GETLASTNEWS,
                Arrays.<Type>asList(new Uint8(_size)),
                Arrays.<TypeReference<?>>asList(new TypeReference<DynamicArray<Utf8String>>() {}));
        return new RemoteCall<List>(
                new Callable<List>() {
                    @Override
                    @SuppressWarnings("unchecked")
                    public List call() throws Exception {
                        List<Type> result = (List<Type>) executeCallSingleValueReturn(function, List.class);
                        return convertToNative(result);
                    }
                });
    }

    public RemoteCall<TransactionReceipt> updateCompanyInfo(String _info) {
        final Function function = new Function(
                FUNC_UPDATECOMPANYINFO,
                Arrays.<Type>asList(new Utf8String(_info)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> transferOwnership(String _newOwner) {
        final Function function = new Function(
                FUNC_TRANSFEROWNERSHIP,
                Arrays.<Type>asList(new Address(_newOwner)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<List> getNoticeItems() {
        final Function function = new Function(FUNC_GETNOTICEITEMS,
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<DynamicArray<Utf8String>>() {}));
        return new RemoteCall<List>(
                new Callable<List>() {
                    @Override
                    @SuppressWarnings("unchecked")
                    public List call() throws Exception {
                        List<Type> result = (List<Type>) executeCallSingleValueReturn(function, List.class);
                        return convertToNative(result);
                    }
                });
    }

    public List<PauseEventResponse> getPauseEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = extractEventParametersWithLog(PAUSE_EVENT, transactionReceipt);
        ArrayList<PauseEventResponse> responses = new ArrayList<PauseEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            PauseEventResponse typedResponse = new PauseEventResponse();
            typedResponse.log = eventValues.getLog();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<PauseEventResponse> pauseEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new io.reactivex.functions.Function<Log, PauseEventResponse>() {
            @Override
            public PauseEventResponse apply(Log log) {
                EventValuesWithLog eventValues = extractEventParametersWithLog(PAUSE_EVENT, log);
                PauseEventResponse typedResponse = new PauseEventResponse();
                typedResponse.log = log;
                return typedResponse;
            }
        });
    }

    public Flowable<PauseEventResponse> pauseEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(PAUSE_EVENT));
        return pauseEventFlowable(filter);
    }

    public List<UnpauseEventResponse> getUnpauseEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = extractEventParametersWithLog(UNPAUSE_EVENT, transactionReceipt);
        ArrayList<UnpauseEventResponse> responses = new ArrayList<UnpauseEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            UnpauseEventResponse typedResponse = new UnpauseEventResponse();
            typedResponse.log = eventValues.getLog();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<UnpauseEventResponse> unpauseEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new io.reactivex.functions.Function<Log, UnpauseEventResponse>() {
            @Override
            public UnpauseEventResponse apply(Log log) {
                EventValuesWithLog eventValues = extractEventParametersWithLog(UNPAUSE_EVENT, log);
                UnpauseEventResponse typedResponse = new UnpauseEventResponse();
                typedResponse.log = log;
                return typedResponse;
            }
        });
    }

    public Flowable<UnpauseEventResponse> unpauseEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(UNPAUSE_EVENT));
        return unpauseEventFlowable(filter);
    }

    public List<OwnershipRenouncedEventResponse> getOwnershipRenouncedEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = extractEventParametersWithLog(OWNERSHIPRENOUNCED_EVENT, transactionReceipt);
        ArrayList<OwnershipRenouncedEventResponse> responses = new ArrayList<OwnershipRenouncedEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            OwnershipRenouncedEventResponse typedResponse = new OwnershipRenouncedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.previousOwner = (String) eventValues.getIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<OwnershipRenouncedEventResponse> ownershipRenouncedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new io.reactivex.functions.Function<Log, OwnershipRenouncedEventResponse>() {
            @Override
            public OwnershipRenouncedEventResponse apply(Log log) {
                EventValuesWithLog eventValues = extractEventParametersWithLog(OWNERSHIPRENOUNCED_EVENT, log);
                OwnershipRenouncedEventResponse typedResponse = new OwnershipRenouncedEventResponse();
                typedResponse.log = log;
                typedResponse.previousOwner = (String) eventValues.getIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<OwnershipRenouncedEventResponse> ownershipRenouncedEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(OWNERSHIPRENOUNCED_EVENT));
        return ownershipRenouncedEventFlowable(filter);
    }

    public List<OwnershipTransferredEventResponse> getOwnershipTransferredEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = extractEventParametersWithLog(OWNERSHIPTRANSFERRED_EVENT, transactionReceipt);
        ArrayList<OwnershipTransferredEventResponse> responses = new ArrayList<OwnershipTransferredEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            OwnershipTransferredEventResponse typedResponse = new OwnershipTransferredEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.previousOwner = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.newOwner = (String) eventValues.getIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<OwnershipTransferredEventResponse> ownershipTransferredEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new io.reactivex.functions.Function<Log, OwnershipTransferredEventResponse>() {
            @Override
            public OwnershipTransferredEventResponse apply(Log log) {
                EventValuesWithLog eventValues = extractEventParametersWithLog(OWNERSHIPTRANSFERRED_EVENT, log);
                OwnershipTransferredEventResponse typedResponse = new OwnershipTransferredEventResponse();
                typedResponse.log = log;
                typedResponse.previousOwner = (String) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.newOwner = (String) eventValues.getIndexedValues().get(1).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<OwnershipTransferredEventResponse> ownershipTransferredEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(OWNERSHIPTRANSFERRED_EVENT));
        return ownershipTransferredEventFlowable(filter);
    }

    public List<ApprovalEventResponse> getApprovalEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = extractEventParametersWithLog(APPROVAL_EVENT, transactionReceipt);
        ArrayList<ApprovalEventResponse> responses = new ArrayList<ApprovalEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            ApprovalEventResponse typedResponse = new ApprovalEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.owner = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.spender = (String) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.value = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<ApprovalEventResponse> approvalEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new io.reactivex.functions.Function<Log, ApprovalEventResponse>() {
            @Override
            public ApprovalEventResponse apply(Log log) {
                EventValuesWithLog eventValues = extractEventParametersWithLog(APPROVAL_EVENT, log);
                ApprovalEventResponse typedResponse = new ApprovalEventResponse();
                typedResponse.log = log;
                typedResponse.owner = (String) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.spender = (String) eventValues.getIndexedValues().get(1).getValue();
                typedResponse.value = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<ApprovalEventResponse> approvalEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(APPROVAL_EVENT));
        return approvalEventFlowable(filter);
    }

    public List<TransferEventResponse> getTransferEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = extractEventParametersWithLog(TRANSFER_EVENT, transactionReceipt);
        ArrayList<TransferEventResponse> responses = new ArrayList<TransferEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            TransferEventResponse typedResponse = new TransferEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.from = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.to = (String) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.value = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<TransferEventResponse> transferEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new io.reactivex.functions.Function<Log, TransferEventResponse>() {
            @Override
            public TransferEventResponse apply(Log log) {
                EventValuesWithLog eventValues = extractEventParametersWithLog(TRANSFER_EVENT, log);
                TransferEventResponse typedResponse = new TransferEventResponse();
                typedResponse.log = log;
                typedResponse.from = (String) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.to = (String) eventValues.getIndexedValues().get(1).getValue();
                typedResponse.value = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<TransferEventResponse> transferEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(TRANSFER_EVENT));
        return transferEventFlowable(filter);
    }

    @Deprecated
    public static PubTokenSol load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new PubTokenSol(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static PubTokenSol load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new PubTokenSol(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static PubTokenSol load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new PubTokenSol(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static PubTokenSol load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new PubTokenSol(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<PubTokenSol> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider, BigInteger _initialAmount, String _tokenName, BigInteger _decimalUnits, String _tokenSymbol) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new Uint256(_initialAmount),
                new Utf8String(_tokenName),
                new Uint8(_decimalUnits),
                new Utf8String(_tokenSymbol)));
        return deployRemoteCall(PubTokenSol.class, web3j, credentials, contractGasProvider, BINARY, encodedConstructor);
    }

    public static RemoteCall<PubTokenSol> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider, BigInteger _initialAmount, String _tokenName, BigInteger _decimalUnits, String _tokenSymbol) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new Uint256(_initialAmount),
                new Utf8String(_tokenName),
                new Uint8(_decimalUnits),
                new Utf8String(_tokenSymbol)));
        return deployRemoteCall(PubTokenSol.class, web3j, transactionManager, contractGasProvider, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<PubTokenSol> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, BigInteger _initialAmount, String _tokenName, BigInteger _decimalUnits, String _tokenSymbol) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new Uint256(_initialAmount),
                new Utf8String(_tokenName),
                new Uint8(_decimalUnits),
                new Utf8String(_tokenSymbol)));
        return deployRemoteCall(PubTokenSol.class, web3j, credentials, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<PubTokenSol> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, BigInteger _initialAmount, String _tokenName, BigInteger _decimalUnits, String _tokenSymbol) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new Uint256(_initialAmount),
                new Utf8String(_tokenName),
                new Uint8(_decimalUnits),
                new Utf8String(_tokenSymbol)));
        return deployRemoteCall(PubTokenSol.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    public static class PauseEventResponse {
        public Log log;
    }

    public static class UnpauseEventResponse {
        public Log log;
    }

    public static class OwnershipRenouncedEventResponse {
        public Log log;

        public String previousOwner;
    }

    public static class OwnershipTransferredEventResponse {
        public Log log;

        public String previousOwner;

        public String newOwner;
    }

    public static class ApprovalEventResponse {
        public Log log;

        public String owner;

        public String spender;

        public BigInteger value;
    }

    public static class TransferEventResponse {
        public Log log;

        public String from;

        public String to;

        public BigInteger value;
    }
}

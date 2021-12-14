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
public class StandContract_sol_PubToken extends Contract {
    private static final String BINARY = "60048054600160a01b60ff021916905560c0604052600360808190527f322e30000000000000000000000000000000000000000000000000000000000060a0908152620000509160089190620000f6565b503480156200005e57600080fd5b50604051620020de380380620020de83398101806040526200008491908101906200021a565b600480546001600160a01b03191633908117909155600185905560009081526002602090815260409091208590558351620000c69160059190860190620000f6565b506006805460ff191660ff84161790558051620000eb906007906020840190620000f6565b50505050506200033c565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f106200013957805160ff191683800117855562000169565b8280016001018555821562000169579182015b82811115620001695782518255916020019190600101906200014c565b50620001779291506200017b565b5090565b6200019891905b8082111562000177576000815560010162000182565b90565b600082601f830112620001ad57600080fd5b8151620001c4620001be82620002db565b620002b4565b91508082526020830160208301858383011115620001e157600080fd5b620001ee83828462000309565b50505092915050565b600062000205825162000198565b9392505050565b600062000205825162000303565b600080600080608085870312156200023157600080fd5b60006200023f8787620001f7565b94505060208501516001600160401b038111156200025c57600080fd5b6200026a878288016200019b565b93505060406200027d878288016200020c565b92505060608501516001600160401b038111156200029a57600080fd5b620002a8878288016200019b565b91505092959194509250565b6040518181016001600160401b0381118282101715620002d357600080fd5b604052919050565b60006001600160401b03821115620002f257600080fd5b506020601f91909101601f19160190565b60ff1690565b60005b83811015620003265781810151838201526020016200030c565b8381111562000336576000848401525b50505050565b611d92806200034c6000396000f3fe608060405234801561001057600080fd5b50600436106102115760003560e01c80635c975abb11610125578063a9059cbb116100ad578063dd62ed3e1161007c578063dd62ed3e14610430578063e611755714610443578063f2fde38b14610456578063fb558db114610469578063fbc4efb21461047c57610211565b8063a9059cbb146103e4578063b5e64ceb146103f7578063c3bd89041461040a578063d73dd6231461041d57610211565b806370a08231116100f457806370a08231146103995780638456cb59146103ac5780638da5cb5b146103b45780639114ad3c146103c957806395d89b41146103dc57610211565b80635c975abb1461036357806364cceaef1461036b57806365ba4e331461037e578063661884631461038657610211565b806323b872dd116101a85780633f4ba83a116101775780633f4ba83a14610318578063410d59cc146103205780634ddb9c2a1461033557806354fd4d50146103485780635c6581651461035057610211565b806323b872dd146102ca57806327e235e3146102dd578063313ce567146102f05780633ef791da1461030557610211565b806310009b01116101e457806310009b011461027c57806312e63d711461028f57806318160ddd146102a257806319e0d8ee146102b757610211565b806306fdde031461021657806308cab3ef14610234578063095ea7b3146102495780630ab1bd7014610269575b600080fd5b61021e610484565b60405161022b9190611c54565b60405180910390f35b6102476102423660046119fd565b610512565b005b61025c610257366004611998565b6105de565b60405161022b9190611c46565b61021e6102773660046119c8565b61060b565b61024761028a3660046119fd565b61067f565b61021e61029d3660046119c8565b610744565b6102aa6107f1565b60405161022b9190611c65565b61021e6102c53660046119c8565b6107f8565b61025c6102d836600461194b565b61080a565b6102aa6102eb3660046118f3565b610837565b6102f8610849565b60405161022b9190611c73565b61025c6103133660046119c8565b610852565b610247610872565b6103286108da565b60405161022b9190611c35565b6102476103433660046119fd565b6109b2565b61021e610a67565b6102aa61035e366004611911565b610ac2565b61025c610adf565b61021e6103793660046119c8565b610aef565b610328610b63565b61025c610394366004611998565b610c32565b6102aa6103a73660046118f3565b610c56565b610247610c71565b6103bc610ce0565b60405161022b9190611c27565b6103286103d7366004611a5c565b610cef565b61021e610e41565b61025c6103f2366004611998565b610e9c565b61021e6104053660046119c8565b610ec0565b61021e6104183660046119c8565b610f34565b61025c61042b366004611998565b610f46565b6102aa61043e366004611911565b610f6a565b610328610451366004611a5c565b610f95565b6102476104643660046118f3565b6110df565b610328610477366004611a5c565b611165565b6103286112af565b6005805460408051602060026001851615610100026000190190941693909304601f8101849004840282018401909252818152929183018282801561050a5780601f106104df5761010080835404028352916020019161050a565b820191906000526020600020905b8154815290600101906020018083116104ed57829003601f168201915b505050505081565b6004546001600160a01b0316331461052957600080fd5b80600e8360405161053a9190611c08565b9081526020016040518091039020908051906020019061055b9291906117e3565b50600e8260405161056c9190611c08565b90815260405190819003602001902054600260001961010060018416150201909116046105da57600f80546001810180835560009290925283516105d7917f8d1108e10bcb7c27dddfc02ed9d693a074039d026cf4ea4240b40f7d581ac802019060208601906117e3565b50505b5050565b600454600090600160a01b900460ff16156105f857600080fd5b610602838361137e565b90505b92915050565b8051602081830181018051600c8252928201938201939093209190925280546040805160026001841615610100026000190190931692909204601f8101859004850283018501909152808252909283018282801561050a5780601f106104df5761010080835404028352916020019161050a565b6004546001600160a01b0316331461069657600080fd5b80600c836040516106a79190611c08565b908152602001604051809103902090805190602001906106c89291906117e3565b50600c826040516106d99190611c08565b90815260405190819003602001902054600260001961010060018416150201909116046105da57600d80546001810180835560009290925283516105d7917fd7b6990105719101dabeb77144f2a3385c8033acd3af97e9423a695e81ad1eb5019060208601906117e3565b60606009826040516107569190611c08565b9081526040805160209281900383018120805460026001821615610100026000190190911604601f810185900485028301850190935282825290929091908301828280156107e55780601f106107ba576101008083540402835291602001916107e5565b820191906000526020600020905b8154815290600101906020018083116107c857829003601f168201915b50505050509050919050565b6001545b90565b6060600e826040516107569190611c08565b600454600090600160a01b900460ff161561082457600080fd5b61082f8484846113f9565b949350505050565b60026020526000908152604090205481565b60065460ff1681565b8051602081830181018051600b8252928201919093012091525460ff1681565b6004546001600160a01b0316331461088957600080fd5b600454600160a01b900460ff1661089f57600080fd5b60048054600160a01b60ff02191690556040517f7805862f689e2f13df9f062ff482ad3ad112aca9e0847911ed832e158c525b3390600090a1565b6060600a805480602002602001604051908101604052809291908181526020016000905b828210156109a95760008481526020908190208301805460408051601f60026000196101006001871615020190941693909304928301859004850281018501909152818152928301828280156109955780601f1061096a57610100808354040283529160200191610995565b820191906000526020600020905b81548152906001019060200180831161097857829003601f168201915b5050505050815260200190600101906108fe565b50505050905090565b6004546001600160a01b031633146109c957600080fd5b806009836040516109da9190611c08565b908152602001604051809103902090805190602001906109fb9291906117e3565b50600b82604051610a0c9190611c08565b9081526040519081900360200190205460ff166105da57600a80546001810180835560009290925283516105d7917fc65a7bb8d6351c1cf70c95a316cc6a92839c986682d98bc35f958f4883f9d2a8019060208601906117e3565b6008805460408051602060026001851615610100026000190190941693909304601f8101849004840282018401909252818152929183018282801561050a5780601f106104df5761010080835404028352916020019161050a565b600360209081526000928352604080842090915290825290205481565b600454600160a01b900460ff1681565b805160208183018101805160098252928201938201939093209190925280546040805160026001841615610100026000190190931692909204601f8101859004850283018501909152808252909283018282801561050a5780601f106104df5761010080835404028352916020019161050a565b6060600d805480602002602001604051908101604052809291908181526020016000905b828210156109a95760008481526020908190208301805460408051601f6002600019610100600187161502019094169390930492830185900485028101850190915281815292830182828015610c1e5780601f10610bf357610100808354040283529160200191610c1e565b820191906000526020600020905b815481529060010190602001808311610c0157829003601f168201915b505050505081526020019060010190610b87565b600454600090600160a01b900460ff1615610c4c57600080fd5b6106028383611575565b6001600160a01b031660009081526002602052604090205490565b6004546001600160a01b03163314610c8857600080fd5b600454600160a01b900460ff1615610c9f57600080fd5b60048054600160a01b60ff021916600160a01b1790556040517f6985a02210a168e66602d3235cb6db0e70f92b3ba4d376a33c0f3d9434bff62590600090a1565b6004546001600160a01b031681565b600a5460609060009060ff841610610d0a5750600a54610d10565b5060ff82165b606081604051908082528060200260200182016040528015610d4657816020015b6060815260200190600190039081610d315790505b50600a54909150600019015b600a54839003811115610e39576009600a8281548110610d6e57fe5b90600052602060002001604051610d859190611c1b565b9081526040805160209281900383018120805460026001821615610100026000190190911604601f81018590048502830185019093528282529092909190830182828015610e145780601f10610de957610100808354040283529160200191610e14565b820191906000526020600020905b815481529060010190602001808311610df757829003601f168201915b5050505050828281518110610e2557fe5b602090810291909101015260001901610d52565b509392505050565b6007805460408051602060026001851615610100026000190190941693909304601f8101849004840282018401909252818152929183018282801561050a5780601f106104df5761010080835404028352916020019161050a565b600454600090600160a01b900460ff1615610eb657600080fd5b6106028383611656565b8051602081830181018051600e8252928201938201939093209190925280546040805160026001841615610100026000190190931692909204601f8101859004850283018501909152808252909283018282801561050a5780601f106104df5761010080835404028352916020019161050a565b6060600c826040516107569190611c08565b600454600090600160a01b900460ff1615610f6057600080fd5b6106028383611736565b6001600160a01b03918216600090815260036020908152604080832093909416825291909152205490565b600d5460609060009060ff841610610fb05750600d54610fb6565b5060ff82165b606081604051908082528060200260200182016040528015610fec57816020015b6060815260200190600190039081610fd75790505b50600d54909150600019015b600d54839003811115610e3957600c600d828154811061101457fe5b9060005260206000200160405161102b9190611c1b565b9081526040805160209281900383018120805460026001821615610100026000190190911604601f810185900485028301850190935282825290929091908301828280156110ba5780601f1061108f576101008083540402835291602001916110ba565b820191906000526020600020905b81548152906001019060200180831161109d57829003601f168201915b50505050508282815181106110cb57fe5b602090810291909101015260001901610ff8565b6004546001600160a01b031633146110f657600080fd5b6001600160a01b03811661110957600080fd5b6004546040516001600160a01b038084169216907f8be0079c531659141344cd1fd0a4f28419497f9722a3daafe3b4186f6b6457e090600090a3600480546001600160a01b0319166001600160a01b0392909216919091179055565b600f5460609060009060ff8416106111805750600f54611186565b5060ff82165b6060816040519080825280602002602001820160405280156111bc57816020015b60608152602001906001900390816111a75790505b50600f54909150600019015b600f54839003811115610e3957600e600f82815481106111e457fe5b906000526020600020016040516111fb9190611c1b565b9081526040805160209281900383018120805460026001821615610100026000190190911604601f8101859004850283018501909352828252909290919083018282801561128a5780601f1061125f5761010080835404028352916020019161128a565b820191906000526020600020905b81548152906001019060200180831161126d57829003601f168201915b505050505082828151811061129b57fe5b6020908102919091010152600019016111c8565b6060600f805480602002602001604051908101604052809291908181526020016000905b828210156109a95760008481526020908190208301805460408051601f600260001961010060018716150201909416939093049283018590048502810185019091528181529283018282801561136a5780601f1061133f5761010080835404028352916020019161136a565b820191906000526020600020905b81548152906001019060200180831161134d57829003601f168201915b5050505050815260200190600101906112d3565b6000604436101561138e57600080fd5b3360008181526003602090815260408083206001600160a01b03881680855292529182902085905590519091907f8c5be1e5ebec7d5bd14f71427d1e84f3dd0314c0f7b2291e5b200ac8c7c3b925906113e8908690611c65565b60405180910390a350600192915050565b60006001600160a01b03831661140e57600080fd5b6001600160a01b03841660009081526002602052604090205482111561143357600080fd5b6001600160a01b038416600090815260036020908152604080832033845290915290205482111561146357600080fd5b6001600160a01b03841660009081526002602052604090205461148c908363ffffffff6117c216565b6001600160a01b0380861660009081526002602052604080822093909355908516815220546114c1908363ffffffff6117d416565b6001600160a01b038085166000908152600260209081526040808320949094559187168152600382528281203382529091522054611505908363ffffffff6117c216565b6001600160a01b0380861660008181526003602090815260408083203384529091529081902093909355915190851691907fddf252ad1be2c89b69c2b068fc378daa952ba7f163c4a11628f55a4df523b3ef90611563908690611c65565b60405180910390a35060019392505050565b3360009081526003602090815260408083206001600160a01b0386168452909152812054808311156115ca573360009081526003602090815260408083206001600160a01b03881684529091528120556115ff565b6115da818463ffffffff6117c216565b3360009081526003602090815260408083206001600160a01b03891684529091529020555b3360008181526003602090815260408083206001600160a01b0389168085529252918290205491519092917f8c5be1e5ebec7d5bd14f71427d1e84f3dd0314c0f7b2291e5b200ac8c7c3b925916115639190611c65565b6000604436101561166657600080fd5b6001600160a01b03831661167957600080fd5b3360009081526002602052604090205482111561169557600080fd5b336000908152600260205260409020546116b5908363ffffffff6117c216565b33600090815260026020526040808220929092556001600160a01b038516815220546116e7908363ffffffff6117d416565b6001600160a01b0384166000818152600260205260409081902092909255905133907fddf252ad1be2c89b69c2b068fc378daa952ba7f163c4a11628f55a4df523b3ef906113e8908690611c65565b3360009081526003602090815260408083206001600160a01b038616845290915281205461176a908363ffffffff6117d416565b3360008181526003602090815260408083206001600160a01b038916808552925291829020849055905190927f8c5be1e5ebec7d5bd14f71427d1e84f3dd0314c0f7b2291e5b200ac8c7c3b925916113e89190611c65565b6000828211156117ce57fe5b50900390565b60008282018381101561060257fe5b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f1061182457805160ff1916838001178555611851565b82800160010185558215611851579182015b82811115611851578251825591602001919060010190611836565b5061185d929150611861565b5090565b6107f591905b8082111561185d5760008155600101611867565b60006106028235611cf4565b600082601f83011261189857600080fd5b81356118ab6118a682611ca8565b611c81565b915080825260208301602083018583830111156118c757600080fd5b6118d2838284611d16565b50505092915050565b600061060282356107f5565b60006106028235611d10565b60006020828403121561190557600080fd5b600061082f848461187b565b6000806040838503121561192457600080fd5b6000611930858561187b565b92505060206119418582860161187b565b9150509250929050565b60008060006060848603121561196057600080fd5b600061196c868661187b565b935050602061197d8682870161187b565b925050604061198e868287016118db565b9150509250925092565b600080604083850312156119ab57600080fd5b60006119b7858561187b565b9250506020611941858286016118db565b6000602082840312156119da57600080fd5b813567ffffffffffffffff8111156119f157600080fd5b61082f84828501611887565b60008060408385031215611a1057600080fd5b823567ffffffffffffffff811115611a2757600080fd5b611a3385828601611887565b925050602083013567ffffffffffffffff811115611a5057600080fd5b61194185828601611887565b600060208284031215611a6e57600080fd5b600061082f84846118e7565b60006106028383611b08565b611a8f81611cf4565b82525050565b6000611aa082611ce2565b611aaa8185611ce6565b935083602082028501611abc85611cd0565b60005b84811015611af3578383038852611ad7838351611a7a565b9250611ae282611cd0565b602098909801979150600101611abf565b50909695505050505050565b611a8f81611cff565b6000611b1382611ce2565b611b1d8185611ce6565b9350611b2d818560208601611d22565b611b3681611d4e565b9093019392505050565b6000611b4b82611ce2565b611b558185611cef565b9350611b65818560208601611d22565b9290920192915050565b600081546001811660008114611b8c5760018114611baf57611bee565b607f6002830416611b9d8187611cef565b60ff1984168152955085019250611bee565b60028204611bbd8187611cef565b9550611bc885611cd6565b60005b82811015611be757815488820152600190910190602001611bcb565b5050850192505b505092915050565b611a8f816107f5565b611a8f81611d10565b6000611c148284611b40565b9392505050565b6000611c148284611b6f565b602081016106058284611a86565b602080825281016106028184611a95565b602081016106058284611aff565b602080825281016106028184611b08565b602081016106058284611bf6565b602081016106058284611bff565b60405181810167ffffffffffffffff81118282101715611ca057600080fd5b604052919050565b600067ffffffffffffffff821115611cbf57600080fd5b506020601f91909101601f19160190565b60200190565b60009081526020902090565b5190565b90815260200190565b919050565b600061060582611d04565b151590565b6001600160a01b031690565b60ff1690565b82818337506000910152565b60005b83811015611d3d578181015183820152602001611d25565b838111156105d75750506000910152565b601f01601f19169056fea265627a7a7230582011a6dbadf7938df6163526c76418aab59c4c7bb9adf60764e07956297d94dc0a6c6578706572696d656e74616cf50037";

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

    public static final String FUNC_TRANSFER = "transfer";

    public static final String FUNC_NOTICE = "notice";

    public static final String FUNC_GETNEWS = "getNews";

    public static final String FUNC_INCREASEAPPROVAL = "increaseApproval";

    public static final String FUNC_ALLOWANCE = "allowance";

    public static final String FUNC_GETLASTNEWS = "getLastNews";

    public static final String FUNC_TRANSFEROWNERSHIP = "transferOwnership";

    public static final String FUNC_GETLASTNOTICE = "getLastNotice";

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
    protected StandContract_sol_PubToken(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected StandContract_sol_PubToken(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected StandContract_sol_PubToken(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected StandContract_sol_PubToken(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
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

    public RemoteCall<TransactionReceipt> transferOwnership(String _newOwner) {
        final Function function = new Function(
                FUNC_TRANSFEROWNERSHIP,
                Arrays.<Type>asList(new Address(_newOwner)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<List> getLastNotice(BigInteger _size) {
        final Function function = new Function(FUNC_GETLASTNOTICE,
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
    public static StandContract_sol_PubToken load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new StandContract_sol_PubToken(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static StandContract_sol_PubToken load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new StandContract_sol_PubToken(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static StandContract_sol_PubToken load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new StandContract_sol_PubToken(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static StandContract_sol_PubToken load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new StandContract_sol_PubToken(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<StandContract_sol_PubToken> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider, BigInteger _initialAmount, String _tokenName, BigInteger _decimalUnits, String _tokenSymbol) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new Uint256(_initialAmount),
                new Utf8String(_tokenName),
                new Uint8(_decimalUnits),
                new Utf8String(_tokenSymbol)));
        return deployRemoteCall(StandContract_sol_PubToken.class, web3j, credentials, contractGasProvider, BINARY, encodedConstructor);
    }

    public static RemoteCall<StandContract_sol_PubToken> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider, BigInteger _initialAmount, String _tokenName, BigInteger _decimalUnits, String _tokenSymbol) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new Uint256(_initialAmount),
                new Utf8String(_tokenName),
                new Uint8(_decimalUnits),
                new Utf8String(_tokenSymbol)));
        return deployRemoteCall(StandContract_sol_PubToken.class, web3j, transactionManager, contractGasProvider, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<StandContract_sol_PubToken> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, BigInteger _initialAmount, String _tokenName, BigInteger _decimalUnits, String _tokenSymbol) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new Uint256(_initialAmount),
                new Utf8String(_tokenName),
                new Uint8(_decimalUnits),
                new Utf8String(_tokenSymbol)));
        return deployRemoteCall(StandContract_sol_PubToken.class, web3j, credentials, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<StandContract_sol_PubToken> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, BigInteger _initialAmount, String _tokenName, BigInteger _decimalUnits, String _tokenSymbol) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new Uint256(_initialAmount),
                new Utf8String(_tokenName),
                new Uint8(_decimalUnits),
                new Utf8String(_tokenSymbol)));
        return deployRemoteCall(StandContract_sol_PubToken.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, encodedConstructor);
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

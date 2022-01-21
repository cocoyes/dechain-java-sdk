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
    private static final String BINARY = "60048054600160a01b60ff021916905560c0604052600360808190527f322e30000000000000000000000000000000000000000000000000000000000060a0908152620000509160089190620000f6565b503480156200005e57600080fd5b50604051620023af380380620023af83398101806040526200008491908101906200021a565b600480546001600160a01b03191633908117909155600185905560009081526002602090815260409091208590558351620000c69160059190860190620000f6565b506006805460ff191660ff84161790558051620000eb906007906020840190620000f6565b50505050506200033c565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f106200013957805160ff191683800117855562000169565b8280016001018555821562000169579182015b82811115620001695782518255916020019190600101906200014c565b50620001779291506200017b565b5090565b6200019891905b8082111562000177576000815560010162000182565b90565b600082601f830112620001ad57600080fd5b8151620001c4620001be82620002db565b620002b4565b91508082526020830160208301858383011115620001e157600080fd5b620001ee83828462000309565b50505092915050565b600062000205825162000198565b9392505050565b600062000205825162000303565b600080600080608085870312156200023157600080fd5b60006200023f8787620001f7565b94505060208501516001600160401b038111156200025c57600080fd5b6200026a878288016200019b565b93505060406200027d878288016200020c565b92505060608501516001600160401b038111156200029a57600080fd5b620002a8878288016200019b565b91505092959194509250565b6040518181016001600160401b0381118282101715620002d357600080fd5b604052919050565b60006001600160401b03821115620002f257600080fd5b506020601f91909101601f19160190565b60ff1690565b60005b83811015620003265781810151838201526020016200030c565b8381111562000336576000848401525b50505050565b612063806200034c6000396000f3fe608060405234801561001057600080fd5b50600436106102485760003560e01c806365ba4e331161013b578063c288b08c116100b8578063e61175571161007c578063e6117557146104b0578063e6a4fbf6146104c3578063f2fde38b146104d6578063fb558db1146104e9578063fbc4efb2146104fc57610248565b8063c288b08c1461045c578063c3bd890414610464578063d38dc21814610477578063d73dd6231461048a578063dd62ed3e1461049d57610248565b80638da5cb5b116100ff5780638da5cb5b146104065780639114ad3c1461041b57806395d89b411461042e578063a9059cbb14610436578063b5e64ceb1461044957610248565b806365ba4e33146103bd57806366188463146103c557806370a08231146103d857806379798896146103eb5780638456cb59146103fe57610248565b8063313ce567116101c95780634ddb9c2a1161018d5780634ddb9c2a1461037457806354fd4d50146103875780635c6581651461038f5780635c975abb146103a257806364cceaef146103aa57610248565b8063313ce5671461032757806336f281a91461033c5780633ef791da146103445780633f4ba83a14610357578063410d59cc1461035f57610248565b806312e63d711161021057806312e63d71146102c657806318160ddd146102d957806319e0d8ee146102ee57806323b872dd1461030157806327e235e31461031457610248565b806306fdde031461024d57806308cab3ef1461026b578063095ea7b3146102805780630ab1bd70146102a057806310009b01146102b3575b600080fd5b610255610504565b6040516102629190611f15565b60405180910390f35b61027e610279366004611c85565b610592565b005b61029361028e366004611c20565b610661565b6040516102629190611f07565b6102556102ae366004611c50565b61068e565b61027e6102c1366004611c85565b610702565b6102556102d4366004611c50565b6107c7565b6102e1610874565b6040516102629190611f36565b6102556102fc366004611c50565b61087b565b61029361030f366004611bd3565b61088d565b6102e1610322366004611b7b565b6108ba565b61032f6108cc565b6040516102629190611f44565b6102556108d5565b610293610352366004611c50565b610930565b61027e610950565b6103676109b8565b6040516102629190611ef6565b61027e610382366004611c85565b610a90565b610255610b45565b6102e161039d366004611b99565b610ba0565b610293610bbd565b6102556103b8366004611c50565b610bcd565b610367610c41565b6102936103d3366004611c20565b610d10565b6102e16103e6366004611b7b565b610d34565b6102556103f9366004611b7b565b610d4f565b61027e610dfa565b61040e610e69565b6040516102629190611ee8565b610367610429366004611ce4565b610e78565b610255610fd3565b610293610444366004611c20565b61102e565b610255610457366004611c50565b611052565b6102556110c6565b610255610472366004611c50565b611166565b61027e610485366004611c50565b611178565b610293610498366004611c20565b611198565b6102e16104ab366004611b99565b6111bc565b6103676104be366004611ce4565b6111e7565b61027e6104d1366004611c50565b611337565b61027e6104e4366004611b7b565b611361565b6103676104f7366004611ce4565b6113e7565b610367611537565b6005805460408051602060026001851615610100026000190190941693909304601f8101849004840282018401909252818152929183018282801561058a5780601f1061055f5761010080835404028352916020019161058a565b820191906000526020600020905b81548152906001019060200180831161056d57829003601f168201915b505050505081565b6004546001600160a01b031633146105a957600080fd5b6000600e836040516105bb9190611ec9565b90815260405190819003602001902054600260001961010060018416150201909116041161065d5780600e836040516105f49190611ec9565b90815260200160405180910390209080519060200190610615929190611a6b565b50600f805460018101808355600092909252835161065a917f8d1108e10bcb7c27dddfc02ed9d693a074039d026cf4ea4240b40f7d581ac80201906020860190611a6b565b50505b5050565b600454600090600160a01b900460ff161561067b57600080fd5b6106858383611606565b90505b92915050565b8051602081830181018051600c8252928201938201939093209190925280546040805160026001841615610100026000190190931692909204601f8101859004850283018501909152808252909283018282801561058a5780601f1061055f5761010080835404028352916020019161058a565b6004546001600160a01b0316331461071957600080fd5b600c826040516107299190611ec9565b908152604051908190036020019020546002600019610100600184161502019091160461065d5780600c836040516107619190611ec9565b90815260200160405180910390209080519060200190610782929190611a6b565b50600d805460018101808355600092909252835161065a917fd7b6990105719101dabeb77144f2a3385c8033acd3af97e9423a695e81ad1eb501906020860190611a6b565b60606009826040516107d99190611ec9565b9081526040805160209281900383018120805460026001821615610100026000190190911604601f810185900485028301850190935282825290929091908301828280156108685780601f1061083d57610100808354040283529160200191610868565b820191906000526020600020905b81548152906001019060200180831161084b57829003601f168201915b50505050509050919050565b6001545b90565b6060600e826040516107d99190611ec9565b600454600090600160a01b900460ff16156108a757600080fd5b6108b2848484611681565b949350505050565b60026020526000908152604090205481565b60065460ff1681565b6010805460408051602060026001851615610100026000190190941693909304601f8101849004840282018401909252818152929183018282801561058a5780601f1061055f5761010080835404028352916020019161058a565b8051602081830181018051600b8252928201919093012091525460ff1681565b6004546001600160a01b0316331461096757600080fd5b600454600160a01b900460ff1661097d57600080fd5b60048054600160a01b60ff02191690556040517f7805862f689e2f13df9f062ff482ad3ad112aca9e0847911ed832e158c525b3390600090a1565b6060600a805480602002602001604051908101604052809291908181526020016000905b82821015610a875760008481526020908190208301805460408051601f6002600019610100600187161502019094169390930492830185900485028101850190915281815292830182828015610a735780601f10610a4857610100808354040283529160200191610a73565b820191906000526020600020905b815481529060010190602001808311610a5657829003601f168201915b5050505050815260200190600101906109dc565b50505050905090565b6004546001600160a01b03163314610aa757600080fd5b80600983604051610ab89190611ec9565b90815260200160405180910390209080519060200190610ad9929190611a6b565b50600b82604051610aea9190611ec9565b9081526040519081900360200190205460ff1661065d57600a805460018101808355600092909252835161065a917fc65a7bb8d6351c1cf70c95a316cc6a92839c986682d98bc35f958f4883f9d2a801906020860190611a6b565b6008805460408051602060026001851615610100026000190190941693909304601f8101849004840282018401909252818152929183018282801561058a5780601f1061055f5761010080835404028352916020019161058a565b600360209081526000928352604080842090915290825290205481565b600454600160a01b900460ff1681565b805160208183018101805160098252928201938201939093209190925280546040805160026001841615610100026000190190931692909204601f8101859004850283018501909152808252909283018282801561058a5780601f1061055f5761010080835404028352916020019161058a565b6060600d805480602002602001604051908101604052809291908181526020016000905b82821015610a875760008481526020908190208301805460408051601f6002600019610100600187161502019094169390930492830185900485028101850190915281815292830182828015610cfc5780601f10610cd157610100808354040283529160200191610cfc565b820191906000526020600020905b815481529060010190602001808311610cdf57829003601f168201915b505050505081526020019060010190610c65565b600454600090600160a01b900460ff1615610d2a57600080fd5b61068583836117fd565b6001600160a01b031660009081526002602052604090205490565b6004546060906001600160a01b03163314610d8857604051600160e51b62461bcd028152600401610d7f90611f26565b60405180910390fd5b6001600160a01b03821660009081526011602090815260409182902080548351601f6002600019610100600186161502019093169290920491820184900484028101840190945280845290918301828280156108685780601f1061083d57610100808354040283529160200191610868565b6004546001600160a01b03163314610e1157600080fd5b600454600160a01b900460ff1615610e2857600080fd5b60048054600160a01b60ff021916600160a01b1790556040517f6985a02210a168e66602d3235cb6db0e70f92b3ba4d376a33c0f3d9434bff62590600090a1565b6004546001600160a01b031681565b600a5460609060009060ff841610610e935750600a54610e99565b5060ff82165b606081604051908082528060200260200182016040528015610ecf57816020015b6060815260200190600190039081610eba5790505b50600a549091508281039060005b82821115610fc8576009600a6001840381548110610ef757fe5b90600052602060002001604051610f0e9190611edc565b9081526040805160209281900383018120805460026001821615610100026000190190911604601f81018590048502830185019093528282529092909190830182828015610f9d5780601f10610f7257610100808354040283529160200191610f9d565b820191906000526020600020905b815481529060010190602001808311610f8057829003601f168201915b5050505050848281518110610fae57fe5b602090810291909101015260001990910190600101610edd565b509195945050505050565b6007805460408051602060026001851615610100026000190190941693909304601f8101849004840282018401909252818152929183018282801561058a5780601f1061055f5761010080835404028352916020019161058a565b600454600090600160a01b900460ff161561104857600080fd5b61068583836118de565b8051602081830181018051600e8252928201938201939093209190925280546040805160026001841615610100026000190190931692909204601f8101859004850283018501909152808252909283018282801561058a5780601f1061055f5761010080835404028352916020019161058a565b3360009081526011602090815260409182902080548351601f600260001961010060018616150201909316929092049182018490048402810184019094528084526060939283018282801561115c5780601f106111315761010080835404028352916020019161115c565b820191906000526020600020905b81548152906001019060200180831161113f57829003601f168201915b5050505050905090565b6060600c826040516107d99190611ec9565b336000908152601160209081526040909120825161065d92840190611a6b565b600454600090600160a01b900460ff16156111b257600080fd5b61068583836119be565b6001600160a01b03918216600090815260036020908152604080832093909416825291909152205490565b600d5460609060009060ff8416106112025750600d54611208565b5060ff82165b60608160405190808252806020026020018201604052801561123e57816020015b60608152602001906001900390816112295790505b50600d549091508281039060005b82821115610fc857600c600d600184038154811061126657fe5b9060005260206000200160405161127d9190611edc565b9081526040805160209281900383018120805460026001821615610100026000190190911604601f8101859004850283018501909352828252909290919083018282801561130c5780601f106112e15761010080835404028352916020019161130c565b820191906000526020600020905b8154815290600101906020018083116112ef57829003601f168201915b505050505084828151811061131d57fe5b60209081029190910101526000199091019060010161124c565b6004546001600160a01b0316331461134e57600080fd5b805161065d906010906020840190611a6b565b6004546001600160a01b0316331461137857600080fd5b6001600160a01b03811661138b57600080fd5b6004546040516001600160a01b038084169216907f8be0079c531659141344cd1fd0a4f28419497f9722a3daafe3b4186f6b6457e090600090a3600480546001600160a01b0319166001600160a01b0392909216919091179055565b600f5460609060009060ff8416106114025750600f54611408565b5060ff82165b60608160405190808252806020026020018201604052801561143e57816020015b60608152602001906001900390816114295790505b50600f549091508281039060005b82821115610fc857600e600f600184038154811061146657fe5b9060005260206000200160405161147d9190611edc565b9081526040805160209281900383018120805460026001821615610100026000190190911604601f8101859004850283018501909352828252909290919083018282801561150c5780601f106114e15761010080835404028352916020019161150c565b820191906000526020600020905b8154815290600101906020018083116114ef57829003601f168201915b505050505084828151811061151d57fe5b60209081029190910101526000199091019060010161144c565b6060600f805480602002602001604051908101604052809291908181526020016000905b82821015610a875760008481526020908190208301805460408051601f60026000196101006001871615020190941693909304928301859004850281018501909152818152928301828280156115f25780601f106115c7576101008083540402835291602001916115f2565b820191906000526020600020905b8154815290600101906020018083116115d557829003601f168201915b50505050508152602001906001019061155b565b6000604436101561161657600080fd5b3360008181526003602090815260408083206001600160a01b03881680855292529182902085905590519091907f8c5be1e5ebec7d5bd14f71427d1e84f3dd0314c0f7b2291e5b200ac8c7c3b92590611670908690611f36565b60405180910390a350600192915050565b60006001600160a01b03831661169657600080fd5b6001600160a01b0384166000908152600260205260409020548211156116bb57600080fd5b6001600160a01b03841660009081526003602090815260408083203384529091529020548211156116eb57600080fd5b6001600160a01b038416600090815260026020526040902054611714908363ffffffff611a4a16565b6001600160a01b038086166000908152600260205260408082209390935590851681522054611749908363ffffffff611a5c16565b6001600160a01b03808516600090815260026020908152604080832094909455918716815260038252828120338252909152205461178d908363ffffffff611a4a16565b6001600160a01b0380861660008181526003602090815260408083203384529091529081902093909355915190851691907fddf252ad1be2c89b69c2b068fc378daa952ba7f163c4a11628f55a4df523b3ef906117eb908690611f36565b60405180910390a35060019392505050565b3360009081526003602090815260408083206001600160a01b038616845290915281205480831115611852573360009081526003602090815260408083206001600160a01b0388168452909152812055611887565b611862818463ffffffff611a4a16565b3360009081526003602090815260408083206001600160a01b03891684529091529020555b3360008181526003602090815260408083206001600160a01b0389168085529252918290205491519092917f8c5be1e5ebec7d5bd14f71427d1e84f3dd0314c0f7b2291e5b200ac8c7c3b925916117eb9190611f36565b600060443610156118ee57600080fd5b6001600160a01b03831661190157600080fd5b3360009081526002602052604090205482111561191d57600080fd5b3360009081526002602052604090205461193d908363ffffffff611a4a16565b33600090815260026020526040808220929092556001600160a01b0385168152205461196f908363ffffffff611a5c16565b6001600160a01b0384166000818152600260205260409081902092909255905133907fddf252ad1be2c89b69c2b068fc378daa952ba7f163c4a11628f55a4df523b3ef90611670908690611f36565b3360009081526003602090815260408083206001600160a01b03861684529091528120546119f2908363ffffffff611a5c16565b3360008181526003602090815260408083206001600160a01b038916808552925291829020849055905190927f8c5be1e5ebec7d5bd14f71427d1e84f3dd0314c0f7b2291e5b200ac8c7c3b925916116709190611f36565b600082821115611a5657fe5b50900390565b60008282018381101561068557fe5b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f10611aac57805160ff1916838001178555611ad9565b82800160010185558215611ad9579182015b82811115611ad9578251825591602001919060010190611abe565b50611ae5929150611ae9565b5090565b61087891905b80821115611ae55760008155600101611aef565b60006106858235611fc5565b600082601f830112611b2057600080fd5b8135611b33611b2e82611f79565b611f52565b91508082526020830160208301858383011115611b4f57600080fd5b611b5a838284611fe7565b50505092915050565b60006106858235610878565b60006106858235611fe1565b600060208284031215611b8d57600080fd5b60006108b28484611b03565b60008060408385031215611bac57600080fd5b6000611bb88585611b03565b9250506020611bc985828601611b03565b9150509250929050565b600080600060608486031215611be857600080fd5b6000611bf48686611b03565b9350506020611c0586828701611b03565b9250506040611c1686828701611b63565b9150509250925092565b60008060408385031215611c3357600080fd5b6000611c3f8585611b03565b9250506020611bc985828601611b63565b600060208284031215611c6257600080fd5b813567ffffffffffffffff811115611c7957600080fd5b6108b284828501611b0f565b60008060408385031215611c9857600080fd5b823567ffffffffffffffff811115611caf57600080fd5b611cbb85828601611b0f565b925050602083013567ffffffffffffffff811115611cd857600080fd5b611bc985828601611b0f565b600060208284031215611cf657600080fd5b60006108b28484611b6f565b60006106858383611d90565b611d1781611fc5565b82525050565b6000611d2882611fb3565b611d328185611fb7565b935083602082028501611d4485611fa1565b60005b84811015611d7b578383038852611d5f838351611d02565b9250611d6a82611fa1565b602098909801979150600101611d47565b50909695505050505050565b611d1781611fd0565b6000611d9b82611fb3565b611da58185611fb7565b9350611db5818560208601611ff3565b611dbe8161201f565b9093019392505050565b6000611dd382611fb3565b611ddd8185611fc0565b9350611ded818560208601611ff3565b9290920192915050565b600081546001811660008114611e145760018114611e3757611e76565b607f6002830416611e258187611fc0565b60ff1984168152955085019250611e76565b60028204611e458187611fc0565b9550611e5085611fa7565b60005b82811015611e6f57815488820152600190910190602001611e53565b5050850192505b505092915050565b6000611e8b600f83611fb7565b7f594f552043414e204e4f54205345450000000000000000000000000000000000815260200192915050565b611d1781610878565b611d1781611fe1565b6000611ed58284611dc8565b9392505050565b6000611ed58284611df7565b602081016106888284611d0e565b602080825281016106858184611d1d565b602081016106888284611d87565b602080825281016106858184611d90565b6020808252810161068881611e7e565b602081016106888284611eb7565b602081016106888284611ec0565b60405181810167ffffffffffffffff81118282101715611f7157600080fd5b604052919050565b600067ffffffffffffffff821115611f9057600080fd5b506020601f91909101601f19160190565b60200190565b60009081526020902090565b5190565b90815260200190565b919050565b600061068882611fd5565b151590565b6001600160a01b031690565b60ff1690565b82818337506000910152565b60005b8381101561200e578181015183820152602001611ff6565b8381111561065a5750506000910152565b601f01601f19169056fea265627a7a72305820be7a530c15bba0b1021aec31df4bc4b76741c8a67966fce70cb1e61113cc63b76c6578706572696d656e74616cf50037";

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

    public static final String FUNC_GETBANKITEM = "getBankItem";

    public static final String FUNC_PAUSE = "pause";

    public static final String FUNC_OWNER = "owner";

    public static final String FUNC_GETLASTREPORT = "getLastReport";

    public static final String FUNC_SYMBOL = "symbol";

    public static final String FUNC_TRANSFER = "transfer";

    public static final String FUNC_NOTICE = "notice";

    public static final String FUNC_GETBANKITEMMY = "getBankItemMy";

    public static final String FUNC_GETNEWS = "getNews";

    public static final String FUNC_SETBANKITEM = "setBankItem";

    public static final String FUNC_INCREASEAPPROVAL = "increaseApproval";

    public static final String FUNC_ALLOWANCE = "allowance";

    public static final String FUNC_GETLASTNEWS = "getLastNews";

    public static final String FUNC_UPDATECOMPANYINFO = "updateCompanyInfo";

    public static final String FUNC_TRANSFEROWNERSHIP = "transferOwnership";

    public static final String FUNC_GETLASTNOTICE = "getLastNotice";

    public static final String FUNC_GETNOTICEITEMS = "getNoticeItems";

    public static final Event CREATEREPORTEVENT_EVENT = new Event("createReportEvent",
            Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}));
    ;

    public static final Event CREATENEWSEVENT_EVENT = new Event("createNewsEvent",
            Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}));
    ;

    public static final Event CREATENOTICEEVENT_EVENT = new Event("createNoticeEvent",
            Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}));
    ;

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
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(item),
                        new org.web3j.abi.datatypes.Utf8String(json)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> approve(String _spender, BigInteger _value) {
        final Function function = new Function(
                FUNC_APPROVE,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(_spender),
                        new org.web3j.abi.datatypes.generated.Uint256(_value)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<String> news(String param0) {
        final Function function = new Function(FUNC_NEWS,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(param0)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<TransactionReceipt> createNews(String item, String json) {
        final Function function = new Function(
                FUNC_CREATENEWS,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(item),
                        new org.web3j.abi.datatypes.Utf8String(json)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<String> getReport(String item) {
        final Function function = new Function(FUNC_GETREPORT,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(item)),
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
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(item)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<TransactionReceipt> transferFrom(String _from, String _to, BigInteger _value) {
        final Function function = new Function(
                FUNC_TRANSFERFROM,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(_from),
                        new org.web3j.abi.datatypes.Address(_to),
                        new org.web3j.abi.datatypes.generated.Uint256(_value)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<BigInteger> balances(String param0) {
        final Function function = new Function(FUNC_BALANCES,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(param0)),
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
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(param0)),
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
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(item),
                        new org.web3j.abi.datatypes.Utf8String(json)),
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
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(param0),
                        new org.web3j.abi.datatypes.Address(param1)),
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
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(param0)),
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
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(_spender),
                        new org.web3j.abi.datatypes.generated.Uint256(_subtractedValue)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<BigInteger> balanceOf(String _owner) {
        final Function function = new Function(FUNC_BALANCEOF,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(_owner)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<String> getBankItem(String _user) {
        final Function function = new Function(FUNC_GETBANKITEM,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(_user)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
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
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint8(_size)),
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
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(_to),
                        new org.web3j.abi.datatypes.generated.Uint256(_value)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<String> notice(String param0) {
        final Function function = new Function(FUNC_NOTICE,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(param0)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<String> getBankItemMy() {
        final Function function = new Function(FUNC_GETBANKITEMMY,
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<String> getNews(String item) {
        final Function function = new Function(FUNC_GETNEWS,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(item)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<TransactionReceipt> setBankItem(String json) {
        final Function function = new Function(
                FUNC_SETBANKITEM,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(json)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> increaseApproval(String _spender, BigInteger _addedValue) {
        final Function function = new Function(
                FUNC_INCREASEAPPROVAL,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(_spender),
                        new org.web3j.abi.datatypes.generated.Uint256(_addedValue)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<BigInteger> allowance(String _owner, String _spender) {
        final Function function = new Function(FUNC_ALLOWANCE,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(_owner),
                        new org.web3j.abi.datatypes.Address(_spender)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<List> getLastNews(BigInteger _size) {
        final Function function = new Function(FUNC_GETLASTNEWS,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint8(_size)),
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
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_info)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> transferOwnership(String _newOwner) {
        final Function function = new Function(
                FUNC_TRANSFEROWNERSHIP,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(_newOwner)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<List> getLastNotice(BigInteger _size) {
        final Function function = new Function(FUNC_GETLASTNOTICE,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint8(_size)),
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

    public List<CreateReportEventEventResponse> getCreateReportEventEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(CREATEREPORTEVENT_EVENT, transactionReceipt);
        ArrayList<CreateReportEventEventResponse> responses = new ArrayList<CreateReportEventEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            CreateReportEventEventResponse typedResponse = new CreateReportEventEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse._item = (String) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse._json = (String) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<CreateReportEventEventResponse> createReportEventEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new io.reactivex.functions.Function<Log, CreateReportEventEventResponse>() {
            @Override
            public CreateReportEventEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(CREATEREPORTEVENT_EVENT, log);
                CreateReportEventEventResponse typedResponse = new CreateReportEventEventResponse();
                typedResponse.log = log;
                typedResponse._item = (String) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse._json = (String) eventValues.getNonIndexedValues().get(1).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<CreateReportEventEventResponse> createReportEventEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(CREATEREPORTEVENT_EVENT));
        return createReportEventEventFlowable(filter);
    }

    public List<CreateNewsEventEventResponse> getCreateNewsEventEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(CREATENEWSEVENT_EVENT, transactionReceipt);
        ArrayList<CreateNewsEventEventResponse> responses = new ArrayList<CreateNewsEventEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            CreateNewsEventEventResponse typedResponse = new CreateNewsEventEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse._item = (String) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse._json = (String) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<CreateNewsEventEventResponse> createNewsEventEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new io.reactivex.functions.Function<Log, CreateNewsEventEventResponse>() {
            @Override
            public CreateNewsEventEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(CREATENEWSEVENT_EVENT, log);
                CreateNewsEventEventResponse typedResponse = new CreateNewsEventEventResponse();
                typedResponse.log = log;
                typedResponse._item = (String) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse._json = (String) eventValues.getNonIndexedValues().get(1).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<CreateNewsEventEventResponse> createNewsEventEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(CREATENEWSEVENT_EVENT));
        return createNewsEventEventFlowable(filter);
    }

    public List<CreateNoticeEventEventResponse> getCreateNoticeEventEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(CREATENOTICEEVENT_EVENT, transactionReceipt);
        ArrayList<CreateNoticeEventEventResponse> responses = new ArrayList<CreateNoticeEventEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            CreateNoticeEventEventResponse typedResponse = new CreateNoticeEventEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse._item = (String) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse._json = (String) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<CreateNoticeEventEventResponse> createNoticeEventEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new io.reactivex.functions.Function<Log, CreateNoticeEventEventResponse>() {
            @Override
            public CreateNoticeEventEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(CREATENOTICEEVENT_EVENT, log);
                CreateNoticeEventEventResponse typedResponse = new CreateNoticeEventEventResponse();
                typedResponse.log = log;
                typedResponse._item = (String) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse._json = (String) eventValues.getNonIndexedValues().get(1).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<CreateNoticeEventEventResponse> createNoticeEventEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(CREATENOTICEEVENT_EVENT));
        return createNoticeEventEventFlowable(filter);
    }

    public List<PauseEventResponse> getPauseEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(PAUSE_EVENT, transactionReceipt);
        ArrayList<PauseEventResponse> responses = new ArrayList<PauseEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
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
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(PAUSE_EVENT, log);
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
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(UNPAUSE_EVENT, transactionReceipt);
        ArrayList<UnpauseEventResponse> responses = new ArrayList<UnpauseEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
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
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(UNPAUSE_EVENT, log);
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
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(OWNERSHIPRENOUNCED_EVENT, transactionReceipt);
        ArrayList<OwnershipRenouncedEventResponse> responses = new ArrayList<OwnershipRenouncedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
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
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(OWNERSHIPRENOUNCED_EVENT, log);
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
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(OWNERSHIPTRANSFERRED_EVENT, transactionReceipt);
        ArrayList<OwnershipTransferredEventResponse> responses = new ArrayList<OwnershipTransferredEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
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
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(OWNERSHIPTRANSFERRED_EVENT, log);
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
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(APPROVAL_EVENT, transactionReceipt);
        ArrayList<ApprovalEventResponse> responses = new ArrayList<ApprovalEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
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
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(APPROVAL_EVENT, log);
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
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(TRANSFER_EVENT, transactionReceipt);
        ArrayList<TransferEventResponse> responses = new ArrayList<TransferEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
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
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(TRANSFER_EVENT, log);
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
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_initialAmount),
                new org.web3j.abi.datatypes.Utf8String(_tokenName),
                new org.web3j.abi.datatypes.generated.Uint8(_decimalUnits),
                new org.web3j.abi.datatypes.Utf8String(_tokenSymbol)));
        return deployRemoteCall(PubTokenSol.class, web3j, credentials, contractGasProvider, BINARY, encodedConstructor);
    }

    public static RemoteCall<PubTokenSol> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider, BigInteger _initialAmount, String _tokenName, BigInteger _decimalUnits, String _tokenSymbol) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_initialAmount),
                new org.web3j.abi.datatypes.Utf8String(_tokenName),
                new org.web3j.abi.datatypes.generated.Uint8(_decimalUnits),
                new org.web3j.abi.datatypes.Utf8String(_tokenSymbol)));
        return deployRemoteCall(PubTokenSol.class, web3j, transactionManager, contractGasProvider, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<PubTokenSol> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, BigInteger _initialAmount, String _tokenName, BigInteger _decimalUnits, String _tokenSymbol) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_initialAmount),
                new org.web3j.abi.datatypes.Utf8String(_tokenName),
                new org.web3j.abi.datatypes.generated.Uint8(_decimalUnits),
                new org.web3j.abi.datatypes.Utf8String(_tokenSymbol)));
        return deployRemoteCall(PubTokenSol.class, web3j, credentials, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<PubTokenSol> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, BigInteger _initialAmount, String _tokenName, BigInteger _decimalUnits, String _tokenSymbol) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_initialAmount),
                new org.web3j.abi.datatypes.Utf8String(_tokenName),
                new org.web3j.abi.datatypes.generated.Uint8(_decimalUnits),
                new org.web3j.abi.datatypes.Utf8String(_tokenSymbol)));
        return deployRemoteCall(PubTokenSol.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    public static class CreateReportEventEventResponse {
        public Log log;

        public String _item;

        public String _json;
    }

    public static class CreateNewsEventEventResponse {
        public Log log;

        public String _item;

        public String _json;
    }

    public static class CreateNoticeEventEventResponse {
        public Log log;

        public String _item;

        public String _json;
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

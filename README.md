## Platform introduction
The dechain SDK provides the underlying signature and communication functions for accessing the dechain blockchain to interact with the dechain blockchain and smart contract. The dechain SDK is also suitable for Ethereum system access. The dechain SDK provides comprehensive decentralized interaction. For better user experience, it is recommended…

## Build
    mvn install

## About Register Token
As long as you have a handling fee, any account can create a smart contract pass at any time. However, if it needs to be displayed on our app, you need to abide by our smart contract protocol. Therefore, we provide a PC desktop client. You can register the contract directly on it, so that your pass still belongs to you and complies with the provisions of our agreement. Our code is compatible with this contract.

## About Pay
The issuer of any pass can open a payment smart contract on our PC client. This smart contract only belongs to you. You need to pay the opening fee to add the information of the payment contract to our registration contract.

### Payment process(After you become a merchant)
1. Push Order(com.dechain.face.PayCenterFace.createOrder)
2. Call app wake-up interface ()
3. User submit pay
4. Get order status (com.dechain.face.PayCenterFace.findOrder)

### Payment Contract Source
If you are not using java development,the following code will be useful to you.
```
pragma solidity ^0.5.6;
pragma experimental ABIEncoderV2;
interface IERC20 {
    function transfer(address recipient, uint256 amount) external;
    function balanceOf(address account) external view returns (uint256);
    function transferFrom(address sender, address recipient, uint256 amount) external ;
    function decimals() external view returns (uint8);
    function name() external view returns (string memory);
    function symbol() external view returns (string memory);
}
contract PayCenter {
    address public owner;
    bool public openStatus=true; //Global control switch
    uint public total=0; //Total number of transactions
    uint public totalFee=0; //Total handling charge
    uint public baseKeepAmount; //Maintenance Margin
    uint public baseFee; //basic rate 10=10%
    uint public minFee=1*(10**17); //0.1
    uint public maxAmount=1*(10**28); //10 Hundred million
    Business[] public businessList; //Merchant list
    OrderInfo[] orderList;

    IERC20 coin; //currency
    //Get basic information
    function getPayBaseInfo() public view returns(
        address  _owner,
        bool _openStatus,
        uint _total,
        uint _totalFee,
        uint _baseKeepAmount,
        uint _baseFee
    ){
        return (
        owner,
        openStatus,
        total,
        totalFee,
        baseKeepAmount,
        baseFee
        );
    }
    /// @notice Constructor
    constructor (IERC20 _token,uint keepAmount,uint _baseFee) public {
        owner = msg.sender;
        coin=_token;
        baseKeepAmount=keepAmount;
        baseFee=_baseFee;
    }

    struct Business{
        address owner; //Merchant address
        string icon; //logo
        string name; // Merchant name
        uint status; // Status 0 normal, 1 abnormal
        uint balance;  //balance
        uint count; //Number of order transactions
        uint fee; //Separate handling rate
        uint totalFee; //Total expenditure handling fee
        uint keepBalance; //bond
        bool used;
    }

    struct OrderInfo {
        string oid;
        uint amount;
        uint status; //Status 0 has been placed, 1 has been paid, and canceling the order represents deletion
        address payUser; //drawee
        uint block; //Block number
        bool used;
        address business;
    }
    //Binding contract unit
    function decimals() public view returns (
        uint _amount
    ) {
        return (coin.decimals());
    }
    //Binding contract symbol
    function symbol() public view returns (
        string memory _symbol
    ) {
        return (coin.symbol());
    }
    //Binding contract name
    function name() public view returns (
        string memory _name
    ) {
        return (coin.name());
    }
    //event
    event createBusinessEvent(address businessAddress);
    event quitBusinessEvent(address businessAddress);
    event createOrderEvent(string oid);
    event orderPayedEvent(string oid,address business,uint amount);
    event orderRefundedEvent(string oid);
    event orderCanceledEvent(string oid);
    event withdrawFromBalanceEvent(address businessAddress);
    mapping(address => Business) private businessInfo;
    mapping(string => OrderInfo) private orderInfo;

    //To register a merchant, you need to approve before doing so
    function createBusiness( uint keepBalance,string memory  icon, string memory name) public returns (bool){
        require(keepBalance>=baseKeepAmount);
        require(openStatus);
        require(!businessInfo[msg.sender].used);

        businessInfo[msg.sender].used=true;
        businessInfo[msg.sender].icon=icon;
        businessInfo[msg.sender].name=name;
        businessInfo[msg.sender].owner=msg.sender;
        businessInfo[msg.sender].keepBalance=keepBalance;
        businessInfo[msg.sender].status=0;
        businessInfo[msg.sender].balance=0;
        businessInfo[msg.sender].count=0;
        businessInfo[msg.sender].fee=baseFee;
        businessInfo[msg.sender].totalFee=0;
        coin.transferFrom(msg.sender,address(this),keepBalance);
        businessList.push( businessInfo[msg.sender]);
        emit createBusinessEvent(msg.sender);
        return true;
    }
    //Cancel merchant
    function quitBusiness() public returns (bool){
        require(openStatus);
        require(businessInfo[msg.sender].used,"not a business");
        require(businessInfo[msg.sender].status==0,"forbidden operator");
        require(businessInfo[msg.sender].keepBalance>=baseKeepAmount,"keep balance not enough");
        require(businessInfo[msg.sender].balance==0,"balance must be zero");
        coin.transfer(msg.sender,businessInfo[msg.sender].keepBalance);
        delete businessInfo[msg.sender];
        emit quitBusinessEvent(msg.sender);
        return true;
    }
    // Merchant withdrawal
    function withdrawFromBalance( address rec) public returns (bool){
        require(openStatus);
        require(businessInfo[msg.sender].used,"not a business");
        require(businessInfo[msg.sender].status==0,"forbidden operator");
        require(businessInfo[msg.sender].keepBalance>=baseKeepAmount,"keep balance not enough");
        require(businessInfo[msg.sender].balance>minFee,"balance + fee is not enough");
        uint real=0;
        uint takeFee=0;
        if (businessInfo[msg.sender].fee==0){
            takeFee=businessInfo[msg.sender].balance * baseFee /100;
        }else {
            takeFee=businessInfo[msg.sender].balance * businessInfo[msg.sender].fee /100;
        }
        real=businessInfo[msg.sender].balance- takeFee ;

        businessInfo[msg.sender].balance=0;

        coin.transfer(rec,real);
        emit withdrawFromBalanceEvent(msg.sender);
        totalFee+=takeFee;
        return true;
    }
    //place an order
    function createOrder(string memory oid,uint amount) public returns (bool) {
        require(openStatus);
        require(!orderInfo[oid].used);
        require(businessInfo[msg.sender].used,"not a business");
        require(businessInfo[msg.sender].status==0,"forbidden operator");
        require(amount<=maxAmount,"more than max amount");
        require(businessInfo[msg.sender].keepBalance>=baseKeepAmount,"keep balance not enough");
        orderInfo[oid].used=true;
        orderInfo[oid].amount=amount;
        orderInfo[oid].status=0;
        orderInfo[oid].block=block.number;
        orderInfo[oid].business=msg.sender;

        orderList.push( orderInfo[oid]);
        emit createOrderEvent(oid);
        return true;
    }
    //Before payment, you need to approve
    function payOrder(string memory oid) public returns (bool){
        require(openStatus);
        require(orderInfo[oid].used,"order not found");

        coin.transferFrom(msg.sender,address(this),orderInfo[oid].amount);
        orderInfo[oid].status=1;
        orderInfo[oid].payUser=msg.sender;
        businessInfo[orderInfo[oid].business].count+=1;
        total++;
        businessInfo[orderInfo[oid].business].balance+=orderInfo[oid].amount;
        emit orderPayedEvent(oid,orderInfo[oid].business,orderInfo[oid].amount);
        return true;
    }
    //refund
    function refundsBalance(string memory oid) public returns (bool){
        require(openStatus);
        require(orderInfo[oid].used,"order not found");
        require(businessInfo[msg.sender].used,"not a business");
        require(businessInfo[msg.sender].status==0,"forbidden operator");
        require(orderInfo[oid].status==1,"order not payed");
        coin.transfer(orderInfo[oid].payUser,orderInfo[oid].amount);
        emit orderRefundedEvent(oid);
    }
    //cancel the order
    function cancelOrder(string memory oid) public returns (bool){
        require(openStatus);
        require(orderInfo[oid].used,"order not found");
        require(businessInfo[msg.sender].used,"not a business");
        require(orderInfo[oid].business==msg.sender,"forbidden operator not business");
        require(businessInfo[msg.sender].status==0,"forbidden operator");
        require(orderInfo[oid].status==0,"order status can not be canceled");
        delete orderInfo[oid];
        emit orderCanceledEvent(oid);
    }
    //Order inquiry
    function findOrder(string memory orderId) public view returns(
        string memory oid,
        uint amount,
        uint status,
        address payUser,
        uint _block,
        bool used,
        address business
    ){
        OrderInfo storage order=orderInfo[orderId];
        return (
        order.oid,
        order.amount,
        order.status,
        order.payUser,
        order.block,
        order.used,
        order.business
        );
    }
    function getAllBusiness() public view returns (
        address[] memory  _ows,
        string[] memory  _icons,
        string[] memory  _names,
        uint[] memory  _statuss,
        uint[] memory  _balances,
        uint[] memory  _counts,
        uint[] memory  _fees,
        uint[] memory  _totalFeeBalances

    ) {
        address[] memory  ows= new address[](businessList.length);
        string[] memory  icons= new string[](businessList.length);
        string[] memory  names= new string[](businessList.length);
        uint[] memory  statuss= new uint[](businessList.length);
        uint[] memory  balances= new uint[](businessList.length);
        uint[] memory  counts= new uint[](businessList.length);
        uint[] memory  fees= new uint[](businessList.length);
        uint[] memory  totalFeeBalances= new uint[](businessList.length);
        for (uint i=0;i<businessList.length;i++){
            ows[i]=businessList[i].owner;
            icons[i]=businessList[i].icon;
            names[i]=businessList[i].name;
            statuss[i]=businessList[i].status;
            balances[i]=businessList[i].balance;
            counts[i]=businessList[i].count;
            fees[i]=businessList[i].fee;
            totalFeeBalances[i]=businessList[i].totalFee;
        }
        return (
        ows,
        icons,
        names,
        statuss,
        balances,
        counts,
        fees,
        totalFeeBalances
        );
    }
    //Query merchants
    function findBusiness(address business) public view returns(
        address ow,
        string memory icon,
        string memory name,
        uint status,
        uint balance,
        uint count,
        uint fee,
        uint totalFeeBalance,
        uint keepBalance,
        bool used
    ){
        Business storage busi=businessInfo[business];
        return (
        busi.owner,
        busi.icon,
        busi.name,
        busi.status,
        busi.balance,
        busi.count,
        busi.fee,
        busi.totalFee,
        busi.keepBalance,
        busi.used
        );
    }
    //------------Administrator operation part
    //Operate the global switch
    function dealContractStatus() public{
        require(msg.sender == owner);
        if (openStatus){
            openStatus=false;
        }else{
            openStatus=true;
        }
    }
    //Withdraw funds to the designated account
    function withdrawBalance(address toAddress,uint amount) public{
        require(msg.sender == owner);
        coin.transfer(toAddress,amount);
    }
    //Change merchant status
    function changeBusiness(address businessAddress) public{
        require(msg.sender == owner);
        require(businessInfo[businessAddress].used);
        uint t_status;
        if (businessInfo[businessAddress].status==0){
            businessInfo[businessAddress].status=1;
            t_status=1;
        }else{
            businessInfo[businessAddress].status=0;
            t_status=0;
        }
        for (uint i=0;i<businessList.length;i++){
            if(businessList[i].owner==address(businessAddress)){
                businessList[i].status=t_status;
            }
        }
    }
    //Change the merchant withdrawal fee
    function changeBusinessFee(address business,uint fee) public{
        require(msg.sender == owner);
        require(businessInfo[business].used);
        businessInfo[business].fee=fee;
        for (uint i=0;i<businessList.length;i++){
            if(businessList[i].owner==address(business)){
                businessList[i].fee=fee;
            }
        }
    }
    //Change the minimum margin
    function changeKeepBalance(uint amount) public{
        require(msg.sender == owner);
        baseKeepAmount=amount;
    }
    //Change base rate
    function changeBaseFee(uint fee) public{
        require(msg.sender == owner);
        baseFee=fee;
    }
    //Add a merchant
    function addBusiness(string memory  icon, string memory name,address baddr) public returns (bool){
        require(msg.sender == owner);
        require(openStatus);
        require(!businessInfo[baddr].used);
        businessInfo[baddr].used=true;
        businessInfo[baddr].icon=icon;
        businessInfo[baddr].name=name;
        businessInfo[baddr].owner=baddr;
        businessInfo[baddr].keepBalance=0;
        businessInfo[baddr].status=0;
        businessInfo[baddr].balance=0;
        businessInfo[baddr].count=0;
        businessInfo[baddr].fee=baseFee;
        businessInfo[baddr].totalFee=0;
        businessList.push( businessInfo[baddr]);
        emit createBusinessEvent(baddr);
        return true;
    }
    function clearOrder() public returns (bool){
        require(msg.sender == owner);
        for (uint i=0;i<orderList.length;i++){
            OrderInfo storage order=orderList[i];
            uint l=orderInfo[order.oid].block;
            uint c=block.number;
            uint n=c-l;
            if (n > 10000 && order.status==0){
                delete orderInfo[order.oid];
            }
        }
    }
}

```



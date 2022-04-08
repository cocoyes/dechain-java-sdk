package com.dechain.utils;

import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.math.BigInteger;

public class QrCodeRouter {
    public static final String HEAD="#DE#EX#";

    public static final String GOODS="GOD#";

    public static final String ORDER="ORD#";

    public static final String CONTENT="CT#";




    /**
     * 构造 创建商品二维码
     * @param tickId 验证凭证
     * @param num 数量
     * @param price 价格
     * @param master 管理员合约
     * @param coin 积分合约
     * @return
     */
    public static String buildCreateGoodsCode(String tickId,int num, BigDecimal price,String master,String coin){
        StringBuffer sb=new StringBuffer(HEAD).append(GOODS).append(CONTENT);
        return sb.append(tickId).append(",").append(num).append(",").append(price).append(",").append(master).append(",").append(coin).toString();
    }

    /**
     * 构造 创建订单二维码内容
     * @param goods 我的商品合约
     * @param otherGoods 对方的商品合约
     * @param myOrderId 我的订单id
     * @param otherOrderId 对方的订单id
     * @param myNum 我商品交易支出的数量
     * @param otherNum 对方商品交易支持的数量
     * @param myPay 是否有我支付零钱
     * @param payAmount 支付的数量，保持18位
     * @return
     */
    public static String buildCreateOrderCode(String goods, String otherGoods, String myOrderId, String otherOrderId, int myNum, int otherNum, boolean myPay, BigInteger payAmount){
        StringBuffer sb=new StringBuffer(HEAD).append(ORDER).append(CONTENT);
        return sb.append(goods).append(",").append(otherGoods).append(",").append(myOrderId).append(",").append(otherOrderId)
                .append(",").append(myNum).append(",").append(otherNum).append(",").append(myPay).append(",").append(payAmount)
                .toString();
    }



    public static Object decode(String code){
        if(StringUtils.isEmpty(code)||!code.startsWith(HEAD)||!code.contains(CONTENT)||code.indexOf(CONTENT)==code.length()){
            return null;
        }
        if (code.contains(GOODS)){
            CreateGoods createGoods=new CreateGoods();
            String content=code.split(CONTENT)[1];
            String[] arr=content.split(",");
            if(arr.length!=5){
                return null;
            }
            createGoods.setTickId(arr[0]);
            createGoods.setNum(Integer.valueOf(arr[1]));
            createGoods.setPrice(new BigDecimal(arr[2]));
            createGoods.setMaster(arr[3]);
            createGoods.setCoin(arr[4]);
            return createGoods;
        }else if (code.contains(ORDER)){
            CreateOrder createOrder=new CreateOrder();
            String content=code.split(CONTENT)[1];
            String[] arr=content.split(",");
            if(arr.length!=8){
                return null;
            }
            createOrder.setGoods(arr[0]);
            createOrder.setOtherGoods(arr[1]);
            createOrder.setMyOrderId(arr[2]);
            createOrder.setOtherOrderId(arr[3]);
            createOrder.setMyNum(Integer.valueOf(arr[4]));
            createOrder.setOtherNum(Integer.valueOf(arr[5]));
            createOrder.setMyPay(Boolean.valueOf(arr[6]));
            createOrder.setPayAmount(new BigInteger(arr[7]));
            return createOrder;
        }
        return null;
    }

    public static class CreateGoods{
        private String tickId;
        private int num;
        private BigDecimal price;
        private String master;
        private String coin;

        public String getTickId() {
            return tickId;
        }

        public void setTickId(String tickId) {
            this.tickId = tickId;
        }

        public int getNum() {
            return num;
        }
        public void setNum(int num) {
            this.num = num;
        }
        public BigDecimal getPrice() {
            return price;
        }
        public void setPrice(BigDecimal price) {
            this.price = price;
        }
        public String getMaster() {
            return master;
        }
        public void setMaster(String master) {
            this.master = master;
        }
        public String getCoin() {
            return coin;
        }
        public void setCoin(String coin) {
            this.coin = coin;
        }

        @Override
        public String toString() {
            return "CreateGoods{" +
                    "tickId='" + tickId + '\'' +
                    ", num=" + num +
                    ", price=" + price +
                    ", master='" + master + '\'' +
                    ", coin='" + coin + '\'' +
                    '}';
        }
    }

    public static class CreateOrder{
        private String goods;
        private String otherGoods;
        private String myOrderId;
        private String otherOrderId;
        private int myNum;
        private int otherNum;
        private boolean myPay;
        private BigInteger payAmount;

        public String getGoods() {
            return goods;
        }

        public void setGoods(String goods) {
            this.goods = goods;
        }

        public String getOtherGoods() {
            return otherGoods;
        }

        public void setOtherGoods(String otherGoods) {
            this.otherGoods = otherGoods;
        }

        public String getMyOrderId() {
            return myOrderId;
        }

        public void setMyOrderId(String myOrderId) {
            this.myOrderId = myOrderId;
        }

        public String getOtherOrderId() {
            return otherOrderId;
        }

        public void setOtherOrderId(String otherOrderId) {
            this.otherOrderId = otherOrderId;
        }

        public int getMyNum() {
            return myNum;
        }

        public void setMyNum(int myNum) {
            this.myNum = myNum;
        }

        public int getOtherNum() {
            return otherNum;
        }

        public void setOtherNum(int otherNum) {
            this.otherNum = otherNum;
        }

        public boolean isMyPay() {
            return myPay;
        }

        public void setMyPay(boolean myPay) {
            this.myPay = myPay;
        }

        public BigInteger getPayAmount() {
            return payAmount;
        }

        public void setPayAmount(BigInteger payAmount) {
            this.payAmount = payAmount;
        }

        @Override
        public String toString() {
            return "CreateOrder{" +
                    "goods='" + goods + '\'' +
                    ", otherGoods='" + otherGoods + '\'' +
                    ", myOrderId='" + myOrderId + '\'' +
                    ", otherOrderId='" + otherOrderId + '\'' +
                    ", myNum=" + myNum +
                    ", otherNum=" + otherNum +
                    ", myPay=" + myPay +
                    ", payAmount=" + payAmount +
                    '}';
        }
    }




    public static void main(String[] args) {
        String goods=buildCreateGoodsCode("123456789",10,new BigDecimal(50),"0x122","0x123");
        System.out.println(goods);

        String order=buildCreateOrderCode("0x122","0x123","123","234",10,10,true,new BigInteger("20000"));
        System.out.println(order);

        Object g=decode(goods);
        Object o=decode(order);
        System.out.println("goods:="+g.toString());
        System.out.println("--------------");
        System.out.println("order:="+o.toString());

        System.out.println(g instanceof CreateGoods);
        System.out.println(o instanceof CreateOrder);

    }
}

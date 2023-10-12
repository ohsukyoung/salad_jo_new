
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.StringTokenizer;

import java.util.Date;

/*
 주문 ----------------------------------------------------------------
*/
class OrderValues implements Serializable{
    private static final long serialVersionUID = -9194797724974080745L;
    private int u_checkNumber;  // 인덱스넘버
    private String u_name;  // 제품
    private int u_Count;    // 수량
    private int u_calorie;  // 칼로리
    private int u_price;    // 금액

    OrderValues(String u_name, int u_Count, int u_calorie, int u_price) {
        this.u_name = u_name;
        this.u_Count = u_Count;
        this.u_calorie = u_calorie;
        this.u_price = u_price;
    }

    OrderValues(int u_checkNumber, String u_name, int u_Count, int u_calorie, int u_price) {
        this.u_checkNumber = u_checkNumber;
        this.u_name = u_name;
        this.u_Count = u_Count;
        this.u_calorie = u_calorie;
        this.u_price = u_price;
    }

    public OrderValues() {}

    public int getU_checkNumber() { return u_checkNumber; }
    public void setU_checkNumber(int u_checkNumber) { this.u_checkNumber = u_checkNumber; }
    public String getName() { return u_name; }
    public void setU_name(String u_name) { this.u_name = u_name; }
    public int getCount() { return u_Count; }
    public void setU_Count(int u_Count) { this.u_Count = u_Count; }
    public int getCalorie() { return u_calorie; }
    public void setU_calorie(int u_calorie) { this.u_calorie = u_calorie; }
    public int getPrice() { return u_price; }
    public void setU_price(int u_price) { this.u_price = u_price; }

    @Override
    public String toString() {
        return "선택내용" + "{" + "상품명:" + u_name +
                ", 선택개수: " + u_Count +
                ", 칼로리: " + u_calorie * u_Count +
                ", 가격: " + u_price * u_Count + "}";
    }
}

public class Order implements Serializable{

    private static final long serialVersionUID = 4125292916481512201L;

    private String o_name;  // 회원아이디
    private String o_nowTime;   // TODO 결재 시간 업데이트

    List<OrderValues> innerList = new ArrayList<OrderValues>();
    //    UserProduct o_userPd;
    private int o_totCalorie;
    private int o_totPrice;

    private boolean isMember;
    private int usedPoints;			// 포인트 사용
    private String paymentMethod;	// 결제 수단
    private double totalAmount;		// 총 결제 금액
    private boolean isCancelled;
    

    Order(String o_name) {
        this.o_name = o_name;
    }

    Order(String o_name, String o_nowTime, int o_totCalorie, int o_totPrice) {
        this.o_name = o_name;
        this.o_nowTime = o_nowTime;
        this.o_totCalorie = o_totCalorie;
        this.o_totPrice = o_totPrice;
    }

    Order(String o_name, String o_nowTime, List<OrderValues> userList, int o_totCalorie, int o_totPrice) {
        this.o_name = o_name;
        this.o_nowTime = o_nowTime;
        this.innerList = userList;
//        this.o_userPd = o_salad;
        this.o_totCalorie = o_totCalorie;
        this.o_totPrice = o_totPrice;
    }

    public Order() { }

    public String getO_name() {
        return o_name;
    }

    public void setO_name(String o_name) {
        this.o_name = o_name;
    }

    public String getO_nowTime() {
        return o_nowTime;
    }

    public void setO_nowTime(String o_nowTime) {
        this.o_nowTime = o_nowTime;
    }

    public List<OrderValues> getO_userList() {
        return innerList;
    }

    public void setO_userList(List<OrderValues> o_userList) {
        this.innerList = innerList;
    }

    public int getO_totCalorie() {
        return o_totCalorie;
    }

    public void setO_totCalorie(int o_totCalorie) {
        this.o_totCalorie = o_totCalorie;
    }

    public int getO_totPrice() {
        return o_totPrice;
    }

    public void setO_totPrice(int o_totPrice) {
        this.o_totPrice = o_totPrice;
    }

    public boolean isMember() {
        return isMember;
    }

    public void setMember(boolean member) {
        isMember = member;
    }

    public int getUsedPoints() {
        return usedPoints;
    }

    public void setUsedPoints(int usedPoints) {
        this.usedPoints = usedPoints;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public boolean isCancelled() {
        return isCancelled;
    }

    public void setCancelled(boolean cancelled) {
        isCancelled = cancelled;
    }

    @Override
    public String toString() {
        return "Order{" +
                "o_name='" + o_name + '\'' +
                ", o_nowTime='" + o_nowTime + '\'' +
                ", innerList=" + innerList +
                ", o_totCalorie=" + o_totCalorie +
                ", o_totPrice=" + o_totPrice +
                ", isMember=" + isMember +
                ", usedPoints=" + usedPoints +
                ", paymentMethod='" + paymentMethod + '\'' +
                ", totalAmount=" + totalAmount +
                ", isCancelled=" + isCancelled +
                '}';
    }
}

class OrderCart {
    public String nowTime() {   // 현재시간
        Date today = new Date();
        Locale currentLocale = new Locale("KOREAN", "KOREA");
        String pattern = "yyyyMMddHHmmss"; //hhmmss로 시간,분,초만 뽑기도 가능
        SimpleDateFormat formatter = new SimpleDateFormat(pattern, currentLocale);
//        System.out.println(formatter.format(today));
        return formatter.format(today);
    }
}

// 주문
class OrderSetting {
    void calculateOrderTotal() {            // 선택 값에 따라 총 칼로리, 총 가격 계산
        List<Order> OrderList = CacheData.orderOuterList;
        List<OrderValues> orderValueList = CacheData.orderInnerValues;

        OrderCart oC = new OrderCart();
        OrderList.get(OrderList.size()-1).setO_nowTime(oC.nowTime());

        if (OrderList.isEmpty()) {
            OrderList.add(new Order());
            OrderList.get(OrderList.size()-1).setO_nowTime(oC.nowTime());
        }
        else {
            OrderList.get(OrderList.size()-1).setO_nowTime(oC.nowTime());
        }

        for (Order order: OrderList){
            order.setO_totCalorie(0);
            order.setO_totPrice(0);
        }
        for (Order order: OrderList){
          for (OrderValues orderValues: orderValueList){
              order.setO_totCalorie(order.getO_totCalorie()+(orderValues.getCalorie() * orderValues.getCount()));
              order.setO_totPrice(order.getO_totPrice()+(orderValues.getPrice() * orderValues.getCount()));
          }
        }
    }

    void calculateOrderDiscount() {            // 선택 값에 따라 재고에서 선택값 빼주기
        List<Product> List1 = CacheData.list1;
        List<Order> OrderList = CacheData.orderOuterList;
        List<OrderValues> orderValueList = CacheData.orderInnerValues;
        int productIdx = 0;
        for (Order order: OrderList){
            for (OrderValues orderValues: orderValueList){
                for(Product list1 : List1){
                    if(list1.getP_name().equals(orderValues.getName())){
                        productIdx = List1.indexOf(list1);
                        break;
                    }
                }
                int productCount = List1.get(productIdx).getP_count();
                int productDiscount = orderValues.getCount();
                List1.get(productIdx).setP_count(productCount - productDiscount);
           }
        }

    }

    void printOrderList() {             // 선택 값 출력
        List<Order> OrderList = CacheData.orderOuterList;
        List<OrderValues> orderInnerValues = CacheData.orderInnerValues;

        System.out.printf("%-4s| %-8s \t%-8s \t%-8s \t%-8s\n", "NO", "이름", "년월일시간", "총칼로리", "총금액");
        int i=1,j=1;
        for (Order order: OrderList){
            System.out.printf("%-4d   %-8s \t%-8s \t%-8d \t%-8d\n", i++, order.getO_name(), order.getO_nowTime(), order.getO_totCalorie(), order.getO_totPrice());
            System.out.printf("\t%-4s) %-4s \t%-4s \t%-8s \t%-8s\n", "NO", "제품", "구매수량", "칼로리", "금액");

            for (OrderValues orderValues: orderInnerValues) {
                System.out.printf("\t%d-%d) %-8s \t%-8s \t%-8d \t%-8d\n", i-1 , j++, orderValues.getName(), orderValues.getCount(), orderValues.getCalorie() * orderValues.getCount(), orderValues.getPrice() * orderValues.getCount());
            }

        }
    }

}
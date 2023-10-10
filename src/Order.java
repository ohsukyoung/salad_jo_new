import java.io.Serial;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.*;

/*
 주문 ----------------------------------------------------------------
*/
class OrderValues {
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

    public OrderValues() {

    }

    public String getName() {
        return u_name;
    }

    public void setU_name(String u_name) {
        this.u_name = u_name;
    }

    public int getCount() {
        return u_Count;
    }

    public void setU_Count(int u_Count) {
        this.u_Count = u_Count;
    }

    public int getCalorie() {
        return u_calorie;
    }

    public void setU_calorie(int u_calorie) {
        this.u_calorie = u_calorie;
    }

    public int getPrice() {
        return u_price;
    }

    public void setU_price(int u_price) {
        this.u_price = u_price;
    }

    @Override
    public String toString() {
        return "OrderValues{" +
                "u_name='" + u_name + '\'' +
                ", u_Count=" + u_Count +
                ", u_calorie=" + u_calorie +
                ", u_price=" + u_price +
                '}';
    }
}

public class Order implements Serializable {
    @Serial
    private static final long serialVersionUID = 4125292916481512201L;
    //(outerList)
    // 이름
    // 년월일/시간
    // 사용자선택 값(innerList)
    // 총 칼로리, 총 결재금액
    private String o_name;
    private String o_nowTime;

    List<OrderValues> innerList = new ArrayList<OrderValues>();
    //    UserProduct o_userPd;
    private int o_totCalorie;
    private int o_totPrice;

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

    public Order() {

    }

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
}

class OrderCart {
    public String nowTime() {
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
    void calculateOrderTotal() {
        List<Order> OrderList = CacheData.orderOuterList;
        List<OrderValues> orderValueList = CacheData.orderInnerValues;

        OrderCart oC = new OrderCart();
        OrderList.get(OrderList.size()-1).setO_nowTime(oC.nowTime());

        for (Order order: OrderList){
          for (OrderValues orderValues: orderValueList){
              order.setO_totCalorie(order.getO_totCalorie()+(orderValues.getCalorie() * orderValues.getCount()));
              order.setO_totPrice(order.getO_totPrice()+(orderValues.getPrice() * orderValues.getCount()));
          }
        }
    }

    void printOrderList() {
        List<Order> OrderList = CacheData.orderOuterList;
        List<OrderValues> orderInnerValues = CacheData.orderInnerValues;

        System.out.printf("%-4s| %-8s \t%-8s \t%-8s \t%-8s\n", "NO", "이름", "년월일시간", "총칼로리", "총금액");
        int i=1,j=1;
        for (Order order: OrderList){
            System.out.printf("%-4d   %-8s \t%-8s \t%-8d \t%-8d\n", i++, order.getO_name(), order.getO_nowTime(), order.getO_totCalorie(), order.getO_totPrice());
            System.out.printf("\t%-4s) %-4s \t%-4s \t%-8s \t%-8s\n", "NO", "제품", "구매수량", "칼로리", "금액");

            for (OrderValues orderValues: orderInnerValues) {
                System.out.printf("\t%d-%d) %-8s \t%-8s \t%-8d \t%-8d\n", i , j++, orderValues.getName(), orderValues.getCount(), orderValues.getCalorie() * orderValues.getCount(), orderValues.getPrice() * orderValues.getCount());
            }

        }
    }

}
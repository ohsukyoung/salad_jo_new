import java.text.SimpleDateFormat;
import java.util.*;

/*
 주문 ----------------------------------------------------------------
*/
class UserProduct {
    private String u_name;  // 제품
    private int u_Count;    // 수량
    private int u_calorie;  // 칼로리
    private int u_price;    // 금액

    UserProduct(String u_name, int u_Count, int u_calorie, int u_price) {
        this.u_name = u_name;
        this.u_Count = u_Count;
        this.u_calorie = u_calorie;
        this.u_price = u_price;
    }

    ;

    public UserProduct() {

    }

    public String getU_name() {
        return u_name;
    }

    public void setU_name(String u_name) {
        this.u_name = u_name;
    }

    public int getU_Count() {
        return u_Count;
    }

    public void setU_Count(int u_Count) {
        this.u_Count = u_Count;
    }

    public int getU_calorie() {
        return u_calorie;
    }

    public void setU_calorie(int u_calorie) {
        this.u_calorie = u_calorie;
    }

    public int getU_price() {
        return u_price;
    }

    public void setU_price(int u_price) {
        this.u_price = u_price;
    }
}

public class Order {
    // 이름
    // 년월일/시간
    // 샐러드(베이스-개수,메인토핑-개수,사이드토핑-개수,소스-개수,치즈-개수)
    // 총 칼로리, 총 결재금액
    private String o_name;
    private String o_nowTime;

    List<UserProduct> innerList = new ArrayList<UserProduct>();
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

    Order(String o_name, String o_nowTime, List<UserProduct> userList, int o_totCalorie, int o_totPrice) {
        this.o_name = o_name;
        this.o_nowTime = o_nowTime;
        this.innerList = userList;
//        this.o_userPd = o_salad;
        this.o_totCalorie = o_totCalorie;
        this.o_totPrice = o_totPrice;
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

    public List<UserProduct> getO_userList() {
        return innerList;
    }

    public void setO_userList(List<UserProduct> o_userList) {
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
        System.out.println(formatter.format(today));
        return formatter.format(today);
    }
}

// 주문 셋팅
class OrderSetting {
    Iterator<Order> outList;
    Iterator<UserProduct> innerList;

    void set_order(List<Order> outerList) {
        outerList.add(new Order("이름1", "20231005153350", 200, 200));
        outerList.add(new Order("이름2", "20231005153350", 200, 200));
        outerList.add(new Order("이름3", "20231005153350", 200, 200));
        outerList.add(new Order("이름4", "20231005153350", 200, 200));
        outerList.add(new Order("이름5", "20231005153350", 200, 200));
    }

    void set_InnerOrder(List<Order> outerList) {
        for (int i = 0; i < 3; i++) {
            outerList.get(i).innerList.add(new UserProduct("베이스", 1, 100, 200));
        }
        System.out.println(outerList.get(1).innerList.get(0).getU_calorie());

//        orderList.add(new Order("이름1","20231005153350",new UserProduct("베이스",1,100,200),200,200));
    }

    void set_Operation(List<Order> outerList, int useridx) {
        OrderCart oC = new OrderCart();
        outerList.get(useridx - 1).setO_nowTime(oC.nowTime());

        outList = outerList.iterator();

        for (int i = 0; i < outerList.size(); i++) {
            Order itS = outList.next();
            innerList = outerList.get(i).innerList.iterator();
            for (int j = 0; j < outerList.get(i).innerList.size(); j++) {
                UserProduct itInner = innerList.next();
                itS.setO_totCalorie(itS.getO_totCalorie() + (itInner.getU_calorie() * itInner.getU_Count()));
                itS.setO_totPrice(itS.getO_totPrice() + itInner.getU_price() * itInner.getU_Count());
            }
        }
    }

    void set_Print(List<Order> outerList) {
        outList = outerList.iterator();

        System.out.printf("%-4s| %-8s \t%-8s \t%-8s \t%-8s\n", "NO", "이름", "년월일시간", "총칼로리", "총금액");
        for (int i = 0; i < outerList.size(); i++) {
            Order itS = outList.next();
            System.out.printf("%-4d   %-8s \t%-8s \t%-8d \t%-8d\n", i, itS.getO_name(), itS.getO_nowTime(), itS.getO_totCalorie(), itS.getO_totPrice());

            System.out.printf("\t%-4s) %-4s \t%-4s \t%-8s \t%-8s\n", "NO", "제품", "구매수량", "칼로리", "금액");
            innerList = outerList.get(i).innerList.iterator();
            for (int j = 0; j < outerList.get(i).innerList.size(); j++) {
                UserProduct itInner = innerList.next();
                System.out.printf("\t%d-%d) %-8s \t%-8s \t%-8d \t%-8d\n", i, j, itInner.getU_name(), itInner.getU_Count(), itInner.getU_calorie() * itInner.getU_Count(), itInner.getU_price() * itInner.getU_Count());
            }
        }
    }

}
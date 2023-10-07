import java.util.ArrayList;
import java.util.List;

/*
 메인 ----------------------------------------------------------------
*/
class CacheData {

    static int useridx = 0;          // 유저 인덱스
    static List<Order> outerList = new ArrayList<Order>();      // 사용자 선택 바깥 리스트
    static List<UserProduct> mRInner = outerList.get(useridx).innerList;    // 사용자 선택 값 재료 리스트

    static{
        //List<PdInterface> allProductList = new ArrayList<>();

    }
    public CacheData(){

    }

    // getter

}


public class Main {
    public static void main(String[] args) {
        Emp emp = new Emp(":)");

        //test
        //test test

        Kiosk ks = new Kiosk();
        ks.kioskStart();
    }
}

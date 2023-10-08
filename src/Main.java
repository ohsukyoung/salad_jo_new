import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/*
 메인 ----------------------------------------------------------------
*/
class CacheData {

    static int userIdx = 0;          // 유저 인덱스
    static List<Order> outerList = new ArrayList<>();      // 사용자 선택 바깥 리스트
    //static List<UserProduct> mRInner = outerList.get(useridx).innerList;    // 사용자 선택 값 재료 리스트

    static List<Product> list1 = new ArrayList<>();

    static{
        //List<PdInterface> allProductList = new ArrayList<>();
        list1.add(new Product(1,1,"양상추","80g",50,19,5,400));
        list1.add(new Product(2,1,"적상추","80g",50,19,5,400));
        list1.add(new Product(3,1,"곡물","170g",50,225,5,400));
        list1.add(new Product(4,2,"닭고기","50g",50,60,5,400));
        list1.add(new Product(5,2,"소고기","40g",50,108,5,400));
        list1.add(new Product(6,2,"연어","60g",50,101,5,400));
        list1.add(new Product(7,2,"우삼겹","50g",50,60,5,400));
        list1.add(new Product(8,2,"계란","50g",50,71,5,400));
        list1.add(new Product(9,3,"토마토","40g",50,7,5,400));
        list1.add(new Product(10,3,"올리브","25g",50,46,5,400));
        list1.add(new Product(11,3,"크렌베리","10g",50,31,5,400));
        list1.add(new Product(12,3,"당근라페","30g",50,25,5,400));
        list1.add(new Product(13,3,"옥수수","30g",50,39,5,400));
        list1.add(new Product(14,3,"오이","30g",50,2,5,400));
        list1.add(new Product(15,3,"리코타치즈","14g",50,54,5,400));
        list1.add(new Product(16,3,"브리타치즈","14g",50,54,5,400));
        list1.add(new Product(17,5,"오리엔탈","50g",50,128,5,400));
        list1.add(new Product(18,5,"발사믹","50g",50,127,5,400));
        list1.add(new Product(19,5,"시저","50g",50,239,5,400));
        list1.add(new Product(20,5,"크리미","50g",50,237,5,400));
        list1.add(new Product(21,5,"칠리","50g",50,237,5,400));
        list1.add(new Product(22,5,"마요네즈","50g",50,250,5,400));
        list1.add(new Product(23,6,"콜라","250ml",50,80,5,400));
        list1.add(new Product(24,6,"사이다","250ml",50,110,5,400));
        list1.add(new Product(25,6,"콜라Zero","250ml",50,0,5,400));
        list1.add(new Product(26,6,"사이다Zero","250ml",50,0,5,400));
        list1.add(new Product(27,7,"양송이스프","100g",243,150,5,400));
        list1.add(new Product(28,7,"콘치즈스프","100g",243,150,5,400));
        list1.add(new Product(29,7,"프로틴바","70g",50,414,5,400));

    }
    public CacheData(){
        // Iterator 활용하여 출력
        /*Iterator<Product> itList;
        itList = list1.iterator();
        while (itList.hasNext())
        {
            Product itS = itList.next();
            System.out.printf("|| %5s || %5s || %5s || %9s || %5s || %5s || %5s || %5s ||\n", itS.getP_checkNumber(),
                    itS.getP_material(), itS.getP_name(), itS.getP_unit(), itS.getP_count(), itS.getP_calorie(),
                    itS.getP_stock(), itS.getP_price());
        }
        System.out.println();*/
        //-----------------
    }

    // getter

}


public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Emp emp = new Emp(":)");

        //test
        //test test

        Kiosk ks = new Kiosk();
        ks.kioskStart();


//        FoodAdmin foodadmin = new FoodAdmin();
//        foodadmin.product_setting();
//        foodadmin.soldout_management();

        //CacheData cd = new CacheData();

    }
}

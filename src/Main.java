import java.io.*;
import java.util.ArrayList;
import java.util.List;

/*
 메인 ----------------------------------------------------------------
*/


class CacheData{

    static List<Order> orderOuterList = new ArrayList<>();      // 사용자 선택 바깥 리스트
    static List<OrderValues> orderInnerValues;// = orderOuterList.get(orderOuterList.size()).innerList;

    static List<Product> list1 = new ArrayList<>(); // TODO 리스트 이름 공통화(ex.allProductList)
    static List<MasterRc> list2 = new ArrayList<>();

    static{

        orderOuterList.add(new Order());
        orderInnerValues = orderOuterList.get(orderOuterList.size()-1).innerList;

        //  settingAllProductList
        list1.add(new Product(1,ProductType.S_BASE,1,"양상추","80g",50,19,50,400));
        list1.add(new Product(2,ProductType.S_BASE,1,"적상추","80g",50,19,5,400));
        list1.add(new Product(3,ProductType.S_BASE,1,"곡물","170g",50,225,5,400));
        list1.add(new Product(4,ProductType.S_MAIN,2,"닭고기","50g",50,60,5,400));
        list1.add(new Product(5,ProductType.S_MAIN,2,"소고기","40g",50,108,5,400));
        list1.add(new Product(6,ProductType.S_MAIN,2,"연어","60g",50,101,5,400));
        list1.add(new Product(7,ProductType.S_MAIN,2,"우삼겹","50g",50,60,5,400));
        list1.add(new Product(8,ProductType.S_MAIN,2,"계란","50g",50,71,5,400));
        list1.add(new Product(9,ProductType.S_SIDE,7,"토마토","40g",50,7,5,400));
        list1.add(new Product(10,ProductType.S_SIDE,7,"올리브","25g",50,46,5,400));
        list1.add(new Product(11,ProductType.S_SIDE,7,"크렌베리","10g",50,31,5,400));
        list1.add(new Product(12,ProductType.S_SIDE,7,"당근라페","30g",50,25,5,400));
        list1.add(new Product(13,ProductType.S_SIDE,7,"옥수수","30g",50,39,5,400));
        list1.add(new Product(14,ProductType.S_SIDE,7,"오이","30g",50,2,5,400));
        list1.add(new Product(15,ProductType.S_SIDE,7,"리코타치즈","14g",50,54,5,400));
        list1.add(new Product(16,ProductType.S_SIDE,7,"브리타치즈","14g",50,54,5,400));
        list1.add(new Product(17,ProductType.S_SOURCE,8,"오리엔탈","50g",50,128,5,400));
        list1.add(new Product(18,ProductType.S_SOURCE,8,"발사믹","50g",50,127,5,400));
        list1.add(new Product(19,ProductType.S_SOURCE,8,"시저","50g",50,239,5,400));
        list1.add(new Product(20,ProductType.S_SOURCE,8,"크리미","50g",50,237,5,400));
        list1.add(new Product(21,ProductType.S_SOURCE,8,"칠리","50g",50,237,5,400));
        list1.add(new Product(22,ProductType.S_SOURCE,8,"마요네즈","50g",50,250,5,400));
        list1.add(new Product(23,ProductType.DRINK,3,"콜라","250ml",50,80,5,400));
        list1.add(new Product(24,ProductType.DRINK,3,"사이다","250ml",50,110,5,400));
        list1.add(new Product(25,ProductType.DRINK,3,"콜라Zero","250ml",50,0,5,400));
        list1.add(new Product(26,ProductType.DRINK,3,"사이다Zero","250ml",50,0,5,400));
        list1.add(new Product(27,ProductType.SIDE,4,"양송이스프","100g",243,150,5,400));
        list1.add(new Product(28,ProductType.SIDE,4,"콘치즈스프","100g",243,150,5,400));
        list1.add(new Product(29,ProductType.SIDE,4,"프로틴바","70g",50,414,5,400));

        //  settingMasterRCProductList

        List<Product> r_products1 = new ArrayList<>();
        List<Product> r_products2 = new ArrayList<>();
        List<Product> r_products3 = new ArrayList<>();

//        void set_Ceo() {
//            ceoList.add(new CeoRcmd("시저치킨샐러드", new CeoDetail("닭고기", "크렌베리", "시저"), 200, 20));
//            ceoList.add(new CeoRcmd("콥샐러드", new CeoDetail("계란", "옥수수", "시저"), 300, 30));
//            ceoList.add(new CeoRcmd("연어샐러드", new CeoDetail("연어", "토마토", "크리미"), 400, 40));
//        }

        Product product1 = new Product(4,ProductType.S_MAIN,2,"닭고기","50g",50,60,5,400);
        Product product2 = new Product(11,ProductType.S_SIDE,7,"크렌베리","10g",50,31,5,400);
        Product product3 = new Product(19,ProductType.S_SOURCE,8,"시저","50g",50,239,5,400);
        r_products1.add(product1);
        r_products1.add(product2);
        r_products1.add(product3);
        list2.add(new MasterRc(1, ProductType.RCMND, "시저치킨샐러드",4000, 5000, r_products1,10,50));

        Product product11 = new Product(8,ProductType.S_MAIN,2,"계란","50g",50,71,5,400);
        Product product12 = new Product(13,ProductType.S_SIDE,7,"옥수수","30g",50,39,5,400);
        Product product13 = new Product(19,ProductType.S_SOURCE,8,"시저","50g",50,239,5,400);
        r_products2.add(product11);
        r_products2.add(product12);
        r_products2.add(product13);
        list2.add(new MasterRc(2, ProductType.RCMND, "콥샐러드",4000, 5000, r_products2,10,50));

        Product product21 = new Product(6,ProductType.S_MAIN,2,"연어","60g",50,101,5,400);
        Product product22 = new Product(9,ProductType.S_SIDE,7,"토마토","40g",50,7,5,400);
        Product product23 = new Product(20,ProductType.S_SOURCE,8,"크리미","50g",50,237,5,400);
        r_products3.add(product21);
        r_products3.add(product22);
        r_products3.add(product23);
        list2.add(new MasterRc(3, ProductType.RCMND, "연어샐러드", 4000, 5000, r_products3,10,50));




    }

    public CacheData(){
//        settingOuterOrder();  // 테스트 데이터: 사용자선택 바깥 리스트
//        settingInnerOrder();  // 테스트 데이터: 사용자선택 안쪽 리스트
    }

    void settingOuterOrder() {  // testData
        orderOuterList.add(new Order("이름1", "20231005153350", 200, 200));
        orderOuterList.add(new Order("이름2", "20231005153350", 200, 200));
        orderOuterList.add(new Order("이름3", "20231005153350", 200, 200));
        orderOuterList.add(new Order("이름4", "20231005153350", 200, 200));
        orderOuterList.add(new Order("이름5", "20231005153350", 200, 200));
    }

    void settingInnerOrder() {  // testData
        for (int i = 0; i < 3; i++) {
            orderOuterList.get(i).innerList.add(new OrderValues("베이스", 1, 100, 200));
        }
        System.out.println(orderOuterList.get(1).innerList.get(0).getCalorie());
    }

}


public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {




        Emp emp = new Emp(":)");

        ProductService productService = new ProductService(); // ProductService 객체 생성



//        관리자 값 받아오고 넘겨주기를 위한 테스트 코드 -----------------------

        Product product = new Product();
        FoodAdmin foodadmin = new FoodAdmin();
        MasterRc masterRc = new MasterRc();

//    adminmenu.printproduct();
//        product.ad_add();
//        product.ad_print();
//		product.ad_modify();
//		product.ad_delete();

//        foodadmin.product_setting();
//        foodadmin.soldout_management();


//        FoodAdmin foodadmin = new FoodAdmin();
//        foodadmin.product_setting();
//        foodadmin.soldout_management();

        masterRc.ad_add();
        masterRc.ad_print();

        Kiosk ks = new Kiosk(productService);
        ks.kioskStart();

    }
}

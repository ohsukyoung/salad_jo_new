import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.io.BufferedReader;
import java.io.InputStreamReader;


/*
 메인 ----------------------------------------------------------------
*/

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {



//        MemberMg mm = new MemberMg();
//        SalesMg sm = new SalesMg();
//
//        sm.menuDisp();
//        sm.menuSelect();
//        sm.menuRun();

        // 객체 역직렬화
        FileMg f = new FileMg();
        MemberMg.hm = f.memberFileIn();
        SalesMg.receipts = f.receiptFileIn();
        CacheData.list1 = f.list1FileIn();
        CacheData.list2 = f.list2FileIn();
        CacheData.list3 = f.list3FileIn();
        CacheData.list4 = f.list4FileIn();

        CacheData cacheData = new CacheData();
        cacheData.testData();

        // 관리자 로그인 폼
        ad_login al = new ad_login();
        al.adLogin();
//
//        // 객체 파일 내보내기
       /* f.memberFileOut();
        f.receiptFileOut();*/

//        System.out.println("\n\n\t====[[[[[ 사용자 화면 ]]]]]====");
//
//        // 환영인사 //TODO 꾸밀때 넣기
//        Emp emp = new Emp(":)");
//        emp.empWelcome();
//
        // 사용자 폼
        /*ProductService productService = new ProductService(); // ProductService 객체 생성
        Kiosk ks = new Kiosk(productService);
        ks.kioskStart();*/


    }
}

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class KioskMg
{
    public static final int E_STOCKMG =1; // 재고관리
    public static final int E_INGMG = 2; // 재료 관리
    public static final int E_RECMG = 3; // 사장추천 관리
    public static final int E_SALESMG = 4; // 매출 관리
    public static final int E_MEMBERMG = 5; // 회원 관리
    public static final int E_KIOSKSTART = 6; // 판매 시작(사용자 화면으로 이동)

    private static BufferedReader br;		         //-- 사용자가 입력시 활용
    private static Integer sel;				         //-- 선택 값
    private static String con;				         //-- 계속 진행 여부

    MemberMg mm = new MemberMg();
    public static boolean memflag;
    public static boolean salesflag;
    static
    {
        //BufferedReader 객체 생성
        br = new BufferedReader(new InputStreamReader(System.in));

        // 사용자 입력값 초기화
        sel = 1;
        con = "Y";
    }


    public static void adMenuDisp()
    {
        System.out.println("\n\t[ 키오스크 관리 메뉴 선택 ]===========");
        System.out.println("\t1. 재고 관리");
        System.out.println("\t2. 재료 관리");
        System.out.println("\t3. 사장추천 관리");
        System.out.println("\t4. 매출 관리");
        System.out.println("\t5. 회원 관리");
        System.out.println("\t6. 판매 시작(사용자 화면으로 이동)");
        System.out.println("\t==============================");
        System.out.print("\t>> 메뉴 선택(1~6) : ");
    }

    // 메뉴 선택 메소드
    public static void adMenuSelect() throws IOException, NumberFormatException
    {
        sel = Integer.parseInt(br.readLine());
    }

    // 메뉴 실행에 따른 기능 호출 메소드
    public static void adMenuRun() throws IOException, ClassNotFoundException {

        MemberMg mm = new MemberMg();
        SalesMg sm = new SalesMg();

        // 클래스를 활용하여 처리
        if (sel==E_STOCKMG){        // 1. 재고 관리
            Product product = new Product();
            salesflag = true;
            while(salesflag) {
                product.ad_add();
                product.ad_print();
                product.ad_modify();
                product.ad_delete();
            }
        }
        else if (sel==E_INGMG){     // 2. 재료 관리
            FoodAdmin foodadmin = new FoodAdmin();
            salesflag = true;
            while(salesflag) {
                foodadmin.product_setting();
                foodadmin.soldout_management();

//            AdminMenu adminmenu = new AdminMenu();
//            adminmenu.printproduct();
            }
        }
        else if (sel==E_RECMG){     // 3. 사장추천 관리
            MasterRc masterRc = new MasterRc();
            salesflag = true;
            while(salesflag) {
                masterRc.ad_add();
                masterRc.ad_print();
            }
        }
        else if (sel==E_SALESMG){   // 4. 매출 관리
            salesflag = true;
            while(salesflag)
            {
                sm.menuDisp();
                sm.menuSelect();
                sm.menuRun();
            }
        }
        else if (sel==E_MEMBERMG){  // 5. 회원관리
            memflag = true;
            while(memflag)
            {
                mm.memMenuDisp();
                mm.memMenuSelect();
                mm.memMenuRun();
            }
        }
        else if (sel==E_KIOSKSTART) // 6. 판매시작(사용자 화면으로 이동)
        {
            ad_login.kioskFlag = false;
        }
        else
            System.out.print("메뉴 선택 오류~!!!");
    }
}
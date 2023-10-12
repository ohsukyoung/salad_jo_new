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
    public static final int E_END = 7; // 종료

    private static BufferedReader br;		         //-- 사용자가 입력시 활용
    private static Integer sel;				         //-- 선택 값
    private static String con;				         //-- 계속 진행 여부
    private static int check;

    private static boolean Exit = false;  // 종료 여부를 나타내는 변수
    // 사용자가 장바구니를 종료하도록 선택한 경우 호출
    public static void exitCart()
    {
        try {
            FileMg f = new FileMg();
            f.memberFileOut();
            f.receiptFileOut();
        } catch (IOException e) {
            System.out.println("e.toString: " + e.toString());
            System.out.println("e.getMessage: " + e.getMessage());
            System.out.println("printStackTrace................");
            e.printStackTrace();
        }
        Exit = true;
        System.exit(0);
    }

    MemberMg mm = new MemberMg();
    public static boolean memflag;
    public static boolean salesflag;
    public static boolean foodadminflag;
    public static boolean productflag;
    public static boolean masterrcflag;
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
        System.out.println("\t7. 종료");
        System.out.println("\t==============================");
        System.out.print("\t>> 메뉴 선택(1~7) : ");
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

        FileMg f = new FileMg();
        MemberMg.hm = f.memberFileIn();
        SalesMg.receipts = f.receiptFileIn();

        // 클래스를 활용하여 처리
        if (sel==E_STOCKMG){
            // 1. 재고 관리
            FoodAdmin foodadmin = new FoodAdmin();
            foodadminflag = true;
            System.out.println("[재고관리]===========");
            System.out.printf("1.재료 세팅\n2.품절관리\n");
            System.out.print(">>메뉴선택(1~2) : ");
            check = Integer.parseInt(br.readLine());
            while(foodadminflag) {

                switch (check){
                    case 1:
                        foodadmin.product_setting();
                        break;
                    case 2:
                        foodadmin.soldout_management();
                        break;
                    default:
                        System.out.println("입력된 숫자가 옳지 않습니다.");
                        break;
                }

//            AdminMenu adminmenu = new AdminMenu();
//            adminmenu.printproduct();
            }
        }
        else if (sel==E_INGMG){
            // 2. 재료 관리
            Product product = new Product();
            productflag = true;
            System.out.println("[재료관리]===========");
            System.out.printf("1.재료출력\n2.신규재료 등록\n3.재료정보 변경\n4.재료 정보 삭제\n");
            System.out.print(">>메뉴선택(1~4) : ");
            check = Integer.parseInt(br.readLine());

            while(productflag) {

                switch (check){
                    case 1 :
                        product.ad_print();
                        return;
                    case 2 :
                        product.ad_add();
                        break;
                    case 3 :
                        product.ad_modify();
                        break;
                    case 4 :
                        product.ad_delete();
                        break;
                    default:
                        System.out.println("입력된 숫자가 옳지 않습니다.");
                        break;
                }
            }
        }
        else if (sel==E_RECMG){     // 3. 사장추천 관리
            MasterRc masterRc = new MasterRc();
            masterrcflag = true;
            System.out.println("[사장추천 관리]===========");
            System.out.printf("1.추천조합 출력\n2.추천조합 등록\n3.추천조합 정보 변경\n4.추천조합 정보 삭제\n");
            System.out.print(">>메뉴선택(1~4) : ");
            check = Integer.parseInt(br.readLine());

            while(masterrcflag) {

                switch (check)
                {
                    case 1 :
                        masterRc.ad_print();
                        break;
                    case 2 :
                        masterRc.ad_add();
                        break;
                    case 3 :
                        masterRc.ad_modify();
                        break;
                    case 4 :
                        masterRc.ad_delete();
                        break;
                    default :
                        System.out.println("입력된 숫자가 옳지 않습니다.");
                        break;
                }

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
            try {
                f.memberFileOut();
                f.receiptFileOut();
                //f.orderOuterFileOut();//TODO 문제 없는지 확인 필요

            } catch (IOException e) {
                System.out.println("e.toString: " + e.toString());
                System.out.println("e.getMessage: " + e.getMessage());
                System.out.println("printStackTrace................");
                e.printStackTrace();
            }
            ProductService productService = new ProductService(); // ProductService 객체 생성
            Kiosk ks = new Kiosk(productService);
            ks.kioskStart();
//            ad_login.kioskFlag = false;
        }
        else if (sel == E_END)      //프로그램 종료
        {
            exitCart();
        }
        else
            System.out.print("메뉴 선택 오류~!!!");
    }
}
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class KioskMg
{
    public static final int E_STOCKMG =1; // 판매관리
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
            // 객체 직렬화
            FileMg f = new FileMg();
            f.memberFileOut();
            f.receiptFileOut();
            f.list1FileOut();
            f.list2FileOut();
            f.list3FileOut();
            f.list4FileOut();
        } catch (IOException e) {
            System.out.println("e.toString: " + e.toString());
            System.out.println("e.getMessage: " + e.getMessage());
            System.out.println("printStackTrace................");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
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
        System.out.println("\n\t[ 키오스크 관리 메뉴 선택 ]=============");
        System.out.println("\t1. 판매 관리");
        System.out.println("\t2. 재료 관리");
        System.out.println("\t3. 사장추천 관리");
        System.out.println("\t4. 매출 관리");
        System.out.println("\t5. 회원 관리");
        System.out.println("\t6. 판매 시작(사용자 화면으로 이동)");
        System.out.println("\t7. 종료");
        System.out.println("\t====================================");
        System.out.print("\t▶ 메뉴 선택(1~7) : ");
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

        // 231012 직렬화
//        FileMg f = new FileMg();
//        MemberMg.hm = f.memberFileIn();
//        SalesMg.receipts = f.receiptFileIn();

        // 클래스를 활용하여 처리
        if (sel==E_STOCKMG){
            // 1. 판매 관리
            FoodAdmin foodadmin = new FoodAdmin();
            foodadminflag = true;
            System.out.println("\n\t[ 판매관리 ]===============");
            System.out.printf("\t1. 판매정보 출력\n\t2. 판매항목 세팅\n\t3. 판매항목 제거\n");
            System.out.println("\t=========================");
            while(foodadminflag) {
                try {
                    System.out.print("\t▶ 메뉴선택(1~3) : ");
                    check = Integer.parseInt(br.readLine());
                }
                catch (NumberFormatException e){
                }
                switch (check){
                    case 1:
                        foodadmin.setting_print();
                        return;
                    case 2:
                        foodadmin.product_setting();
                        return;
                    case 3:
                        foodadmin.soldout_management();
                        return;
                    default:
                        System.out.println("\t[!] 입력된 숫자가 옳지 않습니다.");
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
            System.out.println("\n\t[ 재료관리 ]==============");
            System.out.printf("\t1. 재료출력\n\t2. 신규재료 등록\n\t3. 재료정보 변경\n\t4. 재료정보 삭제\n");
            System.out.println("\t=========================");
            try {
                System.out.print("\t▶ 메뉴선택(1~4) : ");
                check = Integer.parseInt(br.readLine());
            }
            catch (NumberFormatException e){
            }

            while(productflag) {

                switch (check){
                    case 1 :
                        product.ad_print();
                        return;
                    case 2 :
                        product.ad_add();
                        return;
                    case 3 :
                        product.ad_modify();
                        return;
                    case 4 :
                        product.ad_delete();
                        return;
                    default:
                        System.out.println("\t[!] 입력된 숫자가 옳지 않습니다.");
                        break;
                }
            }
        }
        else if (sel==E_RECMG){     // 3. 사장추천 관리
            MasterRc masterRc = new MasterRc();
            masterrcflag = true;
            System.out.println("\n\t[사장추천 관리]==============");
            System.out.printf("\t1.추천조합 출력\n\t2.추천조합 등록\n\t3.추천조합 정보 변경\n\t4.추천조합 정보 삭제\n");
            System.out.println("\t=========================");
            try {
                System.out.print("\t▶ 메뉴선택(1~4) : ");
                check = Integer.parseInt(br.readLine());
            }
            catch (NumberFormatException e){
            }

            while(masterrcflag) {

                switch (check)
                {
                    case 1 :
                        masterRc.ad_print();
                        return;
                    case 2 :
                        masterRc.ad_add();
                        return;
                    case 3 :
                        masterRc.ad_modify();
                        return;
                    case 4 :
                        masterRc.ad_delete();
                        return;
                    default :
                        System.out.println("\t[!] 입력된 숫자가 옳지 않습니다.");
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
            // 231012 직렬화
//            try {
//                f.memberFileOut();
//                f.receiptFileOut();
//
//            } catch (IOException e) {
//                System.out.println("e.toString: " + e.toString());
//                System.out.println("e.getMessage: " + e.getMessage());
//                System.out.println("printStackTrace................");
//                e.printStackTrace();
//            }
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
            System.out.print("\t[!] 메뉴 선택 오류");
    }
}
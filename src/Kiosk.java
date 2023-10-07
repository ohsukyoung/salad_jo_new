import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;

/*
 키오스크 ----------------------------------------------------------------
*/
class Kiosk {
    private final PdSetting pdS = new PdSetting();
    private static BufferedReader br;

    SelectMenu sMenu = new SelectMenu();

    OrderSetting oSetting = new OrderSetting();
    private List<Order> outerList = new ArrayList<Order>();

    UserProduct selUP = new UserProduct();

    // 2dep
    dep2_infoBase dep2InfoBa = new dep2_infoBase();
    dep2_infoMain dep2InfoMa = new dep2_infoMain();
    dep2_infoSide dep2InfoSi = new dep2_infoSide();
    dep2_infoSource dep2InfoSo = new dep2_infoSource();
    dep2_infoCheese dep2InfoCh = new dep2_infoCheese();

    //사장추천
    dep1_infoCeo dep1InfoCe = new dep1_infoCeo();

    // 음료
    dep1_infoDrink dep1InfoDr = new dep1_infoDrink();

    // 사이드
    dep1_infoSide dep1InfoSi = new dep1_infoSide();

//    infoCancel choCancel = new infoCancel();

    // 선택 메뉴 리스트
    static final int e_rcmnd    = 1;   // 사장추천
    static final int e_mySalad  = 2;   // 나만의 샐러드
    static final int e_drink    = 3;   // 음료
    static final int e_side     = 4;   // 사이드
    static final int e_cancel = -1;  // 취소

    int userSelect;         // 유저 선택값
    int useridx=0;          // 유저 인덱스
    String userName = "고객";
    List<UserProduct> mRInner;

    public void kioskStart(){

        storePack();    // 포장 or 매장 여부
        menuDisp();
        menuRun();

        // test 231005
//        oSetting.set_order(outerList);
//        oSetting.set_innerOrder(outerList);
        oSetting.set_Operation(outerList,useridx);
        oSetting.set_Print(outerList);
    }

    public void storePack(){
        useridx++;
        System.out.println("=============================");
        System.out.println("\t 1. 포장");
        System.out.println("\t 2. 매장");
        System.out.println("=============================");
        userSelect = sMenu.menuSelect(2);

        // 선택값 배열-유저 임시 이름 넣기
        outerList.add(new Order(userName + useridx,"yyyyMMddHHmmss",0,0));
        mRInner = outerList.get(useridx-1).innerList;
    }

    public void menuDisp(){
        System.out.println("=============================");
        System.out.println("\t [[샐러드먹조]]");
        System.out.println("\t 1. 사장추천");
        System.out.println("\t 2. 나만의 샐러드");
        System.out.println("\t 3. 음료");
        System.out.println("\t 4. 사이드");
//        System.out.println("\t - 뒤로가기(c)");
        System.out.println("=============================");
        userSelect = sMenu.menuSelect(4);

    }


    public void menuRun(){
        switch (userSelect){
            case e_rcmnd    : menuRcmd();   break;
            case e_mySalad  : menuMySalad();break;
            case e_drink    : menuDrink();  break;
            case e_side     : menuSide();   break;
            case e_cancel   : menuCancel(); break;
        }

        //outerList.get(useridx-1).setO_totCalorie(200);
    }

    public void menuRcmd(){     // 사장추천
        System.out.println("\n1. 사장추천 -------------------------------------- ");
        dep1InfoCe.menuInfo(mRInner);
    }
    public void menuMySalad(){  // 나만의 샐러드

        System.out.println("\n2. 나만의 샐러드 -------------------------------------- ");

        dep2InfoBa.menuInfo(mRInner);
        dep2InfoMa.menuInfo(mRInner);
        dep2InfoSi.menuInfo(mRInner);
        dep2InfoSo.menuInfo(mRInner);
        dep2InfoCh.menuInfo(mRInner);
    }

    public void menuDrink(){    // 음료
        System.out.println("\n2. 음료 -------------------------------------- ");
        dep1InfoDr.menuInfo(mRInner);
    }
    public void menuSide(){     // 사이드
        System.out.println("\n2. 사이드 -------------------------------------- ");
        dep1InfoSi.menuInfo(mRInner);
    }
    public void menuCancel(){   // 취소

    }
}
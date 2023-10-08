import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;

/*
 키오스크 ----------------------------------------------------------------
*/
class Kiosk {
    final String USER_NAME = "고객";
    Imp_info info = new InfoService();


    private BufferedReader br; //TODO 안쓰면 지우기
    SelectMenu sMenu = new SelectMenu();     //TODO 고민
    OrderSetting oSetting = new OrderSetting();     //TODO 고민
    private List<Order> outerList = new ArrayList<>(); //TODO 캐시로 만들기
    List<Product> list1 = CacheData.list1;  //TODO 이름 바꾸기
    List<UserProduct> mRInner; //TODO 캐시로 옮기기
    int useridx = 0; //TODO outerlist 에 저장하기 전에 현재 size 확인 후 idx로 씀

    public void kioskStart() {

        storePack();    // 포장 or 매장 여부
        menuDisp();

        int userSelect = sMenu.menuSelect(4);
        menuRun(userSelect);

        oSetting.set_Operation(outerList, useridx);
        oSetting.set_Print(outerList);
    }

    public void storePack() {
        useridx++;
        System.out.println("=============================");
        System.out.println("\t 1. 포장");
        System.out.println("\t 2. 매장");
        System.out.println("=============================");
        sMenu.menuSelect(2);

        // 선택값 배열-유저 임시 이름 넣기
        outerList.add(new Order(USER_NAME + useridx, "yyyyMMddHHmmss", 0, 0));
        mRInner = outerList.get(useridx - 1).innerList;
    }

    public void menuDisp() {
        System.out.println("=============================");
        System.out.println("\t [[샐러드먹조]]");
        System.out.println("\t 1. 사장추천");
        System.out.println("\t 2. 나만의 샐러드");
        System.out.println("\t 3. 음료");
        System.out.println("\t 4. 사이드");
//        System.out.println("\t - 뒤로가기(c)");
        System.out.println("=============================");
    }


    public void menuRun(int userSelect) {
        ProductType productType = switch (userSelect) {
            case 1 -> ProductType.RCMND;
            case 2 -> ProductType.MY_SALAD;
            case 3 -> ProductType.DRINK;
            case 4 -> ProductType.SIDE;
            default -> null;
        };

        switch (productType) { //swtich문의 조건에 String 타입을 넣게되면, case문에서 enum타입으로 비교할 수 가 없음
            case RCMND:
                menuRcmd();
                break;
            case MY_SALAD:
                menuMySalad();
                break;
            case DRINK:
                menuDrink();
                break;
            case SIDE:
                menuSide();
                break;
            case E_CANCEL:
                menuCancel();
                break;
        }

    }

    public void menuRcmd() {     // 사장추천
        System.out.println("\n1. 사장추천 -------------------------------------- ");
    }

    public void menuMySalad() {  // 나만의 샐러드
        System.out.println("\n2. 나만의 샐러드 -------------------------------------- ");
    }

    public void menuDrink() {    // 음료
        System.out.println("\n2. 음료 -------------------------------------- ");
        info.printInfo(ProductType.DRINK);
    }

    public void menuSide() {     // 사이드
        System.out.println("\n2. 사이드 -------------------------------------- ");
    }

    public void menuCancel() {   // 취소

    }
}
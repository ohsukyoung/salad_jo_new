import java.util.List;

/*
 키오스크 ----------------------------------------------------------------
*/
class Kiosk {
    final String USER_NAME = "고객";
    Imp_info info;

    public Kiosk(ProductService productService) {
        this.info = new InfoService(productService);
    }

    ProductService productService = new ProductService(); // ProductService 객체 생성
    List<Order> outerList = CacheData.orderOuterList;
    List<Product> list1 = CacheData.list1;
    List<OrderValues> orderInnerValues = CacheData.orderInnerValues;

    public void kioskStart() {

        storePack();    // 포장 or 매장 여부
        menuDisp();

        // 선택값 체크
        SelectMenu selectMenu = new SelectMenu();
        int listSize = 4;
        int userSelect = selectMenu.menuSelect(listSize);
        menuRun(userSelect);

        // 선택값 리스트에 담기
        OrderSetting orderSetting = new OrderSetting();
        orderSetting.calculateOrderTotal();
        orderSetting.printOrderList();
    }

    public void storePack() {
        System.out.println("=============================");
        System.out.println("\t 1. 포장");
        System.out.println("\t 2. 매장");
        System.out.println("=============================");
        SelectMenu selectMenu = new SelectMenu();
        int listSize = 2;
        selectMenu.menuSelect(listSize);

        // 선택값 배열-유저 임시 이름 넣기
        int outerListSize = outerList.size();
        outerList.set(outerList.size()-1,new Order(USER_NAME + outerListSize, "yyyyMMddHHmmss", 0, 0));

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
        // userSelect를 ProductType으로 변환
        ProductTypeChange productTypeChange = new ProductTypeChange();
        ProductType productType = productTypeChange.ProductTypeChange(userSelect);

        switch (productType) { // DESC: swtich문의 조건에 String 타입을 넣게되면, case문에서 enum타입으로 비교할 수 가 없음
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
            case CANCEL:
                menuCancel();
                break;
        }

    }

    public void menuRcmd() {     // 사장추천
        System.out.println("\n1. 사장추천 -------------------------------------- ");
    }

    public void menuMySalad() {
        System.out.println("\n2. 나만의 샐러드 -------------------------------------- ");
        OrderValues orderValues;
        info.printInfo(ProductType.S_BASE);

        info.printInfo(ProductType.S_MAIN);

        info.printInfo(ProductType.S_SIDE);

        info.printInfo(ProductType.S_SOURCE);
    }

    public void menuDrink() {
        System.out.println("\n2. 음료 -------------------------------------- ");
        info.printInfo(ProductType.DRINK);
    }

    public void menuSide() {     // 사이드
        System.out.println("\n2. 사이드 -------------------------------------- ");
    }

    public void menuCancel() {   // 취소

    }
}
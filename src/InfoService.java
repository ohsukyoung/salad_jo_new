import java.io.BufferedReader;
import java.util.Iterator;
import java.util.List;

/*
 안내 ----------------------------------------------------------------
*/
interface Imp_info {
    UserProduct printInfo(ProductType productType);
}

class InfoService implements Imp_info {
    private BufferedReader br;
    private ProductService productService;

    public UserProduct printInfo(ProductType productType) {
        List<Product> productList = productService.getList(productType);

        printInfoHeader(productType);
        printInfoBody(productType, productList);

        // 유저 메뉴 숫자 선택
        SelectMenu sMenu = new SelectMenu();
        SelectCount sCount = new SelectCount();

        int userSelect = sMenu.menuSelect(productList.size());
        int pdStock = productList.get(userSelect - 1).getP_stock(); // 선택한 재고 개수
        int userStock = sCount.menuSelect(pdStock); // 유저 재고 개수 선택

        // 유저 선택값 만들기
        return new UserProduct(
                productList.get(userSelect - 1).getP_name(),    // 유저 선택 제품>이름
                userStock,                              // 유저 선택 개수
                productList.get(userSelect - 1).getP_calorie(),  // 유저 선택 제품>칼로리
                productList.get(userSelect - 1).getP_price()   // 유저 선택 제품>가격
        );
    }

    public void printInfoHeader(ProductType productType) {
        //TODO Dept 별로 구분
        System.out.printf("%-4s| %-8s|\t%-8s|\t%-8s|\t%-8s\t|\t%-8s\n", "번호", "상품명", "단위", "칼로리", "가격", "남은수량");
    }

    public void printInfoBody(ProductType productType, List<Product> productInfo) {
        Iterator<Product> citList = productInfo.iterator();
        for (int i = 1; i <= productInfo.size(); i++) {
            Product itS = citList.next();
            //TODO Dept 별로 구분
            System.out.printf("%-4d   %-8s \t%-8s \t%-8s\t \t%-8d \t%-8d\n", i, itS.getP_name(), itS.getP_unit(), itS.getP_calorie(), itS.getP_price(), itS.getP_stock());
        }
    }
}
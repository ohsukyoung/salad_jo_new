import java.io.BufferedReader;
import java.util.Iterator;
import java.util.List;

/*
 안내 ----------------------------------------------------------------
*/
interface Imp_info {
    void printInfo(ProductType productType);
}

class InfoService implements Imp_info {
    private BufferedReader br;
    private PdInterface pdInterface;

    // 생성자를 사용하여 productService 초기화
    public InfoService(ProductService productService) {
        this.pdInterface = productService;
    }

    public void printInfo(ProductType productType) {
        List<Product> productList = pdInterface.getList(productType);

        printInfoHeader(productType);
        printInfoBody(productType, productList);

        // 작성중
        OrderValues orderValues;
        System.out.println("---------");
        SelectContinue selectContinue = new SelectContinue();
        selectContinue.menuSelect(productList);
        //---

        System.out.println("---------");
    }

    public void printInfoHeader(ProductType productType) {

        if(productType != ProductType.RCMND)    // 사장추천이 아닐 경우
            System.out.printf("%-4s| %-8s|\t%-8s|\t%-8s|\t%-8s\t|\t%-8s\n", "번호", "상품명", "단위", "칼로리", "가격", "남은수량");
        else
            System.out.printf("%-4s| %-8s|\t%-8s|\t%-8s\t|\t%-8s\n", "번호", "상품명", "상세재료", "칼로리", "가격");
    }

    public void printInfoBody(ProductType productType, List<Product> productInfo) {
        if(productType != ProductType.RCMND){   // 사장추천이 아닐 경우
            int i=1;
            for(Product product : productInfo){
                if(product.getP_stock()!=0)
                    System.out.printf("%-4d   %-8s \t%-8s \t%-8s\t \t%-8d \t%-8d\n", i++, product.getP_name(), product.getP_unit(), product.getP_calorie(), product.getP_price(), product.getP_stock());
                else i++;
            }
        }
        else{
            Iterator<Product> citList = productInfo.iterator();
            for (int i = 1; i <= productInfo.size(); i++) {
                Product itS = citList.next();
                //TODO 사장추천 리스트 연결
//                System.out.printf("%-4d   %-8s \t%-8s\t \t%-8d \t%-8d\n", i, itS.getC_name(), itS.getC_detail(), itS.getC_calorie(), itS.getC_price());
            }
        }
    }
}
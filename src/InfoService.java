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
        if (productType != ProductType.RCMND){    // 사장추천이 아닐 경우
            List<Product> productList = pdInterface.getList(productType);

            printInfoHeader(productType);
            printInfoBodyProduct(productType, productList);

            // 선택한 값 저장
            SelectContinue selectContinue = new SelectContinue();
            selectContinue.menuSelectProduct(productList);
        }else {
            List<MasterRc> masterList = CacheData.list2;
            printInfoHeader(productType);
            printInfoBodyMaster(productType, masterList);

            // 선택한 값 저장
            SelectContinue selectContinue = new SelectContinue();
            selectContinue.menuSelectMasterRc(masterList);
        }
    }

    public void printInfoHeader(ProductType productType) {

        if(productType != ProductType.RCMND)    // 사장추천이 아닐 경우
            System.out.printf("%-4s| %-8s|\t%-8s|\t%-8s|\t%-8s\t|\t%-8s\n", "번호", "상품명", "단위", "칼로리", "가격", "남은수량");
        else
            System.out.printf("%-4s| %-8s|\t%-8s|\t%-8s\t|\t%-8s|\t%-8s\n", "번호", "상품명", "칼로리", "가격", "남은수량", "상세재료");
    }

    public void printInfoBodyMaster(ProductType productType, List<MasterRc> productInfo) {
        int index=1;
        for(MasterRc masterRc : productInfo){
            System.out.printf("%-4d   %-8s \t%-8d \t%-8d \t%-8d", index++, masterRc.getR_name(), masterRc.getR_totalcalorie(), masterRc.getR_price(), masterRc.getR_count());
            System.out.printf("\t");
            for (Product product : masterRc.getR_products())
            {
                System.out.print(" " + product.getP_name());
            }
            System.out.println();
        }
    }

    public void printInfoBodyProduct(ProductType productType, List<Product> productInfo) {
        if(productType != ProductType.RCMND){   // 사장추천이 아닐 경우
            int index=1;
            for(Product product : productInfo){
                if(product.getP_count()>product.getP_stock())
                    System.out.printf("%-4d   %-8s \t%-8s \t%-8s\t \t%-8d \t%-8d\n", index++, product.getP_name(), product.getP_unit(), product.getP_calorie(), product.getP_price(), product.getP_stock());
                else index++;
            }
        }
    }
}
import java.util.ArrayList;
import java.util.List;

/*
제품 셋팅 ----------------------------------------------------------------
*/
interface PdInterface {
    List<Product> productList = new ArrayList<>();

    List<Product> getList(ProductType productType);
}

class ProductService implements PdInterface {
    @Override
    public List<Product> getList(ProductType productType) {
        List<Product> result = new ArrayList<>();

        for (Product product : productList) {
            if (product.getType().equals(productType)) {
                result.add(product);
            }
        }

        return result;
    }
}
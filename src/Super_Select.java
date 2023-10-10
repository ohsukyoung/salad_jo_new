/*
 메뉴 선택 ----------------------------------------------------------------
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

interface Super_Select_Interface {
    int menuSelect(int listSize);
}

public abstract class Super_Select implements Super_Select_Interface {
    static BufferedReader br;
    protected String message;
    protected String errorMsg;
    int minNum;

    public int menuSelect(int listSize) {
        int userSelect = 0;
        br = new BufferedReader(new InputStreamReader(System.in));
        try {
            do {
                System.out.printf(message);
                userSelect = Integer.parseInt(br.readLine());
                if (userSelect < minNum || userSelect > listSize)
                    System.out.println(errorMsg);
            } while (userSelect < minNum || userSelect > listSize);
        } catch (IOException e) {
            System.out.println("e.toString: " + e.toString());
            System.out.println("e.getMessage: " + e.getMessage());
            System.out.println("printStackTrace................");
            e.printStackTrace();
        }
        return userSelect;
    }
}

// 작업중~~~
class SelectContinue extends Super_Select {
    public void menuSelectProduct(List<Product> productList){
        this.message    = "\t>> 메뉴를 추가 하시겠습니까?(Y/N): ";
        this.errorMsg   = "\t잘못입력했습니다. 다시 입력해주세요.";

        br = new BufferedReader(new InputStreamReader(System.in));
        InsertSelectValue insertSelectValue = new InsertSelectValue();
        List<OrderValues> orderInnerValues = CacheData.orderInnerValues;
        OrderValues orderValues = null;
        char answer;

        try {
            while (true){

                do {
                    System.out.printf(message);
                    answer = br.readLine().charAt(0);
                }while (answer != 'Y' && answer != 'y' && answer != 'N' && answer != 'n');
                if(answer == 'N' || answer == 'n') // N이거나 n이면 -> 반복문 빠져나가기
                    break;

                orderValues = insertSelectValue.insertSelectValueProduct(productList);
                orderInnerValues.add(orderValues);

                System.out.println(orderInnerValues);            // test 선택된 메뉴 출력

            }
        } catch (IOException e) {
            System.out.println("e.toString: " + e.toString());
            System.out.println("e.getMessage: " + e.getMessage());
            System.out.println("printStackTrace................");
            e.printStackTrace();
        }
    }

    public void menuSelectMasterRc(List<MasterRc> productList){
        this.message    = "\t>> 메뉴를 추가 하시겠습니까?(Y/N): ";
        this.errorMsg   = "\t잘못입력했습니다. 다시 입력해주세요.";

        br = new BufferedReader(new InputStreamReader(System.in));
        InsertSelectValue insertSelectValue = new InsertSelectValue();
        List<OrderValues> orderInnerValues = CacheData.orderInnerValues;
        OrderValues orderValues = null;
        char answer;

        try {
            while (true){

                do {
                    System.out.printf(message);
                    answer = br.readLine().charAt(0);
                }while (answer != 'Y' && answer != 'y' && answer != 'N' && answer != 'n');
                if(answer == 'N' || answer == 'n') // N이거나 n이면 -> 반복문 빠져나가기
                    break;

                orderValues = insertSelectValue.insertSelectValueMaster(productList);
                orderInnerValues.add(orderValues);

                System.out.println(orderInnerValues);            // test 선택된 메뉴 출력

            }
        } catch (IOException e) {
            System.out.println("e.toString: " + e.toString());
            System.out.println("e.getMessage: " + e.getMessage());
            System.out.println("printStackTrace................");
            e.printStackTrace();
        }
    }
}

class InsertSelectValue{
    public OrderValues insertSelectValueProduct(List<Product> productList){
        // 유저 메뉴 숫자 선택
        SelectMenu selectMenu = new SelectMenu();
        SelectCount selectCount = new SelectCount();

        int userSelect = selectMenu.menuSelect(productList.size());
        int totalPdCount = productList.get(userSelect - 1).getP_count(); // 선택한 재고 개수
        int pdCount = totalPdCount;

        List<OrderValues> orderInnerValues = CacheData.orderInnerValues;
        for (OrderValues orderValues: orderInnerValues){
            if(orderValues.getName().equals(productList.get(userSelect - 1).getP_name())){
                pdCount -= orderValues.getCount();
            }
        }

        int userStock = selectCount.menuSelect(pdCount); // 유저 재고 개수 선택

        // 유저 선택값 만들기
        return new OrderValues(
                productList.get(userSelect - 1).getP_name(),    // 유저 선택 제품>이름
                userStock,                                      // 유저 선택 개수
                productList.get(userSelect - 1).getP_calorie(), // 유저 선택 제품>칼로리
                productList.get(userSelect - 1).getP_price()    // 유저 선택 제품>가격
        );
    }

    public OrderValues insertSelectValueMaster(List<MasterRc> productList){
        // 유저 메뉴 숫자 선택
        SelectMenu selectMenu = new SelectMenu();
        SelectCount selectCount = new SelectCount();

        int userSelect = selectMenu.menuSelect(productList.size());
        int totalPdCount = productList.get(userSelect - 1).getR_count(); // 선택한 재고 개수
        int pdCount = totalPdCount;

        List<OrderValues> orderInnerValues = CacheData.orderInnerValues;
        for (OrderValues orderValues: orderInnerValues){
            if(orderValues.getName().equals(productList.get(userSelect - 1).getR_name())){
                pdCount -= orderValues.getCount();
            }
        }

        int userStock = selectCount.menuSelect(pdCount); // 유저 재고 개수 선택

//        System.out.println(orderInnerValues);            // test 선택된 메뉴 출력

        // 유저 선택값 만들기
        return new OrderValues(
                productList.get(userSelect - 1).getR_name(),    // 유저 선택 제품>이름
                userStock,                                      // 유저 선택 개수
                productList.get(userSelect - 1).getR_totalcalorie(),  // 유저 선택 제품>칼로리
                productList.get(userSelect - 1).getR_price()   // 유저 선택 제품>가격
        );
    }
}

// 메뉴 선택
class SelectMenu extends Super_Select {
    public SelectMenu() {
        this.message    = "\t>> 메뉴 선택: ";
        this.errorMsg   = "\t메뉴 리스트 번호에서 벗어났습니다. 다시 입력해주세요.";
        this.minNum = 1;
    }

    @Override
    public int menuSelect(int listSize) {
        return super.menuSelect(listSize);
    }
}

// 개수 선택
class SelectCount extends Super_Select {
    public SelectCount() {
        this.message    = "\t>> 수량 선택: ";
        this.errorMsg   = "\t남은 수량에서 벗어났습니다. 다시 입력해주세요.";
        this.minNum = 0;
    }

    @Override
    public int menuSelect(int listSize) {
        return super.menuSelect(listSize);
    }

}
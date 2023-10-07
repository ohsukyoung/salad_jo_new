import java.io.BufferedReader;
import java.util.Iterator;
import java.util.List;

/*
 안내 ----------------------------------------------------------------
*/
interface Impl_Info {
    public List<UserProduct> menuInfo(List<UserProduct> mRInner);     // 메뉴선택
}

abstract class Super_InfoPd implements Impl_Info {
    private static BufferedReader br;
    List<Product> mList = new PdSetting().getsBaseList();
    Iterator<Product> citList;

    int userSelect = 0; // 유저 선택
    int userStock = 0; // 유저 수량 개수
    int pdStock = 0; // 재고 개수



    public void infoHeader(){
        System.out.printf("%-4s| %-8s|\t%-8s|\t%-8s|\t%-8s\t|\t%-8s\n", "번호", "상품명", "단위", "칼로리", "가격", "남은수량");
    }

    public void infoBody(){
        citList = mList.iterator();
        for (int i=1; i<=mList.size();i++){
            Product itS = citList.next();
            System.out.printf("%-4d   %-8s \t%-8s \t%-8s\t \t%-8d \t%-8d\n", i, itS.getP_name(), itS.getP_unit(), itS.getP_calorie(), itS.getP_price(), itS.getP_stock());
        }
    }

    @Override
    public List<UserProduct> menuInfo(List<UserProduct> mRInner){
        SelectMenu sMenu = new SelectMenu();
        SelectCount sCount = new SelectCount();

        infoHeader();   // 정보 표 헤더
        infoBody();     // 정보 표 바디

        // 유저 메뉴 숫자 선택
        userSelect = sMenu.menuSelect(mList.size());

        pdStock = mList.get(userSelect-1).getP_stock(); // 선택한 재고개수
        userStock = sCount.menuSelect(pdStock); // 유저 재고 개수 선택

        // 유저 선택값넣기
        mRInner.add(new UserProduct(
                mList.get(userSelect-1).getP_name(),    // 유저 선택 제품>이름
                userStock,                              // 유저 선택 개수
                mList.get(userSelect-1).getP_calorie(),  // 유저 선택 제품>칼로리
                mList.get(userSelect-1).getP_price()   // 유저 선택 제품>가격
        ));


        /*
        // 재고 빼기
        mList.get(userSelect-1).setP_stock(pdStock - userStock);

        // 테스트를 위한 코드
        pdStock = mList.get(userSelect-1).getP_stock(); // 선택한 재고개수
        System.out.println(pdStock);*/

        return mRInner;
    }
}

abstract class Super_InfoCeo implements Impl_Info {
    private static BufferedReader br;
    List<CeoRcmd> cList = new PdSetting().getCeoList();
    Iterator<CeoRcmd> citList;

    int userSelect = 0; // 유저 선택
    int userStock = 0; // 유저 수량 개수
    int pdStock = 0; // 재고 개수

    public void infoHeader(){
        System.out.printf("%-4s| %-8s|\t%-8s|\t%-8s\t|\t%-8s\n", "번호", "상품명", "상세재료", "칼로리", "가격");
    }

    public void infoBody(){
        citList = cList.iterator();
        for (int i=1; i<=cList.size();i++){
            CeoRcmd itS = citList.next();
            System.out.printf("%-4d   %-8s \t%-8s\t \t%-8d \t%-8d\n", i, itS.getC_name(), itS.getC_detail(), itS.getC_calorie(), itS.getC_price());
        }
    }

    @Override
    public List<UserProduct> menuInfo(List<UserProduct> mRInner){
        SelectMenu sMenu = new SelectMenu();
        SelectCount sCount = new SelectCount();

        infoHeader();   // 정보 표 헤더
        infoBody();     // 정보 표 바디

        // 유저 메뉴 숫자 선택
        userSelect = sMenu.menuSelect(cList.size());

        mRInner.add(new UserProduct(
                cList.get(userSelect-1).getC_name(),    // 유저 선택 제품>이름
                userStock,                              // 유저 선택 개수
                cList.get(userSelect-1).getC_calorie(),  // 유저 선택 제품>칼로리
                cList.get(userSelect-1).getC_price()   // 유저 선택 제품>가격
        ));
        return mRInner;
    }
}

class dep1_infoCeo extends Super_InfoCeo {

    @Override
    public List<UserProduct> menuInfo(List<UserProduct> mRInner){
        super.menuInfo(mRInner);

        return mRInner;
    }
}



// 2dep print ----------------------------------------------------------------
class dep2_infoBase extends Super_InfoPd {
    @Override
    public List<UserProduct> menuInfo(List<UserProduct> mRInner){
        System.out.println("\t\t\t\t[ 베이스 ■ ■ ■ ■ ]");
        mList = new PdSetting().getsBaseList();
        super.menuInfo(mRInner);

//        = userSelect;

//        System.out.println(mList.get(pdStock).getP_calorie());

        return mRInner;
    }
}
class dep2_infoMain extends Super_InfoPd {
    @Override
    public List<UserProduct> menuInfo(List<UserProduct> mRInner){
        System.out.println("\t\t\t\t[ ■ 메인토핑 ■ ■ ■ ]");
        mList = new PdSetting().getsMainList();
        super.menuInfo(mRInner);

        return mRInner;
    }
}
class dep2_infoSide extends Super_InfoPd {
    @Override
    public List<UserProduct> menuInfo(List<UserProduct> mRInner){
        System.out.println("\t\t\t\t[ ■ ■ 사이드토핑 ■ ■ ]");
        mList = new PdSetting().getsSideList();
        super.menuInfo(mRInner);

        return mRInner;
    }
}
class dep2_infoSource extends Super_InfoPd {

    @Override
    public List<UserProduct> menuInfo(List<UserProduct> mRInner){
        System.out.println("\t\t\t\t[ ■ ■ ■ 소스 ■ ]");
        mList = new PdSetting().getsSourceList();
        super.menuInfo(mRInner);

        return mRInner;
    }
}
class dep2_infoCheese extends Super_InfoPd {
    @Override
    public List<UserProduct> menuInfo(List<UserProduct> mRInner){
        System.out.println("\t\t\t\t[ ■ ■ ■ ■ 치즈 ]");
        mList = new PdSetting().getsCheeseList();
        super.menuInfo(mRInner);

        return mRInner;
    }
}

class dep1_infoDrink extends Super_InfoPd {
    @Override
    public void infoHeader(){
        System.out.printf("%-4s| %-8s|\t%-8s|\t%-8s\t|\t%-8s\n", "번호", "상품명", "칼로리", "가격", "남은수량");
    }
    @Override
    public void infoBody(){
        citList = mList.iterator();
        for (int i=1; i<=mList.size();i++){
            Product itS = citList.next();
            System.out.printf("%-4d   %-8s \t%-8s\t \t%-8s \t%-8s\n", i, itS.getP_name(), itS.getP_calorie(), itS.getP_price(), itS.getP_stock());
        }
    }

    @Override
    public List<UserProduct> menuInfo(List<UserProduct> mRInner){
        mList = new PdSetting().getDrinkList();
        super.menuInfo(mRInner);

        return mRInner;
    }
}

class dep1_infoSide extends Super_InfoPd {
    @Override
    public void infoHeader(){
        System.out.printf("%-4s| %-8s|\t%-8s|\t%-8s\t|\t%-8s\n", "번호", "상품명", "칼로리", "가격", "남은수량");
    }
    @Override
    public void infoBody(){
        citList = mList.iterator();
        for (int i=1; i<=mList.size();i++){
            Product itS = citList.next();
            System.out.printf("%-4d   %-8s \t%-8s\t \t%-8s \t%-8s\n", i, itS.getP_name(), itS.getP_calorie(), itS.getP_price(), itS.getP_stock());
        }
    }

    @Override
    public List<UserProduct> menuInfo(List<UserProduct> mRInner){
        mList = new PdSetting().getSideList();
        super.menuInfo(mRInner);

        return mRInner;
    }
}
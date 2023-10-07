import java.util.ArrayList;
import java.util.List;

/*
제품 셋팅 ----------------------------------------------------------------
*/
enum Material{
    S_BASE("베이스"), S_MAIN("메인토핑"), S_SIDE("사이드토핑"), S_SOURCE("소스"), S_CHEESE("치즈");

    private String material;

    // 생성자(싱글톤)
    private Material(String material){
        this.material = material;
    }

    // getter
    public String getMaterial(){
        return material;
    }
}
class PdSetting{
    //S_BASE("베이스"), S_MAIN("메인토핑"), S_SIDE("사이드토핑"), S_SOURCE("소스"), S_CHEESE("치즈");
    // 나만의 샐러드 -------------------------------
    private List<Product> sBaseList = new ArrayList<Product>();         // 제품>베이스 ArrayList
    private List<Product> sMainList = new ArrayList<Product>();         // 제품>메인토핑 ArrayList
    private List<Product> sSideList = new ArrayList<Product>();         // 제품>사이드토핑 ArrayList
    private List<Product> sSourceList = new ArrayList<Product>();       // 제품>소스 ArrayList
    private List<Product> sCheeseList = new ArrayList<Product>();       // 제품>치즈 ArrayList
    // 사장추천 -------------------------------
    private List<CeoRcmd> ceoList = new ArrayList<CeoRcmd>();    // 제품>사장님추천 ArrayList
    // 음료 -------------------------------
    private List<Product> drinkList = new ArrayList<Product>();     // 제품>음료 ArrayList
    // 사이드 -------------------------------
    private List<Product> sideList = new ArrayList<Product>();      // 제품>사이드 ArrayList
    // 회원 -------------------------------
//    private Map<String,Member> mbMap = new HashMap<String,Member>();            // 멤버 ArrayList

    //testProductData
    public PdSetting(){
        // 나만의 샐러드
        setS_Base();
        setS_Main();
        setS_Side();
        setS_Source();
        setS_Cheese();

        //사장추천
        set_Ceo();

        // 음료
        set_Drink();

        //사이드
        set_Side();
    }

    /*
    1. 양상추, 오이, 토마토, 양파
    2. 닭고기, 소고기, 연어, 우삼겹, 베이컨
    3. 토마토, 올리브, 할라피뇨, 새우, 당근, 오이
    //4. 양송이 크림스프, 콘치즈 스프
    4. 오리엔탈, 발사믹, 시저, 크리미, 칠리, 마요네즈
    5. 아메리칸, 모짜렐라, 리코타, 부라타
     */
//        pdMap.put("이름",new Product(분류번호, 단위, 개수, 칼로리, 적정재고, 금액));
    void setS_Base(){
        sBaseList.add(new Product(Material.S_BASE.ordinal(), "양상추", "1", 100, 200, 5, 400));
        sBaseList.add(new Product(Material.S_BASE.ordinal(), "오이", "1", 100, 200, 4, 400));
        sBaseList.add(new Product(Material.S_BASE.ordinal(), "토마토", "1", 100, 200, 3, 400));
        sBaseList.add(new Product(Material.S_BASE.ordinal(), "양파", "1", 100, 200, 1, 400));
    }
    void setS_Main(){
        sMainList.add(new Product(Material.S_MAIN.ordinal(), "닭고기", "1", 100, 200, 5, 400));
        sMainList.add(new Product(Material.S_MAIN.ordinal(), "소고기", "1", 100, 200, 5, 400));
        sMainList.add(new Product(Material.S_MAIN.ordinal(), "연어", "1", 100, 200, 5, 400));
        sMainList.add(new Product(Material.S_MAIN.ordinal(), "우삼겹", "1", 100, 200, 5, 400));
        sMainList.add(new Product(Material.S_MAIN.ordinal(), "계란", "1", 100, 200, 5, 400));
    }
    void setS_Side(){
        sSideList.add(new Product(Material.S_SIDE.ordinal(), "토마토", "1", 100, 200, 5, 400));
        sSideList.add(new Product(Material.S_SIDE.ordinal(), "올리브", "1", 100, 200, 5, 400));
        sSideList.add(new Product(Material.S_SIDE.ordinal(), "크렌베리", "1", 100, 200, 5, 400));
        sSideList.add(new Product(Material.S_SIDE.ordinal(), "새우", "1", 100, 200, 5, 400));
        sSideList.add(new Product(Material.S_SIDE.ordinal(), "옥수수", "1", 100, 200, 5, 400));
        sSideList.add(new Product(Material.S_SIDE.ordinal(), "오이", "1", 100, 200, 5, 400));
    }
    void setS_Source(){
        sSourceList.add(new Product(Material.S_SOURCE.ordinal(), "오리엔탈","1", 100, 200, 5, 400));
        sSourceList.add(new Product(Material.S_SOURCE.ordinal(), "발사믹","1", 100, 200, 5, 400));
        sSourceList.add(new Product(Material.S_SOURCE.ordinal(), "시저","1", 100, 200, 5, 400));
        sSourceList.add(new Product(Material.S_SOURCE.ordinal(), "크리미","1", 100, 200, 5, 400));
        sSourceList.add(new Product(Material.S_SOURCE.ordinal(), "칠리","1", 100, 200, 5, 400));
        sSourceList.add(new Product(Material.S_SOURCE.ordinal(), "마요네즈","1", 100, 200, 5, 400));
    }
    void setS_Cheese(){
        sCheeseList.add(new Product(Material.S_CHEESE.ordinal(), "아메리칸","1", 100, 200, 5, 400));
        sCheeseList.add(new Product(Material.S_CHEESE.ordinal(), "모짜렐라","1", 100, 200, 5, 400));
        sCheeseList.add(new Product(Material.S_CHEESE.ordinal(), "리코타","1", 100, 200, 5, 400));
        sCheeseList.add(new Product(Material.S_CHEESE.ordinal(), "부라타","1", 100, 200, 5, 400));
    }

    void set_Ceo(){
        ceoList.add(new CeoRcmd("시저치킨샐러드",new CeoDetail("닭고기","크렌베리","시저"),200,20));
        ceoList.add(new CeoRcmd("콥샐러드",new CeoDetail("계란","옥수수","시저"),300,30));
        ceoList.add(new CeoRcmd("연어샐러드",new CeoDetail("연어","토마토","크리미"),400,40));
    }

    void set_Drink(){
        drinkList.add(new Product("콜라", 101, 201, 5, 401));
        drinkList.add(new Product("사이다", 102, 202, 5, 402));
        drinkList.add(new Product("콜라Zero", 103, 203, 5, 403));
        drinkList.add(new Product("사이다Zero", 104, 204, 5, 404));
    }
    void set_Side(){
        sideList.add(new Product("양송이스프", 100, 200, 5, 400));
        sideList.add(new Product("콘치즈스프", 100, 200, 5, 400));
    }

    //getter
    public List<Product> getsBaseList() { return sBaseList; }
    public List<Product> getsMainList() { return sMainList; }
    public List<Product> getsSideList() { return sSideList; }
    public List<Product> getsSourceList() { return sSourceList; }
    public List<Product> getsCheeseList() { return sCheeseList; }

    public List<CeoRcmd> getCeoList() { return ceoList; }
    public List<Product> getDrinkList() { return drinkList; }
    public List<Product> getSideList() { return sideList; }

//    public Map<String, Member> getMbMap() { return mbMap; }

    // setter
    public void setsBaseList(List<Product> sBaseList) { this.sBaseList = sBaseList; }
    public void setsMainList(List<Product> sMainList) { this.sMainList = sMainList; }
    public void setsSideList(List<Product> sSideList) { this.sSideList = sSideList; }
    public void setsSourceList(List<Product> sSourceList) { this.sSourceList = sSourceList; }
    public void setsCheeseList(List<Product> sCheeseList) { this.sCheeseList = sCheeseList; }

    public void setCeoList(List<CeoRcmd> ceoList) { this.ceoList = ceoList; }
    public void setDrinkList(List<Product> drinkList) { this.drinkList = drinkList; }
    public void setSideList(List<Product> sideList) { this.sideList = sideList; }

//    public void setMbMap(Map<String, Member> mbMap) { this.mbMap = mbMap; }
}
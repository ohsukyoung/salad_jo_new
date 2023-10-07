// 사장 추천 -------------------------------
class CeoDetail{
    private String c_dt01;
    private String c_dt02;
    private String c_dt03;

    public CeoDetail(String c_dt01, String c_dt02, String c_dt03) {
        this.c_dt01 = c_dt01;
        this.c_dt02 = c_dt02;
        this.c_dt03 = c_dt03;
    }

    public String getC_dt01() { return c_dt01; }

    public void setC_dt01(String c_dt01) { this.c_dt01 = c_dt01; }

    public String getC_dt02() { return c_dt02; }

    public void setC_dt02(String c_dt02) { this.c_dt02 = c_dt02; }

    public String getC_dt03() { return c_dt03; }

    public void setC_dt03(String c_dt03) { this.c_dt03 = c_dt03; }

    @Override
    public String toString() {
        return "{"+ c_dt01 + ',' + c_dt02 + ',' + c_dt03 + '}';
    }
}

class CeoRcmd{
    private String c_name;      // 사장님추천메뉴명
    private CeoDetail c_detail; // 상세재료
    private int c_calorie;      // 칼로리
    private int c_price;        // 가격

    CeoRcmd(String c_name, CeoDetail c_detail, int c_calorie, int c_price){
        this.c_name = c_name;
        this.c_detail = c_detail;
        this.c_calorie = c_calorie;
        this.c_price = c_price;
    }

    // getter, setter
    public String getC_name() { return c_name; }
    public void setC_name(String c_name) { this.c_name = c_name; }
    public CeoDetail getC_detail() { return c_detail; }
    public void setC_detail(CeoDetail c_detail) { this.c_detail = c_detail; }
    public int getC_calorie() { return c_calorie; }
    public void setC_calorie(int c_calorie) { this.c_calorie = c_calorie; }
    public int getC_price() { return c_price; }
    public void setC_price(int c_price) { this.c_price = c_price; }
}
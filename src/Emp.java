/*
 직원 안내 메세지 ----------------------------------------------------------------
*/
public class Emp {
    private String staff;
    public Emp(String staff){
        this.staff = staff;
    }
    public void empWelcome(){
        System.out.println(staff + "안녕하세요. [샐러드조]입니다.\n 방문해주셔서 감사합니다.");
    }
}

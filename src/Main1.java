/*

import java.util.HashMap;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Main1
{
    public static void main(String[] args) throws IOException, ClassNotFoundException
    {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        FileMg f = new FileMg();
        // 객체 파일 불러들이기
        MemberMg.hm = f.memberFileIn();
        SalesMg.receipts = f.receiptFileIn();


        // 관리자 로그인 폼
        ad_login al = new ad_login();
        al.adLogin();

        // 객체 파일 내보내기
        f.memberFileOut();
        f.receiptFileOut();


    }
}
*/

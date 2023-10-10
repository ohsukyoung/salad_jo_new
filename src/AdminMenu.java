import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.util.List;
import java.util.ArrayList;

public class AdminMenu
{
    public void printproduct() throws IOException
    {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("[[재료관리]]===============================================================================");
        System.out.printf("1.재고\n2.재료 등록\n3.회원 변경\n4.매출\n");
        System.out.println("===========================================================================================");
        for (; ; )
        {
            System.out.println("[[재료관리]]===============================================================================");
            System.out.printf("1.재료목록출력\n2.신규재료 등록\n3.재료정보 변경\n4.재료정보 삭제\n");
            System.out.println("===========================================================================================");
            while (true)
            {
                System.out.print("선택할 메뉴의 숫자를 입력하시오 : ");
                int a = Integer.parseInt(br.readLine());
                switch (a)
                {
                    case 1 :
                        // 재료목록 출력 메서드
                        break;
                    case 2 :
                        // 신규재료 등록 클래스
                        break;
                    case 3 :
                        // 재료정보 변경 클래스
                        break;
                    case 4 :
                        // 재료정보 삭제 클래스
                        break;
                    default :
                        System.out.println("입력된 항목이 없습니다.");
                }
            }
        }

    }
}
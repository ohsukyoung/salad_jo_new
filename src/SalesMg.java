import java.io.*;
import java.util.*;

class Menus
{
    public static final int E_CP = 1; // 결제 취소
    public static final int E_REC = 2; // 영수증 출력
    public static final int E_SC = 3; // 매출조회
    public static final int E_EX = 4; // 종료
}

public class SalesMg
{
    static ArrayList<Receipt> receipts = new ArrayList<>();
    private static BufferedReader br;
    private static Integer sel;

    static
    {
        br = new BufferedReader(new InputStreamReader(System.in));
        sel = 1;
    }

    public static void menuDisp()
    {
        System.out.println("---------매출 관리 시스템---------");
        System.out.println("1. 결제 취소");
        System.out.println("2. 영수증 출력");
        System.out.println("3. 매출 조회");
        System.out.println("4. 종료");
        System.out.println("----------------------------------");
        System.out.print("메뉴를 선택하세요: ");
    }

    public static void menuSelect() throws IOException, NumberFormatException
    {
        sel = Integer.parseInt(br.readLine());
    }

    public static void menuRun() throws IOException
    {
        switch (sel)
        {
            case Menus.E_CP : cancelPayment(); break;
            case Menus.E_REC : receipt(); break;
            case Menus.E_SC : salesCheck(); break;
            case Menus.E_EX : exit(); break;
            default:
                System.out.println("메뉴 선택 오류~!!!");
        }
    }

    public static void cancelPayment() throws IOException
    {
        System.out.println();
        System.out.println("-------------결제 취소------------");
        System.out.print("년 입력: ");
        int cancelYear = Integer.parseInt(br.readLine());
        System.out.print("월 입력: ");
        int cancelMonth = Integer.parseInt(br.readLine());
        System.out.print("일 입력: ");
        int cancelDay = Integer.parseInt(br.readLine());
        System.out.println("----------------------------------");

        boolean foundReceipt = false;

        int receiptIndex = 1;

        for (Receipt receipt : receipts)
        {
            if (receipt.getYear() == cancelYear && receipt.getMonth() == cancelMonth && receipt.getDay() == cancelDay)
            {
                System.out.println("구분번호: " + receiptIndex);
                System.out.println("회원 여부: " + (receipt.isMember() ? "회원" : "비회원"));
                if (receipt.isMember())
                    System.out.println("회원 아이디 : " + receipt.getMemberId());
                else
                    System.out.println("회원이 아닙니다");
                System.out.println("포인트 사용: " + receipt.getUsedPoints());
                System.out.println("결제 수단: " + receipt.getPaymentMethod());
                System.out.println("결제 금액: " + receipt.getTotalAmount());
                System.out.println();
                foundReceipt  = true;
                receiptIndex++;
            }
        }




        if (foundReceipt)
        {
            System.out.print("결제 취소할 구분 번호를 입력하세요: ");
            int selectedReceiptIndex = Integer.parseInt(br.readLine());

            // 사용자가 입력한 번호에 해당하는 결제 내역을 가져옴
            if (selectedReceiptIndex >= 1 && selectedReceiptIndex <= receiptIndex - 1)
            {
                Receipt selectedReceipt = null;
                int currentIndex = 1;

                for (Receipt receipt : receipts)
                {
                    if (receipt.getYear() == cancelYear && receipt.getMonth() == cancelMonth && receipt.getDay() == cancelDay)
                    {
                        if (currentIndex == selectedReceiptIndex)
                        {
                            selectedReceipt = receipt;
                            break;
                        }
                        currentIndex++;
                    }
                }


                if (selectedReceipt != null)
                {
                    int returnedPoints = selectedReceipt.getUsedPoints();

                    // TODO: 사용자의 포인트를 증가시키는 로직을 추가하세요
                    if (selectedReceipt.isMember()==true)
                    {
                        String id = selectedReceipt.getMemberId();
                        int memPoint = MemberMg.hm.get(id).getMemPoint()+returnedPoints;
                        MemberMg.hm.get(id).setMemPoint(memPoint);
                    }


                    // 선택된 결제 내역을 삭제
                    receipts.remove(selectedReceipt);

                    System.out.println();
                    System.out.println("-------------결제 취소 내역------------");
                    System.out.println("구분번호: " + selectedReceiptIndex);
                    System.out.println("회원 여부: " + (selectedReceipt.isMember() ? "회원" : "비회원"));
                    if (selectedReceipt.isMember())
                        System.out.println("회원 아이디 : " + selectedReceipt.getMemberId());
                    else
                        System.out.println("회원이 아닙니다");
                    System.out.println("포인트 사용: " + selectedReceipt.getUsedPoints());
                    System.out.println("결제 수단: " + selectedReceipt.getPaymentMethod());
                    System.out.println("결제 금액: " + selectedReceipt.getTotalAmount());
                    System.out.println("반환된 포인트: " + returnedPoints);
                    System.out.println("---------------------------------------");
                    System.out.println("결제가 취소되었습니다.");


                }

            }
            else
                System.out.println("올바른 구분 번호를 입력하세요.");
        }

        else
            System.out.println("해당 일자에 결제 내역이 없습니다.");
        System.out.println();
    }



    public static void receipt() throws IOException
    {
        System.out.println("-----------영수증 출력------------");
        System.out.print("년 입력: ");
        int receiptYear = Integer.parseInt(br.readLine());
        System.out.print("월 입력: ");
        int receiptMonth = Integer.parseInt(br.readLine());
        System.out.print("일 입력: ");
        int receiptDay = Integer.parseInt(br.readLine());
        System.out.print("시간대 입력 : ");
        int receiptHour = Integer.parseInt(br.readLine());
        System.out.println("----------------------------------");

        int receiptIndex = 1;

        boolean foundReceipt = false;

        // 해당 일자의 영수증 내역 출력
        for (Receipt receipt : receipts)
        {
            if (receipt.getYear() == receiptYear && receipt.getMonth() == receiptMonth && receipt.getDay() == receiptDay && receipt.getHour() == receiptHour)
            {
                System.out.println("구분번호: " + receiptIndex);
                System.out.println("회원 여부: " + (receipt.isMember() ? "회원" : "비회원"));
                System.out.println("포인트 사용: " + receipt.getUsedPoints());
                System.out.println("결제 수단: " + receipt.getPaymentMethod());
                System.out.println("결제 금액: " + receipt.getTotalAmount());
                System.out.println();
                receiptIndex++;
                foundReceipt  = true;
            }
        }

        // 사용자가 구분번호 입력하면 그 구분번호의 맞는 영수증 내역 출력
        if (foundReceipt)
        {
            System.out.print("출력할 영수증의 구분번호를 입력하세요 : ");
            int selectedReceiptIndex = Integer.parseInt(br.readLine());
            System.out.println();

            receiptIndex = 1;

            for (Receipt receipt : receipts)
            {
                if (receipt.getYear() == receiptYear && receipt.getMonth() == receiptMonth && receipt.getDay() == receiptDay && receipt.getHour() == receiptHour)
                {
                    if (receiptIndex == selectedReceiptIndex)
                    {
                        System.out.println("--------------------------------------");
                        System.out.println("구분번호: " + receiptIndex);
                        System.out.println("회원 여부: " + (receipt.isMember() ? "회원" : "비회원"));
                        System.out.println("포인트 사용: " + receipt.getUsedPoints());
                        System.out.println("결제 수단: " + receipt.getPaymentMethod());
                        System.out.println("결제 금액: " + receipt.getTotalAmount());
                        System.out.println("영수증 출력이 완료 되었습니다~!!!");
                        System.out.println("--------------------------------------");
                        System.out.println();
                        break;
                    }
                    receiptIndex++;
                }
            }
        }

        else
        {
            System.out.println("입력한 날짜와 시간대에 해당하는 영수증이 없습니다.");
            System.out.println();
        }
    }

    public static void salesCheck()throws IOException
    {
        System.out.println("-------------매출 조회------------");
        System.out.println("1. 년 매출 조회");
        System.out.println("2. 월 매출 조회");
        System.out.println("3. 일 매출 조회");
        System.out.println("----------------------------------");
        System.out.print("메뉴를 선택하세요: ");
        int reportChoice = Integer.parseInt(br.readLine());

        switch (reportChoice)
        {
            case 1:
                System.out.print("년 입력: ");
                int salesYear = Integer.parseInt(br.readLine());
                System.out.println();
                double YearlySales = 0.0;

                // 연도별 매출 메소드
                for (Receipt receipt : receipts)
                {
                    if (receipt.getYear() == salesYear)
                    {
                        YearlySales += receipt.getTotalAmount();
                    }
                }
                System.out.printf("----------%d년 매출 조회-----------\n", salesYear);
                System.out.println(salesYear + "년 매출 현황: " + YearlySales + "원");
                System.out.println("년 매출 조회가 완료되었습니다.");
                System.out.println("-------------------------------------");
                System.out.println();
                break;

            case 2:
                System.out.print("년 입력: ");
                int monthlySalesYear = Integer.parseInt(br.readLine());
                System.out.print("월 입력: ");
                int monthlySalesMonth = Integer.parseInt(br.readLine());
                System.out.println();
                double MonthlySales = 0.0;

                for (Receipt receipt : receipts)
                {
                    if (receipt.getYear() == monthlySalesYear  && receipt.getMonth() == monthlySalesMonth )
                    {
                        MonthlySales += receipt.getTotalAmount();
                    }
                }
                System.out.printf("----------%d년 %d월 매출 조회----------\n", monthlySalesYear, monthlySalesMonth);
                System.out.println(monthlySalesYear + "년 " + monthlySalesMonth + "월 매출 현황: " + MonthlySales + "원");
                System.out.println("월 매출 조회가 완료되었습니다.");
                System.out.println("-----------------------------------------");
                System.out.println();
                break;

            case 3:
                System.out.print("년 입력: ");
                int dailySalesYear = Integer.parseInt(br.readLine());
                System.out.print("월 입력: ");
                int dailySalesMonth = Integer.parseInt(br.readLine());
                System.out.print("일 입력: ");
                int dailySalesDay = Integer.parseInt(br.readLine());
                System.out.println();
                double dailySales = 0.0;

                // 일별 매출 메소드
                for (Receipt receipt : receipts)
                {
                    if (receipt.getYear() == dailySalesYear && receipt.getMonth() == dailySalesMonth && receipt.getDay() == dailySalesDay)
                    {
                        dailySales += receipt.getTotalAmount();
                    }
                }
                System.out.printf("----------%d년 %d월 %d일 매출 조회-----------\n", dailySalesYear, dailySalesMonth, dailySalesDay);
                System.out.println(dailySalesYear + "년 " + dailySalesMonth + "월 " + dailySalesDay + "일 매출 현황: " + dailySales + "원");
                System.out.println("일 매출 조회가 완료되었습니다.");
                System.out.println("----------------------------------------------");
                System.out.println();
                break;

        }
    }

    public static void exit()
    {
        System.out.println();
        System.out.println("\n\t관리자 메뉴로 돌아갑니다.");
        KioskMg.salesflag = false;
    }

}
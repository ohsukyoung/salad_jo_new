import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;

import java.util.ArrayList;
import java.util.List;

import java.util.HashMap;

import java.util.Date;
import java.text.SimpleDateFormat;

//장바구니 및 결제수단 클래스
class cartMe
{
    static final int e_del = 1; //장바구니 비우기
    static final int e_pay = 2; //결제
    static final int e_add = 3; //추가 주문
}

// 장바구니 클래스
class cart
{

    private static String memPw;
    //private static final ArrayList<Object> al;  //자료구조
    private static BufferedReader br;           //입력 시 활용
    private static String con;                  //Y,N으로 계속 진행 여부
    private static Integer sel;                 //선택값
    static String id;
    private static int emptypay;
    private static boolean shouldExit = false;  // 종료 여부를 나타내는 변수

    static final double pointad = 0.10;            //10%씩 적립할 변수

    static
    {
        //al = new ArrayList<Object>();                               //ArrayList 자료구조 생성
        br = new BufferedReader(new InputStreamReader(System.in));  //BufferedReader 객체 생성
        sel = 1;                                                    //사용자 입력값 초기화
    }

    // 사용자가 장바구니를 종료하도록 선택한 경우 호출
    public static void exitCart()
    {
        shouldExit = true;
        System.exit(0);
    }

    private static int tot;
    public static int getTot()
    {
        if (tot<0)
        {
            return -1;
        }
        return tot;
    }

//    private static HashMap<String,Member> hm = new HashMap<String,Member>();
    private static HashMap<String,Member> hm = MemberMg.hm;
    Member m = new Member();

    static int py=0;
    static int wan=0;



    //수경---
    static OrderSetting oSetting = new OrderSetting();


    //장바구니 출력 메소드
    public static void menuDis()
    {
        List<Order> OrderList = CacheData.orderOuterList;
        //선택 재료들, 칼로리 총합, 금액 총합 보여주기
        //oSetting.set_Print(outerList);

        System.out.println("\n\t[장바구니]===============");
        System.out.println("\t1. 장바구니 비우기");
        System.out.println("\t2. 결제");
        System.out.println("\t3. 추가 주문");
        System.out.println("\t=========================");
        System.out.print("\t>>장바구니(1-3) : ");
    }

    //메뉴 선택 메소드
    public static void menuSel() throws IOException
    {
        List<Order> OrderList = CacheData.orderOuterList;
        try
        {
            sel = Integer.parseInt(br.readLine());
        }
        catch (NumberFormatException e)
        {
            System.out.println("숫자를 넣어주세요.");
            menuDis();
            menuSel();
            menuR();
        }
    }

    //선택된 메뉴 실행에 따른 기능 호출 메소드
    public static void menuR() throws IOException
    {
        List<Order> OrderList = CacheData.orderOuterList;
        //cartMe() 메소드 활용하여 처리
        switch (sel)
        {
            case cartMe.e_del : cartdel() ; break;
            case cartMe.e_pay : cartpay() ; break;
            case cartMe.e_add : cartadd() ; break;
            default : System.out.println("\n\t메뉴 선택 오류");
        }
        if (shouldExit) {
            System.out.println("\n\t장바구니를 종료합니다.");
            return;
        }
    }

    //자료구조에 장바구니 비우기 메소드
    public static void cartdel() throws IOException
    {
        List<Order> OrderList = CacheData.orderOuterList;
        System.out.print("\n\t장바구니를 비우시겠습니까(Y/N)? : ");
        con = br.readLine().toUpperCase();

        if (con.equals("Y"))                                        //장바구니를 비우기로 했으면 자료구조 비우기
        {
            OrderList.clear();  // 장바구니를 비우기 (모든 주문 정보 제거) //TODO 장바구니 비운 후 재고 마이너스 되지 않게 하기
//            OrderList.add(new Order());
            System.out.println();
            System.out.println("\t>>장바구니를 비웠습니다.<<");
            System.out.println();

            ProductService productService = new ProductService(); // ProductService 객체 생성

            Kiosk ks = new Kiosk(productService);                   //장바구니 비운 후 다시 메뉴 선택창으로 가기
            ks.kioskStart();
        }
        else if (con.equals("N"))                                   //장바구니를 비우지 않는다 하면 장바구니로 돌아가기
        {
            System.out.println();
            System.out.println();
            System.out.println("\t>>장바구니로 돌아갑니다.<<");
            System.out.println();
        }
        else
        {
            cartdel();
        }
    }

    //결제 메소드
    public static void cartpay() throws IOException
    {
        List<Order> OrderList = CacheData.orderOuterList;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        py=0;

        // 회원 여부에 따라 총 결제 금액 계산
        int memberTotal=0;

        System.out.println("\t");
        System.out.println("\t1. 회원");
        System.out.println("\t2. 비회원");
        System.out.print("\t--==>>");
        try
        {
            sel = Integer.parseInt(br.readLine());
        }
        catch (NumberFormatException e)
        {
            System.out.println("올바른 숫자를 입력하세요.");
            cartpay();
        }

        //회원일 때
        if (sel==1)
        {
            //테스트
//            hm.put("123",new Member("123","1234",1005));
            //hm.put("123",new Member("123","1234",1005));
            wan = 0;

            while(true)
            {
                System.out.println();
                System.out.print("\tID 입력(전화번호) : ");
                id = br.readLine();
                System.out.print("\tPassword 입력(4자리) : ");
                memPw = br.readLine();

                if (!hm.containsKey(id))
                {
                    System.out.println("\t입력하신 ID가 존재하지 않습니다.");
                }
                else
                {
                    if (hm.get(id).getMemPw().equals(memPw))
                    {
                        System.out.println("\t아이디와 비밀번호가 일치합니다.");
                        break;
                    }
                    else
                    {
                        System.out.println("\t비밀번호가 일치하지 않습니다.");
                    }
                }
            }
            // 회원일 때 총 결제 금액 설정
            pointuse(memberTotal);
        }

        //비회원일 때
        else if (sel==2)
        {
            wan = 0;
            memberTotal = OrderList.get(OrderList.size() - 1).getO_totPrice();  // 비회원일 때 총 결제 금액 설정
            System.out.print("\n\t회원가입을 하시겠습니까(Y/N)? : ");
            con = br.readLine().toUpperCase();

            if (con.equals("Y"))
            {
                System.out.println();
                System.out.println("\t>>회원가입을 시작합니다.<<");
                System.out.println("\t가입정보를 입력해주세요.");


                while (true)
                {
                    boolean idCheck = false;                                         // 선언 및 초기화
                    System.out.print("\tID 입력(전화번호) : ");
                    id = br.readLine();                                   // 사용자가 ID를 입력
                    char[] arr1 = id.toCharArray();                              // 사용자가 입력한 id를 char 배열 arr1에 쪼개서 담기
                    for (int i=3; i<id.length(); i++)                            // 010 뒷부분은 인덱스 3~10
                    {
                        if ('0'<=arr1[i] && arr1[i]<='9')                        // 010 뒷부분을 0에서 9까지의 숫자형태로 입력했는지 확인
                            idCheck = true;
                    }


                    if (hm.containsKey(id))
                        System.out.println("\n\t이미 존재하는 ID입니다.");
                    else if (id.length()==11 && idCheck && id.substring(0,3).equals("010"))   // 총 11자리가 이고, 010으로 시작하고, 010 뒷부분을 숫자형태로 입력한 경우
                    {
                        String memPw;
                        while(true) {
                            boolean pwCheck = false;                                         // 선언 및 초기화
                            System.out.print("\tPassword 입력(숫자 4자리) : ");
                            memPw = br.readLine();                                // 사용자가 Password를 입력
                            char[] arr2 = memPw.toCharArray();                    // 사용자가 입력한 Password를 char 배열 arr2에 쪼개서 담기
                            for (int i = 0; i <memPw.length(); i++)               // 인덱스 0~3
                                if ('0' <= arr2[i] && arr2[i] <= '9')             // 0에서 9까지의 숫자형태로 입력했는지 확인
                                    pwCheck = true;
                            if (memPw.length() == 4 && pwCheck)                  // 총 4자리이거나, 숫자형태로 입력한 경우
                                break;
                            else
                                System.out.println("\n\tPassword는 숫자 4자리만 입력가능합니다. 다시 입력해주세요.");
                        }
                        hm.put(id,new Member(id,memPw,1000));
                        System.out.println("\t회원가입이 완료되었습니다.");
                        sel=1;
                        break;
                    }
                    else
                        System.out.println("\n\tID는 전화번호만 입력가능합니다. 다시 입력해주세요.");
                }
                pointuse(memberTotal);

            }
            else if (con.equals("N"))
            {
                System.out.println();
                System.out.println("\t>>결제수단으로 갑니다.<<");

                paysel(py,memberTotal);
            }
            else  //Y/N이 아닌 다른 것을 눌렀을 때 결제 메소드로 이동
            {
                System.out.println("\tY/N을 눌러주세요.");
                cartpay();
            }
        }
        else
        {
            System.out.println("\t유효하지 않은 선택입니다. 1 또는 2를 입력해주세요.");
            cartpay();
        }
    }


    //추가 주문 메소드(수정 필요)
    public static void cartadd() throws IOException
    {
        List<Order> OrderList = CacheData.orderOuterList;
        ProductService productService = new ProductService(); // ProductService 객체 생성

        Kiosk ks = new Kiosk(productService);                   //장바구니 비운 후 다시 메뉴 선택창으로 가기
        ks.kioskStart();
    }

    //포인트 사용 메소드
    public static void pointuse(int memberTotal) throws IOException
    {
        List<Order> OrderList = CacheData.orderOuterList;
        py=0;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //System.out.println(OrderList.size());
        //tot = 0;
        tot = OrderList.get(OrderList.size()-1).getO_totPrice() - py;
        // 수경--
        //System.out.println(outerList.get(useridx-1).getO_totPrice());

        System.out.printf("\t보유한 포인트 %d를 사용하시겠습니까(Y/N)?" , hm.get(id).getMemPoint());
        con = br.readLine().toUpperCase();


        // hm.put(id, new Member(id, memPw,hm.get(id).getMemPoint()- 사용포인트))
        //포인트를 사용할 때
        if (con.equals("Y"))
        {
            //포인트 차감 후 결제수단으로 이동
            System.out.println();
            System.out.print("\t사용할 포인트 입력 : ");
            py = Integer.parseInt(br.readLine());
            System.out.println();
            wan -= py;

            if (py > hm.get(id).getMemPoint()) {
                System.out.println("\n\t보유한 포인트보다 많은 포인트를 입력하셨습니다.");
                pointuse(memberTotal);
                return;
            }
            if (py<=0)
            {
                System.out.println("\t 올바르게 입력해주세요.");
                pointuse(memberTotal);
                return;
            }

            if (py > tot) {
                System.out.println("\t포인트 사용이 총 결제 금액을 초과했습니다.");
                pointuse(memberTotal);
                return;
            }
            tot = OrderList.get(OrderList.size()-1).getO_totPrice()-py;
            emptypay = py;

            System.out.printf("\t남은 포인트: %d\n", hm.get(id).getMemPoint()-py);

            paysel(py, memberTotal);

        }
        //포인트를 사용하지 않을 때
        else if (con.equals("N"))
        {
            wan = 0;
            //결제수단으로 이동
            System.out.println();
            System.out.printf("\t남은 포인트 : %d" , hm.get(id).getMemPoint());
            System.out.println();
            System.out.println("\t>>결제수단으로 갑니다.<<");
            paysel(py, memberTotal);
        }
        else
        {
            pointuse(memberTotal);
        }
    }

    //결제수단 선택하는 메소드
    public static void paysel(int py,int memberTotal) throws IOException
    {
        List<Order> OrderList = CacheData.orderOuterList;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int payus=0;

        if (sel==1) {
            // 회원일 경우 총 결제 금액
            memberTotal = OrderList.get(OrderList.size() - 1).getO_totPrice() - py;
            System.out.println("\t>>포인트 차감 후 결제수단으로 이동<<");
            System.out.printf("\t총 결제 금액은 (%d) 입니다.\n", memberTotal);

            //int pointEarned = (int) (memberTotal * pointad);
            //hm.get(id).addPoints(pointEarned);

        }
        else
        {
            // 비회원일 경우 총 결제 금액
            memberTotal = OrderList.get(OrderList.size() - 1).getO_totPrice();
            System.out.printf("\t총 결제 금액은 (%d) 입니다.\n", memberTotal);
        }

        int inputsel;
        while (true) {
            System.out.println("\n\t[결제수단]===============");
            System.out.println("\t1. 카카오페이");
            System.out.println("\t2. 삼성페이");
            System.out.println("\t3. 일반결제");
            System.out.println("\t=========================");
            System.out.print("\t>>결제수단(1-3) : ");
            try {
                inputsel = Integer.parseInt(br.readLine());
                if (inputsel >= 1 && inputsel <= 3) {
                    break;
                } else {
                    System.out.println("1에서 3 사이의 값을 선택하세요.");
                    continue;
                }
            } catch (NumberFormatException e) {
                System.out.println("올바른 숫자를 입력하세요.");
            }
        }
        switch (inputsel) {
            case 1:
                System.out.print("\t카카오페이 잔액 입력 : ");
                payus = Integer.parseInt(br.readLine());
                break;
            case 2:
                System.out.print("\t삼성페이 잔액 입력 : ");
                payus = Integer.parseInt(br.readLine());
                break;
            case 3:
                System.out.print("\t일반결제 잔액 입력 : ");
                payus = Integer.parseInt(br.readLine());
                break;
            default:
                System.out.print("\t1-3중에 선택해주세요.");
                paysel(py, memberTotal);
                return;
        }

        //남은 금액
        int emptypay;
        py=0;

        if (sel == 1)
        {
            emptypay = payus - (memberTotal - py);
        }
        else if (sel == 2)
        {
            emptypay = payus - memberTotal;
        }
        else
        {
            emptypay = payus;
            return;
        }

        if (sel ==1) {
            if (payus < memberTotal) {
                System.out.println("\t잔액 부족으로 포인트 사용 페이지로 되돌아갑니다.");
                pointuse(memberTotal);
            }
        }
        else if (sel==2)
        {
            if (payus < memberTotal) {
                System.out.println("\t잔액 부족으로 결제 수단으로 되돌아갑니다.");
                paysel(py, memberTotal);
            }
        }
        System.out.printf("\t남은 잔액은 %d원 입니다.", emptypay);
        System.out.println("\t결제가 완료되었습니다. 감사합니다.");
        //장바구니 재료 증감식

        receipt(memberTotal);
    }

    public static void receipt(int memberTotal) throws IOException
    {
        List<Order> OrderList = CacheData.orderOuterList;
        List<OrderValues> orderInnerValues = CacheData.orderInnerValues;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        System.out.print("\n\t영수증을 출력하시겠습니까(Y/N)? : ");
        con = br.readLine().toUpperCase();


        if (con.equals("Y"))
        {
            System.out.println("\n\t=======[영수증]=======");

            OrderSetting orderSetting = new OrderSetting();
            //orderSetting.calculateOrderTotal();
            //orderSetting.printOrderList();

            int i=1,j=1;

            for (Order order: OrderList){
                System.out.printf("%-4d   %-8s \t%-8s \t%-8d \t%-8d\n", i++, order.getO_name(), order.getO_nowTime(), order.getO_totCalorie(), order.getO_totPrice());
                System.out.printf("\t%-4s) %-4s \t%-4s \t%-8s \t%-8s\n", "NO", "제품", "구매수량", "칼로리", "금액");

                for (OrderValues orderValues: orderInnerValues) {
                    System.out.printf("\t%d-%d) %-8s \t%-8s \t%-8d \t%-8d\n", i-1 , j++, orderValues.getName(), orderValues.getCount(), orderValues.getCalorie() * orderValues.getCount(), orderValues.getPrice() * orderValues.getCount());
                }
            }
            System.out.printf("\t\n사용한 포인트 : %d, 총 결제 금액: %d\n", emptypay, memberTotal);  // 사용한 포인트와 총 결제 금액 출력
            if (sel==1)
            {
                int memPoint = MemberMg.hm.get(id).getMemPoint() - emptypay+(int)(memberTotal*0.1);
            }

            System.out.println("\t=========================");
        }
        else if (con.equals("N"))
        {
            System.out.println("\t결제가 모두 완료됐습니다. 이용해주셔서 감사합니다.");
            exitCart();
        }
        else
        {
            receipt(memberTotal);
        }
        exitCart();
    }
}


//샐러드 클래스
public class saledTest
{
    //main() 메소드
	/*
	public static void main(String[] args) throws IOException, NumberFormatException
	{

		HashMap<String,Member> hm = new HashMap<String,Member>();
		Member m = new Member();

		//장바구니 인스턴스
		cart ca = new cart();
		do
		{
			ca.menuDis();
			ca.menuSel();
			ca.menuR(hm);
		}
		while (true);
	}
	*/
}


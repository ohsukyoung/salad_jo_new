import java.io.*;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

import java.util.Iterator;
import java.util.List;

public class FileMg
{
    // 역직렬화 : 파일 -> 객체 저장하기
    public HashMap<String,Member> memberFileIn() throws IOException, ClassNotFoundException
    {
        HashMap<String,Member> h2 = new HashMap<String,Member>();
        try
        {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("data/member.ser"));
            h2 = (HashMap<String,Member>) ois.readObject();
            ois.close();
            return h2;
        }
        catch(FileNotFoundException fnfe)
        {
            System.out.println("\tmember.ser 파일을 찾을 수 없습니다.");
            return h2;
        }
    }

    public ArrayList<Receipt> receiptFileIn() throws IOException, ClassNotFoundException
    {
        ArrayList<Receipt> l2 = new ArrayList<> ();
        try
        {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("data/receipt.ser"));
            l2 = (ArrayList<Receipt>) ois.readObject();
            ois.close();
            return l2;

        }
        catch(FileNotFoundException fnfe)
        {
            System.out.println("\treceipt.ser 파일을 찾을 수 없습니다.");
            return l2;
        }
    }

    public List<Order> orderOuterFileIn() throws IOException, ClassNotFoundException{
        List<Order> orderOuterList = new ArrayList<>();
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("data/order.ser"));
            orderOuterList = (List<Order>) ois.readObject();
            ois.close();
            return orderOuterList;
        }catch (FileNotFoundException fnfe)
        {
            System.out.println("\tmember.ser 파일을 찾을 수 없습니다.");
            return orderOuterList;
        }
    }

    public List<OrderValues> orderInnerValuesFileIn() throws IOException, ClassNotFoundException{
        List<OrderValues> orderInnerValues = new ArrayList<>();
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("data/orderValue.ser"));
            orderInnerValues = (List<OrderValues>) ois.readObject();
            ois.close();
            return orderInnerValues;
        }catch (FileNotFoundException fnfe)
        {
            System.out.println("\torderValue.ser 파일을 찾을 수 없습니다.");
            return orderInnerValues;
        }
    }


    //-------------------------------------------------------------------------------------------------------------
    // 직렬화 : 객체 -> 파일 저장하기
    public void memberFileOut() throws IOException
    {
        String appDir = System.getProperty("user.dir");
        //-- 시스템 속성으로부터 현재 사용자가 사용중인 디렉토리 정보 얻어오기

        File f0 = new File(appDir, "/data/member.ser");

        // test.ser 파일이 만들어지게 될 디렉토리 경로가 구성되어 있지 않다면..
        if (!f0.getParentFile().exists())
        {
            f0.getParentFile().mkdirs();
        }
        HashMap<String,Member> h1 = MemberMg.hm;
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(f0));

        // 생성된 스트림에 내보낼 객체를 기록
        oos.writeObject(h1);

        oos.close();
    }
    public void receiptFileOut() throws IOException
    {
        String appDir = System.getProperty("user.dir");
        //-- 시스템 속성으로부터 현재 사용자가 사용중인 디렉토리 정보 얻어오기

        File f0 = new File(appDir, "/data/receipt.ser");

        ArrayList<Receipt> l1 = SalesMg.receipts;

        //l1.add(new Receipt(2023, 10, 4, 18, "01012341234", true, 1000, "일반카드", 100000, false));
        //l1.add(new Receipt(2022, 10, 2, 20, "회원이 아닙니다", false, 0, "카카오", 50000, false));
        //l1.add(new Receipt(2022, 10, 2, 18, "01024562456", true, 400,  "일반카드", 20000, false));
        //l1.add(new Receipt(2022, 10, 2, 18, "회원이 아닙니다", false, 0, "카카오", 30000, false));
        //l1.add(new Receipt(2020, 10, 2, 18, "01023456789", true, 300, "삼성페이", 10000, false));

        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(f0));

        // 생성된 스트림에 내보낼 객체를 기록
        oos.writeObject(l1);

        oos.close();
    }

    public void orderOuterFileOut() throws IOException{
        String appDir = System.getProperty("user.dir");
        //-- 시스템 속성으로부터 현재 사용자가 사용중인 디렉토리 정보 얻어오기

        File f0 = new File(appDir, "/data/order.ser");

        List<Order> orderOuterList = CacheData.orderOuterList;
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(f0));

        // 생성된 스트림에 내보낼 객체를 기록
        oos.writeObject(orderOuterList);

        oos.close();
    }

    public void orderInnerValuesFileOut() throws IOException{
        String appDir = System.getProperty("user.dir");
        //-- 시스템 속성으로부터 현재 사용자가 사용중인 디렉토리 정보 얻어오기

        File f0 = new File(appDir, "/data/orderInnerValues.ser");

        List<OrderValues> orderInnerValues = CacheData.orderInnerValues;
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(f0));

        // 생성된 스트림에 내보낼 객체를 기록
        oos.writeObject(orderInnerValues);

        oos.close();
    }

//    static List<OrderValues> orderInnerValues;                  // 사용자 선택 안쪽(값) 리스트
//    static List<Product> list1 = new ArrayList<>();             // 사장추천이외(나만의 샐러드, 음료, 사이드, 샐러드세부재료 등)의 리스트 // TODO 리스트 이름 공통화(ex.allProductList)
//    static List<MasterRc> list2 = new ArrayList<>();            // 사장추천 리스트
}
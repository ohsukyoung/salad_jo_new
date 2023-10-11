import java.util.List;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.Serializable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.util.Iterator;

class MasterRc implements Serializable ,Impl_admin
{
    Product product = new Product();
    String appDir = System.getProperty("user.dir");
    private transient BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private int r_checkNumber;
    private String r_name;
    private int r_totalcalorie;
    private int r_price;
    private double r_discount;
    private List<Product> r_products; // 재료 목록
    private ProductType type;
    private int r_count;

    public ProductType getType() {
        return type;
    }

    public MasterRc(int r_checkNumber, ProductType type, String r_name, int r_totalcalorie, int r_price, List<Product> r_products,double r_discount, int r_count)
    {
        this.r_checkNumber = r_checkNumber;
        this.type = type;
        this.r_name = r_name;
        this. r_totalcalorie = r_totalcalorie;
        this.r_price = r_price;
        this.r_products = r_products;
        this.r_discount = r_discount;
        this.r_count = r_count;
    }

    public MasterRc(int r_checkNumber, String r_name, int r_totalcalorie, int r_price, List<Product> r_products)
    {
        this.r_checkNumber = r_checkNumber;
        this.r_name = r_name;
        this.r_price = r_price;
        this.r_products = r_products;
    }

    public MasterRc(){this(0, ProductType.RCMND, "", 0, 0, new ArrayList<>(),0,0);}
    public int getR_checkNumber(){return r_checkNumber;}
    public void setR_checkNumber(int r_checkNumber){this.r_checkNumber = r_checkNumber;}
    public String getR_name(){return r_name;}
    public void setR_name(String r_name){this.r_name = r_name;}
    public int getR_totalcalorie(){return r_totalcalorie;}
    public void setR_totalcalorie(int r_totalcalorie){this.r_totalcalorie = r_totalcalorie;}
    public int getR_price(){return r_price;}
    public void setR_price(int r_price){this.r_price = r_price;}
    public List<Product> getR_products(){return r_products;}
    public double getR_discount(){return r_discount;}
    public void setR_discount(double r_discount){this.r_discount = r_discount;}
    public int getR_count() { return r_count; }
    public void setR_count(int r_count) { this.r_count = r_count; }
    public void setR_products(List<Product> r_products)
    {
        this.r_products = r_products;
    }

    // MasterRc 객체 내의 재료의 총 칼로리 계산
    private int calculateTotalCalorie(List<Product> products)
    {
        int totalCalorie = 0;
        for (Product product : products)
        {
            totalCalorie += product.getP_calorie();
        }
        r_totalcalorie = totalCalorie;
        return totalCalorie;
    }

    // MasterRc 객체 내의 재료의 총 금액 계산
    private int calculateTotalPrice(List<Product> products)
    {
        int totalPrice = 0;
        for (Product product : products)
        {
            totalPrice += product.getP_price();
        }
        return totalPrice;
    }

    // MasterRc 객체 내의 재고 개수 계산
    private int calculateMinCount(List<Product> products)
    {
        int minCount=10000;
        for (Product product : products)
        {
//            System.out.println(product.getP_count());
            if(minCount > product.getP_count())
                minCount = product.getP_count();
        }
        return minCount;
    }


    @Override
    public void ad_print() throws IOException, ClassNotFoundException
    {
//        File f1 = new File(appDir, "\\data\\MasterData.ser");
//        if (!f1.exists())
//        {
//            System.out.println("저장된 데이터가 없습니다.");
//            return;
//        }
//
//        // 파일이 이미 존재하면 기존 데이터를 읽어옵니다.
//        FileInputStream fis1 = new FileInputStream(f1);
//        ObjectInputStream ois1 = new ObjectInputStream(fis1);
//        List<MasterRc> existingList2 = (List<MasterRc>) ois1.readObject();
        List<MasterRc> existingList2 = CacheData.list2;
//        ois1.close();
//        fis1.close();

        System.out.println("[[1.사장 추천 조합 출력]]========================================================================");
        System.out.printf("|| %5s || %5s || %5s || %5s || %5s || %5s \n",
                "구분번호", "이름", "칼로리", "금액", "재료", "개수");
        System.out.println("===========================================================================================");

        // existingList2에 있는 MasterRc 객체를 출력
        for (MasterRc masterRc : existingList2)
        {
            System.out.printf("|| %5d || %5s || %5d || %5d || %5d \n",
                    masterRc.getR_checkNumber(), masterRc.getR_name(),
                    calculateTotalCalorie(masterRc.getR_products()), masterRc.getR_price(), masterRc.getR_count());

            // 재료 정보 출력
            for (Product product : masterRc.getR_products())
            {
                System.out.println(" 재료이름: " + product.getP_name() + " || 재료 갯수: " + product.getP_count());

            }

            System.out.println();
        }

        KioskMg.masterrcflag = false;

    }


    @Override
    public void ad_add() throws IOException, ClassNotFoundException
    {
//        List<MasterRc> list2 = new ArrayList<>();
        List<MasterRc> list2 = CacheData.list2;

        System.out.println();
        System.out.println("[[2.신규 사장 추천 조합 등록]]========================================================================");
        System.out.printf("|| %5s || %5s || %5s || %5s || %5s ||\n",
                "구분번호", "이름", "칼로리", "금액", "재료");
        System.out.println("===========================================================================================");

//        while (true)
//        {
            // 재료를 선택하는 부분
            List<Product> selectedProducts = new ArrayList<>();

//            File f0 = new File(appDir, "\\data\\productData.ser");
//            if (!f0.exists())
//            {
//                System.out.println("저장된 데이터가 없습니다.");
//                return;
//            }
//
//            FileInputStream fis = new FileInputStream(f0);
//            ObjectInputStream ois = new ObjectInputStream(fis);
//            List<Product> product = (List<Product>) ois.readObject();
            List<Product> product = CacheData.list1;
//            ois.close();
//            fis.close();

            while (true)
            {
                System.out.print("조합에 들어갈 재료의 구분번호를 입력하시오 (0: 종료) : ");
                int pointNumber = Integer.parseInt(br.readLine());
                if (pointNumber == 0)
                {
                    break;
                }

                boolean found = false;

                for (Product p : product)
                {
                    if (pointNumber == p.getP_checkNumber())
                    {
                        found = true;
                        selectedProducts.add(p);

                        System.out.println("선택한 재료:");
                        for (Product selected : selectedProducts)
                        {
                            System.out.println("이름: " + selected.getP_name() + ", 갯수: " + selected.getP_count());
                        }
                    }
                }

                if (!found)
                {
                    System.out.println("구분번호가 일치하지 않습니다.");
                }
            }

            // 칼로리 총합 계산
            int totalCalorie = calculateTotalCalorie(selectedProducts);
            // 금액 총합 계산
            int totalPrice = calculateTotalPrice(selectedProducts);

            System.out.println("조합에 들어간 재료의 금액 :" + totalPrice );

            // 재고 개수 계산
            int r_count = calculateMinCount(selectedProducts);

            System.out.print("조합 정보에 들어갈 [구분번호,이름,금액]을 입력하시오(스페이스로 구분) : ");
            String input = br.readLine();
            StringTokenizer tokenizer = new StringTokenizer(input, " ");

            if (tokenizer.countTokens() != 3)
            {
                System.out.println("입력한 항목의 갯수가 맞지 않습니다.");
//                continue;
            }

            int r_checkNumber = Integer.parseInt(tokenizer.nextToken());
            String r_name = tokenizer.nextToken();
            int r_price = Integer.parseInt(tokenizer.nextToken());


            // 할인율 계산
            r_discount = 100-((r_price / totalPrice)*100);



            //역직렬화 데이터 불러오기
//            File f1 = new File(appDir, "\\data\\MasterData.ser");
            List<MasterRc> existingList2;
//            if (!f1.getParentFile().exists())
//            {
//                f1.getParentFile().mkdirs();
//            }
//
//            if (f1.exists())
//            {
//                // 파일이 이미 존재하면 기존 데이터를 읽어옵니다.
//                FileInputStream fis1 = new FileInputStream(f1);
//                ObjectInputStream ois1 = new ObjectInputStream(fis1);
//                existingList2 = (List<MasterRc>) ois1.readObject();
            existingList2 = CacheData.list2;
//                ois1.close();
//                fis1.close();
//            }
//            else
//            {
//                // 파일이 존재하지 않으면 새로운 리스트를 생성합니다.
//                existingList2 = new ArrayList<>();
//            }

            //구분번호 중복 확인하기
            boolean m2 = false;
            for (int i = 0; i < existingList2.size(); i++)
            {
                if (r_checkNumber == existingList2.get(i).getR_checkNumber())
                {
                    m2 = true;
                    break;
                }
            }

            if (m2)
            {
                System.out.println("이미 구분번호가 존재합니다.");
                return;
            }else {
                System.out.print("저장하시겠습니까?(Y/N) : ");
                char x = br.readLine().charAt(0);
                if (x == 'Y' || x == 'y') {
                    // MasterRc 객체 생성 및 리스트에 추가
                    MasterRc masterRc = new MasterRc(r_checkNumber, ProductType.RCMND, r_name, r_totalcalorie, r_price, selectedProducts, r_discount, r_count);
                    list2.add(masterRc);
                }
            }
//
//            System.out.print("저장하시겠습니까?(Y/N) : ");
//            char x = br.readLine().charAt(0);
//            if (x == 'Y' || x == 'y')
//            {
                // 새로운 제품을 기존 리스트에 추가합니다.
//                existingList2.addAll(list2);

                // 업데이트된 리스트를 파일에 저장합니다.
//                FileOutputStream fos1 = new FileOutputStream(f1);
//                ObjectOutputStream oos1 = new ObjectOutputStream(fos1);
//                oos1.writeObject(existingList2);
//                oos1.close();
//                fos1.close();

//                return;
//            }
//            else
//            {
//                return;
//            }
//        }

        KioskMg.masterrcflag = false;
    }

    @Override
    public void ad_modify() throws IOException, ClassNotFoundException
    {
//        File f1 = new File(appDir, "\\data\\MasterData.ser");
//        if (!f1.exists())
//        {
//            System.out.println("저장된 데이터가 없습니다.");
//            return;
//        }

        // 파일이 이미 존재하면 기존 데이터를 읽어옵니다.
//        FileInputStream fis1 = new FileInputStream(f1);
//        ObjectInputStream ois1 = new ObjectInputStream(fis1);
//        List<MasterRc> existingList2 = (List<MasterRc>) ois1.readObject();
        List<MasterRc> existingList2 = CacheData.list2;
//        ois1.close();
//        fis1.close();

        System.out.println();
        System.out.println("[[3.조합정보 변경]]========================================================================");
        System.out.print("작업할 대상의 구분번호를 입력하시오 : ");
        int pointNumber = Integer.parseInt(br.readLine());
        boolean found = false;

        for (int i = 0; i < existingList2.size(); i++)
        {
            if (pointNumber == existingList2.get(i).getR_checkNumber())
            {
                found = true;
                MasterRc masterRc = existingList2.get(i);

                System.out.printf("|| %5s || %5s || %5s || %5s || %5s ||\n",
                        "구분번호", "이름", "칼로리", "금액", "재료");
                System.out.printf("|| %5d || %5s || %5d || %5d ||",
                        masterRc.getR_checkNumber(), masterRc.getR_name(),
                        calculateTotalCalorie(masterRc.getR_products()), masterRc.getR_price());

                // 재료 정보 출력
                for (Product product : masterRc.getR_products())
                {
                    System.out.print(" " + product.getP_name());
                }
                System.out.println(" ||");

                System.out.println("변경할 내용을 선택하시오.");
                System.out.println("1. 구분번호 2. 이름 3. 금액");
                System.out.println();
                while (true)
                {
                    System.out.print("변경할 내용의 숫자를 입력하시오 (0: 변경 완료) : ");
                    int choice = Integer.parseInt(br.readLine());
                    if (choice == 0)
                    {
//                        // 변경 완료 시, 변경된 정보를 저장
//                        FileOutputStream fos1 = new FileOutputStream(f1);
//                        ObjectOutputStream oos1 = new ObjectOutputStream(fos1);
//                        oos1.writeObject(existingList2);
//                        oos1.close();
//                        fos1.close();
                        break;
                    }
                    switch (choice)
                    {
                        case 1:
                            System.out.print("새로운 구분번호 입력: ");
                            int newCheckNumber = Integer.parseInt(br.readLine());
                            masterRc.setR_checkNumber(newCheckNumber);
                            break;
                        case 2:
                            System.out.print("새로운 이름 입력: ");
                            String newName = br.readLine();
                            masterRc.setR_name(newName);
                            break;
                        case 3:
                            System.out.print("새로운 금액 입력: ");
                            int newPrice = Integer.parseInt(br.readLine());
                            masterRc.setR_price(newPrice);
                            break;
                        default:
                            System.out.println("입력된 항목이 없습니다.");
                    }
                }
            }
        }
        if (!found)
        {
            System.out.println("구분번호가 일치하지 않습니다.");
        }

        KioskMg.masterrcflag = false;
    }

    @Override
    public void ad_delete() throws IOException, ClassNotFoundException
    {
        // 역직렬화 기존 데이터 불러오기
//        File f1 = new File(appDir, "\\data\\MasterData.ser");
//        if (!f1.exists())
//        {
//            System.out.println("저장된 데이터가 없습니다.");
//            return;
//        }
//        FileInputStream fis1 = new FileInputStream(f1);
//        ObjectInputStream ois1 = new ObjectInputStream(fis1);
//        List<MasterRc> existingList2 = (List<MasterRc>) ois1.readObject();
        List<MasterRc> existingList2 = CacheData.list2;
//        ois1.close();
//        fis1.close();

        System.out.println();
        System.out.println("[[4.조합정보 삭제]]========================================================================");
        System.out.print("작업할 대상의 구분번호를 입력하시오 : ");
        int pointNumber = Integer.parseInt(br.readLine());
        boolean found = false;

        int deleteIndex = -1; // 삭제할 아이템의 인덱스 초기화

        for (int i = 0; i < existingList2.size(); i++)
        {
            if (pointNumber == existingList2.get(i).getR_checkNumber())
            {
                found = true;
                MasterRc masterRc = existingList2.get(i);

                System.out.printf("|| %5s || %5s || %5s || %5s || %5s ||\n",
                        "구분번호", "이름", "칼로리", "금액", "재료");
                System.out.printf("|| %5d || %5s || %5d || %5d ||",
                        masterRc.getR_checkNumber(), masterRc.getR_name(),
                        calculateTotalCalorie(masterRc.getR_products()), masterRc.getR_price());

                // 재료 정보 출력
                for (Product product : masterRc.getR_products())
                {
                    System.out.print(" " + product.getP_name());
                }
                System.out.println(" ||");

                System.out.print("삭제하시겠습니까?(Y/N) : ");
                char x = br.readLine().charAt(0);
                if (x == 'Y' || x == 'y')
                {
                    deleteIndex = i; // 삭제 대상 재료의 인덱스 설정
                    break;
                }
            }
        }

        if (!found)
        {
            System.out.println("구분번호가 일치하지 않습니다.");
            return; // 일치하지 않으면 삭제 작업을 하지 않고 종료
        }

        // 삭제 대상 재료가 설정된 경우에만 삭제 수행
        if (deleteIndex != -1)
        {
            existingList2.remove(deleteIndex);

            // 변경된 정보를 저장
//            FileOutputStream fos1 = new FileOutputStream(f1);
//            ObjectOutputStream oos1 = new ObjectOutputStream(fos1);
//            oos1.writeObject(existingList2);
//            oos1.close();
//            fos1.close();
        }

        KioskMg.masterrcflag = false;
    }

}

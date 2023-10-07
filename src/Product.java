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

enum ProductType {
    RCMND(1, "사장추천"),
    MY_SALAD(2,"나만의 샐러드"),
    DRINK(3, "음료"),
    SIDE(4, "사이드"),
    E_CANCEL(-1,"취소");

    private int index;
    private String name;

    ProductType(int index, String name) {
        this.index=index;
        this.name=name;
    }


    // getter
    public int getIndex() {
        return index;
    }
    public String getName() {
        return name;
    }
}

public class Product implements Serializable ,Impl_admin
{
    private static final long serialVersionUID = 4570189582182369883L;
    //디렉토리 생성과 재료변수 선언
    String appDir = System.getProperty("user.dir");
    private transient BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private int p_checkNumber;
    private int p_material;
    private String p_name;
    private String p_unit;
    private int p_count;
    private int p_calorie;
    private int p_stock;
    private int p_price;

    private ProductType type;

    public ProductType getType(){
        return type;
    }


    // 생성자
    public Product(int p_checkNumber, int p_material, String p_name, String p_unit, int p_count, int p_calorie, int p_stock, int p_price)
    {
        this.p_checkNumber = p_checkNumber;
        this.p_material = p_material;
        this.p_name = p_name;
        this.p_unit = p_unit;
        this.p_count = p_count;
        this.p_calorie = p_calorie;
        this.p_stock = p_stock;
        this.p_price = p_price;
    }

    // 음료, 사이드
    Product(String p_name,int p_count,int p_calorie,int p_stock,int p_price){
        //이름, 개수, 칼로리, 적정재고, 금액
        this.p_name = p_name;
        this.p_count = p_count;
        this.p_calorie = p_calorie;
        this.p_stock = p_stock;
        this.p_price = p_price;
    }
    // 나만의 샐러드 재료
    Product(int p_material,String p_name,String p_unit,int p_count,int p_calorie,int p_stock,int p_price){
        //("이름", new Product(분류번호, 단위, 개수, 칼로리, 적정재고, 금액))
        this.p_material = p_material;
        this.p_name = p_name;
        this.p_unit = p_unit;
        this.p_count = p_count;
        this.p_calorie = p_calorie;
        this.p_stock = p_stock;
        this.p_price = p_price;
    }

    // 생성자
    public Product()
    {
        this(0, 0, "", "", 0, 0, 0, 0);
    }

    //getter setter
    public int getP_checkNumber() { return p_checkNumber; }
    public void setP_checkNumber(int p_checkNumber) { this.p_checkNumber = p_checkNumber; }
    public int getP_material() { return p_material; }
    public void setP_material(int p_material) { this.p_material = p_material; }
    public String getP_name() { return p_name; }
    public void setP_name(String p_name) { this.p_name = p_name; }
    public String getP_unit() { return p_unit; }
    public void setP_unit(String p_unit) { this.p_unit = p_unit; }
    public int getP_count() { return p_count; }
    public void setP_count(int p_count) { this.p_count = p_count; }
    public int getP_calorie() { return p_calorie; }
    public void setP_calorie(int p_calorie) { this.p_calorie = p_calorie; }
    public int getP_stock() { return p_stock; }
    public void setP_stock(int p_stock) { this.p_stock = p_stock; }
    public int getP_price() { return p_price; }
    public void setP_price(int p_price) { this.p_price = p_price; }

    @Override
    public void ad_print() throws IOException, ClassNotFoundException
    {
        // 역직렬화 기존 데이터 불러오기
        File f0 = new File(appDir, "\\data\\productData.ser");
        if (!f0.exists())
        {
            System.out.println("저장된 데이터가 없습니다.");
            return;
        }

        FileInputStream fis = new FileInputStream(f0);
        ObjectInputStream ois = new ObjectInputStream(fis);
        List<Product> product = (List<Product>) ois.readObject();
        ois.close();
        fis.close();

        System.out.println("[[1.재료정보 출력]]========================================================================");
        System.out.printf("|| %5s || %5s || %5s || %9s || %5s || %5s || %5s || %5s ||\n",
                "구분번호", "분류번호", "이름", "단위", "개수", "칼로리", "적정재고", "금액");
        System.out.println("===========================================================================================");

        // Iterator 활용하여 출력
        Iterator<Product> itList;
        itList = product.iterator();
        while (itList.hasNext())
        {
            Product itS = itList.next();
            System.out.printf("|| %5s || %5s || %5s || %9s || %5s || %5s || %5s || %5s ||\n", itS.getP_checkNumber(),
                    itS.getP_material(), itS.getP_name(), itS.getP_unit(), itS.getP_count(), itS.getP_calorie(),
                    itS.getP_stock(), itS.getP_price());
        }
        System.out.println();
    }


    @Override
    public void ad_add() throws IOException,ClassNotFoundException
    {

        //자료구조 생성
        List<Product> list1 = new ArrayList<Product>();

        System.out.println("[[2.신규재료 등록]]========================================================================");
        System.out.printf("|| %5s || %5s || %5s || %9s || %5s || %5s || %5s || %5s ||\n",
                "구분번호", "분류번호", "이름", "단위", "개수", "칼로리", "적정재고", "금액");
        System.out.println("===========================================================================================");

        // 무한루프를 활용
        while (true)
        {
            System.out.print("재료 정보를 입력하시오 (스페이스로 구분): ");
            String input = br.readLine();

            // 토크나이저로 스페이스로 끊어주기
            StringTokenizer tokenizer = new StringTokenizer(input, " ");
            if (tokenizer.countTokens() != 8)
            {
                System.out.println("입력한 항목의 갯수가 맞지 않습니다.");
                continue;
            }

            int p_checkNumber = Integer.parseInt(tokenizer.nextToken());
            int p_material = Integer.parseInt(tokenizer.nextToken());
            String p_name = tokenizer.nextToken();
            String p_unit = tokenizer.nextToken();
            int p_count = Integer.parseInt(tokenizer.nextToken());
            int p_calorie = Integer.parseInt(tokenizer.nextToken());
            int p_stock = Integer.parseInt(tokenizer.nextToken());
            int p_price = Integer.parseInt(tokenizer.nextToken());

            //prduct 인스턴스 생성하여 자료구조에 넣어주기
            Product product = new Product(p_checkNumber, p_material, p_name, p_unit, p_count, p_calorie, p_stock, p_price);
            list1.add(product);

            System.out.print("저장하시겠습니까?(Y/N) : ");
            char x = br.readLine().charAt(0);
            if (x == 'Y' || x == 'y')
            {
                File f0 = new File(appDir, "\\data\\productData.ser");
                List<Product> existingList;
                if (!f0.getParentFile().exists())
                {
                    f0.getParentFile().mkdirs();
                }

                if (f0.exists())
                {
                    // 파일이 이미 존재하면 기존 데이터를 읽어옵니다.
                    FileInputStream fis = new FileInputStream(f0);
                    ObjectInputStream ois = new ObjectInputStream(fis);
                    existingList = (List<Product>) ois.readObject();
                    ois.close();
                    fis.close();
                }
                else
                {
                    // 파일이 존재하지 않으면 새로운 리스트를 생성합니다.
                    existingList = new ArrayList<>();
                }

                // 새로운 제품을 기존 리스트에 추가합니다.
                existingList.addAll(list1);

                // 업데이트된 리스트를 파일에 저장합니다.
                FileOutputStream fos = new FileOutputStream(f0);
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                oos.writeObject(existingList);
                oos.close();
                fos.close();

                break;
            }
        }


    }

    @Override
    public void ad_modify() throws IOException, ClassNotFoundException
    {
        File f0 = new File(appDir, "\\data\\productData.ser");
        if (!f0.exists())
        {
            System.out.println("저장된 데이터가 없습니다.");
            return;
        }

        FileInputStream fis = new FileInputStream(f0);
        ObjectInputStream ois = new ObjectInputStream(fis);
        List<Product> product = (List<Product>) ois.readObject();
        ois.close();
        fis.close();

        System.out.println("[[3.재료정보 변경]]========================================================================");
        System.out.print("작업할 대상의 구분번호를 입력하시오 : ");
        int pointNumber = Integer.parseInt(br.readLine());

        boolean found = false;

        for (int i = 0; i < product.size(); i++)
        {
            if (pointNumber == product.get(i).getP_checkNumber())
            {
                found = true;
                Iterator<Product> itList;
                itList = product.iterator();
                Product itS = product.get(i);

                System.out.printf("|| %5s || %5s || %5s || %9s || %5s || %5s || %5s || %5s ||\n",
                        itS.getP_checkNumber(), itS.getP_material(), itS.getP_name(),
                        itS.getP_unit(), itS.getP_count(), itS.getP_calorie(),
                        itS.getP_stock(), itS.getP_price());
                System.out.println();

                System.out.println();
                System.out.println("변경할 내용을 선택하시오.");
                System.out.println("1. 구분번호 2. 분류번호 3. 이름 4. 단위 5. 개수 6. 칼로리 7. 적정재고 8. 금액");
                System.out.println();
                while (true)
                {
                    System.out.print("변경할 내용의 숫자를 입력하시오 (0: 변경 완료) : ");
                    int a = Integer.parseInt(br.readLine());
                    if (a == 0)
                    {
                        // 변경 완료 시, 변경된 정보를 저장
                        FileOutputStream fos = new FileOutputStream(f0);
                        ObjectOutputStream oos = new ObjectOutputStream(fos);
                        oos.writeObject(product);
                        oos.close();
                        fos.close();
                        break;
                    }
                    switch (a) {
                        case 1:
                            System.out.print("새로운 구분번호 입력: ");
                            int newCheckNumber = Integer.parseInt(br.readLine());
                            product.get(i).setP_checkNumber(newCheckNumber);
                            break;
                        case 2:
                            System.out.print("새로운 분류번호 입력: ");
                            int newMaterial = Integer.parseInt(br.readLine());
                            product.get(i).setP_material(newMaterial);
                            break;
                        case 3:
                            System.out.print("새로운 이름 입력: ");
                            String newName = br.readLine();
                            product.get(i).setP_name(newName);
                            break;
                        case 4:
                            System.out.print("새로운 단위 입력: ");
                            String newUnit = br.readLine();
                            product.get(i).setP_unit(newUnit);
                            break;
                        case 5:
                            System.out.print("새로운 개수 입력: ");
                            int newCount = Integer.parseInt(br.readLine());
                            product.get(i).setP_count(newCount);
                            break;
                        case 6:
                            System.out.print("새로운 칼로리 입력: ");
                            int newCalorie = Integer.parseInt(br.readLine());
                            product.get(i).setP_calorie(newCalorie);
                            break;
                        case 7:
                            System.out.print("새로운 적정재고 입력: ");
                            int newStock = Integer.parseInt(br.readLine());
                            product.get(i).setP_stock(newStock);
                            break;
                        case 8:
                            System.out.print("새로운 금액 입력: ");
                            int newPrice = Integer.parseInt(br.readLine());
                            product.get(i).setP_price(newPrice);
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

    }


    @Override
    public void ad_delete() throws IOException, ClassNotFoundException
    {
        // 역직렬화 기존 데이터 불러오기
        File f0 = new File(appDir, "\\data\\productData.ser");
        if (!f0.exists())
        {
            System.out.println("저장된 데이터가 없습니다.");
            return;
        }
        FileInputStream fis = new FileInputStream(f0);
        ObjectInputStream ois = new ObjectInputStream(fis);
        List<Product> product = (List<Product>)ois.readObject();
        ois.close();
        fis.close();


        System.out.println("[[4.재료정보 삭제]]========================================================================");
        System.out.print("작업할 대상의 구분번호를 입력하시오 : ");
        int pointNumber = Integer.parseInt(br.readLine());
        boolean found = false;

        int deleteIndex = -1; // 삭제할 아이템의 인덱스 초기화

        for (int i = 0; i < product.size(); i++)
        {
            if (pointNumber == product.get(i).getP_checkNumber())
            {
                found = true;
                Iterator<Product> itList;
                itList = product.iterator();
                Product itS = product.get(i);

                System.out.printf("|| %5s || %5s || %5s || %9s || %5s || %5s || %5s || %5s ||\n",
                        itS.getP_checkNumber(), itS.getP_material(), itS.getP_name(),
                        itS.getP_unit(), itS.getP_count(), itS.getP_calorie(),
                        itS.getP_stock(), itS.getP_price());
                System.out.println();
                System.out.println();

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
            product.remove(deleteIndex);

            // 변경된 정보를 저장
            FileOutputStream fos = new FileOutputStream(f0);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(product);
            oos.close();
            fos.close();
        }



    }

}
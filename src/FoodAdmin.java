import java.io.*;
import java.util.Iterator;
import java.util.List;


public class FoodAdmin implements Serializable
{
    //Product product = new Product();
    List<Product> product = CacheData.list1;
    //String appDir = System.getProperty("user.dir");
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public void product_setting() throws IOException, ClassNotFoundException
    {
        // 역직렬화 기존 데이터 불러오기
        /*File f0 = new File(appDir, "\\data\\productData.ser");
        if (!f0.exists())
        {
            System.out.println("저장된 데이터가 없습니다.");
            return;
        }
        FileInputStream fis = new FileInputStream(f0);
        ObjectInputStream ois = new ObjectInputStream(fis);
        List<Product> product = (List<Product>)ois.readObject();
        ois.close();
        fis.close();*/

        // test 231008 --------------------------------
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
        //-----------------


        System.out.println("[[1. 재료세팅]]========================================================================");
        System.out.print("작업할 대상의 구분번호를 입력하시오 : ");
        int pointNumber = Integer.parseInt(br.readLine());
        boolean found = false;



        for (int i = 0; i < product.size(); i++)
        {
            if (pointNumber == product.get(i).getP_checkNumber())
            {
                found = true;
                Iterator<Product> itListTeset;
                itListTeset = product.iterator();
                Product itS = product.get(i);

                System.out.println("===========================================================================================");
                System.out.printf("|| %5s || %5s || %5s || %9s || %5s || %5s || %5s || %5s ||\n",
                        "구분번호", "분류번호", "이름", "단위", "개수", "칼로리", "적정재고", "금액");
                System.out.println("===========================================================================================");

                System.out.printf("|| %5s || %5s || %5s || %9s || %5s || %5s || %5s || %5s ||\n",
                        itS.getP_checkNumber(), itS.getP_material(), itS.getP_name(),
                        itS.getP_unit(), itS.getP_count(), itS.getP_calorie(),
                        itS.getP_stock(), itS.getP_price());
                System.out.println();

                System.out.print("변경할 항목의 개수를 입력하시오 : ");
                int newCount = Integer.parseInt(br.readLine());
                product.get(i).setP_count(newCount);

                /*System.out.print("저장하시겠습니까?(Y/N) : ");
                char x = br.readLine().charAt(0);
                if (x == 'Y' || x == 'y')
                {
                    FileOutputStream fos = new FileOutputStream(f0);
                    ObjectOutputStream oos = new ObjectOutputStream(fos);
                    oos.writeObject(product);
                    oos.close();
                    fos.close();
                    break;
                }
                else
                    break;*/
            }
        }

        if (!found)
        {
            System.out.println("구분번호가 일치하지 않습니다.");
            return; // 일치하지 않으면 삭제 작업을 하지 않고 종료
        }

    }

    public void soldout_management() throws IOException, ClassNotFoundException
    {
        // 역직렬화 기존 데이터 불러오기
        /*File f0 = new File(appDir, "\\data\\productData.ser");
        if (!f0.exists())
        {
            System.out.println("저장된 데이터가 없습니다.");
            return;
        }
        FileInputStream fis = new FileInputStream(f0);
        ObjectInputStream ois = new ObjectInputStream(fis);
        List<Product> product = (List<Product>)ois.readObject();
        ois.close();
        fis.close();*/


        System.out.println("[[2. 품절관리]]========================================================================");
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

                System.out.println("===========================================================================================");
                System.out.printf("|| %5s || %5s || %5s || %9s || %5s || %5s || %5s || %5s ||\n",
                        "구분번호", "분류번호", "이름", "단위", "개수", "칼로리", "적정재고", "금액");
                System.out.println("===========================================================================================");

                System.out.printf("|| %5s || %5s || %5s || %9s || %5s || %5s || %5s || %5s ||\n",
                        itS.getP_checkNumber(), itS.getP_material(), itS.getP_name(),
                        itS.getP_unit(), itS.getP_count(), itS.getP_calorie(),
                        itS.getP_stock(), itS.getP_price());

                System.out.print("변경할 항목의 개수를 입력하시오 : ");
                int newStock = Integer.parseInt(br.readLine());
                product.get(i).setP_stock(newStock);

                /*System.out.print("저장하시겠습니까?(Y/N) : ");
                char x = br.readLine().charAt(0);
                if (x == 'Y' || x == 'y')
                {
                    FileOutputStream fos = new FileOutputStream(f0);
                    ObjectOutputStream oos = new ObjectOutputStream(fos);
                    oos.writeObject(product);
                    oos.close();
                    fos.close();
                    break;
                }
                else
                    break;*/
            }
        }

        /*if (!found)
        {
            System.out.println("구분번호가 일치하지 않습니다.");
            return; // 일치하지 않으면 삭제 작업을 하지 않고 종료
        }*/

    }
}
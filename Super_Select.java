/*
 메뉴 선택 ----------------------------------------------------------------
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

interface Super_Select_Interface {
    int menuSelect(int listSize);
}

public abstract class Super_Select implements Super_Select_Interface {
    static BufferedReader br;
    protected String selmessage01;
    protected String selmessage02;
    int minNum;

    public int menuSelect(int listSize) {
        int userSelect = 0;
        br = new BufferedReader(new InputStreamReader(System.in));
        try {
            do {
                System.out.printf(selmessage01);
                userSelect = Integer.parseInt(br.readLine());
                if (userSelect < minNum || userSelect > listSize)
                    System.out.println(selmessage02);
            } while (userSelect < minNum || userSelect > listSize);
        } catch (IOException e) {
            System.out.println("e.toString: " + e.toString());
            System.out.println("e.getMessage: " + e.getMessage());
            System.out.println("printStackTrace................");
            e.printStackTrace();
        }
        return userSelect;
    }
}

// 수량 확인
class CheckCount extends Super_Select {
    public CheckCount() {
        this.selmessage01 = ">> 메뉴 선택: ";
        this.selmessage02 = "수량이 남은 수량에서 벗어났습니다.";
        this.minNum = 1;
    }

    @Override
    public int menuSelect(int listSize) {
        return super.menuSelect(listSize);
    }
}

// 메뉴 선택
class SelectMenu extends Super_Select {
    public SelectMenu() {
        this.selmessage01 = ">> 메뉴 선택: ";
        this.selmessage02 = "메뉴 리스트 번호에서 벗어났습니다. 다시 입력해주세요.";
        this.minNum = 1;
    }

    @Override
    public int menuSelect(int listSize) {
        return super.menuSelect(listSize);
    }
}

// 개수 선택
class SelectCount extends Super_Select {
    public SelectCount() {
        this.selmessage01 = ">> 수량 선택: ";
        this.selmessage02 = "수량이 남은 수량에서 벗어났습니다. 다시 입력해주세요.";
        this.minNum = 0;
    }

    @Override
    public int menuSelect(int listSize) {
        return super.menuSelect(listSize);
    }

}
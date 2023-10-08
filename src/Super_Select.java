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
    protected String message;
    protected String errorMsg;
    int minNum;

    public int menuSelect(int listSize) {
        int userSelect = 0;
        br = new BufferedReader(new InputStreamReader(System.in));
        try {
            do {
                System.out.printf(message);
                userSelect = Integer.parseInt(br.readLine());
                if (userSelect < minNum || userSelect > listSize)
                    System.out.println(errorMsg);
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

// 메뉴 선택
class SelectMenu extends Super_Select {
    public SelectMenu() {
        this.message = ">> 메뉴 선택: ";
        this.errorMsg = "메뉴 리스트 번호에서 벗어났습니다. 다시 입력해주세요.";
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
        this.message = ">> 수량 선택: ";
        this.errorMsg = "수량이 남은 수량에서 벗어났습니다. 다시 입력해주세요.";
        this.minNum = 0;
    }

    @Override
    public int menuSelect(int listSize) {
        return super.menuSelect(listSize);
    }

}
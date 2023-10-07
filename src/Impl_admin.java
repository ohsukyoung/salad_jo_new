//public interface Impl_admin {
//    public void ad_print();
//    public void ad_add();
//    public void ad_modify();
//    public void ad_delete();
//}

import java.io.IOException;

public interface Impl_admin
{
    void ad_print()throws IOException, ClassNotFoundException; // 출력 메소드

    void ad_add()throws IOException, ClassNotFoundException; // 생성 메소드

    void ad_modify()throws IOException, ClassNotFoundException; // 수정 메소드

    void ad_delete()throws IOException, ClassNotFoundException; // 삭제 메소드
}
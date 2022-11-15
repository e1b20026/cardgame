package playingcards.cardgame.model;

public class Member {
  int id;
  String userName;
  boolean exist;

  // Thymeleafでフィールドを扱うためにはgetter/setterが必ず必要
  // vscodeのソースコード右クリック->ソースアクションでsetter/getterを簡単に追加できる
  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public boolean getExist() {
    return exist;
  }

  public void setExist(boolean exist) {
    this.exist = exist;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

}

package playingcards.cardgame.model;

public class Member {
  int id;
  String userName;
  boolean exist;
  int rank;

  // Thymeleafでフィールドを扱うためにはgetter/setterが必ず必要
  // vscodeのソースコード右クリック->ソースアクションでsetter/getterを簡単に追加できる

  public Member() {

  }

  public Member(int id, String userName) {
    this.id = id;
    this.userName = userName;
  }

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

  public int getRank() {
    return rank;
  }

  public void setRank(int rank) {
    this.rank = rank;
  }

}

package playingcards.cardgame.model;

public class Member {
  int id;
  String userName;
  boolean exist;
  boolean exist2;
  boolean round3;
  boolean round4;
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

  public boolean getexist2() {
    return exist2;
  }

  public void setRound2(boolean exist2) {
    this.exist2 = exist2;
  }

  public boolean getRound3() {
    return round3;
  }

  public void setRound3(boolean round3) {
    this.round3 = round3;
  }

  public boolean getRound4() {
    return round4;
  }

  public void setRound4(boolean round4) {
    this.round4 = round4;
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

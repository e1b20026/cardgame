package playingcards.cardgame.model;

public class Member {
  int id;
  String userName;
  boolean gameexist;
  boolean exist1;
  boolean exist2;
  boolean exist3;
  boolean exist4;
  boolean result;
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

  public boolean getGameexist() {
    return gameexist;
  }

  public void setGameexist(boolean gameexist) {
    this.gameexist = gameexist;
  }

  public boolean getExist1() {
    return exist1;
  }

  public void setExist1(boolean exist1) {
    this.exist1 = exist1;
  }

  public boolean getexist2() {
    return exist2;
  }

  public void setRound2(boolean exist2) {
    this.exist2 = exist2;
  }

  public boolean getexist3() {
    return exist3;
  }

  public void setexist3(boolean exist3) {
    this.exist3 = exist3;
  }

  public boolean getexist4() {
    return exist4;
  }

  public void setexist4(boolean exist4) {
    this.exist4 = exist4;
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

  public boolean getResult() {
    return result;
  }

  public void setResult(boolean result) {
    this.result = result;
  }

}

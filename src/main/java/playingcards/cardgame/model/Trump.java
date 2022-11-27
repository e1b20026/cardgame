package playingcards.cardgame.model;

public class Trump {
  int id;
  String suit;
  int number;
  boolean place;

  // Thymeleafでフィールドを扱うためにはgetter/setterが必ず必要
  // vscodeのソースコード右クリック->ソースアクションでsetter/getterを簡単に追加できる

  public Trump() {

  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getSuit() {
    return suit;
  }

  public void setSuit(String suit) {
    this.suit = suit;
  }

  public int getNumber() {
    return number;
  }

  public void setNumber(int number) {
    this.number = number;
  }

  public boolean getPlace() {
    return place;
  }

  public void setPlace(boolean place) {
    this.place = place;
  }

}

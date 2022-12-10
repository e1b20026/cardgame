package playingcards.cardgame.model;

public class AllTrump {
  int id;// カードの位置づけ
  String number;
  String suit;
  int evaluationNumber;

  public AllTrump() {

  }

  public AllTrump(int id, String number, String suit) {
    this.id = id;
    this.number = number;
    this.suit = suit;
  }

  public AllTrump(int id, String number, String suit, int evaluationNumber) {
    this.id = id;
    this.number = number;
    this.suit = suit;
    this.evaluationNumber = evaluationNumber;
  }

  public static ArrayList<Trump1> RoyalStraight(ArrayList<Trump1> trump) {
    ArrayList<Trump1> royalStraight = new ArrayList<>();
    ArrayList<Trump1> Straight = Straight(trump);
    ArrayList<Trump1> Frash = Frash(Straight);

    if (Straight.size() != 0) {// ストレートの時
      if (Straight.get(Straight.size() - 1).evaluationNumber == 10 && Frash.size() != 0) {
        royalStraight = Frash;
      }
    }
    return royalStraight;
  }
}

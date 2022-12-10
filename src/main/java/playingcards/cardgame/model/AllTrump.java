package playingcards.cardgame.model;

import java.util.ArrayList;

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

  public static ArrayList<AllTrump> RoyalStraight(ArrayList<AllTrump> trump) {
    ArrayList<AllTrump> royalStraight = new ArrayList<>();
    ArrayList<AllTrump> Straight = Straight(trump);
    ArrayList<AllTrump> Frash = Frash(Straight);

    if (Straight.size() != 0) {// ストレートの時
      if (Straight.get(Straight.size() - 1).evaluationNumber == 10 && Frash.size() != 0) {
        royalStraight = Frash;
      }
    }
    return RoyalStraight;
  }

  // ストレートフラッシュの際に利用
  public static ArrayList<AllTrump> StraightFrash(ArrayList<AllTrump> trump) {
    ArrayList<AllTrump> StraightFrash = new ArrayList<>();
    ArrayList<AllTrump> Straight = Straight(trump);
    ArrayList<AllTrump> Frash = Frash(Straight);

    if (Straight.size() != 0) {// ストレートの時
      if (Frash.size() != 0) {
        StraightFrash = Frash;
      }
    }
    return StraightFrash;
  }
}

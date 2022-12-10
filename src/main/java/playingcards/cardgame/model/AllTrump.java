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

  // ロイヤルストレートフラッシュの際に利用
  public static ArrayList<AllTrump> RoyalStraight(ArrayList<AllTrump> trump) {
    ArrayList<AllTrump> royalStraight = new ArrayList<>();
    ArrayList<AllTrump> Straight = Straight(trump);
    ArrayList<AllTrump> Frash = Frash(Straight);

    if (Straight.size() != 0) {// ストレートの時
      if (Straight.get(Straight.size() - 1).evaluationNumber == 10 && Frash.size() != 0) {
        royalStraight = Frash;
      }
    }
    return royalStraight;
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

  // フォーカードの際に利用
  public static ArrayList<AllTrump> FourCard(ArrayList<AllTrump> trump) {
    ArrayList<AllTrump> fourCard = new ArrayList<>();
    for (int i = 0; i < trump.size(); i++) {
      for (int j = 0; j < trump.size(); j++) {
        if (trump.get(i).evaluationNumber == trump.get(j).evaluationNumber) {
          fourCard.add(trump.get(j));
        }
      }
      if (fourCard.size() != 4) {
        fourCard.clear();
      } else {
        return fourCard;
      }
    }
    return fourCard;
  }

  // ストレートの際に利用
  public static ArrayList<AllTrump> Straight(ArrayList<AllTrump> trump) {
    ArrayList<AllTrump> straight = new ArrayList<>();// suit関係なしにストレートになる数を格納している
    int compareNextCount = 0;
    int compareColumnCount = 0;
    int compareLast = 0;
    for (int i = 0; i < trump.size(); i++) {
      if (compareColumnCount == trump.size() - 4) {
        break;
      }
      for (int j = i; j < trump.size(); j++) {
        if (compareNextCount == 4) {// 4回隣と比較したら抜ける
          break;
        }
        if (trump.get(j).evaluationNumber == trump.get(j + 1).evaluationNumber) {
          straight.add(trump.get(j));
        } else if (trump.get(j).evaluationNumber - 1 == trump.get(j + 1).evaluationNumber) {
          straight.add(trump.get(j));
          compareNextCount++;
          compareLast = j;
        } else {
          straight.clear();
          compareNextCount = 0;// 連続していなかったので0にする
          compareLast = 0;
          break;
        }
      }
      if (compareNextCount == 4) {
        straight.add(trump.get(compareLast + 1));
        break;
      }
      compareColumnCount++;
    }
    return straight;// suit関係なしにストレートになる数を格納しているリストを返す
  }

  // フラッシュの際に利用
  public static ArrayList<AllTrump> Frash(ArrayList<AllTrump> trump) {
    ArrayList<AllTrump> suit = new ArrayList<>();
    int AFlag = 0;

    for (int i = 0; i < trump.size(); i++) {
      if (trump.get(i).evaluationNumber == 14) {
        AFlag = 1;
      }
    }

    for (int i = 0; i < trump.size(); i++) {
      if (AFlag == 1 && trump.get(i).evaluationNumber == 1) {
        break;
      }
      for (int j = 0; j < trump.size(); j++) {
        if (trump.get(i).suit.equals(trump.get(j).suit)) {
          if ((AFlag != 1 || trump.get(j).evaluationNumber != 1)) {
            suit.add(trump.get(j));
          }
        }
      }
      if (suit.size() <= 4) {
        suit.clear();
      } else {
        AFlag = 0;
        return suit;
      }
    }
    if (suit.size() <= 4) {
      suit.clear();
    }
    AFlag = 0;
    return suit;
  }

  // フルハウスの際に利用
  public static ArrayList<AllTrump> FullHouse(ArrayList<AllTrump> trump) {
    ArrayList<AllTrump> fullHouse = new ArrayList<>();
    int nextFlag = 0;
    int nextCount = 0;

    for (int i = 0; i < trump.size(); i++) {
      if (nextCount == 3) {
        fullHouse.add(trump.get(i));
        break;
      }
      if (i != trump.size() - 1 && trump.get(i).evaluationNumber == trump.get(i + 1).evaluationNumber) {// {2,2,2,Q,Q,10,K},{K,Q,Q,10,2,2,2}
        fullHouse.add(trump.get(i));
        nextFlag = 1;
        nextCount++;
      } else {
        if (nextFlag == 1) {
          fullHouse.add(trump.get(i));
          nextFlag = 0;
        }
      }
    }
    return fullHouse;

  }
}

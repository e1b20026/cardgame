package playingcards.cardgame.model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Collections;

public class AllTrump {
  int id;// カードの位置づけ
  String number;
  String suit;
  int evaluationNumber;// 数字を数字評価する
  int evaluationSuit;// スーツを数字評価する

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

  // プリントの際に利用
  public static void AlltrumpPrint(ArrayList<AllTrump> trump) {
    for (int i = 0; i < trump.size(); i++) {
      System.out.print(trump.get(i).evaluationNumber + " " + trump.get(i).number + " " + trump.get(i).suit
          + trump.get(i).evaluationSuit + "|");
    }
    System.out.println();
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

  // ストレートの際に利用
  public static ArrayList<AllTrump> Straight(ArrayList<AllTrump> trump) {// {11,11,10,9,9,9,9}
    ArrayList<AllTrump> straight = new ArrayList<>();// suit関係なしにストレートになる数を格納している
    int compareNextCount = 0;
    int compareColumnCount = 0;
    int sameCount = 0;
    int compareLast = 0;
    for (int i = 0; i < trump.size(); i++) {
      if (compareColumnCount == trump.size() - 4) {
        break;
      }
      for (int j = i; j < trump.size(); j++) {
        if (compareNextCount == 4 || j == trump.size() - 1) {// 4回隣と比較したら抜ける
          break;
        }
        if (trump.get(j).evaluationNumber == trump.get(j + 1).evaluationNumber) {
          sameCount++;
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
    if (sameCount >= 3 || straight.size() <= 4) {
      straight.clear();
    }
    AlltrumpPrint(straight);
    System.out.println(straight.size());
    return straight;// suit関係なしにストレートになる数を格納しているリストを返す
  }

  // ストレート判定を受けたカードが6枚以上の場合に利用
  public static ArrayList<AllTrump> ResultStraight(ArrayList<AllTrump> trump) {
    ArrayList<AllTrump> returnTrump = new ArrayList<>();
    ArrayList<AllTrump> tmpTrump = new ArrayList<>();// 連続しているトランプを格納し、この中から一番強いスーツを取得する
    int maxSuit_index = 0;
    // スーツの強さ
    // ♠（スペード4）＞♥（ハート3）＞♦（ダイヤ2）＞♣（クローバー1）
    for (int i = 0; i < trump.size(); i++) {
      if (i == trump.size() - 1) {
        returnTrump.add(trump.get(i));
        break;
      }
      if (trump.get(i).evaluationNumber == trump.get(i + 1).evaluationNumber) {// {8, 6, 7S,7C,7D, 10,
                                                                               // 9},{10,9,8,7S,7C,7D,6}
        tmpTrump.add(trump.get(i));
        AlltrumpPrint(tmpTrump);
      } else {
        AlltrumpPrint(tmpTrump);
        if (tmpTrump.size() > 0) {
          tmpTrump.add(trump.get(i));
          System.out.println("***************");
          AlltrumpPrint(tmpTrump);
          for (int j = 1; j < tmpTrump.size(); j++) {
            if (tmpTrump.get(j).evaluationSuit > tmpTrump.get(maxSuit_index).evaluationSuit) {
              maxSuit_index = j;
            }
          }
          returnTrump.add(tmpTrump.get(maxSuit_index));
          maxSuit_index = 0;
          tmpTrump.clear();
        } else {
          returnTrump.add(trump.get(i));
        }
      }
    }

    return returnTrump;
  }

  // スリーカードの際に利用
  public static ArrayList<AllTrump> ThreeCard(ArrayList<AllTrump> trump) {
    ArrayList<AllTrump> threeCard = new ArrayList<>();
    int nextCount = 0;

    for (int i = 0; i < trump.size(); i++) {
      if (nextCount == 2) {
        threeCard.add(trump.get(i));
        break;
      }
      if (i != trump.size() - 1 && trump.get(i).evaluationNumber == trump.get(i + 1).evaluationNumber) {// {2,2,2,Q,Q,10,K},{K,Q,Q,10,2,2,2}
        threeCard.add(trump.get(i));
        nextCount++;
      } else {
        if (nextCount == 1) {
          threeCard.clear();
          nextCount = 0;
        }
      }
    }
    return threeCard;
  }

  // ツーペアの際に利用
  public static ArrayList<AllTrump> TwoPair(ArrayList<AllTrump> trump) {
    ArrayList<AllTrump> twoPair = new ArrayList<>();
    int pairCount = 0;
    int pairFlag = 0;

    for (int i = 0; i < trump.size(); i++) {
      if (pairCount == 2) {
        twoPair.add(trump.get(i));
        break;
      }
      if (i != trump.size() - 1 && trump.get(i).evaluationNumber == trump.get(i + 1).evaluationNumber) {
        twoPair.add(trump.get(i));
        pairFlag = 1;
        pairCount++;
      } else {
        if (pairFlag == 1) {
          pairFlag = 0;
          twoPair.add(trump.get(i));
        }
      }
    }
    return twoPair;
  }

  // ワンペアの際に利用
  public static ArrayList<AllTrump> OnePair(ArrayList<AllTrump> trump) {
    ArrayList<AllTrump> onePair = new ArrayList<>();

    for (int i = 0; i < trump.size(); i++) {
      if (i != trump.size() - 1 && trump.get(i).evaluationNumber == trump.get(i + 1).evaluationNumber) {
        onePair.add(trump.get(i));
        onePair.add(trump.get(i + 1));
        break;
      }
    }
    return onePair;
  }

  // ノーハンドの際に利用
  public static ArrayList<AllTrump> noHand(ArrayList<AllTrump> trump) {
    ArrayList<AllTrump> noHand = new ArrayList<>();
    for (int i = 0; i < 5; i++) {
      noHand.add(trump.get(i));
    }
    return noHand;
  }

  // トランプを数字評価するためのメソッド(改)
  public static void numberChange(ArrayList<AllTrump> trump) {
    for (int i = 0; i < trump.size(); i++) {
      if (trump.get(i).number.equals("J") == true) {
        trump.get(i).evaluationNumber = 11;
      } else if (trump.get(i).number.equals("Q") == true) {
        trump.get(i).evaluationNumber = 12;
      } else if (trump.get(i).number.equals("K") == true) {
        trump.get(i).evaluationNumber = 13;
      } else if (trump.get(i).number.equals("A") == true && trump.get(i).evaluationNumber != 1) {
        trump.get(i).evaluationNumber = 14;
        AllTrump addTrump = new AllTrump(trump.get(i).id, trump.get(i).number,
            trump.get(i).suit, 1);
        trump.add(addTrump);
      } else if (trump.get(i).number.equals("A") != true) {
        trump.get(i).evaluationNumber = Integer.parseInt(trump.get(i).number);
      }
    }
  }

  // トランプのスーツを数字評価するためのメソッド
  public static void suitChange(ArrayList<AllTrump> trump) {
    for (int i = 0; i < trump.size(); i++) {
      if (trump.get(i).suit.equals("Spade")) {
        trump.get(i).evaluationSuit = 4;
      } else if (trump.get(i).suit.equals("Heart")) {
        trump.get(i).evaluationSuit = 3;
      } else if (trump.get(i).suit.equals("Dia")) {
        trump.get(i).evaluationSuit = 2;
      } else {
        trump.get(i).evaluationSuit = 1;
      }
    }
  }

  // ソートするためのメソッド(改)
  public static void sorted(ArrayList<AllTrump> trump) {
    Comparator<AllTrump> comparator = new Comparator<AllTrump>() {
      @Override
      public int compare(AllTrump T1, AllTrump T2) {
        Integer t1 = T1.evaluationNumber;
        Integer t2 = T2.evaluationNumber;
        Integer judgement = Integer.valueOf(t1).compareTo(Integer.valueOf(t2));
        return judgement;
      }
    };
    Collections.sort(trump, comparator); // sortメソッドの第2引数に並べ替え方を渡す
    Collections.reverse(trump);
  }

  // カードが5枚になっていない場合に利用
  public static void addTrump(ArrayList<AllTrump> ReTrump, ArrayList<AllTrump> AllTrump) {
    int Flag = 0;
    for (int i = 0; i < AllTrump.size(); i++) {
      if (ReTrump.size() == 5) {
        break;
      }
      for (int j = 0; j < ReTrump.size(); j++) {
        if (AllTrump.get(i).id == ReTrump.get(j).id) {
          Flag = 1;
        }
      }
      if (Flag == 1) {
        break;
      } else {
        ReTrump.add(AllTrump.get(i));
      }
    }
  }

  public String getNumber() {
    return this.number;
  }

  public String getSuit() {
    return this.suit;
  }

}

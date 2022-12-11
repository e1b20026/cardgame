package playingcards.cardgame.model;

import java.lang.Exception;

public class Sample {

  // ロイヤルストレートフラッシュ
  public Boolean IsRoyalFlush(Trump i0, Trump i1, Trump i2, Trump i3, Trump i4) {

    int flag1 = 0, flag2 = 0, flag3 = 0, flag4 = 0, flag5 = 0;
    Trump[] cards = { i0, i1, i2, i3, i4 };

    for (int i = 0; i < 4; i++) {
      if (cards[i].getSuit() != cards[i + 1].getSuit())
        return false;
    }
    if (IsFlush(i0, i1, i2, i3, i4)) {
      for (int i = 0; i < 4; i++) {
        if (cards[i].getNumber() == 10) {
          flag1 = 1;
        }
        if (cards[i].getNumber() == 11) {
          flag2 = 1;
        }
        if (cards[i].getNumber() == 12) {
          flag3 = 1;
        }
        if (cards[i].getNumber() == 13) {
          flag4 = 1;
        }
        if (cards[i].getNumber() == 1) {
          flag5 = 1;
        }
      }

      if (flag1 == 1 && flag2 == 1 && flag3 == 1 && flag4 == 1 && flag5 == 1)
        return true;
    }
    return false;
  }

  // フラッシュ
  public Boolean IsFlush(Trump i0, Trump i1, Trump i2, Trump i3, Trump i4) {
    Trump[] cards = { i0, i1, i2, i3, i4 };
    for (int i = 0; i < 4; i++) {
      if (cards[i].getSuit() != cards[i + 1].getSuit())
        return false;
    }
    return true;
  }

  // ペア系の役(フォー・オブ・ア・カインド、フルハウス、スリー・オブ・ア・カインド、ツーペア、ワンペア)
  private int CountSameRankLine(Trump i0, Trump i1, Trump i2, Trump i3, Trump i4) {
    int count = 0;
    Trump[] cards = { i0, i1, i2, i3, i4 };
    for (int i = 0; i < 4; i++) {
      for (int j = i; j < 4; j++) {
        if (cards[i].getNumber() == cards[i + 1].getNumber())
          ++count;
      }
    }
    return count;
  }

  // フォーカード
  public Boolean IsQuads(Trump i0, Trump i1, Trump i2, Trump i3, Trump i4) {
    return CountSameRankLine(i0, i1, i2, i3, i4) == 6;
  }

  // フルハウス
  public Boolean IsFullHouse(Trump i0, Trump i1, Trump i2, Trump i3, Trump i4) {
    return CountSameRankLine(i0, i1, i2, i3, i4) == 4;
  }

  // スリーカード
  public Boolean IsTrips(Trump i0, Trump i1, Trump i2, Trump i3, Trump i4) {
    return CountSameRankLine(i0, i1, i2, i3, i4) == 3;
  }

  // ツーペア
  public Boolean IsTwoPairs(Trump i0, Trump i1, Trump i2, Trump i3, Trump i4) {
    return CountSameRankLine(i0, i1, i2, i3, i4) == 2;
  }

  // ワンペア
  public Boolean IsOnePair(Trump i0, Trump i1, Trump i2, Trump i3, Trump i4) {
    return CountSameRankLine(i0, i1, i2, i3, i4) == 1;
  }

}

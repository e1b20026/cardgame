package playingcards.cardgame.controller;

import playingcards.cardgame.model.*;

import java.security.Principal;
//import java.util.ArrayList;
import java.util.ArrayList;
import java.util.Random;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
//import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import java.io.IOException;

import playingcards.cardgame.service.*;

/**
 * Sample21Controller
 *
 * クラスの前に@Controllerをつけていると，HTTPリクエスト（GET/POSTなど）があったときに，このクラスが呼び出される
 */
@Controller
@RequestMapping("/Room")
public class CardgameController {

  @Autowired
  TrumpMapper trumpmapper;

  @Autowired
  MemberMapper membermapper;

  @Autowired
  RandTrumpMapper randtrumpmapper;

  @Autowired
  UserResultMapper userresultmapper;

  @Autowired
  private AsyncCard User;

  private final Logger logger = LoggerFactory.getLogger(CardgameController.class);

  @GetMapping("/room1")
  public String room1(ModelMap model, Principal prin) {

    String login_name = prin.getName();
    Member member = membermapper.selectNameMember(login_name);
    // update
   // membermapper.updateByexistT(member);

    // TrumpTableのplaceを初期化する
    trumpmapper.initPlacebyTrumpTable();

    // UserResultTableのデータ消去
    userresultmapper.deleteUserResult();

    // model.addAttribute("members", members2);
    return "room1.html";
  }

  @GetMapping("/round1")
  public String round1(ModelMap model) {
    Random random = new Random();
    int num1 = random.nextInt(52) + 1;
    int num2 = random.nextInt(52) + 1;

    Trump hand1 = trumpmapper.selectOneTrump(num1);
    while (hand1.getPlace() == true) {
      num1 = random.nextInt(52) + 1;
      hand1 = trumpmapper.selectOneTrump(num1);
    }
    trumpmapper.updateByPlace(num1);

    Trump hand2 = trumpmapper.selectOneTrump(num2);
    while (hand2.getPlace() == true) {
      num2 = random.nextInt(52) + 1;
      hand2 = trumpmapper.selectOneTrump(num2);
    }
    trumpmapper.updateByPlace(num2);

    int cpuNum1 = random.nextInt(52) + 1;
    Trump cpuHand1 = trumpmapper.selectOneTrump(cpuNum1);
    while (cpuHand1.getPlace() == true) {
      cpuNum1 = random.nextInt(52) + 1;
      cpuHand1 = trumpmapper.selectOneTrump(cpuNum1);
    }
    trumpmapper.updateByPlace(cpuNum1);

    int cpuNum2 = random.nextInt(52) + 1;
    Trump cpuHand2 = trumpmapper.selectOneTrump(cpuNum2);
    while (cpuHand2.getPlace() == true) {
      cpuNum2 = random.nextInt(52) + 1;
      cpuHand2 = trumpmapper.selectOneTrump(cpuNum2);
    }
    trumpmapper.updateByPlace(cpuNum2);

    int cpuNum3 = random.nextInt(52) + 1;
    Trump cpuHand3 = trumpmapper.selectOneTrump(cpuNum3);
    while (cpuHand3.getPlace() == true) {
      cpuNum3 = random.nextInt(52) + 1;
      cpuHand3 = trumpmapper.selectOneTrump(cpuNum3);
    }
    trumpmapper.updateByPlace(cpuNum3);

    randtrumpmapper.updateIdRandTrump(cpuHand1, 1);
    randtrumpmapper.updateIdRandTrump(cpuHand2, 2);
    randtrumpmapper.updateIdRandTrump(cpuHand3, 3);

    model.addAttribute("hand1", hand1);
    model.addAttribute("hand2", hand2);
    return "round1.html";
  }

  @GetMapping("/round2")
  public String round2(@RequestParam Integer hand1, @RequestParam Integer hand2, ModelMap model, Principal prin) {
    Random random = new Random();
    Trump myHand1 = trumpmapper.selectOneTrump(hand1);
    Trump myHand2 = trumpmapper.selectOneTrump(hand2);

    RandTrump cpuRandHand1 = randtrumpmapper.selectIdRandTrump(1);
    RandTrump cpuRandHand2 = randtrumpmapper.selectIdRandTrump(2);
    RandTrump cpuRandHand3 = randtrumpmapper.selectIdRandTrump(3);

    int cpuNum4 = random.nextInt(52) + 1;
    Trump cpuHand4 = trumpmapper.selectOneTrump(cpuNum4);
    while (cpuHand4.getPlace() == true) {
      cpuNum4 = random.nextInt(52) + 1;
      cpuHand4 = trumpmapper.selectOneTrump(cpuNum4);
    }
    randtrumpmapper.updateIdRandTrump(cpuHand4, 4);
    trumpmapper.updateByPlace(cpuNum4);

    String login_name = prin.getName();
    Member member = membermapper.selectNameMember(login_name);
    // update
    membermapper.updateByexist2T(member);

    model.addAttribute("cpuHand1", cpuRandHand1);
    model.addAttribute("cpuHand2", cpuRandHand2);
    model.addAttribute("cpuHand3", cpuRandHand3);
    model.addAttribute("myHand1", myHand1);
    model.addAttribute("myHand2", myHand2);
    return "round2.html";
  }

  @GetMapping("/round3")
  public String round3(@RequestParam Integer myHand1, @RequestParam Integer myHand2, ModelMap model, Principal prin) {
    Trump hand1 = trumpmapper.selectOneTrump(myHand1);
    Trump hand2 = trumpmapper.selectOneTrump(myHand2);

    Random random = new Random();
    int cpuNum5 = random.nextInt(52) + 1;
    Trump cpuHand5 = trumpmapper.selectOneTrump(cpuNum5);
    while (cpuHand5.getPlace() == true) {
      cpuNum5 = random.nextInt(52) + 1;
      cpuHand5 = trumpmapper.selectOneTrump(cpuNum5);
    }
    randtrumpmapper.updateIdRandTrump(cpuHand5, 5);
    trumpmapper.updateByPlace(cpuNum5);

    RandTrump cpuRandHand1 = randtrumpmapper.selectIdRandTrump(1);
    RandTrump cpuRandHand2 = randtrumpmapper.selectIdRandTrump(2);
    RandTrump cpuRandHand3 = randtrumpmapper.selectIdRandTrump(3);
    RandTrump cpuRandHand4 = randtrumpmapper.selectIdRandTrump(4);

    String login_name = prin.getName();
    Member member = membermapper.selectNameMember(login_name);
    // update
    membermapper.updateByexist3T(member);

    model.addAttribute("cpuHand1", cpuRandHand1);
    model.addAttribute("cpuHand2", cpuRandHand2);
    model.addAttribute("cpuHand3", cpuRandHand3);
    model.addAttribute("cpuHand4", cpuRandHand4);
    model.addAttribute("myHand1", hand1);
    model.addAttribute("myHand2", hand2);
    return "round3.html";
  }

  @GetMapping("/round4")
  public String round4(@RequestParam Integer myHand1, @RequestParam Integer myHand2, ModelMap model, Principal prin) {

    String login_name = prin.getName();
    Member member = membermapper.selectNameMember(login_name);

    Trump hand1 = trumpmapper.selectOneTrump(myHand1);
    Trump hand2 = trumpmapper.selectOneTrump(myHand2);

    ArrayList<AllTrump> JudgeTrump = new ArrayList<>();// 手札とランダムカードの合計7枚を格納するリスト
    ArrayList<AllTrump> ReturnTrump = new ArrayList<>();// 判定後のカードを格納するリスト

    ArrayList<RandTrump> cpuTrump = randtrumpmapper.selectAllRandTrump();

    AllTrump trump = new AllTrump();

    for (int i = 0; i < 7; i++) {
      if (i < 5) {
        trump = new AllTrump(i, cpuTrump.get(i).getNumber(), cpuTrump.get(i).getSuit());
      } else if (i == 5) {
        trump = new AllTrump(i, hand1.getNumber(), hand1.getSuit());
      } else if (i == 6) {
        trump = new AllTrump(i, hand2.getNumber(), hand2.getSuit());
      }

      JudgeTrump.add(trump);

    }

    // Allmethod.AlltrumpPrint(Alltrump);// 準備確認(全てのカードを表示)
    AllTrump.numberChange(JudgeTrump);
    AllTrump.suitChange(JudgeTrump);
    AllTrump.sorted(JudgeTrump);
    // ロイヤルストレートかどうか

    if (AllTrump.RoyalStraight(JudgeTrump).size() == 5) {
      // membermapper.insertRank(10);
      ReturnTrump = AllTrump.RoyalStraight(JudgeTrump);
      System.out.println("ロイヤルストレートフラッシュです");// デバッグ用
    } else if (AllTrump.StraightFrash(JudgeTrump).size() == 5) {
      // membermapper.insertRank(9);
      ReturnTrump = AllTrump.StraightFrash(JudgeTrump);
      System.out.println("ストレートフラッシュです");// デバッグ用
    } else if (AllTrump.FourCard(JudgeTrump).size() == 4) {
      // membermapper.insertRank(8);
      ReturnTrump = AllTrump.FourCard(JudgeTrump);
      System.out.println("フォーカードです");// デバッグ用
    } else if (AllTrump.FullHouse(JudgeTrump).size() == 5) {
      // membermapper.insertRank(7);
      ReturnTrump = AllTrump.FullHouse(JudgeTrump);
      System.out.println("フルハウスです");// デバッグ用
    } else if (AllTrump.Frash(JudgeTrump).size() == 5) {
      // membermapper.insertRank(6);
      ReturnTrump = AllTrump.Frash(JudgeTrump);
      System.out.println("フラッシュです");// デバッグ用
    } else if (AllTrump.ResultStraight(AllTrump.Straight(JudgeTrump)).size() == 5) {
      // membermapper.insertRank(5);
      ReturnTrump = AllTrump.ResultStraight(AllTrump.Straight(JudgeTrump));
      System.out.println("ストレートです");// デバッグ用
    } else if (AllTrump.ThreeCard(JudgeTrump).size() == 3) {
      // membermapper.insertRank(4);
      ReturnTrump = AllTrump.ThreeCard(JudgeTrump);
      System.out.println("スリーカードです");// デバッグ用
    } else if (AllTrump.TwoPair(JudgeTrump).size() == 4) {
      // membermapper.insertRank(3);//デバッグ用
      ReturnTrump = AllTrump.TwoPair(JudgeTrump);
      System.out.println("ツーペアです");// デバッグ用
    } else if (AllTrump.OnePair(JudgeTrump).size() == 2) {
      // membermapper.insertRank(2);
      ReturnTrump = AllTrump.OnePair(JudgeTrump);
      System.out.println("ワンペアです");// デバッグ用
    } else {
      // membermapper.insertRank(1);
      ReturnTrump = AllTrump.noHand(JudgeTrump);
      System.out.println("ノーペアです");// デバッグ用
    }

    // フォーカード・スリーカード・ツーペア・ワンペアの際に利用
    if (ReturnTrump.size() != 5) {
      AllTrump.addTrump(ReturnTrump, JudgeTrump);
    }

    // カード重複バグ回避
    while (AllTrump.checkTrump(ReturnTrump) == false) {
      AllTrump.deleteDoubleTrump(ReturnTrump);
      AllTrump.addTrump(ReturnTrump, JudgeTrump);
    }

    AllTrump.AlltrumpPrint(ReturnTrump);// デバッグ用
    int id = member.getId();

    userresultmapper.insertResult(id, login_name, ReturnTrump.get(0).getNumber(), ReturnTrump.get(0).getSuit(),
        ReturnTrump.get(1).getNumber(), ReturnTrump.get(1).getSuit(), ReturnTrump.get(2).getNumber(),
        ReturnTrump.get(2).getSuit(), ReturnTrump.get(3).getNumber(), ReturnTrump.get(3).getSuit(),
        ReturnTrump.get(4).getNumber(), ReturnTrump.get(4).getSuit());

    // update
    membermapper.updateByexist4T(member);

    model.addAttribute("myHand1", hand1);
    model.addAttribute("myHand2", hand2);
    return "round4.html";
  }

  @GetMapping("/result")
  public String result(Principal prin, ModelMap model) {
    String name = prin.getName();
    Member member = membermapper.selectNameMember(name);
    ArrayList<UserResult> resultTrump = userresultmapper.selectAllByResult();
  //  membermapper.updateByexistF(member);
    model.addAttribute("resultTrump", resultTrump);
    return "result.html";
  }

/*  @GetMapping("/exist")
public SseEmitter showexist() {
  // infoレベルでログを出力する
  logger.info("exist");
  final SseEmitter sseEmitter = new SseEmitter();
  try {
    this.User.loginUser(sseEmitter);
  } catch (IOException e) {
    e.printStackTrace();
  }
  return sseEmitter;

}


@GetMapping("/exist2")
public SseEmitter showexist2() {
  // infoレベルでログを出力する
  logger.info("exist2");
  final SseEmitter sseEmitter = new SseEmitter();
  try {
    this.User.accessexist2User(sseEmitter);
  } catch (IOException e) {
    e.printStackTrace();
  }
  return sseEmitter;

}

@GetMapping("/exist3")
public SseEmitter showexist3() {
  // infoレベルでログを出力する
  logger.info("exist3");
  final SseEmitter sseEmitter = new SseEmitter();
  try {
    this.User.accessexist3User(sseEmitter);
  } catch (IOException e) {
    e.printStackTrace();
  }
  return sseEmitter;

}

@GetMapping("/exist4")
public SseEmitter showexist4() {
  // infoレベルでログを出力する
  logger.info("exist4");
  final SseEmitter sseEmitter = new SseEmitter();
  try {
    this.User.accessexist4User(sseEmitter);
  } catch (IOException e) {
    e.printStackTrace();
  }
  return sseEmitter;

}

  @GetMapping("/sendresult")
  public SseEmitter showresult() {
    // infoレベルでログを出力する
    logger.info("result");
    final SseEmitter sseEmitter = new SseEmitter();
    try {
      this.User.resultUser(sseEmitter);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return sseEmitter;

  }

  */
}

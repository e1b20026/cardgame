package playingcards.cardgame.controller;

import playingcards.cardgame.model.*;

import java.security.Principal;
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
  private AsyncCard User;

  private final Logger logger = LoggerFactory.getLogger(CardgameController.class);

  @GetMapping("/room1")
  public String room1(ModelMap model, Principal prin) {

    String login_name = prin.getName();
    Member member = membermapper.selectNameMember(login_name);
    // update
    membermapper.updateByexistT(member);

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

    int cpuNum2 = random.nextInt(52) + 1;
    Trump cpuHand2 = trumpmapper.selectOneTrump(cpuNum2);
    while (cpuHand2.getPlace() == true) {
      cpuNum2 = random.nextInt(52) + 1;
      cpuHand2 = trumpmapper.selectOneTrump(cpuNum2);
    }

    int cpuNum3 = random.nextInt(52) + 1;
    Trump cpuHand3 = trumpmapper.selectOneTrump(cpuNum3);
    while (cpuHand3.getPlace() == true) {
      cpuNum3 = random.nextInt(52) + 1;
      cpuHand3 = trumpmapper.selectOneTrump(cpuNum3);
    }

    randtrumpmapper.updateIdRandTrump(cpuHand1, 1);
    randtrumpmapper.updateIdRandTrump(cpuHand2, 2);
    randtrumpmapper.updateIdRandTrump(cpuHand3, 3);

    model.addAttribute("hand1", hand1);
    model.addAttribute("hand2", hand2);
    return "round1.html";
  }

  @GetMapping("/round2")
  public String round2(@RequestParam Integer hand1, @RequestParam Integer hand2, ModelMap model) {
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

    model.addAttribute("cpuHand1", cpuRandHand1);
    model.addAttribute("cpuHand2", cpuRandHand2);
    model.addAttribute("cpuHand3", cpuRandHand3);
    model.addAttribute("myHand1", myHand1);
    model.addAttribute("myHand2", myHand2);
    return "round2.html";
  }

  @GetMapping("/round3")
  public String round3(@RequestParam Integer myHand1, @RequestParam Integer myHand2, ModelMap model) {
    Trump hand1 = trumpmapper.selectOneTrump(myHand1);
    Trump hand2 = trumpmapper.selectOneTrump(myHand2);

    RandTrump cpuRandHand1 = randtrumpmapper.selectIdRandTrump(1);
    RandTrump cpuRandHand2 = randtrumpmapper.selectIdRandTrump(2);
    RandTrump cpuRandHand3 = randtrumpmapper.selectIdRandTrump(3);
    RandTrump cpuRandHand4 = randtrumpmapper.selectIdRandTrump(4);

    model.addAttribute("cpuHand1", cpuRandHand1);
    model.addAttribute("cpuHand2", cpuRandHand2);
    model.addAttribute("cpuHand3", cpuRandHand3);
    model.addAttribute("cpuHand3", cpuRandHand4);
    model.addAttribute("myHand1", hand1);
    model.addAttribute("myHand2", hand2);
    return "round3.html";
  }

  @GetMapping("/round4")
  public String round4(@RequestParam Integer myHand1, @RequestParam Integer myHand2, ModelMap model) {

    return "round4.html";
  }

  @GetMapping("/result")
  public String result(Principal prin, ModelMap model) {
    String name = prin.getName();
    Member member = membermapper.selectNameMember(name);
    membermapper.updateByexistF(member);
    return "result.html";
  }

  @GetMapping("/exist")
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
}

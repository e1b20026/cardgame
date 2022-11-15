package playingcards.cardgame.controller;

import playingcards.cardgame.model.*;


import java.security.Principal;
//import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
//import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;


/**
 * Sample21Controller
 *
 * クラスの前に@Controllerをつけていると，HTTPリクエスト（GET/POSTなど）があったときに，このクラスが呼び出される
 */
@Controller
@RequestMapping("/Room")
public class CardgameController {

  @Autowired
  MemberMapper membermapper;

  @GetMapping("/room1")
  public String room1(ModelMap model, Principal prin) {

    String login_name = prin.getName();
    Member member = membermapper.selectNameMember(login_name);
    // update
    membermapper.updateByName(member);

    //model.addAttribute("members", members2);
    return "room1.html";
  }
}
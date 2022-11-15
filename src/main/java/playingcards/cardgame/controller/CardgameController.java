package playingcards.cardgame.controller;

import org.springframework.stereotype.Controller;
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

  @GetMapping("/room1")
  public String room1(ModelMap model, Principal prin) {

    Member member = new member(userName);
    // update
    MemberMapper.updateByName(member);
    // フルーツリストを取得
    ArrayList<member> members2 = MemberMapper.selectAllmember();
    model.addAttribute("members2", members2);
    return "room1.html";
  }
}

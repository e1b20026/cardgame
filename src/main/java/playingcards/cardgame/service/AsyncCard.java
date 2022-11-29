package playingcards.cardgame.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import playingcards.cardgame.model.*;

@Service
public class AsyncCard {
  boolean dbUpdated = false;
  private final Logger logger = LoggerFactory.getLogger(AsyncCard.class);

  @Autowired
  MemberMapper membermapper;

  @Async
  public void loginUser(SseEmitter emitter) throws IOException {
    ArrayList<Member> members;
    try {
      members = membermapper.selectTrueMember();
      int count = 0;
      for (Member member : members) {
        if (member.getExist() == true) {
          count++;
        }
      }
      emitter.send(count);
      // sendによってcountがブラウザにpushされる
      // 1秒STOP
      TimeUnit.SECONDS.sleep(1);
    } catch (InterruptedException e) {
      // 例外の名前とメッセージだけ表示する
      logger.warn("Exception:" + e.getClass().getName() + ":" + e.getMessage());
    }
    emitter.complete();// emitterの後始末．明示的にブラウザとの接続を一度切る．
  }

  @Async
  public void resultUser(SseEmitter emitter) throws IOException {
    ArrayList<Member> members;
    try {
      members = membermapper.selectTrueMember();
      int count = 0;
      for (Member member : members) {
        if (member.getExist() == true) {
          count++;
        }
      }
      emitter.send(count);
      // sendによってcountがブラウザにpushされる
      // 1秒STOP
      TimeUnit.SECONDS.sleep(1);
    } catch (InterruptedException e) {
      // 例外の名前とメッセージだけ表示する
      logger.warn("Exception:" + e.getClass().getName() + ":" + e.getMessage());
    }
    emitter.complete();// emitterの後始末．明示的にブラウザとの接続を一度切る．
  }
}

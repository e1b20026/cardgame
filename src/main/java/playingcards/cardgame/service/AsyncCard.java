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
  public void accessGameExistUser(SseEmitter emitter) throws IOException {
    ArrayList<Member> members;
    try {
      members = membermapper.selectgameexistTrueMember();
      int count = 0;
      for (Member member : members) {
        if (member.getGameexist() == true) {
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
  public void accessexist1User(SseEmitter emitter) throws IOException {
    ArrayList<Member> members;
    try {
      members = membermapper.selectexit1TrueMember();
      int count = 0;
      for (Member member : members) {
        if (member.getExist1() == true) {
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
  public void accessexist2User(SseEmitter emitter) throws IOException {
    ArrayList<Member> members;
    ArrayList<Member> resultMembers;
    try {
      resultMembers = membermapper.selectresultTrueMember();
      members = membermapper.selectexist2TrueMember();
      int count2 = 0;
      int countR = 0;
      for (Member member : members) {
        if (member.getExist2() == true) {
          count2++;
        }
      }
      for (Member member : resultMembers) {
        if (member.getResult() == true) {
          countR++;
        }
      }
      emitter.send(count2 + countR);
      // sendによってcountがブラウザにpushされる
      // 1秒STOP
      TimeUnit.SECONDS.sleep(1);
    } catch (InterruptedException e) {
      // 例外の名前とメッセージだけ表示する
      logger.warn("Exception:" + e.getClass().getName() + ":" + e.getMessage());
    }
    emitter.complete();// emitterの後始末．明示的にブラウザとの接続を一度切る．
  }

  /*
   * @Async
   * public void accessexist3User(SseEmitter emitter) throws IOException {
   * ArrayList<Member> members;
   * try {
   * members = membermapper.selectexist3TrueMember();
   * int count = 0;
   * for (Member member : members) {
   * if (member.getExist() == true) {
   * count++;
   * }
   * }
   * emitter.send(count);
   * // sendによってcountがブラウザにpushされる
   * // 1秒STOP
   * TimeUnit.SECONDS.sleep(1);
   * } catch (InterruptedException e) {
   * // 例外の名前とメッセージだけ表示する
   * logger.warn("Exception:" + e.getClass().getName() + ":" + e.getMessage());
   * }
   * emitter.complete();// emitterの後始末．明示的にブラウザとの接続を一度切る．
   * }
   *
   * @Async
   * public void accessexist4User(SseEmitter emitter) throws IOException {
   * ArrayList<Member> members;
   * try {
   * members = membermapper.selectexist4TrueMember();
   * int count = 0;
   * for (Member member : members) {
   * if (member.getExist() == true) {
   * count++;
   * }
   * }
   * emitter.send(count);
   * // sendによってcountがブラウザにpushされる
   * // 1秒STOP
   * TimeUnit.SECONDS.sleep(1);
   * } catch (InterruptedException e) {
   * // 例外の名前とメッセージだけ表示する
   * logger.warn("Exception:" + e.getClass().getName() + ":" + e.getMessage());
   * }
   * emitter.complete();// emitterの後始末．明示的にブラウザとの接続を一度切る．
   * }
   */
  @Async
  public void accessResultUser(SseEmitter emitter) throws IOException {
    ArrayList<Member> members;
    try {
      members = membermapper.selectresultTrueMember();
      int count = 0;
      for (Member member : members) {
        if (member.getResult() == true) {
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

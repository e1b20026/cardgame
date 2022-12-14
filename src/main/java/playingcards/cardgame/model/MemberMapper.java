package playingcards.cardgame.model;

import java.util.ArrayList;

//import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
//import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface MemberMapper {

  @Select("SELECT * from member")
  ArrayList<Member> selectAllMember();

  @Select("SELECT * from member where userName = #{name}")
  Member selectNameMember(String name);

  @Select("SELECT * from member where gameexist = true")
  ArrayList<Member> selectgameexistTrueMember();

  @Select("SELECT * from member where exist1 = true")
  ArrayList<Member> selectexit1TrueMember();

  @Select("SELECT * from member where exist2 = true")
  ArrayList<Member> selectexist2TrueMember();

  @Select("SELECT * from member where exist3 = true")
  ArrayList<Member> selectexist3TrueMember();

  @Select("SELECT * from member where exist4 = true")
  ArrayList<Member> selectexist4TrueMember();

  @Select("SELECT * from member where result = true")
  ArrayList<Member> selectresultTrueMember();

  // @Select("SELECT * from member where exist = false")
  // ArrayList<Member> selectFalseMember();

  @Update("UPDATE member SET gameexist = false, exist1 = false, exist2 = false, exist3 = false, exist4 = false,result = false WHERE id = #{id}")
  void updateByexistF(Member member);

  @Update("UPDATE member SET gameexist = true WHERE id = #{id}")
  void updateBygameexistT(Member member);

  @Update("UPDATE member SET exist1 = true WHERE id = #{id}")
  void updateByexist1T(Member member);

  @Update("UPDATE member SET exist2 = true WHERE id = #{id}")
  void updateByexist2T(Member member);

  @Update("UPDATE member SET exist3 = true WHERE id = #{id}")
  void updateByexist3T(Member member);

  @Update("UPDATE member SET exist4 = true WHERE id = #{id}")
  void updateByexist4T(Member member);

  @Update("UPDATE member SET result = true WHERE id = #{id}")
  void updateByresultT(Member member);




}

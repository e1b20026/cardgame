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

  @Select("SELECT * from member where exist = true")
  ArrayList<Member> selectTrueMember();

  @Select("SELECT * from member where exist2 = true")
  ArrayList<Member> selectexist2TrueMember();

  @Select("SELECT * from member where exist3 = true")
  ArrayList<Member> selectexist3TrueMember();

  @Select("SELECT * from member where exist = false")
  ArrayList<Member> selectFalseMember();

  @Update("UPDATE member SET exist = true WHERE id = #{id}")
  void updateByexistT(Member member);

  @Update("UPDATE member SET exist = false WHERE id = #{id}")
  void updateByexistF(Member member);

  @Update("UPDATE member SET exist2 = true WHERE id = #{id}")
  void updateByexist2T(Member member);

  @Update("UPDATE member SET exist3 = true WHERE id = #{id}")
  void updateByexist3T(Member member);
}

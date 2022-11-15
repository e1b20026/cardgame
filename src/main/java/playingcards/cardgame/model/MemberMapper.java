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

  @Update("UPDATE member SET exist = true where userName = #{userName}")
  void updateByName(Member member);
}

package playingcards.cardgame.model;

import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
//import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
//import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserResultMapper {
  @Select("SELECT * from userresult order by rank desc")
  ArrayList<UserResult> selectAllByResult();

  @Insert("INSERT INTO userresult (id, username, tn1, ts1 ,tn2, ts2 , tn3, ts3 , tn4, ts4 , tn5, ts5, rank, roleName) values (#{id}, #{username}, #{tn1}, #{ts1}, #{tn2}, #{ts2}, #{tn3}, #{ts3}, #{tn4}, #{ts4}, #{tn5}, #{ts5}, #{rank}, #{role})")
  void insertResult(int id, String username, String tn1, String ts1, String tn2, String ts2, String tn3, String ts3,
      String tn4, String ts4, String tn5, String ts5, int rank, String role);

  @Delete("DELETE FROM UserResult")
  void deleteUserResult();
}

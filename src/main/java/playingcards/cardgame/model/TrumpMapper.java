package playingcards.cardgame.model;

import java.util.ArrayList;

//import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
//import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface TrumpMapper {

  @Select("SELECT * from trump")
  ArrayList<Trump> selectAllTrump();

  @Select("SELECT * from trump where id = #{id}")
  Trump selectOneTrump(int id);

  @Update("UPDATE trump SET place = true WHERE id = #{id}")
  void updateByPlace(int id);

  @Update("UPDATE trump SET place = false")
  void initPlacebyTrumpTable();
}

package playingcards.cardgame.model;

import java.util.ArrayList;

//import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
//import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface RandTrumpMapper {

  @Select("SELECT * from randtrump")
  ArrayList<RandTrump> selectAllRandTrump();

  @Select("SELECT * from randtrump where id = #{id}")
  RandTrump selectIdRandTrump(int id);

  @Select("SELECT * from randtrump where suit is not null")
  ArrayList<RandTrump> selectIdRandTrumpNotNull();

  @Update("UPDATE randtrump SET suit = #{trump.suit},  number = #{trump.number} where id = #{id}")
  boolean updateIdRandTrump(Trump trump, int id);
}

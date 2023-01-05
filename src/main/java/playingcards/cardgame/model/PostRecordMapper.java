package playingcards.cardgame.model;

import java.util.ArrayList;

//import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
//import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface PostRecordMapper {

  @Select("select * from PostRecord")
  ArrayList<PostRecord> selectAllPostRecord();

  @Select("select * from PostRecord where id = #{id}")
  ArrayList<PostRecord> selectIdPostRecord(int id);
}

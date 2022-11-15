package playingcards.cardgame.model;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface MemberMapper {

  @Select("SELECT * from member")
  Member selectById(int id);
}

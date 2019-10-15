package com.lee.springbootdemo.Mapper;

        import com.lee.springbootdemo.entities.manager;
        import org.apache.ibatis.annotations.Mapper;
        import org.apache.ibatis.annotations.Select;
        import org.springframework.stereotype.Component;
        import org.springframework.web.bind.annotation.Mapping;

        import java.util.List;


@Mapper
public interface ManagerMapper {

//    @Select("select * from manager where mid = #{mid}")
//    public manager queryAll(int mid);

   @Select("select * from manager where mid = #{mid}")
   public manager queryManagerByIdTest(int mid);

    @Select("select * from manager")
    public List<manager> queryAll();

    public manager queryManagerById(int mid);

}

package com.example.crud;


import com.example.crud.dto.LanguageForm;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper

public interface LanguageMapper {

 //get
@Select("SELECT * FROM language")
List<LanguageForm> getAll();
 
@Select("SELECT * FROM language WHERE language_id=#{id}") // id를 매개 값으로
LanguageForm getById(@Param("id") int id);



 //insert

 @Insert("INSERT INTO language(name) VALUES(#{LanguageForm.name})")
 @Options(useGeneratedKeys = true,keyProperty = "id")
 int insert(@Param("LanguageForm")LanguageForm languageForm); // dto LanguageForm에서 받은걸 위에 쿼리에 넣기


 //update
 @Update("UPDATE  language  SET  name='${name}' WHERE language_id=${id}")
 int updateLanguage(@Param("id") int id,@Param("name") String name);





 //delete
@Delete("DELETE FROM language WHERE language_id=${id}")
 int deleteLanguage(@Param("id") int id);



}

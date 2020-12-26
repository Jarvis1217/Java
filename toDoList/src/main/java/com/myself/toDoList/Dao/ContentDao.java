package com.myself.toDoList.Dao;

import com.myself.toDoList.pojo.Content;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

public interface ContentDao extends CrudRepository<Content,Integer> {
    @Modifying
    @Transactional
    @Query(value = "delete from content where text=?1",nativeQuery = true)
    void DeleteByText(String text);
}

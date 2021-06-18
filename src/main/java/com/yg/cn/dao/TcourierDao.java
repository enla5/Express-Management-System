package com.yg.cn.dao;

import com.yg.cn.entity.Tcourier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TcourierDao extends JpaRepository<Tcourier,Integer>, JpaSpecificationExecutor<Tcourier> {

    @Query("from Tcourier where couName=?1")
    Tcourier findbyExName(String couName);
}

package com.yg.cn.dao;

import com.yg.cn.entity.TexpressCabinet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface TexpressCabinetDao extends JpaRepository<TexpressCabinet,Integer>, JpaSpecificationExecutor<TexpressCabinet> {

    @Query("from TexpressCabinet where exPickupCode=?1")
    TexpressCabinet findByExPickupCode(String exPickupCode);

    /**
     * 根据快递位置和快递员查询
     * @param exPosition
     * @param exPersonName
     * @return
     */
    @Query("from TexpressCabinet where exPosition=?1 and exPersonName=?2")
    List<TexpressCabinet> findByNameAndPosition(String exPosition,String exPersonName);

}

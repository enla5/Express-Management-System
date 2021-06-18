package com.yg.cn.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 快递柜列表
 */
@Entity
@Table(name = "t_express_cabinet")
public class TexpressCabinet {

    /**
     * 快递柜序号
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ex_id")
    private  Integer exId;

    /**
     * 快递柜位置
     */
    @Column(name ="ex_position")
    private String exPosition;

    /**
     * 快递仓编号
     */
    @Column(name ="ex_warehouse_id")
    private String  exWarehouseId;

    /**
     * 状态
     * 1.空闲
     * 2.使用中
     */
    @Column(name ="ex_state")
    private String exState;

    /**
     * 快递单号
     */
    @Column(name ="ex_card_id")
    private String exCardId;

    /**
     * 快递员姓名
     */
    @Column(name ="ex_person_name")
    private String exPersonName;

    /**
     * 快递公司
     */
    @Column(name ="ex_company")
    private String exCompany;

    /**
     * 快递员手机号
     */
    @Column(name ="ex_person_phone")
    private String exPersonPhone;

    /**
     * 取件码
     */
    @Column(name ="ex_pickup_code")
    private String exPickupCode;

    /**
     * 收件人姓名
     */
    @Column(name ="ex_receive_name")
    private String exReceiveName;

    /**
     * 收件人手机号码
     */
    @Column(name ="ex_warehouse_phone")
    private String exReceivePhone;

    /**
     * 一个快递柜对应多个记录表
     * 级联操作:fetch = FetchType.EAGER
     */
    @OneToMany(targetEntity = TexpressRecord.class)
    @JoinColumn(name = "ex_id",referencedColumnName = "ex_id")
    private List<TexpressRecord> texpressRecords=new ArrayList<>();

    public Integer getExId() {
        return exId;
    }

    public void setExId(Integer exId) {
        this.exId = exId;
    }

    public String getExPosition() {
        return exPosition;
    }

    public void setExPosition(String exPosition) {
        this.exPosition = exPosition;
    }

    public String getExWarehouseId() {
        return exWarehouseId;
    }

    public void setExWarehouseId(String exWarehouseId) {
        this.exWarehouseId = exWarehouseId;
    }

    public String getExState() {
        return exState;
    }

    public void setExState(String exState) {
        this.exState = exState;
    }

    public String getExCardId() {
        return exCardId;
    }

    public void setExCardId(String exCardId) {
        this.exCardId = exCardId;
    }

    public String getExPersonName() {
        return exPersonName;
    }

    public void setExPersonName(String exPersonName) {
        this.exPersonName = exPersonName;
    }

    public String getExCompany() {
        return exCompany;
    }

    public void setExCompany(String exCompany) {
        this.exCompany = exCompany;
    }

    public String getExPersonPhone() {
        return exPersonPhone;
    }

    public void setExPersonPhone(String exPersonPhone) {
        this.exPersonPhone = exPersonPhone;
    }

    public String getExPickupCode() {
        return exPickupCode;
    }

    public void setExPickupCode(String exPickupCode) {
        this.exPickupCode = exPickupCode;
    }

    public String getExReceiveName() {
        return exReceiveName;
    }

    public void setExReceiveName(String exReceiveName) {
        this.exReceiveName = exReceiveName;
    }

    public String getExReceivePhone() {
        return exReceivePhone;
    }

    public void setExReceivePhone(String exReceivePhone) {
        this.exReceivePhone = exReceivePhone;
    }

//    public List<TexpressRecord> getTexpressRecords() {
//        return texpressRecords;
//    }
//
//    public void setTexpressRecords(List<TexpressRecord> texpressRecords) {
//        this.texpressRecords = texpressRecords;
//    }


    @Override
    public String toString() {
        return "TexpressCabinet{" +
                "exId=" + exId +
                ", exPosition='" + exPosition + '\'' +
                ", exWarehouseId='" + exWarehouseId + '\'' +
                ", exState='" + exState + '\'' +
                ", exCardId='" + exCardId + '\'' +
                ", exPersonName='" + exPersonName + '\'' +
                ", exCompany='" + exCompany + '\'' +
                ", exPersonPhone='" + exPersonPhone + '\'' +
                ", exPickupCode='" + exPickupCode + '\'' +
                ", exReceiveName='" + exReceiveName + '\'' +
                ", exReceivePhone='" + exReceivePhone + '\'' +
                '}';
    }
}

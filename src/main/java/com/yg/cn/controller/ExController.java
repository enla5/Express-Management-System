package com.yg.cn.controller;

import com.yg.cn.bean.GirdBean;
import com.yg.cn.dao.TcourierDao;
import com.yg.cn.dao.TexpressCabinetDao;
import com.yg.cn.dao.TexpressRecordDao;
import com.yg.cn.entity.Tcourier;
import com.yg.cn.entity.TexpressCabinet;
import com.yg.cn.entity.TexpressRecord;
import com.yg.cn.service.CodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
public class ExController {
    @Autowired
    private TcourierDao tcourierDao;

    @Autowired
    private TexpressCabinetDao texpressCabinetDao;

    @Autowired
    private TexpressRecordDao texpressRecordDao;

    /**
     * 验证码操作
     */
    @Autowired
    private CodeService codeService;

    /**
     * 查询所有快递柜的存储情况
     * @return
     */
    @RequestMapping("showExCard")
    @ResponseBody
    public GirdBean showExCard(){
        System.out.println("showExCard*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-查询所有快递柜的存储情况！");
        List<TexpressCabinet> records=texpressCabinetDao.findAll();
        return new GirdBean(0,"快递柜状态查询完毕！",records,records.size());
    }

    /**
     * 添加快递员信息
     * @return
     */
    @RequestMapping("addCourier")
    @ResponseBody
    public GirdBean addCourier(Tcourier tcourier){
        System.out.println("addCourier*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-添加快递员信息！");
        Tcourier records= tcourierDao.save(tcourier);
        return new GirdBean(0,"快递员添加完毕！",null,0);
    }

    /**
     *查询所有快递员的信息
     * @param tcourier
     * @return
     */
    @RequestMapping("findAllCourier")
    @ResponseBody
    public GirdBean findAllCourier(Tcourier tcourier){
        System.out.println("findAllCourier*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-查询快递员信息！");
        List<Tcourier> records= tcourierDao.findAll();
        return new GirdBean(0,"快递员查询完毕！",records,records.size());
    }

    /**
     * 根据id查询快递员信息
     * @param couId
     * @return
     */
    @RequestMapping("findCourierById")
    @ResponseBody
    public GirdBean findCourierById(Integer couId){
        System.out.println("findCourierById*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-根据ID查询快递员信息！");
        Optional<Tcourier> records= tcourierDao.findById(couId);
        return new GirdBean(0,"快递员添加完毕！", Collections.singletonList(records),0);
    }

    /**
     * 查询所有快递柜信息
     * @param
     * @return
     */
    @RequestMapping("findAllCabinet")
    @ResponseBody
    public GirdBean findAllCabinet(TexpressCabinet texpressCabinet){
        System.out.println("findAllCabinet*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-查询所有快递柜信息！");
        List<TexpressCabinet> records= texpressCabinetDao.findAll();
        return new GirdBean(0,"快递员查询完毕！",records,records.size());
    }

    /**
     * 根据id展示快递柜的信息
     * @param exId
     * @return
     */
    @RequestMapping("findCabinetById")
    @ResponseBody
    public GirdBean findCabinetById(Integer exId){
        System.out.println("findCourierById*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-根据ID查询快递员信息！");
        Optional<TexpressCabinet> records= texpressCabinetDao.findById(exId);
        return new GirdBean(0,"快递员添加完毕！", Collections.singletonList(records),0);
    }

    /**
     * 存入快递的相关操作
     * @return
     */
    @RequestMapping("saveBox")
    @ResponseBody
    public GirdBean saveBox(TexpressCabinet texpressCabinet){
        System.out.println("saveBox*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-存入快递的信息！");
        //发送验证码
        if (!texpressCabinet.getExState().equals("使用中")){
            Integer couId= Integer.valueOf(texpressCabinet.getExPersonName());
            String code = codeService.sendCode(texpressCabinet.getExReceivePhone());
            texpressCabinet.setExPickupCode(code);
            texpressCabinet.setExState("使用中");
            System.out.println(texpressCabinet.getExWarehouseId()+"---------------");
            Optional<TexpressCabinet> byId = texpressCabinetDao.findById(Integer.valueOf(texpressCabinet.getExWarehouseId()));
            texpressCabinet.setExWarehouseId(byId.get().getExWarehouseId());
            Optional<Tcourier> tcourierDaoById = tcourierDao.findById(Integer.valueOf(texpressCabinet.getExPersonName()));
            texpressCabinet.setExPersonName(tcourierDaoById.get().getCouName());
            //存入id以防止重新添加
            texpressCabinet.setExId(byId.get().getExId());
            //将原来的藏柜id存上用来更新操作
            System.out.println(texpressCabinet.toString());
            texpressCabinetDao.save(texpressCabinet);
            //将信息同步到快递存储信息记录表中
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            // new Date()为获取当前系统时间
            String createDate = df.format(new Date());
            TexpressRecord texpressRecord=new TexpressRecord();
            texpressRecord.setReDate(createDate);
            texpressRecord.setReState("存入");
            texpressRecord.setRePosition(texpressCabinet.getExPosition());
            texpressRecord.setReBoxNumber(texpressCabinet.getExWarehouseId());
            texpressRecord.setRePeople(texpressCabinet.getExPersonName());
            texpressRecord.setRePeoplePhone(texpressCabinet.getExPersonPhone());
            texpressRecord.setTexpressCabinet(byId.get());
            texpressRecordDao.save(texpressRecord);
            //将快递员的投递数量+1
            Optional<Tcourier> tcouriers = tcourierDao.findById(couId);
            Integer excout=tcouriers.get().getReCount()+1;
            tcouriers.get().setReCount(excout);
            tcourierDao.save(tcouriers.get());
            return new GirdBean(0,"快递添加成功，已向用户发送短信！",null,0 );
        }else{
            return new GirdBean(0,"请注意，该藏柜已使用，请选择其他藏柜使用！",null,0 );
        }
    }

    /**
     * 验证取件码
     * 取出快递后将快递柜置空
     * @param code
     * @param phone
     * @return
     */
    @RequestMapping("checkCode")
    @ResponseBody
    public GirdBean checkCode(String code,String phone){
        System.out.println("checkCode*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-验证取件码！");
        String checkCode = codeService.checkCode(code, phone);
        String recode="1";
        if (recode.equals(checkCode)){
            TexpressCabinet byExPickupCode = texpressCabinetDao.findByExPickupCode(code);

            //将信息同步到快递存储信息记录表中
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            // new Date()为获取当前系统时间
            String createDate = df.format(new Date());
            TexpressRecord texpressRecord=new TexpressRecord();
            texpressRecord.setReDate(createDate);
            texpressRecord.setReState("取出");
            texpressRecord.setRePosition(byExPickupCode.getExPosition());
            texpressRecord.setReBoxNumber(byExPickupCode.getExWarehouseId());
            texpressRecord.setRePeople(byExPickupCode.getExPersonName());
            texpressRecord.setRePeoplePhone(byExPickupCode.getExPersonPhone());
            texpressRecord.setTexpressCabinet(byExPickupCode);
            texpressRecordDao.save(texpressRecord);
            System.out.println(byExPickupCode.toString());
            //将该快递柜置空
            byExPickupCode.setExCardId("");
            byExPickupCode.setExCompany("");
            byExPickupCode.setExPersonName("");
            byExPickupCode.setExPersonPhone("");
            byExPickupCode.setExPickupCode("");
            byExPickupCode.setExReceiveName("");
            byExPickupCode.setExReceivePhone("");
            byExPickupCode.setExState("空闲");
            texpressCabinetDao.save(byExPickupCode);
            return new GirdBean(0,"验证完毕！", Collections.singletonList(byExPickupCode),0);
        }else{
            return new GirdBean(0,"验证失败，请检查验证码或手机号码是否正确！", null,0);
        }
    }

    /**
     * 根据快递柜和快递员姓名查询
     * @param exPosition
     * @param exPersonName
     * @return
     */
    @RequestMapping("findBoxByPositionAndExName")
    @ResponseBody
    public GirdBean findBoxByPositionAndExName(String exPosition,String exPersonName ){
        System.out.println("findCourierById*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-根据ID查询快递员信息！");
        List<TexpressCabinet> records= texpressCabinetDao.findByNameAndPosition(exPosition,exPersonName);
        if (!records.isEmpty()){
            return new GirdBean(0,"快递信息查询完毕！", records,records.size());
        }else{
            return new GirdBean(0,"查询失败，请检查快递员姓名或快递位置信息！", records,records.size());
        }

    }


    /**
     *查询所有快递存取记录
     * @return
     */
    @RequestMapping("findAllRecord")
    @ResponseBody
    public GirdBean findAllRecord(){
        System.out.println("findAllCourier*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-查询所有快递存储记录信息！");
        List<TexpressRecord> records= texpressRecordDao.findAll();
        return new GirdBean(0,"存取查询完毕！",records,records.size());
    }

//    /**
//     *
//     * @return
//     */
//    @RequestMapping("findAllCourier")
//    @ResponseBody
//    public GirdBean findAllCourier(){
//        System.out.println("findAllCourier*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-查询快递员信息！");
//        List<Tcourier> records= tcourierDao.findAll();
//        return new GirdBean(0,"存取查询完毕！",records,records.size());
//    }
}

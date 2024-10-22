
package com.controller;

import java.io.File;
import java.math.BigDecimal;
import java.net.URL;
import java.text.SimpleDateFormat;
import com.alibaba.fastjson.JSONObject;
import java.util.*;
import org.springframework.beans.BeanUtils;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.context.ContextLoader;
import javax.servlet.ServletContext;
import com.service.TokenService;
import com.utils.*;
import java.lang.reflect.InvocationTargetException;

import com.service.DictionaryService;
import org.apache.commons.lang3.StringUtils;
import com.annotation.IgnoreAuth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.entity.*;
import com.entity.view.*;
import com.service.*;
import com.utils.PageUtils;
import com.utils.R;
import com.alibaba.fastjson.*;

/**
 * 教研论文
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/lunwen")
public class LunwenController {
    private static final Logger logger = LoggerFactory.getLogger(LunwenController.class);

    private static final String TABLE_NAME = "lunwen";

    @Autowired
    private LunwenService lunwenService;


    @Autowired
    private TokenService tokenService;

    @Autowired
    private DictionaryService dictionaryService;//字典
    @Autowired
    private GonggaoService gonggaoService;//公告
    @Autowired
    private JiaocaiService jiaocaiService;//教材
    @Autowired
    private JioayanguanliService jioayanguanliService;//教研管理
    @Autowired
    private XiangmuService xiangmuService;//教研项目
    @Autowired
    private YonghuService yonghuService;//教研人员
    @Autowired
    private ZiliaoService ziliaoService;//教研资料
    @Autowired
    private UsersService usersService;//管理员


    /**
    * 后端列表
    */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("page方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));
        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永不会进入");
        else if("教研人员".equals(role))
            params.put("yonghuId",request.getSession().getAttribute("userId"));
        else if("教研管理".equals(role))
            params.put("jioayanguanliId",request.getSession().getAttribute("userId"));
        params.put("lunwenDeleteStart",1);params.put("lunwenDeleteEnd",1);
        CommonUtil.checkMap(params);
        PageUtils page = lunwenService.queryPage(params);

        //字典表数据转换
        List<LunwenView> list =(List<LunwenView>)page.getList();
        for(LunwenView c:list){
            //修改对应字典表字段
            dictionaryService.dictionaryConvert(c, request);
        }
        return R.ok().put("data", page);
    }

    /**
    * 后端详情
    */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("info方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        LunwenEntity lunwen = lunwenService.selectById(id);
        if(lunwen !=null){
            //entity转view
            LunwenView view = new LunwenView();
            BeanUtils.copyProperties( lunwen , view );//把实体数据重构到view中
            //级联表 教研项目
            //级联表
            XiangmuEntity xiangmu = xiangmuService.selectById(lunwen.getXiangmuId());
            if(xiangmu != null){
            BeanUtils.copyProperties( xiangmu , view ,new String[]{ "id", "createTime", "insertTime", "updateTime", "yonghuId"});//把级联的数据添加到view中,并排除id和创建时间字段,当前表的级联注册表
            view.setXiangmuId(xiangmu.getId());
            }
            //级联表 教研人员
            //级联表
            YonghuEntity yonghu = yonghuService.selectById(lunwen.getYonghuId());
            if(yonghu != null){
            BeanUtils.copyProperties( yonghu , view ,new String[]{ "id", "createTime", "insertTime", "updateTime", "yonghuId"});//把级联的数据添加到view中,并排除id和创建时间字段,当前表的级联注册表
            view.setYonghuId(yonghu.getId());
            }
            //修改对应字典表字段
            dictionaryService.dictionaryConvert(view, request);
            return R.ok().put("data", view);
        }else {
            return R.error(511,"查不到数据");
        }

    }

    /**
    * 后端保存
    */
    @RequestMapping("/save")
    public R save(@RequestBody LunwenEntity lunwen, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,lunwen:{}",this.getClass().getName(),lunwen.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");
        else if("教研人员".equals(role))
            lunwen.setYonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));

        Wrapper<LunwenEntity> queryWrapper = new EntityWrapper<LunwenEntity>()
            .eq("xiangmu_id", lunwen.getXiangmuId())
            .eq("yonghu_id", lunwen.getYonghuId())
            .eq("lunwen_name", lunwen.getLunwenName())
            .eq("lunwen_types", lunwen.getLunwenTypes())
            .eq("lunwen_delete", lunwen.getLunwenDelete())
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        LunwenEntity lunwenEntity = lunwenService.selectOne(queryWrapper);
        if(lunwenEntity==null){
            lunwen.setLunwenDelete(1);
            lunwen.setInsertTime(new Date());
            lunwen.setCreateTime(new Date());
            lunwenService.insert(lunwen);
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody LunwenEntity lunwen, HttpServletRequest request) throws NoSuchFieldException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        logger.debug("update方法:,,Controller:{},,lunwen:{}",this.getClass().getName(),lunwen.toString());
        LunwenEntity oldLunwenEntity = lunwenService.selectById(lunwen.getId());//查询原先数据

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");
//        else if("教研人员".equals(role))
//            lunwen.setYonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));
        if("".equals(lunwen.getLunwenPhoto()) || "null".equals(lunwen.getLunwenPhoto())){
                lunwen.setLunwenPhoto(null);
        }
        if("".equals(lunwen.getLunwenFile()) || "null".equals(lunwen.getLunwenFile())){
                lunwen.setLunwenFile(null);
        }

            lunwenService.updateById(lunwen);//根据id更新
            return R.ok();
    }



    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids, HttpServletRequest request){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        List<LunwenEntity> oldLunwenList =lunwenService.selectBatchIds(Arrays.asList(ids));//要删除的数据
        ArrayList<LunwenEntity> list = new ArrayList<>();
        for(Integer id:ids){
            LunwenEntity lunwenEntity = new LunwenEntity();
            lunwenEntity.setId(id);
            lunwenEntity.setLunwenDelete(2);
            list.add(lunwenEntity);
        }
        if(list != null && list.size() >0){
            lunwenService.updateBatchById(list);
        }

        return R.ok();
    }


    /**
     * 批量上传
     */
    @RequestMapping("/batchInsert")
    public R save( String fileName, HttpServletRequest request){
        logger.debug("batchInsert方法:,,Controller:{},,fileName:{}",this.getClass().getName(),fileName);
        Integer yonghuId = Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId")));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            List<LunwenEntity> lunwenList = new ArrayList<>();//上传的东西
            Map<String, List<String>> seachFields= new HashMap<>();//要查询的字段
            Date date = new Date();
            int lastIndexOf = fileName.lastIndexOf(".");
            if(lastIndexOf == -1){
                return R.error(511,"该文件没有后缀");
            }else{
                String suffix = fileName.substring(lastIndexOf);
                if(!".xls".equals(suffix)){
                    return R.error(511,"只支持后缀为xls的excel文件");
                }else{
                    URL resource = this.getClass().getClassLoader().getResource("static/upload/" + fileName);//获取文件路径
                    File file = new File(resource.getFile());
                    if(!file.exists()){
                        return R.error(511,"找不到上传文件，请联系管理员");
                    }else{
                        List<List<String>> dataList = PoiUtil.poiImport(file.getPath());//读取xls文件
                        dataList.remove(0);//删除第一行，因为第一行是提示
                        for(List<String> data:dataList){
                            //循环
                            LunwenEntity lunwenEntity = new LunwenEntity();
//                            lunwenEntity.setXiangmuId(Integer.valueOf(data.get(0)));   //教研项目 要改的
//                            lunwenEntity.setYonghuId(Integer.valueOf(data.get(0)));   //用户 要改的
//                            lunwenEntity.setLunwenName(data.get(0));                    //教研论文名称 要改的
//                            lunwenEntity.setLunwenUuidNumber(data.get(0));                    //教研论文编号 要改的
//                            lunwenEntity.setLunwenPhoto("");//详情和图片
//                            lunwenEntity.setLunwenTypes(Integer.valueOf(data.get(0)));   //教研论文类型 要改的
//                            lunwenEntity.setLunwenFile(data.get(0));                    //教研论文下载 要改的
//                            lunwenEntity.setLunwenContent("");//详情和图片
//                            lunwenEntity.setLunwenDelete(1);//逻辑删除字段
//                            lunwenEntity.setInsertTime(date);//时间
//                            lunwenEntity.setCreateTime(date);//时间
                            lunwenList.add(lunwenEntity);


                            //把要查询是否重复的字段放入map中
                                //教研论文编号
                                if(seachFields.containsKey("lunwenUuidNumber")){
                                    List<String> lunwenUuidNumber = seachFields.get("lunwenUuidNumber");
                                    lunwenUuidNumber.add(data.get(0));//要改的
                                }else{
                                    List<String> lunwenUuidNumber = new ArrayList<>();
                                    lunwenUuidNumber.add(data.get(0));//要改的
                                    seachFields.put("lunwenUuidNumber",lunwenUuidNumber);
                                }
                        }

                        //查询是否重复
                         //教研论文编号
                        List<LunwenEntity> lunwenEntities_lunwenUuidNumber = lunwenService.selectList(new EntityWrapper<LunwenEntity>().in("lunwen_uuid_number", seachFields.get("lunwenUuidNumber")).eq("lunwen_delete", 1));
                        if(lunwenEntities_lunwenUuidNumber.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(LunwenEntity s:lunwenEntities_lunwenUuidNumber){
                                repeatFields.add(s.getLunwenUuidNumber());
                            }
                            return R.error(511,"数据库的该表中的 [教研论文编号] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                        lunwenService.insertBatch(lunwenList);
                        return R.ok();
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            return R.error(511,"批量插入数据异常，请联系管理员");
        }
    }




}


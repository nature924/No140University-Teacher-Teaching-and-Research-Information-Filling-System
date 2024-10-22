
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
 * 教材
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/jiaocai")
public class JiaocaiController {
    private static final Logger logger = LoggerFactory.getLogger(JiaocaiController.class);

    private static final String TABLE_NAME = "jiaocai";

    @Autowired
    private JiaocaiService jiaocaiService;


    @Autowired
    private TokenService tokenService;

    @Autowired
    private DictionaryService dictionaryService;//字典
    @Autowired
    private GonggaoService gonggaoService;//公告
    @Autowired
    private JioayanguanliService jioayanguanliService;//教研管理
    @Autowired
    private LunwenService lunwenService;//教研论文
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
        params.put("jiaocaiDeleteStart",1);params.put("jiaocaiDeleteEnd",1);
        CommonUtil.checkMap(params);
        PageUtils page = jiaocaiService.queryPage(params);

        //字典表数据转换
        List<JiaocaiView> list =(List<JiaocaiView>)page.getList();
        for(JiaocaiView c:list){
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
        JiaocaiEntity jiaocai = jiaocaiService.selectById(id);
        if(jiaocai !=null){
            //entity转view
            JiaocaiView view = new JiaocaiView();
            BeanUtils.copyProperties( jiaocai , view );//把实体数据重构到view中
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
    public R save(@RequestBody JiaocaiEntity jiaocai, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,jiaocai:{}",this.getClass().getName(),jiaocai.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");

        Wrapper<JiaocaiEntity> queryWrapper = new EntityWrapper<JiaocaiEntity>()
            .eq("jiaocai_name", jiaocai.getJiaocaiName())
            .eq("jiaocai_types", jiaocai.getJiaocaiTypes())
            .eq("jiaocai_delete", jiaocai.getJiaocaiDelete())
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        JiaocaiEntity jiaocaiEntity = jiaocaiService.selectOne(queryWrapper);
        if(jiaocaiEntity==null){
            jiaocai.setJiaocaiDelete(1);
            jiaocai.setInsertTime(new Date());
            jiaocai.setCreateTime(new Date());
            jiaocaiService.insert(jiaocai);
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody JiaocaiEntity jiaocai, HttpServletRequest request) throws NoSuchFieldException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        logger.debug("update方法:,,Controller:{},,jiaocai:{}",this.getClass().getName(),jiaocai.toString());
        JiaocaiEntity oldJiaocaiEntity = jiaocaiService.selectById(jiaocai.getId());//查询原先数据

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");
        if("".equals(jiaocai.getJiaocaiPhoto()) || "null".equals(jiaocai.getJiaocaiPhoto())){
                jiaocai.setJiaocaiPhoto(null);
        }
        if("".equals(jiaocai.getJiaocaiFile()) || "null".equals(jiaocai.getJiaocaiFile())){
                jiaocai.setJiaocaiFile(null);
        }

            jiaocaiService.updateById(jiaocai);//根据id更新
            return R.ok();
    }



    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids, HttpServletRequest request){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        List<JiaocaiEntity> oldJiaocaiList =jiaocaiService.selectBatchIds(Arrays.asList(ids));//要删除的数据
        ArrayList<JiaocaiEntity> list = new ArrayList<>();
        for(Integer id:ids){
            JiaocaiEntity jiaocaiEntity = new JiaocaiEntity();
            jiaocaiEntity.setId(id);
            jiaocaiEntity.setJiaocaiDelete(2);
            list.add(jiaocaiEntity);
        }
        if(list != null && list.size() >0){
            jiaocaiService.updateBatchById(list);
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
            List<JiaocaiEntity> jiaocaiList = new ArrayList<>();//上传的东西
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
                            JiaocaiEntity jiaocaiEntity = new JiaocaiEntity();
//                            jiaocaiEntity.setJiaocaiName(data.get(0));                    //教材名称 要改的
//                            jiaocaiEntity.setJiaocaiUuidNumber(data.get(0));                    //教材编号 要改的
//                            jiaocaiEntity.setJiaocaiPhoto("");//详情和图片
//                            jiaocaiEntity.setJiaocaiTypes(Integer.valueOf(data.get(0)));   //教材类型 要改的
//                            jiaocaiEntity.setJiaocaiFile(data.get(0));                    //教材下载 要改的
//                            jiaocaiEntity.setJiaocaiContent("");//详情和图片
//                            jiaocaiEntity.setJiaocaiDelete(1);//逻辑删除字段
//                            jiaocaiEntity.setInsertTime(date);//时间
//                            jiaocaiEntity.setCreateTime(date);//时间
                            jiaocaiList.add(jiaocaiEntity);


                            //把要查询是否重复的字段放入map中
                                //教材编号
                                if(seachFields.containsKey("jiaocaiUuidNumber")){
                                    List<String> jiaocaiUuidNumber = seachFields.get("jiaocaiUuidNumber");
                                    jiaocaiUuidNumber.add(data.get(0));//要改的
                                }else{
                                    List<String> jiaocaiUuidNumber = new ArrayList<>();
                                    jiaocaiUuidNumber.add(data.get(0));//要改的
                                    seachFields.put("jiaocaiUuidNumber",jiaocaiUuidNumber);
                                }
                        }

                        //查询是否重复
                         //教材编号
                        List<JiaocaiEntity> jiaocaiEntities_jiaocaiUuidNumber = jiaocaiService.selectList(new EntityWrapper<JiaocaiEntity>().in("jiaocai_uuid_number", seachFields.get("jiaocaiUuidNumber")).eq("jiaocai_delete", 1));
                        if(jiaocaiEntities_jiaocaiUuidNumber.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(JiaocaiEntity s:jiaocaiEntities_jiaocaiUuidNumber){
                                repeatFields.add(s.getJiaocaiUuidNumber());
                            }
                            return R.error(511,"数据库的该表中的 [教材编号] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                        jiaocaiService.insertBatch(jiaocaiList);
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


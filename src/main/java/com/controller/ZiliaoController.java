
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
 * 教研资料
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/ziliao")
public class ZiliaoController {
    private static final Logger logger = LoggerFactory.getLogger(ZiliaoController.class);

    private static final String TABLE_NAME = "ziliao";

    @Autowired
    private ZiliaoService ziliaoService;


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
    private LunwenService lunwenService;//教研论文
    @Autowired
    private XiangmuService xiangmuService;//教研项目
    @Autowired
    private YonghuService yonghuService;//教研人员
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
        params.put("ziliaoDeleteStart",1);params.put("ziliaoDeleteEnd",1);
        CommonUtil.checkMap(params);
        PageUtils page = ziliaoService.queryPage(params);

        //字典表数据转换
        List<ZiliaoView> list =(List<ZiliaoView>)page.getList();
        for(ZiliaoView c:list){
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
        ZiliaoEntity ziliao = ziliaoService.selectById(id);
        if(ziliao !=null){
            //entity转view
            ZiliaoView view = new ZiliaoView();
            BeanUtils.copyProperties( ziliao , view );//把实体数据重构到view中
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
    public R save(@RequestBody ZiliaoEntity ziliao, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,ziliao:{}",this.getClass().getName(),ziliao.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");

        Wrapper<ZiliaoEntity> queryWrapper = new EntityWrapper<ZiliaoEntity>()
            .eq("ziliao_name", ziliao.getZiliaoName())
            .eq("ziliao_types", ziliao.getZiliaoTypes())
            .eq("ziliao_delete", ziliao.getZiliaoDelete())
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        ZiliaoEntity ziliaoEntity = ziliaoService.selectOne(queryWrapper);
        if(ziliaoEntity==null){
            ziliao.setZiliaoDelete(1);
            ziliao.setInsertTime(new Date());
            ziliao.setCreateTime(new Date());
            ziliaoService.insert(ziliao);
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody ZiliaoEntity ziliao, HttpServletRequest request) throws NoSuchFieldException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        logger.debug("update方法:,,Controller:{},,ziliao:{}",this.getClass().getName(),ziliao.toString());
        ZiliaoEntity oldZiliaoEntity = ziliaoService.selectById(ziliao.getId());//查询原先数据

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");
        if("".equals(ziliao.getZiliaoPhoto()) || "null".equals(ziliao.getZiliaoPhoto())){
                ziliao.setZiliaoPhoto(null);
        }
        if("".equals(ziliao.getZiliaoFile()) || "null".equals(ziliao.getZiliaoFile())){
                ziliao.setZiliaoFile(null);
        }

            ziliaoService.updateById(ziliao);//根据id更新
            return R.ok();
    }



    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids, HttpServletRequest request){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        List<ZiliaoEntity> oldZiliaoList =ziliaoService.selectBatchIds(Arrays.asList(ids));//要删除的数据
        ArrayList<ZiliaoEntity> list = new ArrayList<>();
        for(Integer id:ids){
            ZiliaoEntity ziliaoEntity = new ZiliaoEntity();
            ziliaoEntity.setId(id);
            ziliaoEntity.setZiliaoDelete(2);
            list.add(ziliaoEntity);
        }
        if(list != null && list.size() >0){
            ziliaoService.updateBatchById(list);
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
            List<ZiliaoEntity> ziliaoList = new ArrayList<>();//上传的东西
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
                            ZiliaoEntity ziliaoEntity = new ZiliaoEntity();
//                            ziliaoEntity.setZiliaoName(data.get(0));                    //教研资料名称 要改的
//                            ziliaoEntity.setZiliaoUuidNumber(data.get(0));                    //教研资料编号 要改的
//                            ziliaoEntity.setZiliaoPhoto("");//详情和图片
//                            ziliaoEntity.setZiliaoTypes(Integer.valueOf(data.get(0)));   //教研资料类型 要改的
//                            ziliaoEntity.setZiliaoFile(data.get(0));                    //教研资料下载 要改的
//                            ziliaoEntity.setZiliaoContent("");//详情和图片
//                            ziliaoEntity.setZiliaoDelete(1);//逻辑删除字段
//                            ziliaoEntity.setInsertTime(date);//时间
//                            ziliaoEntity.setCreateTime(date);//时间
                            ziliaoList.add(ziliaoEntity);


                            //把要查询是否重复的字段放入map中
                                //教研资料编号
                                if(seachFields.containsKey("ziliaoUuidNumber")){
                                    List<String> ziliaoUuidNumber = seachFields.get("ziliaoUuidNumber");
                                    ziliaoUuidNumber.add(data.get(0));//要改的
                                }else{
                                    List<String> ziliaoUuidNumber = new ArrayList<>();
                                    ziliaoUuidNumber.add(data.get(0));//要改的
                                    seachFields.put("ziliaoUuidNumber",ziliaoUuidNumber);
                                }
                        }

                        //查询是否重复
                         //教研资料编号
                        List<ZiliaoEntity> ziliaoEntities_ziliaoUuidNumber = ziliaoService.selectList(new EntityWrapper<ZiliaoEntity>().in("ziliao_uuid_number", seachFields.get("ziliaoUuidNumber")).eq("ziliao_delete", 1));
                        if(ziliaoEntities_ziliaoUuidNumber.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(ZiliaoEntity s:ziliaoEntities_ziliaoUuidNumber){
                                repeatFields.add(s.getZiliaoUuidNumber());
                            }
                            return R.error(511,"数据库的该表中的 [教研资料编号] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                        ziliaoService.insertBatch(ziliaoList);
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


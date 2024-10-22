
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
 * 教研管理
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/jioayanguanli")
public class JioayanguanliController {
    private static final Logger logger = LoggerFactory.getLogger(JioayanguanliController.class);

    private static final String TABLE_NAME = "jioayanguanli";

    @Autowired
    private JioayanguanliService jioayanguanliService;


    @Autowired
    private TokenService tokenService;

    @Autowired
    private DictionaryService dictionaryService;//字典
    @Autowired
    private GonggaoService gonggaoService;//公告
    @Autowired
    private JiaocaiService jiaocaiService;//教材
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
        CommonUtil.checkMap(params);
        PageUtils page = jioayanguanliService.queryPage(params);

        //字典表数据转换
        List<JioayanguanliView> list =(List<JioayanguanliView>)page.getList();
        for(JioayanguanliView c:list){
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
        JioayanguanliEntity jioayanguanli = jioayanguanliService.selectById(id);
        if(jioayanguanli !=null){
            //entity转view
            JioayanguanliView view = new JioayanguanliView();
            BeanUtils.copyProperties( jioayanguanli , view );//把实体数据重构到view中
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
    public R save(@RequestBody JioayanguanliEntity jioayanguanli, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,jioayanguanli:{}",this.getClass().getName(),jioayanguanli.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");

        Wrapper<JioayanguanliEntity> queryWrapper = new EntityWrapper<JioayanguanliEntity>()
            .eq("username", jioayanguanli.getUsername())
            .or()
            .eq("jioayanguanli_phone", jioayanguanli.getJioayanguanliPhone())
            .or()
            .eq("jioayanguanli_id_number", jioayanguanli.getJioayanguanliIdNumber())
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        JioayanguanliEntity jioayanguanliEntity = jioayanguanliService.selectOne(queryWrapper);
        if(jioayanguanliEntity==null){
            jioayanguanli.setCreateTime(new Date());
            jioayanguanli.setPassword("123456");
            jioayanguanliService.insert(jioayanguanli);
            return R.ok();
        }else {
            return R.error(511,"账户或者教研管理手机号或者教研管理身份证号已经被使用");
        }
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody JioayanguanliEntity jioayanguanli, HttpServletRequest request) throws NoSuchFieldException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        logger.debug("update方法:,,Controller:{},,jioayanguanli:{}",this.getClass().getName(),jioayanguanli.toString());
        JioayanguanliEntity oldJioayanguanliEntity = jioayanguanliService.selectById(jioayanguanli.getId());//查询原先数据

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");
        if("".equals(jioayanguanli.getJioayanguanliPhoto()) || "null".equals(jioayanguanli.getJioayanguanliPhoto())){
                jioayanguanli.setJioayanguanliPhoto(null);
        }

            jioayanguanliService.updateById(jioayanguanli);//根据id更新
            return R.ok();
    }



    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids, HttpServletRequest request){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        List<JioayanguanliEntity> oldJioayanguanliList =jioayanguanliService.selectBatchIds(Arrays.asList(ids));//要删除的数据
        jioayanguanliService.deleteBatchIds(Arrays.asList(ids));

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
            List<JioayanguanliEntity> jioayanguanliList = new ArrayList<>();//上传的东西
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
                            JioayanguanliEntity jioayanguanliEntity = new JioayanguanliEntity();
//                            jioayanguanliEntity.setUsername(data.get(0));                    //账户 要改的
//                            //jioayanguanliEntity.setPassword("123456");//密码
//                            jioayanguanliEntity.setJioayanguanliName(data.get(0));                    //教研管理姓名 要改的
//                            jioayanguanliEntity.setJioayanguanliPhone(data.get(0));                    //教研管理手机号 要改的
//                            jioayanguanliEntity.setJioayanguanliIdNumber(data.get(0));                    //教研管理身份证号 要改的
//                            jioayanguanliEntity.setJioayanguanliPhoto("");//详情和图片
//                            jioayanguanliEntity.setSexTypes(Integer.valueOf(data.get(0)));   //性别 要改的
//                            jioayanguanliEntity.setJioayanguanliEmail(data.get(0));                    //教研管理邮箱 要改的
//                            jioayanguanliEntity.setCreateTime(date);//时间
                            jioayanguanliList.add(jioayanguanliEntity);


                            //把要查询是否重复的字段放入map中
                                //账户
                                if(seachFields.containsKey("username")){
                                    List<String> username = seachFields.get("username");
                                    username.add(data.get(0));//要改的
                                }else{
                                    List<String> username = new ArrayList<>();
                                    username.add(data.get(0));//要改的
                                    seachFields.put("username",username);
                                }
                                //教研管理手机号
                                if(seachFields.containsKey("jioayanguanliPhone")){
                                    List<String> jioayanguanliPhone = seachFields.get("jioayanguanliPhone");
                                    jioayanguanliPhone.add(data.get(0));//要改的
                                }else{
                                    List<String> jioayanguanliPhone = new ArrayList<>();
                                    jioayanguanliPhone.add(data.get(0));//要改的
                                    seachFields.put("jioayanguanliPhone",jioayanguanliPhone);
                                }
                                //教研管理身份证号
                                if(seachFields.containsKey("jioayanguanliIdNumber")){
                                    List<String> jioayanguanliIdNumber = seachFields.get("jioayanguanliIdNumber");
                                    jioayanguanliIdNumber.add(data.get(0));//要改的
                                }else{
                                    List<String> jioayanguanliIdNumber = new ArrayList<>();
                                    jioayanguanliIdNumber.add(data.get(0));//要改的
                                    seachFields.put("jioayanguanliIdNumber",jioayanguanliIdNumber);
                                }
                        }

                        //查询是否重复
                         //账户
                        List<JioayanguanliEntity> jioayanguanliEntities_username = jioayanguanliService.selectList(new EntityWrapper<JioayanguanliEntity>().in("username", seachFields.get("username")));
                        if(jioayanguanliEntities_username.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(JioayanguanliEntity s:jioayanguanliEntities_username){
                                repeatFields.add(s.getUsername());
                            }
                            return R.error(511,"数据库的该表中的 [账户] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                         //教研管理手机号
                        List<JioayanguanliEntity> jioayanguanliEntities_jioayanguanliPhone = jioayanguanliService.selectList(new EntityWrapper<JioayanguanliEntity>().in("jioayanguanli_phone", seachFields.get("jioayanguanliPhone")));
                        if(jioayanguanliEntities_jioayanguanliPhone.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(JioayanguanliEntity s:jioayanguanliEntities_jioayanguanliPhone){
                                repeatFields.add(s.getJioayanguanliPhone());
                            }
                            return R.error(511,"数据库的该表中的 [教研管理手机号] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                         //教研管理身份证号
                        List<JioayanguanliEntity> jioayanguanliEntities_jioayanguanliIdNumber = jioayanguanliService.selectList(new EntityWrapper<JioayanguanliEntity>().in("jioayanguanli_id_number", seachFields.get("jioayanguanliIdNumber")));
                        if(jioayanguanliEntities_jioayanguanliIdNumber.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(JioayanguanliEntity s:jioayanguanliEntities_jioayanguanliIdNumber){
                                repeatFields.add(s.getJioayanguanliIdNumber());
                            }
                            return R.error(511,"数据库的该表中的 [教研管理身份证号] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                        jioayanguanliService.insertBatch(jioayanguanliList);
                        return R.ok();
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            return R.error(511,"批量插入数据异常，请联系管理员");
        }
    }

    /**
    * 登录
    */
    @IgnoreAuth
    @RequestMapping(value = "/login")
    public R login(String username, String password, String captcha, HttpServletRequest request) {
        JioayanguanliEntity jioayanguanli = jioayanguanliService.selectOne(new EntityWrapper<JioayanguanliEntity>().eq("username", username));
        if(jioayanguanli==null || !jioayanguanli.getPassword().equals(password))
            return R.error("账号或密码不正确");
        String token = tokenService.generateToken(jioayanguanli.getId(),username, "jioayanguanli", "教研管理");
        R r = R.ok();
        r.put("token", token);
        r.put("role","教研管理");
        r.put("username",jioayanguanli.getJioayanguanliName());
        r.put("tableName","jioayanguanli");
        r.put("userId",jioayanguanli.getId());
        return r;
    }

    /**
    * 注册
    */
    @IgnoreAuth
    @PostMapping(value = "/register")
    public R register(@RequestBody JioayanguanliEntity jioayanguanli, HttpServletRequest request) {
//    	ValidatorUtils.validateEntity(user);
        Wrapper<JioayanguanliEntity> queryWrapper = new EntityWrapper<JioayanguanliEntity>()
            .eq("username", jioayanguanli.getUsername())
            .or()
            .eq("jioayanguanli_phone", jioayanguanli.getJioayanguanliPhone())
            .or()
            .eq("jioayanguanli_id_number", jioayanguanli.getJioayanguanliIdNumber())
            ;
        JioayanguanliEntity jioayanguanliEntity = jioayanguanliService.selectOne(queryWrapper);
        if(jioayanguanliEntity != null)
            return R.error("账户或者教研管理手机号或者教研管理身份证号已经被使用");
        jioayanguanli.setCreateTime(new Date());
        jioayanguanliService.insert(jioayanguanli);

        return R.ok();
    }

    /**
     * 重置密码
     */
    @GetMapping(value = "/resetPassword")
    public R resetPassword(Integer  id, HttpServletRequest request) {
        JioayanguanliEntity jioayanguanli = jioayanguanliService.selectById(id);
        jioayanguanli.setPassword("123456");
        jioayanguanliService.updateById(jioayanguanli);
        return R.ok();
    }

	/**
	 * 修改密码
	 */
	@GetMapping(value = "/updatePassword")
	public R updatePassword(String  oldPassword, String  newPassword, HttpServletRequest request) {
        JioayanguanliEntity jioayanguanli = jioayanguanliService.selectById((Integer)request.getSession().getAttribute("userId"));
		if(newPassword == null){
			return R.error("新密码不能为空") ;
		}
		if(!oldPassword.equals(jioayanguanli.getPassword())){
			return R.error("原密码输入错误");
		}
		if(newPassword.equals(jioayanguanli.getPassword())){
			return R.error("新密码不能和原密码一致") ;
		}
        jioayanguanli.setPassword(newPassword);
		jioayanguanliService.updateById(jioayanguanli);
		return R.ok();
	}



    /**
     * 忘记密码
     */
    @IgnoreAuth
    @RequestMapping(value = "/resetPass")
    public R resetPass(String username, HttpServletRequest request) {
        JioayanguanliEntity jioayanguanli = jioayanguanliService.selectOne(new EntityWrapper<JioayanguanliEntity>().eq("username", username));
        if(jioayanguanli!=null){
            jioayanguanli.setPassword("123456");
            jioayanguanliService.updateById(jioayanguanli);
            return R.ok();
        }else{
           return R.error("账号不存在");
        }
    }


    /**
    * 获取用户的session用户信息
    */
    @RequestMapping("/session")
    public R getCurrJioayanguanli(HttpServletRequest request){
        Integer id = (Integer)request.getSession().getAttribute("userId");
        JioayanguanliEntity jioayanguanli = jioayanguanliService.selectById(id);
        if(jioayanguanli !=null){
            //entity转view
            JioayanguanliView view = new JioayanguanliView();
            BeanUtils.copyProperties( jioayanguanli , view );//把实体数据重构到view中

            //修改对应字典表字段
            dictionaryService.dictionaryConvert(view, request);
            return R.ok().put("data", view);
        }else {
            return R.error(511,"查不到数据");
        }
    }


    /**
    * 退出
    */
    @GetMapping(value = "logout")
    public R logout(HttpServletRequest request) {
        request.getSession().invalidate();
        return R.ok("退出成功");
    }



}


package com.dao;

import com.entity.JiaocaiEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.JiaocaiView;

/**
 * 教材 Dao 接口
 *
 * @author 
 */
public interface JiaocaiDao extends BaseMapper<JiaocaiEntity> {

   List<JiaocaiView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}

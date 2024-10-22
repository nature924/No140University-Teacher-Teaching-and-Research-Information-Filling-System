package com.dao;

import com.entity.LunwenEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.LunwenView;

/**
 * 教研论文 Dao 接口
 *
 * @author 
 */
public interface LunwenDao extends BaseMapper<LunwenEntity> {

   List<LunwenView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}

package com.dao;

import com.entity.JiaofeixinxiEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.JiaofeixinxiVO;
import com.entity.view.JiaofeixinxiView;


/**
 * 交费信息
 * 
 * @author 
 * @email 
 * @date 2023-05-10 10:19:26
 */
public interface JiaofeixinxiDao extends BaseMapper<JiaofeixinxiEntity> {
	
	List<JiaofeixinxiVO> selectListVO(@Param("ew") Wrapper<JiaofeixinxiEntity> wrapper);
	
	JiaofeixinxiVO selectVO(@Param("ew") Wrapper<JiaofeixinxiEntity> wrapper);
	
	List<JiaofeixinxiView> selectListView(@Param("ew") Wrapper<JiaofeixinxiEntity> wrapper);

	List<JiaofeixinxiView> selectListView(Pagination page,@Param("ew") Wrapper<JiaofeixinxiEntity> wrapper);
	
	JiaofeixinxiView selectView(@Param("ew") Wrapper<JiaofeixinxiEntity> wrapper);
	

}

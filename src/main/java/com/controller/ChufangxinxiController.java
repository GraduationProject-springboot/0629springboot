package com.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import com.utils.ValidatorUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.annotation.IgnoreAuth;

import com.entity.ChufangxinxiEntity;
import com.entity.view.ChufangxinxiView;

import com.service.ChufangxinxiService;
import com.service.TokenService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.MD5Util;
import com.utils.MPUtil;
import com.utils.CommonUtil;
import java.io.IOException;

/**
 * 处方信息
 * 后端接口
 * @author 
 * @email 
 * @date 2023-05-10 10:19:26
 */
@RestController
@RequestMapping("/chufangxinxi")
public class ChufangxinxiController {
    @Autowired
    private ChufangxinxiService chufangxinxiService;


    


    /**
     * 后端列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,ChufangxinxiEntity chufangxinxi,
		HttpServletRequest request){
		String tableName = request.getSession().getAttribute("tableName").toString();
		if(tableName.equals("yonghu")) {
			chufangxinxi.setZhanghao((String)request.getSession().getAttribute("username"));
		}
		if(tableName.equals("yisheng")) {
			chufangxinxi.setYishenggonghao((String)request.getSession().getAttribute("username"));
		}
        EntityWrapper<ChufangxinxiEntity> ew = new EntityWrapper<ChufangxinxiEntity>();

		PageUtils page = chufangxinxiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, chufangxinxi), params), params));

        return R.ok().put("data", page);
    }
    
    /**
     * 前端列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,ChufangxinxiEntity chufangxinxi, 
		HttpServletRequest request){
        EntityWrapper<ChufangxinxiEntity> ew = new EntityWrapper<ChufangxinxiEntity>();

		PageUtils page = chufangxinxiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, chufangxinxi), params), params));
        return R.ok().put("data", page);
    }

	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( ChufangxinxiEntity chufangxinxi){
       	EntityWrapper<ChufangxinxiEntity> ew = new EntityWrapper<ChufangxinxiEntity>();
      	ew.allEq(MPUtil.allEQMapPre( chufangxinxi, "chufangxinxi")); 
        return R.ok().put("data", chufangxinxiService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(ChufangxinxiEntity chufangxinxi){
        EntityWrapper< ChufangxinxiEntity> ew = new EntityWrapper< ChufangxinxiEntity>();
 		ew.allEq(MPUtil.allEQMapPre( chufangxinxi, "chufangxinxi")); 
		ChufangxinxiView chufangxinxiView =  chufangxinxiService.selectView(ew);
		return R.ok("查询处方信息成功").put("data", chufangxinxiView);
    }
	
    /**
     * 后端详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        ChufangxinxiEntity chufangxinxi = chufangxinxiService.selectById(id);
        return R.ok().put("data", chufangxinxi);
    }

    /**
     * 前端详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        ChufangxinxiEntity chufangxinxi = chufangxinxiService.selectById(id);
        return R.ok().put("data", chufangxinxi);
    }
    



    /**
     * 后端保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody ChufangxinxiEntity chufangxinxi, HttpServletRequest request){
    	chufangxinxi.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
    	//ValidatorUtils.validateEntity(chufangxinxi);
        chufangxinxiService.insert(chufangxinxi);
        return R.ok();
    }
    
    /**
     * 前端保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody ChufangxinxiEntity chufangxinxi, HttpServletRequest request){
    	chufangxinxi.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
    	//ValidatorUtils.validateEntity(chufangxinxi);
        chufangxinxiService.insert(chufangxinxi);
        return R.ok();
    }



    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody ChufangxinxiEntity chufangxinxi, HttpServletRequest request){
        //ValidatorUtils.validateEntity(chufangxinxi);
        chufangxinxiService.updateById(chufangxinxi);//全部更新
        return R.ok();
    }



    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        chufangxinxiService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    
	









}

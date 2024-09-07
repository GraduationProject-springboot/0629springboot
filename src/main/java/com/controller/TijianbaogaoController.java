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

import com.entity.TijianbaogaoEntity;
import com.entity.view.TijianbaogaoView;

import com.service.TijianbaogaoService;
import com.service.TokenService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.MD5Util;
import com.utils.MPUtil;
import com.utils.CommonUtil;
import java.io.IOException;

/**
 * 体检报告
 * 后端接口
 * @author 
 * @email 
 * @date 2023-05-10 10:19:26
 */
@RestController
@RequestMapping("/tijianbaogao")
public class TijianbaogaoController {
    @Autowired
    private TijianbaogaoService tijianbaogaoService;


    


    /**
     * 后端列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,TijianbaogaoEntity tijianbaogao,
		HttpServletRequest request){
		String tableName = request.getSession().getAttribute("tableName").toString();
		if(tableName.equals("yisheng")) {
			tijianbaogao.setYishenggonghao((String)request.getSession().getAttribute("username"));
		}
		if(tableName.equals("yonghu")) {
			tijianbaogao.setZhanghao((String)request.getSession().getAttribute("username"));
		}
        EntityWrapper<TijianbaogaoEntity> ew = new EntityWrapper<TijianbaogaoEntity>();

		PageUtils page = tijianbaogaoService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, tijianbaogao), params), params));

        return R.ok().put("data", page);
    }
    
    /**
     * 前端列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,TijianbaogaoEntity tijianbaogao, 
		HttpServletRequest request){
        EntityWrapper<TijianbaogaoEntity> ew = new EntityWrapper<TijianbaogaoEntity>();

		PageUtils page = tijianbaogaoService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, tijianbaogao), params), params));
        return R.ok().put("data", page);
    }

	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( TijianbaogaoEntity tijianbaogao){
       	EntityWrapper<TijianbaogaoEntity> ew = new EntityWrapper<TijianbaogaoEntity>();
      	ew.allEq(MPUtil.allEQMapPre( tijianbaogao, "tijianbaogao")); 
        return R.ok().put("data", tijianbaogaoService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(TijianbaogaoEntity tijianbaogao){
        EntityWrapper< TijianbaogaoEntity> ew = new EntityWrapper< TijianbaogaoEntity>();
 		ew.allEq(MPUtil.allEQMapPre( tijianbaogao, "tijianbaogao")); 
		TijianbaogaoView tijianbaogaoView =  tijianbaogaoService.selectView(ew);
		return R.ok("查询体检报告成功").put("data", tijianbaogaoView);
    }
	
    /**
     * 后端详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        TijianbaogaoEntity tijianbaogao = tijianbaogaoService.selectById(id);
        return R.ok().put("data", tijianbaogao);
    }

    /**
     * 前端详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        TijianbaogaoEntity tijianbaogao = tijianbaogaoService.selectById(id);
        return R.ok().put("data", tijianbaogao);
    }
    



    /**
     * 后端保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody TijianbaogaoEntity tijianbaogao, HttpServletRequest request){
    	tijianbaogao.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
    	//ValidatorUtils.validateEntity(tijianbaogao);
        tijianbaogaoService.insert(tijianbaogao);
        return R.ok();
    }
    
    /**
     * 前端保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody TijianbaogaoEntity tijianbaogao, HttpServletRequest request){
    	tijianbaogao.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
    	//ValidatorUtils.validateEntity(tijianbaogao);
        tijianbaogaoService.insert(tijianbaogao);
        return R.ok();
    }



    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody TijianbaogaoEntity tijianbaogao, HttpServletRequest request){
        //ValidatorUtils.validateEntity(tijianbaogao);
        tijianbaogaoService.updateById(tijianbaogao);//全部更新
        return R.ok();
    }



    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        tijianbaogaoService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    
	









}

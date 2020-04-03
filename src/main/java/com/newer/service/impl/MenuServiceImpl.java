package com.newer.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.newer.dao.MenuDao;
import com.newer.domain.Menu;
import com.newer.service.MenuService;

import tk.mybatis.mapper.entity.Example;

@Service
public class MenuServiceImpl  implements MenuService{
	@Autowired
	private MenuDao menuDao;

	@Override
	public List<Menu> findMenusByPid(Integer pid) {

            Example example=new Example(Menu.class);
            example.createCriteria().andEqualTo("pid", pid);
		return this.menuDao.selectByExample(example);
	}

}

package com.newer.service;

import java.util.List;

import com.newer.domain.Menu;

public interface MenuService {
 public List<Menu> findMenusByPid(Integer pid);
}

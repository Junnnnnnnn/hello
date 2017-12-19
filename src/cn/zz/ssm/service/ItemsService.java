package cn.zz.ssm.service;

import java.util.List;

import cn.zz.ssm.domain.Items;

public interface ItemsService {
	
	public List<Items> findAll();

	public Items findItemsByID(Integer id);

	public void saveOrUpdate(Items items);

	public void deleteByID(Integer id);

}

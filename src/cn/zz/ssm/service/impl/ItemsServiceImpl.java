package cn.zz.ssm.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.zz.ssm.mapper.ItemsMapper;
import cn.zz.ssm.domain.Items;
import cn.zz.ssm.service.ItemsService;

@Service
public class ItemsServiceImpl implements ItemsService {
	@Resource
	private ItemsMapper itemsMapper;
	@Override
	public List<Items> findAll() {
		List<Items> list=itemsMapper.findAll();
		return list;
	}
	@Override
	public Items findItemsByID(Integer id) {
		Items items=itemsMapper.selectByPrimaryKey(id);
		return items;
	}
	@Override
	public void saveOrUpdate(Items items) {
		itemsMapper.updateByPrimaryKey(items);
		
	}
	@Override
	public void deleteByID(Integer id) {
		itemsMapper.deleteByPrimaryKey(id);
		
	}

}

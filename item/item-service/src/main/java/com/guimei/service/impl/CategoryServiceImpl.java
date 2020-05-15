package com.guimei.service.impl;

import com.guimei.mapper.CategoryMapper;
import com.guimei.model.Category;
import com.guimei.myexception.LyException;
import com.guimei.myexception.MyException;
import com.guimei.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @Author: York
 * @Date: 2020/5/8 000821:56
 * @Version:1.0
 * @Description: 品牌的实现类
 */
@Service
public class CategoryServiceImpl implements ICategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    /**
     * @Description:
     *             根据ID查找数据信息
     * @Author: York
     * @Date: 2020/5/15 0015 9:43
     * @param pid
     * @Return: java.util.List<com.guimei.model.Category>
     **/
    public List<Category> queryCategoryByPid(Long pid) throws MyException {
        Example example = new Example(Category.class);
        example.createCriteria().andEqualTo("parentId",pid);
        List<Category> list = this.categoryMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(list)){
            throw new MyException(LyException.CATEGORY_NOT_FOUND);
        }
        return list;
    }
}

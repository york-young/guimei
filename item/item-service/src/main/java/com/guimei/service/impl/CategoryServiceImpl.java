package com.guimei.service.impl;

import com.guimei.mapper.CategoryMapper;
import com.guimei.pojo.Category;
import com.guimei.myexception.ExceptionEnum;
import com.guimei.myexception.MyException;
import com.guimei.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: York
 * @Date: 2020/5/8 000821:56
 * @Version:1.0
 * @Description: 表服务实现类
 */
@Service
public class CategoryServiceImpl implements ICategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    /**
     * @Description:
     *             根据PID查找数据信息,并以树的形式展示出来
     * @Author: York
     * @Date: 2020/5/15 0015 9:43
     * @param pid
     * @Return: java.util.List<com.guimei.model.Category>
     **/
    @Override
    public List<Category> queryCategoryByPid(Long pid) throws MyException {
        //创建一个Example对象，并至少传一个实体对象
        Example example = new Example(Category.class);
        //通过Example对象穿件Criteria对象
        example.createCriteria().andEqualTo("parentId",pid);
        List<Category> list = this.categoryMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(list)){
            throw new MyException(ExceptionEnum.CATEGORY_NOT_FOUND);
        }
        return list;
    }

    /**
     * @param bid
     * @Description: 根据BID查找数据信息, 回显数据的
     * @Author: York
     * @Date: 2020/5/18 0018 22:09
     * @Return: java.util.List<com.guimei.model.Category>
     **/
    @Override
    public List<Category> queryByBrandId(Long bid) {
        return this.categoryMapper.queryByBrandId(bid);
    }

    /**
     * @param category
     * @Description: 添加数据信息 TODO 为什么这么写
     * @Author: York
     * @Date: 2020/5/18 0018 22:10
     * @Return: org.springframework.http.ResponseEntity<java.lang.Void>
     **/
    @Override
    public void saveCategory(Category category) {

        //1.首先置id为null
        category.setId(null);
        //2.保存
        this.categoryMapper.insert(category);
        //3.修改父节点
        Category parent = new Category();
        parent.setId(category.getParentId()).setIsParent(true);
        this.categoryMapper.updateByPrimaryKeySelective(parent);

    }

    /**
     * @param category
     * @Description: 更新数据信息
     * @Author: York
     * @Date: 2020/5/18 0018 22:11
     * @Return: org.springframework.http.ResponseEntity<java.lang.Void>
     **/
    @Override
    public void updateCategory(Category category) {
        this.categoryMapper.updateByPrimaryKeySelective(category);
    }

    /**
     * @param id
     * @Description: 根据id删除数据信息
     * @Author: York
     * @Date: 2020/5/18 0018 22:12
     * @Return: org.springframework.http.ResponseEntity<java.lang.Void>
     **/
    @Override
    public void deleteCategory(Long id) {
        /**
         * 先根据id查询要删除的对象，然后进行判断
         * 如果是父节点，那么删除所有附带子节点,然后维护中间表
         * 如果是子节点，那么只删除自己,然后判断父节点孩子的个数，如果孩子不为0，则不做修改；如果孩子个数为0，则修改父节点isParent
         * 的值为false,最后维护中间表
         */
        Category category=this.categoryMapper.selectByPrimaryKey(id);
        if(category.getIsParent()){
            //1.查找所有叶子节点
            List<Category> list = new ArrayList<>();
            queryAllLeafNode(category,list);

            //2.查找所有子节点
            List<Category> list2 = new ArrayList<>();
            queryAllNode(category,list2);

            //3.删除tb_category中的数据,使用list2
            for (Category c:list2){
                this.categoryMapper.delete(c);
            }

            //4.维护中间表
            for (Category c:list){
                this.categoryMapper.deleteByCategoryIdInCategoryBrand(c.getId());
            }

        }else {
            //1.查询此节点的父亲节点的孩子个数 ===> 查询还有几个兄弟
            Example example = new Example(Category.class);
            example.createCriteria().andEqualTo("parentId",category.getParentId());
            List<Category> list=this.categoryMapper.selectByExample(example);
            if(list.size()!=1){
                //有兄弟,直接删除自己
                this.categoryMapper.deleteByPrimaryKey(category.getId());

                //维护中间表
                this.categoryMapper.deleteByCategoryIdInCategoryBrand(category.getId());
            }
            else {
                //已经没有兄弟了
                this.categoryMapper.deleteByPrimaryKey(category.getId());

                Category parent = new Category();
                parent.setId(category.getParentId());
                parent.setIsParent(false);
                this.categoryMapper.updateByPrimaryKeySelective(parent);
                //维护中间表
                this.categoryMapper.deleteByCategoryIdInCategoryBrand(category.getId());
            }
        }
    }

    /**
     * @param asList
     * @Description: 根据分类id集合查询分类名称
     * @Author: York
     * @Date: 2020/5/18 0018 22:13
     * @Return: org.springframework.http.ResponseEntity<java.util.List < java.lang.String>>
     **/
    @Override
    public List<String> queryNameByIds(List<Long> asList) {
        List<String> names = new ArrayList<>();
        if (asList != null && asList.size() !=0){
            for (Long id : asList) {
                names.add(this.categoryMapper.queryNameById(id));
            }
        }
        return names;
    }

    /**
     * @param ids
     * @Description: 查询数据库中最后一条数据
     * @Author: York
     * @Date: 2020/5/18 0018 22:14
     * @Return: org.springframework.http.ResponseEntity<java.util.List < com.guimei.model.Category>>
     **/
    @Override
    public List<Category> queryCategoryByIds(List<Long> ids) {
        List<Category> last =this.categoryMapper.selectLast();
        return last;
    }

    /**
     * @param id
     * @Description: 根据分类id集合查询分类名称，根据cid3查询其所有层级分类
     * @Author: York
     * @Date: 2020/5/18 0018 22:14
     * @Return: org.springframework.http.ResponseEntity<java.util.List < com.guimei.model.Category>>
     **/
    @Override
    public List<Category> queryAllCategoryLevelByCid3(Long id) {
        List<Category> categoryList = new ArrayList<>();
        Category category = this.categoryMapper.selectByPrimaryKey(id);
        while (category.getParentId() != 0){
            categoryList.add(category);
            category = this.categoryMapper.selectByPrimaryKey(category.getParentId());
        }
        categoryList.add(category);
        return categoryList;
    }

    /**
     * 查询本节点下所包含的所有叶子节点，用于维护tb_category_brand中间表 TODO
     * @param category
     * @param leafNode
     */
    private void queryAllLeafNode(Category category,List<Category> leafNode){
        if(!category.getIsParent()){
            leafNode.add(category);
        }
        Example example = new Example(Category.class);
        example.createCriteria().andEqualTo("parentId",category.getId());
        List<Category> list=this.categoryMapper.selectByExample(example);

        for (Category category1:list){
            queryAllLeafNode(category1,leafNode);
        }
    }

    /**
     * 查询本节点下所有子节点
     * @param category
     * @param node
     */
    private void queryAllNode(Category category,List<Category> node){

        node.add(category);
        Example example = new Example(Category.class);
        example.createCriteria().andEqualTo("parentId",category.getId());
        List<Category> list=this.categoryMapper.selectByExample(example);

        for (Category category1:list){
            queryAllNode(category1,node);
        }
    }
}

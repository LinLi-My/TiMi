package com.ml.timi.service.impl;

import com.ml.timi.model.entity.UserTestClient;
import com.ml.timi.mapper.UserTestClientMapper;
import com.ml.timi.service.UserTestClientService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用户中间表(UserTestClient)表服务实现类
 *
 * @author Lin
 * @since 2021-08-30 16:48:43
 */
@Service("userTestClientService")
public class UserTestClientServiceImpl implements UserTestClientService {
    @Resource
    private UserTestClientMapper userTestClientMapper;

    /**
     * 通过实体作为筛选条件查询
     * 查询条件为空时，则默认查询全部
     *
     * @param userTestClient UserTestClient对象
     * @return UserTestClient集合
     */
    @Override
    public List<UserTestClient> search(UserTestClient userTestClient) {

        return this.userTestClientMapper.search(userTestClient);
    }


    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public UserTestClient searchById(Long id) {
        return this.userTestClientMapper.searchById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<UserTestClient> searchAllByLimit(int offset, int limit) {
        return this.userTestClientMapper.searchAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param userTestClient 实例对象
     * @return 实例对象
     */
    @Override
    public int insert(UserTestClient userTestClient) {
        return this.userTestClientMapper.insert(userTestClient);

    }

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param  userTestClientList UserTestClient对象集合
     * @return 影响行数
     */
    @Override
    public int insertBatch(List<UserTestClient> userTestClientList) {
        return this.userTestClientMapper.insertBatch(userTestClientList);
    }

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param  userTestClientList UserTestClient对象集合
     * @return 影响行数
     */
    @Override
    public int insertOrUpdateBatch(List<UserTestClient> userTestClientList) {
        return this.userTestClientMapper.insertOrUpdateBatch(userTestClientList);
    }

    /**
     * 修改数据
     *
     * @param userTestClient UserTestClient对象
     * @return 实例对象
     */
    @Override
    public UserTestClient update(UserTestClient userTestClient) {
        this.userTestClientMapper.update(userTestClient);
        return this.searchById(userTestClient.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    @Override
    public int deleteById(Long id) {
        return this.userTestClientMapper.deleteById(id);
    }
}

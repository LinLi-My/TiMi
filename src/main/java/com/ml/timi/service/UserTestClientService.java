package com.ml.timi.service;

import com.ml.timi.model.entity.UserTestClient;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户中间表(UserTestClient)表服务接口
 *
 * @author Lin
 * @since 2021-08-30 16:48:42
 */
@Service
public interface UserTestClientService {
    /**
     * 通过实体作为筛选条件查询
     * 查询条件为空时，则默认查询全部
     *
     * @param userTestClient UserTestClient对象
     * @return UserTestClient集合
     */
    List<UserTestClient> search(UserTestClient userTestClient);

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    UserTestClient searchById(Long id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<UserTestClient> searchAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param userTestClient 实例对象
     * @return 实例对象
     */
    int insert(UserTestClient userTestClient);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param  userTestClientList UserTestClient对象集合
     * @return 影响行数
     */
    int insertBatch(List<UserTestClient> userTestClientList);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param  userTestClientList UserTestClient对象集合
     * @return 影响行数
     */
    int insertOrUpdateBatch(List<UserTestClient> userTestClientList);

    /**
     * 修改数据
     *
     * @param userTestClient UserTestClient对象
     * @return 实例对象
     */
    UserTestClient update(UserTestClient userTestClient);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

}

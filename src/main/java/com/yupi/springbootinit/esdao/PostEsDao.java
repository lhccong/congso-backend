package com.yupi.springbootinit.esdao;

import com.yupi.springbootinit.model.dto.post.PostEsDTO;
import java.util.List;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * 帖子 ES 操作
 *
 * @author <a href="https://github.com/liyupi">程序员鱼皮</a>
 * @from <a href="https://yupi.icu">编程导航知识星球</a>
 */
public interface  PostEsDao extends ElasticsearchRepository<PostEsDTO, Long> {

    /**
     * 根据用户id查找
     *
     * @param userId 用户id
     * @return {@link List}<{@link PostEsDTO}>
     */
    List<PostEsDTO> findByUserId(Long userId);

    /**
     * 根据title查找
     *
     * @param title 标题
     * @return {@link List}<{@link PostEsDTO}>
     */
    List<PostEsDTO> findByTitle(String title);
}
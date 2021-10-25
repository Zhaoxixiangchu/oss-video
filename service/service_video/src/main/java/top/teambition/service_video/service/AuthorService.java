package top.teambition.service_video.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import top.teambition.service_video.entity.Author;
import com.baomidou.mybatisplus.extension.service.IService;
import top.teambition.service_video.entity.vo.AuthorQuery;

/**
 * <p>
 * 创作者 服务类
 * </p>
 *
 * @author Liupengyu
 * @since 2021-07-03
 */
public interface AuthorService extends IService<Author> {


    void pageQuery(Page<Author> pageInfo, AuthorQuery authorQuery);

}

package top.teambition.service_video.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.lang3.StringUtils;
import top.teambition.service_video.entity.Author;
import top.teambition.service_video.entity.vo.AuthorQuery;
import top.teambition.service_video.mapper.AuthorMapper;
import top.teambition.service_video.service.AuthorService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 创作者 服务实现类
 * </p>
 *
 * @author Liupengyu
 * @since 2021-07-03
 */
@Service
public class AuthorServiceImpl extends ServiceImpl<AuthorMapper, Author> implements AuthorService {


    @Override
    public void pageQuery(Page<Author> pageInfo, AuthorQuery authorQuery) {
        QueryWrapper<Author> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByAsc("sort");
        if (authorQuery == null){
            baseMapper.selectPage(pageInfo, queryWrapper);
        }
        //有条件 看条件是不是空
        if (!StringUtils.isEmpty(authorQuery.getName())){
            queryWrapper.like("name", authorQuery.getName());
        }

        if (!StringUtils.isEmpty(authorQuery.getLevel())){
            queryWrapper.eq("level", authorQuery.getLevel());
        }

        if (!StringUtils.isEmpty(authorQuery.getBegin())){
            queryWrapper.ge("gmt_create", authorQuery.getBegin());
        }

        if (!StringUtils.isEmpty(authorQuery.getEnd())){
            queryWrapper.le("gmt_create", authorQuery.getEnd());
        }

        baseMapper.selectPage(pageInfo, queryWrapper);
    }


}

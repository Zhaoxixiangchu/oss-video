package top.teambition.service_video.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import top.teambition.service_video.entity.Author;
import top.teambition.service_video.entity.vo.AuthorQuery;
import top.teambition.service_video.service.AuthorService;
import top.teambition.utils.ResponseResult;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * 创作者 前端控制器
 * </p>
 *
 * @author Liupengyu
 * @since 2021-07-03
 */
@RestController
@RequestMapping("/service_video/author")
@Api(tags = "视频作者组")
@CrossOrigin
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @GetMapping("/getAuthorList")
    @ApiOperation(value = "获取所有作者列表")
    public List<Author> getAuthorList(HttpServletRequest request){
        List<Author> list = authorService.list(null);
        return list;
    }

    @PostMapping("/deleteAuthor/{id}")
    @ApiOperation(value = "逻辑删除作者")
    public ResponseResult deleteAuthor(@ApiParam(name = "id", value = "作者ID", required = true) @PathVariable String id, HttpServletRequest request){
        authorService.removeById(id);
        return ResponseResult.ok();
    }


    /**
     * 分页
     */
    @ApiModelProperty(value = "作者分页数据")
    @PostMapping("/pageList/{page}/{limit}")
    public ResponseResult pageList(@PathVariable @ApiParam(name = "page", value = "当前页", required = true) Long page,
                                   @PathVariable @ApiParam(name = "limit", value = "每页记录数", required = true) Long limit,
                                   HttpServletRequest request,
                                   @ApiParam(name = "authorQuery", value = "条件查询的对象")
                                   @RequestBody AuthorQuery authorQuery){
        Page<Author> authorPage = new Page<>(page, limit);
        authorService.pageQuery(authorPage, authorQuery);
        List<Author> authors = authorPage.getRecords();
        long total = authorPage.getTotal();
        return ResponseResult.ok().data("total", total).data("rows", authors);
    }

    /**
     * 根据Id查
     */
    @GetMapping("/getAuthorWithId/{id}")
    public ResponseResult getAuthorWithId(@PathVariable @ApiParam(name = "ID", value = "作者ID", required = true) String id, HttpServletRequest request){
        Author author = authorService.getById(id);
        return ResponseResult.ok().data("item", author);
    }

    /**
     * 创建作者
     */
    @PostMapping("/addAuthor")
    public ResponseResult addAuthor(@RequestBody @ApiParam(name = "author", value = "添加作者") Author author){
        authorService.save(author);
        return ResponseResult.ok();
    }

    /**
     * 更新作者
     */
    @PostMapping("/updateAuthor")
    public ResponseResult updateAuthor(@RequestBody @ApiParam(name = "author", value = "更新作者") Author author){
        boolean flag = authorService.updateById(author);
        if (flag){
            return ResponseResult.ok();
        } else {
            return ResponseResult.error();
        }
    }
}


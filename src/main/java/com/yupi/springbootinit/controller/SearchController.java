package com.yupi.springbootinit.controller;

import com.google.common.collect.Lists;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.gson.Gson;
import com.yupi.springbootinit.common.BaseResponse;
import com.yupi.springbootinit.common.ErrorCode;
import com.yupi.springbootinit.common.ResultUtils;
import com.yupi.springbootinit.exception.BusinessException;
import com.yupi.springbootinit.exception.ThrowUtils;
import com.yupi.springbootinit.manager.SearchFacade;
import com.yupi.springbootinit.model.dto.picture.PictureQueryRequest;
import com.yupi.springbootinit.model.dto.post.PostQueryRequest;
import com.yupi.springbootinit.model.dto.search.SearchQueryRequest;
import com.yupi.springbootinit.model.dto.user.UserQueryRequest;
import com.yupi.springbootinit.model.entity.Picture;
import com.yupi.springbootinit.model.enums.SearchTypeEnum;
import com.yupi.springbootinit.model.vo.PostVO;
import com.yupi.springbootinit.model.vo.SearchVO;
import com.yupi.springbootinit.model.vo.UserVO;
import com.yupi.springbootinit.service.PictureService;
import com.yupi.springbootinit.service.PostService;
import com.yupi.springbootinit.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.CompletableFuture;

/**
 * 图片接口
 *
 * @author <a href="https://github.com/liyupi">程序员鱼皮</a>
 * @from <a href="https://yupi.icu">编程导航知识星球</a>
 */
@RestController
@RequestMapping("/search")
@Slf4j
public class SearchController {

    @Resource
    private PictureService pictureService;
    @Resource
    private UserService userService;
    @Resource
    private PostService postService;
    @Resource
    private SearchFacade searchFacade;


    /**
     * 分页获取列表（封装类）
     *
     * @param searchQueryRequest
     * @param request
     * @return
     */
    @PostMapping("/all")
    public BaseResponse<SearchVO> searchAll(@RequestBody SearchQueryRequest searchQueryRequest,
                                            HttpServletRequest request) {
        return ResultUtils.success(searchFacade.searchAll(searchQueryRequest, request));


    }


}

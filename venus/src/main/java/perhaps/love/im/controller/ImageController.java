/**
 * Copyright 2017 yezi.gl. All Rights Reserved.
 */
package perhaps.love.im.controller;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;

import perhaps.love.im.context.UserContextHolder;
import perhaps.love.im.dto.JsonResponse;
import perhaps.love.im.dto.Status;
import perhaps.love.im.entity.MessageType;
import perhaps.love.im.utils.ImgUtils;
import perhaps.love.im.utils.Upload;

/**
 * description here
 *
 * @author yezi
 * @since 2017年1月4日
 */
@RestController
@RequestMapping("/api/v1/image")
public class ImageController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public JsonResponse<JSONObject> upload(@RequestParam("file") MultipartFile file,
            @RequestParam(required = false) MessageType type) {
        JSONObject ret = new JSONObject();

        Upload upload = null;
        try {
            if (type == MessageType.IMAGE) {
                upload = ImgUtils.uploadImage(UserContextHolder.getUid(), file.getInputStream());
            } else if (type == MessageType.FILE) {
                ImgUtils.uploadFile(UserContextHolder.getUid(), file.getInputStream());
            }
            if (upload != null) {
                ret.put("url", upload.getW300Url());
                ret.put("originUrl", upload.getOriginUrl());
            }
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
        if (upload == null) {
            return JsonResponse.error(Status.FILE_UPLOAD_ERROR);
        }

        return JsonResponse.success(ret);
    }
}

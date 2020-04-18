package com.example.es_jd.controller;

import com.example.es_jd.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
public class ContentController {

    @Autowired
    private ContentService contentService;

    @GetMapping("/parse/{keyword}")
    public boolean parse(@PathVariable String keyword) throws IOException {
        return contentService.addJdGoods(keyword);
    }

    @GetMapping("/search/{keyword}/{pageNum}/{pageSum}")
    public List<Map<String, Object>> search(@PathVariable Integer pageNum,
                                            @PathVariable Integer pageSum,
                                            @PathVariable String keyword) throws IOException {
        return contentService.getJdFoods(pageNum,pageSum,keyword);
    }
}

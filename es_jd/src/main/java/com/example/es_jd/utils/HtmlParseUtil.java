package com.example.es_jd.utils;

import com.example.es_jd.pojo.Content;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class HtmlParseUtil {
    public static void main(String[] args) throws IOException {
//        getJd();
        getZh("1");

    }

    public static List<Content> getJd(String keyword) throws IOException {
        //获取请求
        String url = "https://www.zhihu.com/topic/19828946/top-answers";
        //解析网页。（Jsoup返回Document就是浏览器的Document对象）    URL 设置中文编码  后面拼接 enc=utf8
        Document document = Jsoup.parse(new URL(url), 3000);
        Elements list1 = document.getElementsByClass("List");
        Element element = document.getElementById("J_goodsList");
//        System.out.println(element);
        //获取所有的li标签
        Elements elements = element.getElementsByTag("li");
        List<Content> list = new ArrayList<>();
        for (Element el:elements
             ) {
            String img = el.getElementsByTag("img").eq(0).attr("source-data-lazy-img");
            String price = el.getElementsByClass("p-price").eq(0).text();
            String title = el.getElementsByClass("p-name").eq(0).text();
            Content content = new Content(title,img,price);
            list.add(content);
        }
        return list;
    }

    public static List<Content> getZh(String keyword) throws IOException {
        //获取请求
        String url = "https://www.zhihu.com/question/34643719";
        //解析网页。（Jsoup返回Document就是浏览器的Document对象）    URL 设置中文编码  后面拼接 enc=utf8
        Document document = Jsoup.parse(new URL(url), 3000);
//        Elements list1 = document.getElementsByClass("List-item TopicFeedItem");
//        Element element = document.getElementById("J_goodsList");
//        System.out.println(element);
        //获取所有的li标签
        Elements elementsByAttribute = document.getElementsByAttribute("data-zop");
//        System.out.println(elementsByAttribute);
//        Elements elements = element.getElementsByTag("li");
//        List<Content> list = new ArrayList<>();
        for (Element el:elementsByAttribute
        ) {
            System.out.println(el.text());
        }
        return null;
    }

}

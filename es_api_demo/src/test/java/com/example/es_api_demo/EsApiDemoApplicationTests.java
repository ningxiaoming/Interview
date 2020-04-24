package com.example.es_api_demo;

import com.alibaba.fastjson.JSON;
import com.example.es_api_demo.config.EsClientConfig;
import com.example.es_api_demo.pojo.User;
import lombok.val;
import org.apache.lucene.util.QueryBuilder;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.FetchSourceContext;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

@SpringBootTest
class EsApiDemoApplicationTests {

    @Autowired
    private RestHighLevelClient restHighLevelClient;

    @Test
    void testCreateIndex() throws IOException {
        //1.创建索引请求
        CreateIndexRequest request = new CreateIndexRequest("kuang_index");
        //2.客户端执行请求IndicesClient，请求后获得响应
        CreateIndexResponse response = restHighLevelClient.indices().create(request, RequestOptions.DEFAULT);

        System.out.println(response);
    }
    //测试获取索引
    @Test
    void testExistIndex() throws IOException {
        GetIndexRequest request = new GetIndexRequest("kuang_index");
        boolean exists = restHighLevelClient.indices().exists(request, RequestOptions.DEFAULT);
        System.out.println(exists);
    }
    //测试删除索引
    @Test
    void testDelIndex() throws IOException {
        DeleteIndexRequest request = new DeleteIndexRequest("kuang_index");
        AcknowledgedResponse delete = restHighLevelClient.indices().delete(request, RequestOptions.DEFAULT);
        System.out.println(delete.isAcknowledged());
    }

    //添加文档
    @Test
    void testAddDocument() throws IOException {
        User user = new User("张三",3);
        //创建请求
        IndexRequest request = new IndexRequest("kuang_index");

        //  规则：put/kuang_index/_doc/1
        request.id("1");
        request.timeout("1s");

        // 将user数据放入到请求  json
        IndexRequest source = request.source(JSON.toJSONString(user), XContentType.JSON);

        //客户端发送请求，  获取相应的结果
        IndexResponse index = restHighLevelClient.index(request, RequestOptions.DEFAULT);
        System.out.println(index);
        System.out.println(index.status()); //对应命令返回的状态    insert   update
    }
    //判断文档是否存在
    @Test
    void testExist() throws IOException {
        GetRequest request = new GetRequest("kuang_index", "1");
        //不获取返回的  _source 的上下文了
        request.fetchSourceContext(new FetchSourceContext(false));
        request.storedFields("_none_");

        boolean exists = restHighLevelClient.exists(request, RequestOptions.DEFAULT);
        System.out.println(exists);
    }
    //获取并且打印文档
    @Test
    void testGetElement() throws IOException {
        GetRequest request = new GetRequest("kuang_index", "1");
        GetResponse documentFields = restHighLevelClient.get(request, RequestOptions.DEFAULT);
        System.out.println(documentFields.getSourceAsString());
        System.out.println(documentFields);
    }

    //更新文档内容
    @Test
    void testUpElement() throws IOException {
        UpdateRequest request = new UpdateRequest("kuang_index", "1");
        User user = new User("李四", 2);
        request.doc(JSON.toJSONString(user), XContentType.JSON);
        UpdateResponse response = restHighLevelClient.update(request, RequestOptions.DEFAULT);
        System.out.println(response.status());
    }

    //删除文档内容
    @Test
    void testDelElement() throws IOException {
        DeleteRequest request = new DeleteRequest("kuang_index", "1");

        DeleteResponse delete = restHighLevelClient.delete(request, RequestOptions.DEFAULT);
        System.out.println(delete.status());
    }

    //批量插入
    @Test
    void testInsertElement() throws IOException {

        BulkRequest bulkRequest = new BulkRequest();
        ArrayList<User> list = new ArrayList<>();
        User user = new User("张三1",12);
        User user1 = new User("张三2",12);
        User user2 = new User("张三3",12);
        User user3 = new User("zhangsan4",12);
        User user4 = new User("zhangsan4",12);
        User user5 = new User("zhangsan4",12);
        list.add(user);list.add(user1);list.add(user2);list.add(user3);list.add(user4);list.add(user5);
        for (int i = 0; i < list.size(); i++) {
            bulkRequest.add(
                    new IndexRequest("kuang_index")
                    .id(""+i)
                    .source(JSON.toJSONString(list.get(i)),XContentType.JSON)
            );
        }
        BulkResponse bulk = restHighLevelClient.bulk(bulkRequest, RequestOptions.DEFAULT);
        System.out.println(bulk.hasFailures());
    }

    //构造条件查询
    //  SearchRequest   搜索请求
    //  SearchSourceBuilder 条件构造
    //  HighlightBuilder    高亮显示
    //  TermQueryBuilder    精确查询
    //  xxxQueryBuilder     对应刚才的命令
    @Test
    void testSearch() throws IOException {
        SearchRequest searchRequest = new SearchRequest("kuang_index");
        //构造搜索条件
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();

        //查询条件   使用QueryBuilders 工具类实现
        //QueryBuilders.termQuery 精确
        //QueryBuilders.matchAllQuery() 匹配所有
        sourceBuilder.highlighter();
        TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("name", "zhangsan4");
        sourceBuilder.query(termQueryBuilder);
        sourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));

        searchRequest.source(sourceBuilder);

        SearchResponse response = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        System.out.println(JSON.toJSONString(response.getHits()));
        System.out.println("==============================");
        for (SearchHit hit : response.getHits().getHits()) {
            System.out.println(hit.getSourceAsMap());
        }


    }

}

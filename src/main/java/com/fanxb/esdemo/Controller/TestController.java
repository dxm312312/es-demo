package com.fanxb.esdemo.Controller;

import com.fanxb.esdemo.entity.Product;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;
import org.springframework.data.elasticsearch.core.query.UpdateQuery;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/")
public class TestController {
    @Autowired
    ElasticsearchOperations elasticsearchTemplate;
   //RestHighLevelClient elasticsearchClient;

    @PostMapping("/save")
    public String save(@RequestBody Product product) throws IOException {
//
        //IndexRequest indexRequest = new IndexRequest("product");
        //indexRequest.source(product, XContentType.JSON);
        //elasticsearchTemplate.index(indexRequest, RequestOptions.DEFAULT);
        elasticsearchTemplate.save(product);
        return "success";
    }

    @PostMapping("/get")
    public Product get(@RequestBody Product product) throws IOException {

        Product product1 = elasticsearchTemplate.get(product.getId().toString(), product.getClass());
        return product1;
    }

    @PostMapping("/update")
    public Product update(@RequestBody Product product) throws IOException {

        elasticsearchTemplate.delete(product);
        elasticsearchTemplate.save(product);
        return product;
    }

    @PostMapping("/delete")
    public String delete(@RequestBody Product product) throws IOException {
        elasticsearchTemplate.delete(product);
        //
        return "success";
    }

}

package com.fanxb.esdemo.Controller;

import com.alibaba.fastjson.JSON;
import com.fanxb.esdemo.entity.Product;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.data.elasticsearch.core.query.UpdateQuery;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.elasticsearch.index.query.QueryBuilders.matchAllQuery;
import static org.elasticsearch.index.query.QueryBuilders.matchQuery;

//换了template必须要重新创建一个索引，否则找不到？？ 这里新建了product2索引
@RestController
public class SpringDataESController {

      @Autowired
      ElasticsearchRestTemplate elasticsearchRestTemplate;


    @RequestMapping("/save")
    public String save(@RequestBody Product product){
        IndexQuery indexQuery = new IndexQuery();
        indexQuery.setObject(product);
        indexQuery.setId(product.getId().toString());
        elasticsearchRestTemplate.index(indexQuery);
        return "success";
    }

    @RequestMapping("/bulkSave")
    public String bulkSave(@RequestBody List<Product> plist){
        List<IndexQuery> list = new ArrayList<>();
        for(Product p:plist){
            IndexQuery indexQuery = new IndexQuery();
            indexQuery.setObject(p);
            list.add(indexQuery);
        }
        elasticsearchRestTemplate.bulkIndex(list);
        return "success";
    }

      @RequestMapping("/searchAll")
      public String searchAll(){
          SearchQuery searchQuery = new NativeSearchQueryBuilder()
                  .withQuery(matchAllQuery()).build();
          List<Product> products = elasticsearchRestTemplate.queryForList(searchQuery, Product.class);
          return JSON.toJSONString(products);
      }

    @RequestMapping("/update")
    public String update(@RequestBody Product product){
        UpdateQuery updateQuery = new UpdateQuery();
        //updateQuery.setIndexName("product");
        updateQuery.setClazz(Product.class);//更新必须要有这行代码。。。否则空指针
        updateQuery.setId(product.getId().toString());
        UpdateRequest request = new UpdateRequest();
        //java.lang.IllegalArgumentException: The number of object passed must be even but was [1]
        //request.doc(product);  需要转map 不然报上面的错
        String json = JSON.toJSONString(product);
        Map m = JSON.parseObject(json,Map.class);
        request.doc(m);
        updateQuery.setUpdateRequest(request);
        elasticsearchRestTemplate.update(updateQuery);
        return null;
    }

    @RequestMapping("/bulkUpdate")
    public String bulkUpdate(@RequestBody List<Product> pList){
        List<UpdateQuery> list = new ArrayList<>();
        for(Product p:pList){
            UpdateQuery updateQuery = new UpdateQuery();
            updateQuery.setClazz(Product.class);
            updateQuery.setId(p.getId().toString());
            UpdateRequest request = new UpdateRequest();
            //java.lang.IllegalArgumentException: The number of object passed must be even but was [1]
            //request.doc(product);  需要转map 不然报上面的错
            String json = JSON.toJSONString(p);
            Map m = JSON.parseObject(json,Map.class);
            request.doc(m);
            updateQuery.setUpdateRequest(request);
            list.add(updateQuery);
        }
        elasticsearchRestTemplate.bulkUpdate(list);
        return null;
    }

    @RequestMapping("/delete")
    public String delete(@RequestBody Product product){
        elasticsearchRestTemplate.delete(Product.class,product.getId().toString());

        return "success";
    }

}







//package com.fanxb.esdemo.Controller;
//
//import com.alibaba.fastjson.JSON;
//import com.fanxb.esdemo.entity.Product;
//import com.fanxb.esdemo.repository.ProductRepository;
//import org.elasticsearch.action.index.IndexRequest;
//import org.elasticsearch.client.RequestOptions;
//import org.elasticsearch.client.RestHighLevelClient;
//import org.elasticsearch.common.xcontent.XContentType;
//import org.elasticsearch.index.query.BoolQueryBuilder;
//import org.elasticsearch.index.query.QueryBuilder;
//import org.elasticsearch.index.query.QueryBuilders;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.domain.Sort;
//import org.springframework.data.elasticsearch.core.*;
//import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
//import org.springframework.data.elasticsearch.core.query.*;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.List;
//import java.util.Optional;
//import java.util.stream.Collectors;
//
//@RestController
//@RequestMapping("/")
//public class TestController {
//    @Autowired
//    ElasticsearchOperations elasticsearchTemplate;
//
//    //高版本用这个
//
//    @Autowired
//    ElasticsearchRestTemplate elasticsearchRestTemplate;
//
//   //RestHighLevelClient elasticsearchClient;
//
//    @Autowired
//    ProductRepository productRepository;
//
//    @PostMapping("/save")
//    public String save(@RequestBody Product product) throws IOException {
////
//        //IndexRequest indexRequest = new IndexRequest("product");
//        //indexRequest.source(product, XContentType.JSON);
//        //elasticsearchTemplate.index(indexRequest, RequestOptions.DEFAULT);
//        elasticsearchRestTemplate.save(product);
//        return "success";
//    }
//
//    @PostMapping("/get")
//    public Product get(@RequestBody Product product) throws IOException {
//
//        Product product1 = elasticsearchRestTemplate.get(product.getId().toString(), product.getClass());
//        return product1;
//    }
//
//    @PostMapping("/update")
//    public Product update(@RequestBody Product product) throws IOException {
//        IndexCoordinates index = IndexCoordinates.of("product");
//        UpdateQuery query = UpdateQuery.builder(product.getId().toString()).build();
//       // elasticsearchRestTemplate.update(query,index);
//
//        BoolQueryBuilder builder = QueryBuilders.boolQuery();
//        builder.must(QueryBuilders.matchQuery("id",product.getId()));
//        Query searchQuery = new NativeSearchQuery(builder);
//        //查询id的方法
//        //List<String> strings = elasticsearchRestTemplate.queryForIds(searchQuery, Product.class, index);
//
//        GetQuery getQuery = new GetQuery(product.getId().toString());
//        return elasticsearchRestTemplate.queryForObject(getQuery,Product.class);
//    }
//
//    @PostMapping("/delete")
//    public String delete(@RequestBody Product product) throws IOException {
//        elasticsearchRestTemplate.delete(product);
//        //
//        return "success";
//    }
//
//    @PostMapping("/condetionSearch")
//    public String condetionSearch(@RequestBody Product product) throws IOException {
//        //withExcludes() 剔除的字段集合   withIncludes 要查的字段集合
//        SourceFilter filter = new FetchSourceFilterBuilder().withIncludes("name","sellPoint").build();
//        NativeSearchQueryBuilder searchQuery = new NativeSearchQueryBuilder().withSourceFilter(filter);
//        BoolQueryBuilder builder = QueryBuilders.boolQuery();
//
//        builder.must(QueryBuilders.matchQuery("id",product.getId()));
//
//
//        searchQuery.withQuery(builder);
//        /*Pageable pageable = PageRequest.of(0,10);
//        searchQuery.withPageable(pageable);*/
//
//
//        IndexCoordinates index = IndexCoordinates.of("product");
//        List<Product> products = elasticsearchRestTemplate.queryForList(searchQuery.build(), Product.class, index);
//        return JSON.toJSONString(products);
//    }
//
//    @PostMapping("/searchByCid")
//    public String searchByCid(@RequestBody Product product) throws IOException {
//       // List<Product> pList = productRepository.findByCid(product.getCid());
//        Optional<Product> byId = productRepository.findById(product.getId());
//
//        return JSON.toJSONString(byId.get());
//    }
//
//
//    @PostMapping("/searchSort")
//    public String searchSort(){
//        //根据id 升序  cid 倒序 查询所有数据
//        /*Sort sort = Sort.by("id").ascending()
//                .and(Sort.by("cid").descending());
//        Iterable<Product> all = productRepository.findAll(sort);
//        List<Product> list = new ArrayList<>();
//        all.forEach(e->list.add(e));
//        return JSON.toJSONString(list);*/
//
//        Sort sort = Sort.by("price").ascending();
//        List<Product> collect = productRepository.findByCategoryName("电脑配件", sort).stream().collect(Collectors.toList());
//        return JSON.toJSONString(collect);
//
//    }
//
//}

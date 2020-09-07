package com.example.demo.repository.impl;

import com.example.demo.controller.request.ApiSearchRequest;
import com.example.demo.controller.response.ApiSearchResponse;
import com.example.demo.dto.EmployeeDto;
import com.example.demo.repository.EsEmployeeRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class EsEmployeeRepositoryImpl implements EsEmployeeRepository {

    private final RestHighLevelClient client;
    private final ObjectMapper objectMapper;

    @Value("${elasticsearch.index}")
    private String index;

    @Override
    public boolean create(EmployeeDto employee) {
        UpdateRequest updateRequest = new UpdateRequest(index, employee.getId().toString());
        try {
            updateRequest.doc(objectMapper.writeValueAsString(employee), XContentType.JSON);
            updateRequest.upsert(objectMapper.writeValueAsString(employee), XContentType.JSON);
            client.update(updateRequest, RequestOptions.DEFAULT);
        } catch (Exception e) {
            // log
            return false;
        }
        return true;
    }

    @Override
    public ApiSearchResponse search(ApiSearchRequest request) {
        long total = 0;
        List<EmployeeDto> items = new ArrayList<>();
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        sourceBuilder.query(QueryBuilders.matchQuery("name", request.getKeyword()));
        sourceBuilder.size(10);
        SearchRequest searchRequest = new SearchRequest();
        searchRequest.indices(index);
        searchRequest.source(sourceBuilder);
        try {
            SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
            items = Arrays.stream(searchResponse.getHits().getHits())
                          .map(this::convertSearchHitToResult)
                          .filter(Objects::nonNull)
                          .collect(Collectors.toList());
            total = searchResponse.getHits().getTotalHits().value;
        } catch (Exception e) {
            // log
        }
        return ApiSearchResponse.of(total, items);
    }

    private EmployeeDto convertSearchHitToResult(SearchHit searchHit) {
        try {
            return objectMapper.readValue(searchHit.getSourceAsString(), EmployeeDto.class);
        } catch (IOException e) {
            // log
        }
        return null;
    }
}

package com.milicaculibrk.udd.repository;

import com.milicaculibrk.udd.model.IndexUnit;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface BookRepository extends ElasticsearchRepository<IndexUnit, String> {

}

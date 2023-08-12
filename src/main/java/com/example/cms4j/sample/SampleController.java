package com.example.cms4j.sample;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequestMapping("/api/sample")
public class SampleController {

    private final SampleRespository repository;

    @Autowired
    public SampleController(SampleRespository repository) {
        this.repository = repository;
    }

    @GetMapping("/article/{id}")
    public Mono<SampleArticleEntity> getOne(@PathVariable("id") int id) {
        log.info("Control inside getOne({})", id);
        return repository.findById(id);
    }

    @PostMapping("/article")
    public Mono<SampleArticleEntity> addOne(@RequestBody() SampleArticleEntity sampleArticleEntity) {
        log.info("Control inside addOne({})", sampleArticleEntity.toString());
        return repository.save(sampleArticleEntity);
    }

    @GetMapping("/article")
    public Flux<SampleArticleEntity> getMany() {
        log.info("Control inside getMany");
        return repository.findAll();
    }

    @DeleteMapping("/article/{id}")
    public Mono<Void> deleteOne(@PathVariable("id") int id) {
        log.info("Control inside deleteOne({})", id);
        return repository.deleteById(id);
    }

    @PutMapping("/article/{id}")
    public Mono<SampleArticleEntity> updateOne(@PathVariable("id") int id,
                                               @RequestBody() SampleArticleEntity articleEntity) {
        log.info("Control inside updateOne({}, {})", id, articleEntity.toString());
        var existingArticle = repository.existsById(id);
        return existingArticle.flatMap(exists -> {
            if (exists) {
                articleEntity.setId(id);
                return repository.save(articleEntity);
            }
            return Mono.error(new RuntimeException("Could not find article by id: " + id));
        });
    }
}

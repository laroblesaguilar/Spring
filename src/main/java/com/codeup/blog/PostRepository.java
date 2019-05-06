package com.codeup.blog;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PostRepository extends CrudRepository<Post, Long> {
    List<Post>findByTitle(String searchFor);
    List<Post>findByBody(String searchFor);
}

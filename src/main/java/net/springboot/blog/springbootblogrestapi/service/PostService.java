package net.springboot.blog.springbootblogrestapi.service;

import net.springboot.blog.springbootblogrestapi.payload.PostDto;
import net.springboot.blog.springbootblogrestapi.payload.PostResponse;

import java.util.List;

public interface PostService {
    PostDto createPost(PostDto postDto);

    PostResponse getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir);

    PostDto getPostById(long id);

    PostDto updatePost(PostDto postDto, long id);

    void deletePostById(long id);
}

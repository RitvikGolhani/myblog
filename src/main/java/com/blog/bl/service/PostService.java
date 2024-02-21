package com.blog.bl.service;

import com.blog.bl.payload.PostDto;

import java.util.List;


public interface PostService {
    public PostDto createPost(PostDto postDto);

    public void deletePost(long id);

    List<PostDto> getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir);

    PostDto updatePost(long postId, PostDto postDto);
}

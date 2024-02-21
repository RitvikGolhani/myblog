package com.blog.bl.service.impl;

import com.blog.bl.entity.Post;
import com.blog.bl.exception.ResourceNotFoundException;
import com.blog.bl.payload.PostDto;
import com.blog.bl.repository.PostRepository;
import com.blog.bl.service.PostService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {
    public PostServiceImpl(PostRepository postRepo) {
        this.postRepo = postRepo;
    }

    private PostRepository postRepo;
    @Override
    public PostDto createPost(PostDto postDto) {
        Post post=new Post();
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());
        Post savdPost=postRepo.save(post);

        PostDto dto=new PostDto();
        dto.setId(savdPost.getId());
        dto.setTitle(savdPost.getTitle());
        dto.setContent(savdPost.getContent());
        dto.setDescription(savdPost.getDescription());
//        dto.setMessage("Post is created");
        return dto;
    }

    @Override
    public void deletePost(long id) {

         postRepo.findById(id).orElseThrow(
                 ()->new ResourceNotFoundException("Post not found with id : "+id)
         );
        postRepo.deleteById(id);
    }

    @Override
    public List<PostDto> getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir) {
       Sort sort= sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo,pageSize,sort);
        Page<Post> pagePosts = postRepo.findAll(pageable);
        List<Post> posts = pagePosts.getContent();
        List<PostDto> dtos = posts.stream().map(p -> mapToDto(p)).collect(Collectors.toList());
        return dtos;
    }

    @Override
    public PostDto updatePost(long postId, PostDto postDto) {
        Post post = postRepo.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException("Post Not Found with this : " + postId)
        );
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setDescription(postDto.getDescription());
        postRepo.save(post);
        PostDto postDto1 = mapToDto(post);
        return postDto1;
    }

    PostDto mapToDto(Post post){
        PostDto dto=new PostDto();
        dto.setId(post.getId());
        dto.setTitle(post.getTitle());
        dto.setContent(post.getContent());
        dto.setDescription(post.getDescription());
        return  dto;
    }

}

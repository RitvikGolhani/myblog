package com.blog.bl.repository;

import com.blog.bl.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository  extends JpaRepository<Post,Long> {
}

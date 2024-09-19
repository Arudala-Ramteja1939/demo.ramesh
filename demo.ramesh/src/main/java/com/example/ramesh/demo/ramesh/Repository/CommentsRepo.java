package com.example.ramesh.demo.ramesh.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ramesh.demo.ramesh.Entity.Comments;

public interface CommentsRepo extends JpaRepository<Comments,Long>{

	List<Comments> findByPostId(Long post_id);
}

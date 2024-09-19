package com.example.ramesh.demo.ramesh.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ramesh.demo.ramesh.Entity.Post;

@Repository
public interface PostRepo extends JpaRepository<Post,Long> {

	public List<Post> findByCategoryId(Long category_id);

}

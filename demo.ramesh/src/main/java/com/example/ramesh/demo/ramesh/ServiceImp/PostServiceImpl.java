package com.example.ramesh.demo.ramesh.ServiceImp;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.example.ramesh.demo.ramesh.DTOs.PostDto;
import com.example.ramesh.demo.ramesh.DTOs.PostResponse;
import com.example.ramesh.demo.ramesh.Entity.Category;
import com.example.ramesh.demo.ramesh.Entity.Post;
import com.example.ramesh.demo.ramesh.Exceptions.BlogApiException;
import com.example.ramesh.demo.ramesh.Exceptions.ResourceNotFoundException;
import com.example.ramesh.demo.ramesh.Repository.CategoryRepo;
import com.example.ramesh.demo.ramesh.Repository.PostRepo;
import com.example.ramesh.demo.ramesh.Service.PostService;
@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private PostRepo repo;
	@Autowired
	private CategoryRepo categoryRepo1;
	@Autowired
	private ModelMapper mapper;

	@Autowired
	private CategoryRepo categoryRepo;

	@Override
	public PostDto createPost(PostDto postDto) {

		Category category=categoryRepo.findById(postDto.getCategoryId())
				.orElseThrow(() -> new ResourceNotFoundException("category", "id", postDto.getCategoryId()));

		Post post=this.mapToEntity(postDto);
		post.setCategory(category);
		repo.save(post);
		PostDto postDto1=this.mapToDto(post);

		return postDto1;
	}

	@Override
	public List<PostDto> getAllPosts() {

		List<Post> post=repo.findAll();

		//     	post.stream().map(x ->x.setComments(crepo.findByPostId(x.getId()))).collect(Collectors.toList()));
		//		
		List<PostDto> r=post.stream().map(this::mapToDto).collect(Collectors.toList());

		return r;
	}



	private PostDto mapToDto(Post post)
	{

		PostDto postDto=mapper.map(post,PostDto.class);
		//postDto.setComments(null);
		//		postDto.setId(post.getId());
		//		postDto.setTitle(post.getTitle());
		//		postDto.setDescription(post.getDescription());
		//		postDto.setContent(post.getContent());
		//		postDto.setComments(post.getComments());
		return postDto;
	}

	private Post mapToEntity(PostDto postDto)
	{
		//		List<Comments> comments1=crepo.findAll();
		//		Set<Comments> comments=new HashSet<Comments>(comments1);
		//		comments.stream().filter(x ->postDto.getId()==x.getPost().getId()).collect(Collectors.toSet());
		Post post=mapper.map(postDto, Post.class);


		//		post.setTitle(postDto.getTitle());
		//		post.setDescription(postDto.getDescription());
		//		post.setContent(postDto.getContent());
		//		post.setComments(comments);

		return post;
	}

	@Override
	public PostDto update(Long id,PostDto postDto) {
		// TODO Auto-generated method stub
		Post post=repo.findById(id).orElseThrow(() -> new BlogApiException(HttpStatus.BAD_REQUEST, "id is not found"));
		Category category=categoryRepo.findById(postDto.getCategoryId())
				.orElseThrow(() -> new ResourceNotFoundException("category", "id", postDto.getCategoryId()));
		if(post!=null)
		{
			post.setTitle(postDto.getTitle());
			post.setDescription(postDto.getDescription());
			post.setContent(postDto.getContent());
			post.setCategory(category);

			repo.save(post);
			PostDto posts=this.mapToDto(post);

			return posts;
		}
		else
		{
			return null;
		}

	}

	@Override
	public PostDto getById(Long id) {
		Post post=repo.findById(id).orElseThrow(()->new ResourceNotFoundException("post","id",id));
		if(post!=null)
		{
			PostDto postDto=this.mapToDto(post);
			return postDto;
		}
		else
		{
			return null;
		}

	}

	@Override
	public String delete(Long id) {
		Post post=repo.findById(id).orElseThrow(() -> new BlogApiException(HttpStatus.BAD_REQUEST, "id is not found"));
		if(post!=null)
		{
			repo.delete(post);
		}
		return "Successfully deleted the post";
	}

	@Override
	public PostResponse getPostsPage(int page, int size,String sortBy,String sortDir)
	{
		Sort sort=sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())?Sort.by(sortBy).ascending()
				:Sort.by(sortBy).descending();

		Pageable pageable=PageRequest.of(page,size,sort);
		Page<Post> posts=repo.findAll(pageable);
		List<Post> e=posts.getContent();
		List<PostDto> d=e.stream().map(this::mapToDto).collect(Collectors.toList());

		PostResponse postResponse=new PostResponse();
		postResponse.setContent(d);
		postResponse.setPageNo(posts.getNumber());
		postResponse.setPageSize(posts.getSize());
		postResponse.setTotalElements(posts.getTotalElements());
		postResponse.setLast(posts.isLast());

		return postResponse;
	}

	@Override
	public List<Post> getPostByCategory(Long category_id) {
		Category category=categoryRepo1.findById(category_id)
				.orElseThrow(() -> new ResourceNotFoundException("category", "id", category_id));

		if(category!=null)
		{
			List<Post> posts=repo.findByCategoryId(category_id);
			return posts;
		}
		else
		{
			return null;
		}


	}

}

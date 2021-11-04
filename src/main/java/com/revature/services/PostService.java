package com.revature.services;

import com.revature.models.Post;
import com.revature.models.Profile;
import com.revature.models.User;
import com.revature.repositories.CommentRepository;
import com.revature.repositories.PostRepository;
import com.revature.repositories.ProfileRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {
	private final PostRepository postRepository;
	private final CommentRepository commentRepository;
	private final ProfileRepository profileRepository;

	@Autowired
	public PostService(PostRepository postRepository, CommentRepository commentRepository,
			ProfileRepository profileRepository) {
		this.postRepository = postRepository;
		this.commentRepository = commentRepository;
		this.profileRepository = profileRepository;

	}

	public List<Post> getPosts() {
		return postRepository.findAll();
	}

	public Post addNewPost(Post post, User user)
    {
        post.setAuthor(user);
<<<<<<< HEAD
        postRepository.saveAndFlush(post);
=======
        Profile profile = profileRepository.getProfileByUser(user);
        post.setProfile(profile);
        postRepository.save(post);
>>>>>>> 7499f38571af0de55e0f26925fd88ec4dba6c861
        return post;
    }

	public void deletePost(Post post) {
		Optional<Post> temp = postRepository.findById(post.getId());

		if (temp.isPresent()) {
			for (int i = 0; i < temp.get().getComments().size(); i++) {
				commentRepository.deleteById(temp.get().getComments().get(i).getId());
			}

			postRepository.deleteById(temp.get().getId());
			System.out.println("post deleted");
		} else
			throw new IllegalStateException("post does not exist");
	}

	public List<Post> getUserPosts(String authorUID) {
		List<Post> ret = new ArrayList<Post>();
		for (Post p : postRepository.findAll()) {
			if (p.getAuthor().getUid().equals(authorUID)) {
				ret.add(p);
			}
		}
		return ret;
	}
}

package com.ssinsaram2.book.springboot.service.posts;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssinsaram2.book.springboot.domain.posts.Posts;
import com.ssinsaram2.book.springboot.domain.posts.PostsRepository;
import com.ssinsaram2.book.springboot.web.dto.PostsResponseDto;
import com.ssinsaram2.book.springboot.web.dto.PostsSaveRequestDto;
import com.ssinsaram2.book.springboot.web.dto.PostsUpdateRequestDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PostsService {
	private final PostsRepository postsRepository;

	@Transactional
	public Long save(PostsSaveRequestDto requestDto) {
		return postsRepository.save(requestDto.toEntity()).getId();
	}

	@Transactional
	public Long update(Long id, PostsUpdateRequestDto requestDto) {
		Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

		posts.update(requestDto.getTitle(), requestDto.getContent());

		return id;
	}

	public PostsResponseDto findById(Long id) {
		Posts entity = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

		return new PostsResponseDto(entity);
	}
}

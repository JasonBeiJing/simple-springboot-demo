package com.springboot.demo.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.demo.dao.TagRepository;
import com.springboot.demo.entity.Tag;
import com.springboot.demo.exception.EntityNotFoundException;

@Service
public class TagService {

	@Autowired
	private TagRepository tagRepository;

	public Tag createTag(Tag tag) {
		return tagRepository.save(tag);
	}

	public Tag getTagById(String id) throws EntityNotFoundException {
		Optional<Tag> tag = tagRepository.findById(id);
		if(!tag.isPresent()) {
			throw new EntityNotFoundException(EntityNotFoundException.ERROR_CODE.TAG_NOT_FOUND, id);
		}
		return tag.get();
	}
	
	public Tag getTagByKey(String key) throws EntityNotFoundException {
		Tag tag = tagRepository.findByKey(key);
		if(Objects.isNull(tag)) {
			throw new EntityNotFoundException(EntityNotFoundException.ERROR_CODE.TAG_NOT_FOUND, key);
		}
		return tag;
	}
	
	public List<Tag> listTags(List<String> keys, List<String> types, String name){
		return tagRepository.findAllByArguments(keys, types, name);
	}
}

package com.springboot.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.demo.controller.namespace.ApiNamespace;
import com.springboot.demo.entity.Tag;
import com.springboot.demo.exception.EntityNotFoundException;
import com.springboot.demo.service.TagService;

import io.swagger.annotations.Api;

@RestController
@RequestMapping(value = ApiNamespace.URI_TAGS, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(value = "标签APIs", tags= {"B"})
public class TagController {
	
	@Autowired
	private TagService tagService;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Tag create(@RequestBody Tag tag) {
		tag.setId(null);
		return tagService.createTag(tag);
	}
	
	@GetMapping
	public List<Tag> list(){
		return tagService.listTags(null, null, null);
	}

	@GetMapping("/{id}")
	public Tag get(@PathVariable String id) throws EntityNotFoundException {
		return tagService.getTagById(id);
	}
}

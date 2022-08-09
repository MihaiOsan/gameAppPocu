package com.endava.pocu.gamesApp.services;

import com.endava.pocu.gamesApp.models.Game;
import com.endava.pocu.gamesApp.models.Tag;
import com.endava.pocu.gamesApp.repositories.TagRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TagService {

    @Autowired
    private TagRepository tagRepository;

    public Tag saveTag(final Tag tag){
        return tagRepository.saveAndFlush(tag);
    }

    public List<Tag> findAllTags(){
        final List<Tag> tags = new ArrayList<>();
        tagRepository.findAll().forEach(tag -> tags.add(tag));
        return tags;
    }

    public Tag findTagById (final Long id){
        Optional<Tag> tag = tagRepository.findById(id);
        if (tag.isPresent())
            return tag.get();
        else
            throw new RuntimeException("Could not find a tag with id: " + id);
    }

    public Tag updateTag(Long id,final Tag tag){
        Tag existingTag = tagRepository.getReferenceById(id);
        BeanUtils.copyProperties(tag, existingTag, "tagId");
        return tagRepository.saveAndFlush(existingTag);
    }

    public void deleteTag(final Long id){
        tagRepository.deleteById(id);
    }
}

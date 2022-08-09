package com.endava.pocu.gamesApp.services;

import com.endava.pocu.gamesApp.exceptions.InvalidRequestException;
import com.endava.pocu.gamesApp.exceptions.RecordNotFoundException;
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
            throw new RecordNotFoundException("Tag with id "+id+" does not exist");
    }

    public Tag updateTag(Long id,final Tag tag){
        if (id == null || tag == null)
            throw new InvalidRequestException("Tag and id must not be null");
        Optional<Tag> optionalExistingTag = tagRepository.findById(id);
        if (optionalExistingTag.isEmpty())
            throw new RecordNotFoundException("Tag with id "+id+" does not exist");
        Tag existingTag = optionalExistingTag.get();
        BeanUtils.copyProperties(tag, existingTag, "tagId");
        return tagRepository.saveAndFlush(existingTag);
    }

    public void deleteTag(final Long id){
        if (id == null)
            throw new InvalidRequestException("Id must not be null");
        Optional<Tag> optionalExistingTag = tagRepository.findById(id);
        if (optionalExistingTag.isEmpty())
            throw new RecordNotFoundException("Tag with id "+id+" does not exist");
        tagRepository.deleteById(id);
    }
}

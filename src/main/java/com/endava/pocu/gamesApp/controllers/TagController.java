package com.endava.pocu.gamesApp.controllers;

import com.endava.pocu.gamesApp.models.Game;
import com.endava.pocu.gamesApp.models.Tag;
import com.endava.pocu.gamesApp.services.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/tags")
public class TagController {

    @Autowired
    private TagService tagService;

    // as a user I want to be able to get all tags
    @GetMapping
    public List<Tag> getTags(){return  tagService.findAllTags();}

    // as a user I want to be able to get a tag with a specific id
    @GetMapping(value = "{id}")
    public Tag getTagById(@PathVariable Long id){return tagService.findTagById(id);}

    // as a user I want to be able to create new tags
    @PostMapping
    public Tag create(@RequestBody final Tag tag){
        return tagService.saveTag(tag);
    }

    // as a user I want to be able to update a tag with a specified id
    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public void update(@PathVariable Long id, @RequestBody Tag tag){
        tagService.updateTag(id,tag);
    }

    // as a user I want to be able to delete a tag by id
    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id){
        tagService.deleteTag(id);
    }
}

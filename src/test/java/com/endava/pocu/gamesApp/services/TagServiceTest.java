package com.endava.pocu.gamesApp.services;

import com.endava.pocu.gamesApp.exceptions.RecordNotFoundException;
import com.endava.pocu.gamesApp.models.Game;
import com.endava.pocu.gamesApp.models.Tag;
import com.endava.pocu.gamesApp.repositories.TagRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TagServiceTest {

    @Autowired
    TagRepository tagRepository;

    @Autowired
    TagService tagService;

    private static final Long TAG_ID = 1000l;
    private static final String TAG_TITLE = "tagTitle";
    private static final String TAG_DESCRIPTION = "tagDescription";
    private static final List<Game> GAMES = new ArrayList<>();

    private static final Tag TAG = new Tag();

    @BeforeAll
    static void setupTag(){
        TAG.setTagId(TAG_ID);
        TAG.setTagDescription(TAG_DESCRIPTION);
        TAG.setTagTitle(TAG_TITLE);
        TAG.setGames(GAMES);
    }

    @Test
    @Order(1)
    void saveTagShouldIncreaseNumberOfElementsInDB() {
        int beforeSize = tagRepository.findAll().size();

        tagService.saveTag(TAG);

        int afterSize = tagRepository.findAll().size();
        assertThat(beforeSize).isEqualTo(afterSize-1);
    }

    @Test
    @Order(2)
    void saveTagSuccess() {

        tagService.saveTag(TAG);

        Tag tag = tagRepository.findById(1l).get();
        assertThat(tag.getTagTitle()).isEqualTo(TAG.getTagTitle());
        assertThat(tag.getTagDescription()).isEqualTo(TAG.getTagDescription());
    }

    @Test
    @Order(3)
    void updateTagShouldNotIncreaseNumberOfElementsInDB() {
        tagService.saveTag(TAG);
        int beforeSize = tagRepository.findAll().size();
        Tag newTag = new Tag();
        BeanUtils.copyProperties(newTag, TAG, "tagId");

        tagService.updateTag(1l, newTag);

        int afterSize = tagRepository.findAll().size();
        assertThat(beforeSize).isEqualTo(afterSize);
    }

    @Test
    @Order(4)
    void updateTagSuccess() {
        tagService.saveTag(TAG);
        Tag newTag = new Tag();
        BeanUtils.copyProperties( TAG, newTag,"tagId");
        newTag.setTagDescription("CHANGED_DESCRIPTION");

        tagService.updateTag(1l, newTag);

        Tag updatedTag = tagRepository.findById(1l).get();
        assertThat(updatedTag.getTagDescription()).isEqualTo("CHANGED_DESCRIPTION");

    }

    @Test
    @Order(5)
    void deleteTagShouldDecreaseNumberOfElementsInDB() {
        tagService.saveTag(TAG);
        int beforeSize = tagRepository.findAll().size();

        tagService.deleteTag(1l);

        int afterSize = tagRepository.findAll().size();
        assertThat(beforeSize-1).isEqualTo(afterSize);
    }

    @Test
    @Order(6)
    void deleteTagSuccess() {
        tagService.saveTag(TAG);

        tagService.deleteTag(2l);

        assertThatThrownBy(()-> tagService.findTagById(1l))
                .isInstanceOf(RecordNotFoundException.class);
    }
}

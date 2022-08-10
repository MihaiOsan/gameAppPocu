package com.endava.pocu.gamesApp.services;

import com.endava.pocu.gamesApp.exceptions.InvalidRequestException;
import com.endava.pocu.gamesApp.exceptions.RecordNotFoundException;
import com.endava.pocu.gamesApp.models.Game;
import com.endava.pocu.gamesApp.models.Tag;
import com.endava.pocu.gamesApp.repositories.TagRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TagServiceTest {

    private static final Long TAG_ID = 1000l;
    private static final String TAG_TITLE = "tagTitle";
    private static final String TAG_DESCRIPION = "tagDescription";
    private static final List<Game> GAMES = new ArrayList<>();

    private static final Tag TAG = new Tag();

    @BeforeAll
    static void setupTag(){
        TAG.setTagId(TAG_ID);
        TAG.setTagDescription(TAG_DESCRIPION);
        TAG.setTagTitle(TAG_TITLE);
        TAG.setGames(GAMES);
    }

    @Mock
    TagRepository tagRepository;

    @InjectMocks
    TagService tagService;


    @Test
    void saveTagShouldThrowInvalidRequestExceptionIfTagIsNull() {

        assertThatThrownBy(()-> tagService.saveTag(null))
                .isInstanceOf(InvalidRequestException.class)
                .hasMessage("Tag must not be null");
    }

    @Test
    void findAllTags() {

        List<Tag> tags = tagService.findAllTags();
        assertThat(tags.size()).isGreaterThanOrEqualTo(0);
    }
    @Test
    void findTagByIdShouldThrowRecordNotFoundWhenTagIsNotInDb(){
        when(tagRepository.findById(TAG_ID)).thenReturn(Optional.empty());

        assertThatThrownBy(()-> tagService.findTagById(TAG_ID))
                .isInstanceOf(RecordNotFoundException.class)
                .hasMessage("Tag with id "+TAG_ID+" does not exist");
    }

    @Test
    void updateTagShouldThrowRecordNotFoundWhenTagIsNotInDb() {
        when(tagRepository.findById(TAG_ID)).thenReturn(Optional.empty());

        assertThatThrownBy(()-> tagService.updateTag(TAG_ID, TAG))
                .isInstanceOf(RecordNotFoundException.class)
                .hasMessage("Tag with id "+TAG_ID+" does not exist");
    }

    @Test
    void updateTagShouldThrowInvalidRequestExceptionWhenTagOrIdIsNull() {

        assertThatThrownBy(()-> tagService.updateTag(null ,null))
                .isInstanceOf(InvalidRequestException.class)
                .hasMessage("Tag and id must not be null");
    }

    @Test
    void deleteTagShouldThrowRecordNotFoundWhenTagIsNotInDb() {
        when(tagRepository.findById(TAG_ID)).thenReturn(Optional.empty());

        assertThatThrownBy(()-> tagService.findTagById(TAG_ID))
                .isInstanceOf(RecordNotFoundException.class)
                .hasMessage("Tag with id "+TAG_ID+" does not exist");
    }

    @Test
    void deleteShouldThrowInvalidRequestExceptionWhenTagOrIdIsNull() {

        assertThatThrownBy(()-> tagService.deleteTag(null))
                .isInstanceOf(InvalidRequestException.class)
                .hasMessage("Id must not be null");
    }
}
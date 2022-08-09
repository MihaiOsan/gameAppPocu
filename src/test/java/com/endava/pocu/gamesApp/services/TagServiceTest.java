package com.endava.pocu.gamesApp.services;

import com.endava.pocu.gamesApp.exceptions.InvalidRequestException;
import com.endava.pocu.gamesApp.exceptions.RecordNotFoundException;
import com.endava.pocu.gamesApp.models.Game;
import com.endava.pocu.gamesApp.models.Tag;
import com.endava.pocu.gamesApp.repositories.TagRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

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


    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;

    @Mock
    TagRepository tagRepository;

    @InjectMocks
    TagService tagService;

    @Test
    void saveTag() {
    }

    @Test
    void findAllTags() {
    }

    @Test
    void findTagByIdShouldThrowRecordNotFoundWhenTagIsNotInDb(){
        when(tagRepository.findById(TAG_ID)).thenReturn(Optional.empty());

        assertThatThrownBy(()-> tagService.findTagById(TAG_ID))
                .isInstanceOf(RecordNotFoundException.class)
                .hasMessage("Tag with id "+TAG_ID+" does not exist");
    }

    @Test
    void updateTag() {
    }

    @Test
    void deleteTag() {
    }
}
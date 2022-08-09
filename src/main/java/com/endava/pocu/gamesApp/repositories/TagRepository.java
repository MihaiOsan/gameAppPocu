package com.endava.pocu.gamesApp.repositories;

import com.endava.pocu.gamesApp.models.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {
}

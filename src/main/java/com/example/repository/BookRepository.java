package com.example.repository;
import com.example.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
public interface BookRepository extends JpaRepository<BookEntity, Long>{

    List<BookEntity> findAllByActiveIsTrueOrderByIdBookDesc();

    boolean existsByActiveIsTrueAndTitleIgnoreCase(String title);

    Optional<BookEntity> findFirstByActiveIsTrueAndIdBook (Long id);

    boolean existsByActiveIsTrueAndIdBookNotAndTitleIgnoreCase(Long id, String title);
}

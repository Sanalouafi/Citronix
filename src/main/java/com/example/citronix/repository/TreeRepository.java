package com.example.citronix.repository;

import com.example.citronix.entity.Tree;
import com.example.citronix.entity.Field;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TreeRepository extends JpaRepository<Tree, Long> {

    List<Tree> findByField(Field field);
}

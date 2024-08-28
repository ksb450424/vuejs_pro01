package com.company.app1.per;

import com.company.app1.entity.Qna;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QnaRepository extends JpaRepository<Qna, Integer> {
}
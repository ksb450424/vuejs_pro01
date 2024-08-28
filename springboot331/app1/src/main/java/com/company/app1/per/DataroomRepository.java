package com.company.app1.per;

import com.company.app1.entity.Dataroom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DataroomRepository extends JpaRepository<Dataroom, Integer> {
}
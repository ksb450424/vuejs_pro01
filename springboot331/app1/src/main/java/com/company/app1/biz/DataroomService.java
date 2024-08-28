package com.company.app1.biz;

import com.company.app1.entity.Dataroom;
import com.company.app1.per.DataroomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DataroomService {

    @Autowired
    private DataroomRepository dataroomRepository;

    public List<Dataroom> getAllDatarooms() {
        return dataroomRepository.findAll();
    }

    public Optional<Dataroom> getDataroomById(int dno) {
        return dataroomRepository.findById(dno);
    }

    public Dataroom saveDataroom(Dataroom dataroom) {
        return dataroomRepository.save(dataroom);
    }

    public void deleteDataroom(int dno) {
        dataroomRepository.deleteById(dno);
    }

    public Dataroom updateDataroom(int dno, Dataroom updatedDataroom) {
        return dataroomRepository.findById(dno)
                .map(dataroom -> {
                    dataroom.setTitle(updatedDataroom.getTitle());
                    dataroom.setContent(updatedDataroom.getContent());
                    dataroom.setAuthor(updatedDataroom.getAuthor());
                    dataroom.setDatafile(updatedDataroom.getDatafile());
                    dataroom.setHits(updatedDataroom.getHits());
                    return dataroomRepository.save(dataroom);
                })
                .orElseGet(() -> {
                    updatedDataroom.setDno(dno);
                    return dataroomRepository.save(updatedDataroom);
                });
    }
}
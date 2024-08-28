package com.company.app1.biz;

import com.company.app1.entity.Qna;
import com.company.app1.per.QnaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QnaService {

    @Autowired
    private QnaRepository qnaRepository;

    public List<Qna> getAllQnas() {
        return qnaRepository.findAll();
    }

    public Optional<Qna> getQnaById(int qno) {
        return qnaRepository.findById(qno);
    }

    public Qna saveQna(Qna qna) {
        return qnaRepository.save(qna);
    }

    public void deleteQna(int qno) {
        qnaRepository.deleteById(qno);
    }

    public Qna updateQna(int qno, Qna updatedQna) {
        return qnaRepository.findById(qno)
                .map(qna -> {
                    qna.setLev(updatedQna.getLev());
                    qna.setParno(updatedQna.getParno());
                    qna.setTitle(updatedQna.getTitle());
                    qna.setContent(updatedQna.getContent());
                    qna.setAuthor(updatedQna.getAuthor());
                    qna.setHits(updatedQna.getHits());
                    return qnaRepository.save(qna);
                })
                .orElseGet(() -> {
                    updatedQna.setQno(qno);
                    return qnaRepository.save(updatedQna);
                });
    }
}
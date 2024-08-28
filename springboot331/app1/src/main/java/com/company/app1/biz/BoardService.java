package com.company.app1.biz;

import com.company.app1.entity.Board;
import com.company.app1.per.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;

    public List<Board> getAllBoards() {
        return boardRepository.findAll();
    }

    public Optional<Board> getBoardById(int no) {
        return boardRepository.findById(no);
    }

    public Board saveBoard(Board board) {
        return boardRepository.save(board);
    }

    public void deleteBoard(int no) {
        boardRepository.deleteById(no);
    }

    public Board updateBoard(int no, Board updatedBoard) {
        return boardRepository.findById(no)
                .map(board -> {
                    board.setTitle(updatedBoard.getTitle());
                    board.setContent(updatedBoard.getContent());
                    board.setAuthor(updatedBoard.getAuthor());
                    board.setHits(updatedBoard.getHits());
                    return boardRepository.save(board);
                })
                .orElseGet(() -> {
                    updatedBoard.setNo(no);
                    return boardRepository.save(updatedBoard);
                });
    }
}
package com.company.app1.ctrl;

import com.company.app1.biz.BoardService;
import com.company.app1.entity.Board;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/boards")
public class BoardController {

    @Autowired
    private BoardService boardService;

    @GetMapping("/list")
    public List<Board> getAllBoards() {
        return boardService.getAllBoards();
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<Board> getBoardById(@PathVariable int id) {
        Optional<Board> board = boardService.getBoardById(id);
        return board.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/insert")
    public Board createBoard(@RequestBody Board board) {
        return boardService.saveBoard(board);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Board> updateBoard(@PathVariable int id, @RequestBody Board board) {
        Board updatedBoard = boardService.updateBoard(id, board);
        return ResponseEntity.ok(updatedBoard);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteBoard(@PathVariable int id) {
        boardService.deleteBoard(id);
        return ResponseEntity.noContent().build();
    }
}
package example.board.service;

import example.board.domain.Board;
import example.board.repository.BoardRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BoardServiceTest {

    @Autowired
    private BoardService boardService;
    @Autowired
    private BoardRepository boardRepository;


    @Test
    void 게시판등록() {
        Board board432 = new Board();
        board432.setId(1L);
        board432.setTitle("제목");
        board432.setContent("내용");

        Long register = boardService.register(board432.getId(), board432.getTitle(), board432.getContent());

        Board board = boardRepository.findById(register).orElseThrow();

        Assertions.assertThat(1L).isEqualTo(board.getId());
        Assertions.assertThat("제목").isEqualTo(board.getTitle());
        Assertions.assertThat("내용").isEqualTo(board.getContent());

    }

    @Test
    void findAll() {

        Board board = new Board();
        Board board1 = new Board();

        board.setId(1L);
        board.setTitle("title");
        board.setContent("content");

        board1.setId(1L);
        board1.setTitle("title");
        board1.setContent("content");

        //when
        Long register = boardService.register(board.getId(), board.getTitle(), board.getContent());
        Long register1 = boardService.register(board1.getId(), board1.getTitle(), board1.getContent());

        List<Board> boards = boardService.findAll();

        //then
        Assertions.assertThat(boards.size()).isEqualTo(2);

    }


}
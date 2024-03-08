package example.board.service;

import example.board.domain.Board;
import example.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    /**
     * 게시글 생성
     */
    @Transactional
    public Long register(Long id, String title, String content) {
        Board board = Board.createBoard(title, content);
        boardRepository.save(board);

        return board.getId();
    }

    public Optional<Board> findOne(Long id) {
        return boardRepository.findById(id);
    }

    public List<Board> findAll() {
        return boardRepository.findAll();
    }


    /**
     * 게시글 수정
     */
    @Transactional
    public void updateBoard(Long id, String title, String content) {
        Board board = boardRepository.findById(id).orElseThrow(()
                -> new IllegalArgumentException("존재하지 않습니다.")
        );
        board.update(title, content);
    }

    /**
     * 게시글 삭제
     */
    @Transactional
    public void deleteById(Long id) {
        boardRepository.deleteById(id);
    }
}

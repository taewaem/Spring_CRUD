package example.board.controller;

import example.board.domain.Board;
import example.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;

    @GetMapping
    public String boardList(Model model) {
        List<Board> boards = boardService.findAll();
        model.addAttribute("boards", boards);

        return "board/boardList";
    }

    @GetMapping("/{boardId}")
    public String board(@PathVariable("boardId") Long boardId, Model model) {
        Board board = boardService.findOne(boardId).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않음")
        );

        model.addAttribute("board", board);

        return "board/board";
    }

    @GetMapping("/register")
    public String registerForm(@ModelAttribute("board") Board board) {
        return "board/registerForm";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute("board") Board board, RedirectAttributes redirectAttributes) {
        Long savedBoard = boardService.register(board.getId(), board.getTitle(),board.getContent());

        return "redirect:/board";
    }


    @GetMapping("/{boardId}/edit")
    public String editForm(@PathVariable("boardId") Long boardId, Model model) {

        Board board = boardService.findOne(boardId).orElseThrow();

        model.addAttribute("board", board);

        return "board/editForm";
    }

    @PostMapping("/{boardId}/edit")
    public String edit(@PathVariable("boardId") Long boardId, @ModelAttribute("board") Board board) {
        boardService.updateBoard(boardId, board.getTitle(), board.getContent());

        return "redirect:/board/{boardId}";
    }


    @PostMapping("/{boardId}/delete")
    public String delete(@PathVariable("boardId") Long boardId) {
        boardService.deleteById(boardId);

        return "redirect:/board";
      }

}
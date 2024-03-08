package example.board.domain;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Board {

    @Id         //해당 변수를 ID값으로 선언
    @GeneratedValue(strategy = GenerationType.IDENTITY) //기본키 지정, DB를 식별하는 요소
    private Long id;

    private String title;

    private String content;


    //==생성 메서드==//
    public static Board createBoard(String title, String content) {
        Board board = new Board();
        board.setTitle(title);
        board.setContent(content);

        return board;
    }

    //==비즈니스 로직==//
    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }

}

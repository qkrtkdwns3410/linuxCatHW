package linux.java.numberBaseball;

import java.util.Optional;
import java.util.function.Consumer;

import static linux.java.numberBaseball.Message.*;

/**
 * packageName    : linux.java.numberBaseball
 * fileName       : ResultPrinter
 * author         : ipeac
 * date           : 2023-06-07
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-06-07        ipeac       최초 생성
 */
public class ResultPrinter {
    private final Score score;
    private static final int MAX_THREE_STRIKE = 3;
    
    public ResultPrinter(Score score) {
        this.score = score;
    }
    
    public boolean print(Consumer<String> printer) {
        boolean reGame = false;
        String message = "";
        
        String strikeMessage = Optional.of(score.getStrikeCount())
                                       .filter(strikeCount -> strikeCount > 0)
                                       .map(STRIKE_MESSAGE::getMessage)
                                       .orElse(" ");
        String ballMessage = Optional.of(score.getBallCount())
                                     .filter(ballCount -> ballCount > 0)
                                     .map(BALL_MESSAGE::getMessage)
                                     .orElse(" ");
        
        if (score.getStrikeCount() == MAX_THREE_STRIKE) {
            message += ALL_MATCH.getMessage();
            message += RE_GAME.getMessage();
            reGame = true;
        } else if (score.getStrikeCount() == 0 && score.getBallCount() == 0) {
            message += NOT_MATCH.getMessage();
        } else {
            message += strikeMessage + ballMessage;
        }
        printer.accept(message);
        
        return reGame;
    }
}
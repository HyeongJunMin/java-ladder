package ladder;

import ladder.domain.Ladder;
import ladder.domain.Players;
import ladder.view.InputView;
import ladder.view.ResultView;

public class ConsoleMain {
    public static void main(String[] args) {
        String names = InputView.inputTryLine("참여할 사람 이름을 입력하세요. (이름은 쉼표(,)로 구분하세요)");
        int height = InputView.inputTryNo("최대 사다리 높이는 몇 개인가요?");

        Players players = Players.of(names);
        Ladder ladder = Ladder.of(height, players.getCountOfPlayer());

        ResultView.printResult(players, ladder);
    }
}
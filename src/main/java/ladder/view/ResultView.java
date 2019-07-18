package ladder.view;

import ladder.domain.GameResult;
import ladder.domain.GameReward;
import ladder.domain.LadderLine;
import ladder.domain.SingleUser;

import java.util.Comparator;
import java.util.List;
import java.util.stream.IntStream;

public class ResultView {
    private final static String LADDER_HORIZON_LINE = "-----";
    private final static String LADDER_VERTICAL_LINE = "|";
    private final static String LADDER_BLANK = "     ";
    private final static String USER_SEPARATOR = "   ";

    public static void printLadder(List<LadderLine> ladder, List<SingleUser> userGroup, GameResult result) {
        System.out.println("\n사다리 결과\n");
        // User 리스트 출력
        userGroup.stream()
                .map(SingleUser::getName)
                .forEach(name -> System.out.print(name + USER_SEPARATOR));

        System.out.println("\n");
        // 생성 사다리 출력
        ladder.forEach(ladderLine -> {
            IntStream.range(0, userGroup.size())
                    .forEach(j -> System.out.print(
                            ladderLine.getPoints().get(j).getDirection().isRight()
                                    ? (LADDER_VERTICAL_LINE + LADDER_HORIZON_LINE) : (LADDER_VERTICAL_LINE + LADDER_BLANK)));
            System.out.println("\n");
        });
        // 실행 결과 리스트 출력
        result.getGameReward().stream()
                .map(GameReward::getNameReward)
                .forEach(name -> System.out.print(name + USER_SEPARATOR));
        System.out.println("\n");
    }

    public static void showResult(String request, GameResult result) {
        System.out.println("\n실행 결과");

        if (request.equals("all")) {
            result.getGameReward().stream()
                    .sorted(Comparator.comparing(GameReward::getNameOfWinner))
                    .forEach(System.out::println);
        } else {
            result.getGameReward().stream()
                    .filter(i -> i.getNameOfWinner().equals(request))
                    .forEach(i -> System.out.println(i.getNameReward()));
        }
    }
}
package racingcar.view;

import java.util.List;
import racingcar.model.Car;
import racingcar.util.Messages;

public class RaceGameView {

    public void displayMessage(String message) {
        System.out.println(message);
    }

    public void printRoundResults(List<Car> cars) {
        cars.forEach(car -> System.out.println(car.getName() + " : " + "-".repeat(car.getPosition())));
        System.out.println();
    }

    public void printWinners(List<Car> winners) {
        String result = winners.stream()
            .map(Car::getName)
            .reduce((a, b) -> a + ", " + b)
            .orElse(Messages.NO_WINNERS);
        System.out.println(Messages.WINNER_ANNOUNCEMENT + result);
    }
}

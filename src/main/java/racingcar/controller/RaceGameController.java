package racingcar.controller;

import camp.nextstep.edu.missionutils.Console;
import java.util.List;
import java.util.stream.Collectors;
import racingcar.model.Car;
import racingcar.model.RaceGame;
import racingcar.util.Messages;
import racingcar.view.RaceGameView;

public class RaceGameController {
    private RaceGame model;
    private RaceGameView view;

    public RaceGameController() {
        this.view = new RaceGameView();
        initializeGame();
    }

    private void initializeGame() {
        String[] names = getValidNames();
        int rounds = getValidRounds();
        this.model = new RaceGame(names, rounds);
    }

    private String[] getValidNames() {
        view.displayMessage(Messages.INPUT_CAR_NAMES);
        String input = Console.readLine();
        return validateNames(input.split(","));
    }

    private int getValidRounds() {
        view.displayMessage(Messages.INPUT_NUMBER_OF_ROUNDS);
        return validateRounds(Console.readLine());
    }

    private String[] validateNames(String[] names) {
        for (String name : names) {
            if (name.length() > 5) {
                throw new IllegalArgumentException(Messages.ERROR_INVALID_NAME_LENGTH);
            }
        }
        return names;
    }

    private int validateRounds(String roundsInput) {
        int rounds = Integer.parseInt(roundsInput);
        if (rounds <= 0) {
            throw new IllegalArgumentException(Messages.ERROR_POSITIVE_NUMBER_REQUIRED);
        }
        return rounds;
    }

    public void startGame() {
        for (int i = 0; i < model.getRounds(); i++) {
            model.runRound();
            view.printRoundResults(model.getCars());
        }
        view.printWinners(findWinners());
    }

    private List<Car> findWinners() {
        int maxPosition = model.getMaxPosition();
        return model.getCars().stream()
            .filter(car -> car.getPosition() == maxPosition)
            .collect(Collectors.toList());
    }
}

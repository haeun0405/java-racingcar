package racingcar.model;

import java.util.ArrayList;
import java.util.List;

public class RaceGame {
    private List<Car> cars;
    private int rounds;

    public RaceGame(String[] names, int rounds) {
        cars = new ArrayList<>();
        for (String name : names) {
            cars.add(new Car(name));
        }
        this.rounds = rounds;
    }

    public List<Car> getCars() {
        return cars;
    }

    public int getRounds() {
        return rounds;
    }

    public int getMaxPosition() {
        return cars.stream()
            .mapToInt(Car::getPosition)
            .max()
            .orElse(0);
    }

    public void runRound() {
        cars.forEach(Car::advance);
    }
}

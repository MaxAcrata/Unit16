package ru.netologia.qamid;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {
    private Game game;

    // Метод для регистрации списка игроков
    private void registerPlayers(Player... players) {
        for (Player player : players) {
            game.register(player);
        }
    }

    @BeforeEach
    void setUp() {
        game = new Game();
    }

    // todo Проверяем, что метод корректно работает при двух зарегистрированных игроках.
    @Test
    void testTwoRegisteredPlayers() throws NotRegisteredException {
        Player alice = new Player(1, "Alice", 50);
        Player bob = new Player(2, "Bob", 70);

        // Регистрируем игроков
        registerPlayers(alice, bob);

        // Проверяем, что Боб выигрывает у Алисы, так как его сила больше
        assertEquals(2, game.round("Alice", "Bob"));


    }

    // todo Проверяем, что метод корректно если хотя бы один игрок отсутствует в списке .
    @Test
    void testOneRegisteredPlayer() {

        Player alice = new Player(1, "Alice", 50);

        registerPlayers(alice); // Регистрируем только Алису

        // Проверяем исключение для незарегистрированного игрока
        assertThrows(NotRegisteredException.class, () -> game.round("Alice", "Ivan"));
    }

    // todo Проверяем, что метод корректно работает при двух зарегистрированных игроках одинаковой силы.
    @Test
    void testRoundWithEqualStrength() throws NotRegisteredException {

        Player alice = new Player(1, "Alice", 50);
        Player eve = new Player(2, "Bob", 50);

        registerPlayers(alice, eve);

        // Проверяем, что результатом будет ничья, так как сила игроков равна
        assertEquals(0, game.round("Alice", "Bob"));
    }

    // todo Проверяем, что метод корректно работает при большой разнице в силе между игроками.
    @Test
    void testRoundWithLargeStrengthDifference() throws NotRegisteredException {
        Game game = new Game();
        Player player1 = new Player(1, "Alice", 10); // Сила 10
        Player player2 = new Player(2, "Bob", 100); // Сила 100

        // Регистрируем игроков
        game.register(player1);
        game.register(player2);

        // Проверяем, что Боб выигрывает у Алисы
        assertEquals(2, game.round("Alice", "Bob"));

        // Проверяем обратное соревнование (Алиса против Боба)
        assertEquals(2, game.round("Bob", "Alice")); // Боб выигрывает
    }

    // todo Проверяем, когда разница в силе между игроками минимальна.

    @Test
    void testRoundWithMinimalStrengthDifference() throws NotRegisteredException {
        Game game = new Game();
        Player player1 = new Player(1, "Alice", 50); // Сила 50
        Player player2 = new Player(2, "Bob", 51); // Сила 51

        // Регистрируем игроков
        game.register(player1);
        game.register(player2);

        // Проверяем, что Боб выигрывает у Алисы
        assertEquals(2, game.round("Alice", "Bob"));

        // Проверяем обратное соревнование (Боб против Алисы)
        assertEquals(1, game.round("Bob", "Alice")); // Алиса проигрывает
    }

    // todo Проверяем, при условии что у игроков одинаковые имена но сила разная.
    @Test
    void testRoundWithSameNameButDifferentStrength() throws NotRegisteredException {
        Game game = new Game();
        Player player1 = new Player(1, "Alice", 50); // Сила 50
        Player player2 = new Player(2, "Alice", 60); // Сила 60

        // Регистрируем игроков
        game.register(player1);
        game.register(player2);

        // Проверяем, что второй игрок (с большей силой) выигрывает
        assertEquals(2, game.round("Alice", "Alice"));
    }

    // todo Проверяем, при условии что у одного игрока 0 сила.
    @Test
    void testRoundWithZeroStrength() throws NotRegisteredException {

        Player player1 = new Player(1, "Alice", 0); // Сила 0
        Player player2 = new Player(2, "Bob", 50); // Сила 50

        // Регистрируем игроков
        game.register(player1);
        game.register(player2);

        // Проверяем, что Боб выигрывает у Алисы
        assertEquals(2, game.round("Alice", "Bob"));

        // Проверяем обратное соревнование (Боб против Алисы)
        assertEquals(1, game.round("Bob", "Alice")); // Алиса проигрывает
    }

    // todo Проверяем, при условии что у обоих игроков 0 сила.

    @Test
    void testRoundWithBothPlayersHavingZeroStrength() throws NotRegisteredException {
        Player player1 = new Player(1, "Alice", 0); // Сила 0
        Player player2 = new Player(2, "Bob", 0); // Сила 0

        // Регистрируем игроков
        game.register(player1);
        game.register(player2);

        // Проверяем, что результат будет ничья
        assertEquals(0, game.round("Alice", "Bob"));
    }
}




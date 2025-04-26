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

    // todo Проверяем регистрацию игроков

    // Проверка регистрации null-игрока
    @Test
    void testRegisterNullPlayer() {
        game.register(null);
        assertEquals(0, game.getRegisteredPlayers().size());
    }


    // Проверка регистрации дубликата игрока
    @Test
    void testRegisterDuplicatePlayer() {
        Player player = new Player(1, "Alice", 50);
        game.register(player);
        game.register(player); // Повторная регистрация
        assertEquals(1, game.getRegisteredPlayers().size());
    }

    @Test
    void testNotRegisteredPlayersCases() {
        // 1. Не зарегистрирован первый игрок
        Player player2 = new Player(2, "Ivan", 50);
        game.register(player2);
        assertThrows(NotRegisteredException.class,
                () -> game.round("UNREGISTERED", "Ivan"),
                "Должно выбрасываться исключение, когда первый игрок не зарегистрирован");

        // 2. Не зарегистрирован второй игрок
        Player player1 = new Player(1, "Alice", 50);
        game.register(player1);
        assertThrows(NotRegisteredException.class,
                () -> game.round("Alice", "UNREGISTERED"),
                "Должно выбрасываться исключение, когда второй игрок не зарегистрирован");

        // 3. Не зарегистрированы оба игрока
        assertThrows(NotRegisteredException.class,
                () -> game.round("UNREGISTERED1", "UNREGISTERED2"),
                "Должно выбрасываться исключение, когда оба игрока не зарегистрированы");
    }

    // todo Проверяем, что метод корректно работает при двух зарегистрированных игроках.
    @Test
    void testTwoRegisteredPlayers() throws NotRegisteredException {
        Player player1 = new Player(1, "Alice", 50);
        Player player2 = new Player(2, "Ivan", 70);

        // Регистрируем игроков
        registerPlayers(player1, player2);

        // Проверяем, что Иван выигрывает у Алисы, так как его сила больше

        assertEquals(2, game.round("Alice", "Ivan"));


    }

    // todo Проверяем, что метод корректно если хотя бы один игрок отсутствует в списке .
    @Test
    void testOneRegisteredPlayer() {

        Player player1 = new Player(1, "Alice", 50);

        registerPlayers(player1); // Регистрируем только Алису

        // Проверяем исключение для незарегистрированного игрока
        assertThrows(NotRegisteredException.class, () -> game.round("Alice", "Ivan"));
    }

    // todo Проверяем, что метод корректно работает при двух зарегистрированных игроках одинаковой силы.
    @Test
    void testEqualStrength() throws NotRegisteredException {

        Player player1 = new Player(1, "Alice", 50);
        Player player2 = new Player(2, "Ivan", 50);

        registerPlayers(player1, player2);

        // Проверяем, что результатом будет ничья, так как сила игроков равна
        assertEquals(0, game.round("Alice", "Ivan"));
    }

    // todo Проверяем, что метод корректно работает при большой разнице в силе между игроками.
    @Test
    void testLargeStrengthDifference() throws NotRegisteredException {
        Player player1 = new Player(1, "Alice", 1); // Сила 1
        Player player2 = new Player(2, "Ivan", 100); // Сила 100

        // Регистрируем игроков
        game.register(player1);
        game.register(player2);

        // Проверяем, что Иван выигрывает у Алисы
        assertEquals(2, game.round("Alice", "Ivan"));

        // Проверяем обратное соревнование (Алиса против Ивана)
        assertEquals(1, game.round("Ivan", "Alice")); // Иван выигрывает
    }

    // todo Проверяем, когда разница в силе между игроками минимальна.

    @Test
    void testMinimalStrengthDifference() throws NotRegisteredException {
        Player player1 = new Player(1, "Alice", 50); // Сила 50
        Player player2 = new Player(2, "Ivan", 51); // Сила 51

        // Регистрируем игроков
        game.register(player1);
        game.register(player2);

        // Проверяем, что Иван выигрывает у Алисы
        assertEquals(2, game.round("Alice", "Ivan"));

        // Проверяем обратное соревнование (Иван против Алисы)
        assertEquals(1, game.round("Ivan", "Alice")); // Алиса проигрывает
    }


    // todo Проверяем, при условии что у одного игрока 0 сила.
    @Test
    void testZeroStrength() throws NotRegisteredException {

        Player player1 = new Player(1, "Alice", 0); // Сила 0
        Player player2 = new Player(2, "Ivan", 50); // Сила 50

        // Регистрируем игроков
        game.register(player1);
        game.register(player2);

        // Проверяем, что Иван выигрывает у Алисы
        assertEquals(2, game.round("Alice", "Ivan"));

        // Проверяем обратное соревнование (Иван против Алисы)
        assertEquals(1, game.round("Ivan", "Alice")); // Алиса проигрывает
    }

    // todo Проверяем, при условии что у обоих игроков 0 сила.

    @Test
    void testBothPlayersZeroStrength() throws NotRegisteredException {
        Player player1 = new Player(1, "Alice", 0); // Сила 0
        Player player2 = new Player(2, "Ivan", 0); // Сила 0

        // Регистрируем игроков
        game.register(player1);
        game.register(player2);

        // Проверяем, что результат будет ничья
        assertEquals(0, game.round("Alice", "Ivan"));
    }
}




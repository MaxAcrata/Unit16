package ru.netologia.qamid;
import java.util.ArrayList;
import java.util.List;

// Класс Game управляет турниром между игроками.
// Он содержит методы регистрации игроков и проведения раундов.
public class Game {
    private final List<Player> registeredPlayers = new ArrayList<>(); // Список зарегистрированных игроков

    // Метод register используется для регистрации нового игрока в турнире.
    // Если игрок уже зарегистрирован или равен null, ничего не происходит.
    public void register(Player player) {
        if (player != null && !registeredPlayers.contains(player)) { // Проверяем, что игрок не null и не зарегистрирован
            registeredPlayers.add(player); // Добавляем игрока в список зарегистрированных
        }
    }

    // Метод round проводит соревнование между двумя игроками по их именам.
    // Возвращает:
    // - 0 в случае ничьи
    // - 1 если первый игрок выиграл
    // - 2 если второй игрок выиграл
    // Выбрасывает исключение NotRegisteredException, если хотя бы один из игроков не зарегистрирован.
    public int round(String playerName1, String playerName2) throws NotRegisteredException {
        Player player1 = findPlayerByName(playerName1); // Находим первого игрока по имени
        Player player2 = findPlayerByName(playerName2); // Находим второго игрока по имени

        // Если хотя бы один из игроков не найден, выбрасываем исключение
        if (player1 == null || player2 == null) {
            throw new NotRegisteredException("One or both players are not registered.");
        }

        // Сравниваем силу игроков и определяем победителя
        if (player1.getStrength() > player2.getStrength()) {
            return 1; // Первый игрок выиграл
        } else if (player1.getStrength() < player2.getStrength()) {
            return 2; // Второй игрок выиграл
        } else {
            return 0; // Ничья
        }
    }

    // Вспомогательный метод для поиска игрока по имени.
    // Возвращает объект Player, если игрок найден, или null, если не найден.
    private Player findPlayerByName(String name) {
        for (Player player : registeredPlayers) { // Проходим по списку зарегистрированных игроков
            if (player.getName().equals(name)) { // Сравниваем имена
                return player; // Возвращаем игрока, если имя совпадает
            }
        }
        return null; // Если игрок не найден, возвращаем null
    }
}

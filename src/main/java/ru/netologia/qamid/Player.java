package ru.netologia.qamid;
// Класс Player представляет игрока в турнире.
// Содержит уникальный ID, имя и силу (strength) игрока.
public class Player {
    private final int id; // Уникальный идентификатор игрока
    private final String name; // Имя игрока
    private final int strength; // Сила игрока (чем больше, тем сильнее)

    // Конструктор для создания нового игрока
    public Player(int id, String name, int strength) {
        this.id = id;
        this.name = name;
        this.strength = strength;
    }


    public int getId() {
        return id;
    }


    public String getName() {
        return name;
    }


    public int getStrength() {
        return strength;
    }
}
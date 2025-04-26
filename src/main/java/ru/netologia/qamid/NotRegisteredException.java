package ru.netologia.qamid;

// Пользовательское исключение, которое выбрасывается,
// если игрок не зарегистрирован в турнире.
public class NotRegisteredException extends Exception {

    public NotRegisteredException(String message) {
        super(message); // Передаем сообщение в конструктор родительского класса Exception
    }
}

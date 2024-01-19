// Формат сдачи: ссылка на подписанный git-проект.
// Задание:
// Реализуйте структуру телефонной книги с помощью HashMap.
// Программа также должна учитывать, что в во входной структуре будут повторяющиеся имена с разными телефонами, их необходимо считать, как одного человека с разными телефонами. Вывод должен быть отсортирован по убыванию числа телефонов.

import java.util.*;

public class PhoneBook {
    private Map<String, List<String>> phoneBook;

    public PhoneBook() {
        this.phoneBook = new HashMap<>();
    }

    public void addContact(String name, String phone) {
        phoneBook.computeIfAbsent(name, k -> new ArrayList<>()).add(phone);
    }

    public void displayPhoneBook() {
        for (Map.Entry<String, List<String>> entry : phoneBook.entrySet()) {
            System.out.println(entry.getKey() + ": " + String.join(", ", entry.getValue()));
        }
    }

    public Map<String, List<String>> sortPhoneBookByNumberOfPhones() {
        return phoneBook.entrySet()
                .stream()
                .sorted((e1, e2) -> Integer.compare(e2.getValue().size(), e1.getValue().size()))
                .collect(LinkedHashMap::new, (map, entry) -> map.put(entry.getKey(), entry.getValue()), Map::putAll);
    }

    public static void main(String[] args) {
        PhoneBook phoneBook = new PhoneBook();

        phoneBook.addContact("Васильев", "13-46-78");
        phoneBook.addContact("Степанов", "98-65-32");
        phoneBook.addContact("Иванова", "55-13-45");
        phoneBook.addContact("Маяковский", "99-88-77");
        phoneBook.addContact("Пушкин", "33-22-11");
        phoneBook.addContact("Иванова", "25-99-57");
        phoneBook.addContact("Пушкин", "45-22-22");
        phoneBook.addContact("Пушкин", "31-28-11");

        System.out.println("\nТелефонная книга с сортировкой по убыванию числа телефонов:");
        Map<String, List<String>> sortedPhoneBook = phoneBook.sortPhoneBookByNumberOfPhones();
        for (Map.Entry<String, List<String>> entry : sortedPhoneBook.entrySet()) {
            System.out.println(entry.getKey() + ": " + String.join(", ", entry.getValue()));
        }
    }
}

package pete.eremeykin.alfa.form.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pete.eremeykin.alfa.form.customer.Customer;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class ValidRandomCustomer extends Customer {

    private static final List<String> femaleFirstNames = Arrays.asList("Аделина", "Кристина", "Алевтина", "Ксения", "Александра", "Лада", "Алёна", "Лариса", "Алеся", "Лиана", "Алина", "Лидия", "Алиса", "Лилия", "Алла", "Лолита", "Альбина", "Луиза", "Анастасия", "Любовь", "Ангелина", "Людмила", "Анжела", "Майя", "Анна");
    private static final List<String> femaleLastNames = Arrays.asList("Смирнова", "Иванова", "Кузнецова", "Соколова", "Попова", "Лебедева", "Козлова", "Новикова", "Морозова", "Петрова", "Волкова", "Соловьёва", "Васильева", "Зайцева", "Павлова", "Семёнова", "Голубева", "Виноградова", "Богданова", "Воробьёва", "Фёдорова", "Михайлова", "Беляева", "Тарасова", "Белова", "Комарова", "Орлова", "Киселёва", "Макарова", "Андреева");
    private static final List<String> femalePatronymics = Arrays.asList("Александровна", "Дмитровна", "Константиновна", "Давидовна", "Максимовна", "Тимуровна", "Платоновна", "Сергеевна", "Олеговна", "Андреевна", "Ярославовна", "Григорьевна", "Алексеевна", "Антоновна", "Николаевна", "Даниловна", "Игнатовна", "Глебовна", "Станиславовна", "Кирилловна", "Васильевна", "Михаиловна");

    private static final List<String> maleFirstNames = Arrays.asList("Артём", "Александр", "Максим", "Даниил", "Дмитрий", "Иван", "Кирилл", "Никита", "Михаил", "Егор + Егорий", "Матвей", "Андрей", "Илья", "Алексей", "Роман", "Сергей", "Владислав", "Ярослав", "Тимофей", "Арсений", "Денис", "Владимир", "Павел", "Глеб", "Константин");
    private static final List<String> maleLastNames = Arrays.asList("Смирнов", "Иванов", "Кузнецов", "Соколов", "Попов", "Лебедев", "Козлов", "Новиков", "Морозов", "Петров", "Волков", "Соловьёв", "Васильев", "Зайцев", "Павлов", "Семёнов", "Голубев", "Виноградов", "Богданов", "Воробьёв", "Фёдоров", "Михайлов", "Беляев", "Тарасов", "Белов", "Комаров", "Орлов", "Киселёв", "Макаров", "Андреев", "Ковалёв", "Ильин", "Гусев", "Титов", "Кузьмин", "Кудрявцев", "Баранов", "Куликов", "Алексеев", "Степанов", "Яковлев", "Сорокин", "Сергеев", "Романов", "Захаров", "Борисов", "Королёв", "Герасимов", "Пономарёв", "Григорьев", "Лазарев", "Медведев", "Ершов", "Никитин", "Соболев", "Рябов", "Поляков", "Цветков", "Данилов", "Жуков", "Фролов", "Журавлёв", "Николаев", "Крылов", "Максимов", "Сидоров", "Осипов", "Белоусов", "Федотов", "Дорофеев", "Егоров", "Матвеев", "Бобров", "Дмитриев", "Калинин", "Анисимов", "Петухов", "Антонов", "Тимофеев", "Никифоров", "Веселов", "Филиппов", "Марков", "Большаков", "Суханов", "Миронов", "Ширяев", "Александров", "Коновалов", "Шестаков", "Казаков", "Ефимов", "Денисов", "Громов", "Фомин", "Давыдов", "Мельников", "Щербаков", "Блинов", "Колесников", "Карпов", "Афанасьев", "Власов", "Маслов", "Исаков", "Тихонов", "Аксёнов", "Гаврилов", "Родионов", "Котов", "Горбунов", "Кудряшов", "Быков", "Зуев", "Третьяков", "Савельев", "Панов", "Рыбаков", "Суворов", "Абрамов", "Воронов", "Мухин", "Архипов", "Трофимов", "Мартынов", "Емельянов", "Горшков", "Чернов", "Овчинников", "Селезнёв", "Панфилов", "Копылов", "Михеев", "Галкин", "Назаров", "Лобанов", "Лукин", "Беляков", "Потапов", "Некрасов", "Хохлов", "Жданов", "Наумов", "Шилов", "Воронцов", "Ермаков", "Дроздов", "Игнатьев", "Савин", "Логинов", "Сафонов", "Капустин", "Кириллов", "Моисеев", "Елисеев", "Кошелев", "Костин", "Горбачёв", "Орехов", "Ефремов", "Исаев", "Евдокимов", "Калашников", "Кабанов", "Носков", "Юдин", "Кулагин", "Лапин", "Прохоров", "Нестеров", "Харитонов", "Агафонов", "Муравьёв", "Ларионов", "Федосеев", "Зимин", "Пахомов", "Шубин", "Игнатов", "Филатов", "Крюков", "Рогов", "Кулаков", "Терентьев", "Молчанов", "Владимиров", "Артемьев", "Гурьев", "Зиновьев", "Гришин", "Кононов", "Дементьев", "Ситников", "Симонов", "Мишин", "Фадеев", "Комиссаров", "Мамонтов", "Носов", "Гуляев", "Шаров", "Устинов", "Вишняков", "Евсеев", "Лаврентьев", "Брагин", "Константинов", "Корнилов", "Авдеев", "Зыков", "Бирюков", "Шарапов", "Никонов", "Щукин", "Дьячков", "Одинцов", "Сазонов", "Якушев", "Красильников", "Гордеев", "Самойлов", "Князев", "Беспалов", "Уваров", "Шашков", "Бобылёв", "Доронин", "Белозёров", "Рожков", "Самсонов", "Мясников", "Лихачёв", "Буров", "Сысоев", "Фомичёв", "Русаков", "Стрелков", "Гущин", "Тетерин", "Колобов", "Субботин", "Фокин", "Блохин", "Селиверстов", "Пестов", "Кондратьев", "Силин", "Меркушев", "Лыткин", "Туров");
    private static final List<String> malePatronymics = Arrays.asList("Александрович", "Дмитрович", "Константинович", "Давидович", "Максимович", "Тимурович", "Платонич", "Сергеевич", "Олегович", "Анатолевич", "Андреевич", "Ярославич", "Григорьевич", "Алексеевич", "Антонович", "Демидович", "Рустамович", "Николаевич", "Данилович", "Игнатович", "Глебович", "Станиславович", "Кириллович", "Васильевич", "Михаилович");
    private static final List<String> domains = Arrays.asList("example.com", "test.com", "yandex.ru", "gmail.com", "mail.ru");
    private static final List<String> passwords = Arrays.asList("123456", "password", "qwerty", "abc123", "football", "baseball", "welcome", "admin", "1qaz2wsx", "starwars", "princess", "master", "sunshine", "dragon", "flower", "shadow", "monkey", "passw0rd", "batman", "hello");
    private static final List<String> logins = Arrays.asList("antol", "aozt", "art", "avto", "bank", "buh", "buhgalter", "business", "bux", "catchthismail", "company", "contact", "contactus", "corp", "design", "dir", "director", "dragon", "economist", "edu", "email", "er", "expert", "export", "fabrika", "fin", "finance", "ftp", "glavbuh", "glbuh", "help", "home", "hr", "iamj", "info", "ingthisleter", "job", "letmein", "mail", "manager", "marketing", "mike", "mogggnomgon", "monkey", "moscow", "office", "ok", "oracle", "personal", "postmaster");
    private static final List<String> cities = Arrays.asList("Москва", "Санкт-Петербург", "Новосибирск", "Екатеринбург", "Нижний Новгород", "Казань", "Самара", "Омск", "Челябинск", "Ростов-на-Дону", "Уфа", "Волгоград", "Пермь", "Красноярск", "Воронеж", "Саратов", "Краснодар", "Тольятти", "Барнаул", "Ижевск", "Ульяновск", "Владивосток", "Ярославль", "Иркутск", "Тюмень", "Махачкала", "Хабаровск", "Оренбург", "Новокузнецк", "Кемерово", "Рязань", "Томск", "Астрахань", "Пенза", "Набережные Челны", "Липецк");
    private static final List<String> streets = Arrays.asList("ул. Донбасская", "ул. Абаканская", "пер. Баланово", "ул. Белякова", "ул. Горная", "ул. Дизельная", "ул. Жигулёвская", "ул. Затонская", "ул. Звёздная", "ул. Зилимская", "ул. Ижевская", "ул. Искры", "ул. Канинская", "ул. Каховского", "ул. Кишинёвская", "Кооперативный переулок", "ул. Красноармейская", "ул. Кубанская", "ул. Кутузова", "ул. Левая Белая", "ул. Лесотехникума", "ул. Магистральная", "ул. Мелеузовская", "ул. Мира", "ул. Морякова");
    private static Random random = new Random(345328877);

    public ValidRandomCustomer() {
        List<String> firstNames;
        List<String> lastNames;
        List<String> patronymics;
        if (random.nextBoolean()) {
            this.setSex(Customer.Sex.MALE);
            firstNames = maleFirstNames;
            lastNames = maleLastNames;
            patronymics = malePatronymics;
        } else {
            this.setSex(Customer.Sex.FEMALE);
            firstNames = femaleFirstNames;
            lastNames = femaleLastNames;
            patronymics = femalePatronymics;
        }
        String login = logins.get(random.nextInt(logins.size()));
        String domain = domains.get(random.nextInt(domains.size()));
        this.setEmail(login + "@" + domain);
        this.setPassword(passwords.get(random.nextInt(passwords.size())));
        this.setFirstName(firstNames.get(random.nextInt(firstNames.size())));
        this.setLastName(lastNames.get(random.nextInt(lastNames.size())));
        this.setPatronymic(patronymics.get(random.nextInt(patronymics.size())));


        int year = random.nextInt(30) + 1970;
        int month = random.nextInt(12)+1;
        int day = random.nextInt(28)+1;
        this.setBirthDate(LocalDate.of(year, month, day));
        String city = cities.get(random.nextInt(cities.size()));
        String street = streets.get(random.nextInt(streets.size()));
        int num = random.nextInt(50) + 1;
        this.setAddress("г. " + city + " " + street + " д." + num);
        int inn1 = random.nextInt(990000) + 010000;
        int inn2 = random.nextInt(1000000);
        this.setInn(String.format("%06d%06d", inn1, inn2).substring(0, 12));
    }


}
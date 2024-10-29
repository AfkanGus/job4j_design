package ru.job4j.assertj;

/**
 * 3. Утверждения с примитивными типами [#504883 #344295]
 * Поговорим о структуре тестового метода. Тесты желательно
 * писать в одном стиле. Хорошо зарекомендовал себя шаблон
 * тестов, получивший название AAA - Arrange, Act, Assert.
 * ____На этапе Arrange производится подготовка данных для
 * проверяемого действия - создаются нужные объекты, поля
 * объектов наполняются тестовыми данными.
 * ____Этап Act - это выполнение действия объекта, которое
 * должно будет оцениваться, и сохранение результата этого
 * действия.
 * ____Этап Assert - это проверка соответствие фактического
 * результата действия ожидаемому результату.
 * Рассмотрим метод из предыдущего урока и отметим на нем
 * этапы Arrange-Act-Assert:
 */
public class Box {
    private static final String UNKNOWN = "Unknown object";
    private int vertex;
    private final int edge;
    private String type = "";

    public Box(int vertex, int edge) {
        this.vertex = vertex;
        this.edge = edge;
        init();
    }

    private void init() {
        type = switch (vertex) {
            case 0 -> "Sphere";
            case 4 -> "Tetrahedron";
            case 8 -> "Cub";
            default -> UNKNOWN;
        };
        if (UNKNOWN.equals(type)) {
            vertex = -1;
        }
        if (edge <= 0) {
            vertex = -1;
            type = UNKNOWN;
        }
    }

    public String whatsThis() {
        return this.type;
    }

    public int getNumberOfVertices() {
        return this.vertex;
    }

    public boolean isExist() { /*проверка явл ли обект допустимым*/
        return this.vertex != -1;
    }

    public double getArea() {
        double a = edge;
        return switch (vertex) {
            case 0 -> 4 * Math.PI * (a * a);/*4pr2*/
            case 4 -> Math.sqrt(3) * (a * a); /*корень 3 * a2*/
            case 8 -> 6 * (a * a);
            default -> 0;
        };
    }
}


# Java Swing — интерфейс для Snake

Этот README рассчитан на работу **без интернета**. Здесь собраны основы Swing, которые нужны именно для создания интерфейса игры Snake.

---

## 1. Что мы создаём

Пока НЕ делаем игровую логику.

Наша задача — получить примерно такой интерфейс:

```text
┌──────────────────────────────────┐
│              SNAKE               │
│             Score: 0             │
├──────────────────────────────────┤
│                                  │
│                                  │
│           ИГРОВОЕ ПОЛЕ           │
│                                  │
│                                  │
├──────────────────────────────────┤
│          [ RESTART ]              │
└──────────────────────────────────┘
```

Нам понадобятся:

- `JFrame` — окно;
- `JPanel` — панели/области интерфейса;
- `JLabel` — текст;
- `JButton` — кнопка;
- `BorderLayout` — расположение элементов;
- `Graphics` — рисование игрового поля;
- `Color` — цвета;
- `Dimension` — размеры;
- `paintComponent()` — место для собственного рисования.

---

# 2. Самая важная идея Swing

В Swing обычно есть главное окно:

```java
JFrame
```

В него помещаются панели:

```java
JPanel
```

А уже в панели помещаются другие элементы:

```text
JFrame
│
├── topPanel
│   ├── title
│   └── score
│
├── gamePanel
│
└── bottomPanel
    └── restartButton
```

То есть `JFrame` можно представить как оболочку программы.

---

# 3. Минимальное окно

```java
import javax.swing.*;

public class Main {

    public static void main(String[] args) {

        JFrame window = new JFrame("Snake");

        window.setSize(600, 600);

        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        window.setLocationRelativeTo(null);

        window.setVisible(true);
    }
}
```

## Что здесь происходит

### Создание окна

```java
JFrame window = new JFrame("Snake");
```

Создаём объект окна.

`"Snake"` — заголовок окна.

---

### Размер

```java
window.setSize(600, 600);
```

Ширина = `600`.

Высота = `600`.

---

### Закрытие

```java
window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
```

Без этого закрытие окна может не завершить программу нормально.

Для обычного приложения почти всегда используем:

```java
JFrame.EXIT_ON_CLOSE
```

---

### Центрирование

```java
window.setLocationRelativeTo(null);
```

`null` означает разместить окно по центру экрана.

---

### Показ окна

```java
window.setVisible(true);
```

До этого момента окно существует, но не отображается.

---

# 4. JPanel — основа нашего интерфейса

`JPanel` — это область, в которую можно помещать другие компоненты или в которой можно рисовать.

Например:

```java
JPanel panel = new JPanel();
```

Можно изменить фон:

```java
panel.setBackground(Color.BLACK);
```

Для этого нужен импорт:

```java
import java.awt.Color;
```

---

# 5. GamePanel — игровая область

Для Snake лучше создать собственный класс:

```java
import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {

    public GamePanel() {
        setBackground(Color.BLACK);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.GREEN);
        g.fillRect(100, 100, 20, 20);
    }
}
```

Это уже можно воспринимать как наше игровое поле.

---

# 6. Что такое paintComponent()

Очень важный метод:

```java
protected void paintComponent(Graphics g)
```

Swing вызывает его, когда панель нужно нарисовать.

Внутри мы можем рисовать:

```java
g.fillRect(...)
g.drawRect(...)
g.drawLine(...)
g.drawString(...)
```

Например:

```java
g.setColor(Color.GREEN);

g.fillRect(100, 100, 20, 20);
```

Нарисует зелёный квадрат.

---

# 7. Почему нужно писать super.paintComponent(g)

В начале `paintComponent` обычно пишем:

```java
super.paintComponent(g);
```

Это позволяет Swing нормально очистить/перерисовать панель перед нашим рисованием.

Поэтому используй шаблон:

```java
@Override
protected void paintComponent(Graphics g) {
    super.paintComponent(g);

    // здесь своё рисование
}
```

---

# 8. Добавляем GamePanel в JFrame

```java
import javax.swing.*;

public class Main {

    public static void main(String[] args) {

        JFrame window = new JFrame("Snake");

        GamePanel gamePanel = new GamePanel();

        window.add(gamePanel);

        window.setSize(600, 600);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }
}
```

Теперь:

```text
JFrame
│
└── GamePanel
```

---

# 9. BorderLayout

Для интерфейса Snake очень удобно использовать:

```java
BorderLayout
```

Он разделяет пространство на:

```text
        NORTH
┌─────────────────────┐
│                     │
├─────────────────────┤
│                     │
│       CENTER        │
│                     │
├─────────────────────┤
│                     │
└─────────────────────┘
        SOUTH
```

Также есть:

```text
WEST       CENTER       EAST
```

---

# 10. Пример BorderLayout

```java
window.setLayout(new BorderLayout());

window.add(topPanel, BorderLayout.NORTH);
window.add(gamePanel, BorderLayout.CENTER);
window.add(bottomPanel, BorderLayout.SOUTH);
```

Это означает:

- `topPanel` → сверху;
- `gamePanel` → центр;
- `bottomPanel` → снизу.

---

# 11. JLabel

`JLabel` используется для текста.

Например:

```java
JLabel title = new JLabel("SNAKE");
```

И:

```java
JLabel score = new JLabel("Score: 0");
```

Добавляем их в панель:

```java
topPanel.add(title);
topPanel.add(score);
```

---

# 12. Настройка текста

Можно изменить размер шрифта:

```java
title.setFont(new Font("Arial", Font.BOLD, 30));
```

Здесь:

```text
Arial  → шрифт
BOLD   → жирный
30     → размер
```

Можно изменить цвет:

```java
title.setForeground(Color.WHITE);
```

---

# 13. JButton

Кнопка:

```java
JButton restartButton = new JButton("RESTART");
```

Добавляем:

```java
bottomPanel.add(restartButton);
```

Пока кнопка ничего не делает.

Это нормально.

Мы сейчас изучаем интерфейс, а не игровую логику.

---

# 14. Важный момент: Layout Manager

Не стоит обычно делать так:

```java
button.setBounds(100, 200, 150, 50);
```

Для начала лучше использовать Layout Manager.

Например:

```java
panel.setLayout(new BorderLayout());
```

или:

```java
panel.setLayout(new FlowLayout());
```

или:

```java
panel.setLayout(new GridLayout(2, 2));
```

---

# 15. FlowLayout

`FlowLayout` располагает элементы друг за другом.

```java
JPanel panel = new JPanel();

panel.setLayout(new FlowLayout());

panel.add(new JLabel("Score:"));
panel.add(new JLabel("0"));
panel.add(new JButton("Restart"));
```

Примерно:

```text
┌─────────────────────────────┐
│ Score:   0   [ Restart ]    │
└─────────────────────────────┘
```

Для маленьких панелей это удобно.

---

# 16. GridLayout

`GridLayout` делит область на сетку.

Например:

```java
panel.setLayout(new GridLayout(3, 3));
```

Получится:

```text
┌─────┬─────┬─────┐
│     │     │     │
├─────┼─────┼─────┤
│     │     │     │
├─────┼─────┼─────┤
│     │     │     │
└─────┴─────┴─────┘
```

Для настоящего игрового поля Snake мы можем использовать похожую идею, но само поле лучше рисовать самостоятельно через `Graphics`.

---

# 17. Как мыслить о координатах

В Swing начало координат находится в левом верхнем углу:

```text
(0,0)
  ┌──────────────────────────>
  │
  │
  │
  │
  v
```

Например:

```java
g.fillRect(100, 50, 20, 20);
```

означает:

```text
x = 100
y = 50
width = 20
height = 20
```

То есть квадрат начинается в точке `(100, 50)`.

---

# 18. Координаты Snake

Допустим, размер клетки:

```java
int cellSize = 20;
```

Тогда клетки могут находиться:

```text
(0,0)      (20,0)      (40,0)
(0,20)     (20,20)     (40,20)
(0,40)     (20,40)     (40,40)
```

Это очень удобно для Snake.

Например:

```java
g.fillRect(100, 100, 20, 20);
```

— одна клетка.

Следующая:

```java
g.fillRect(120, 100, 20, 20);
```

Ещё следующая:

```java
g.fillRect(140, 100, 20, 20);
```

Так можно будет нарисовать тело змейки.

---

# 19. Поле с сеткой

Можно нарисовать сетку:

```java
@Override
protected void paintComponent(Graphics g) {
    super.paintComponent(g);

    g.setColor(Color.DARK_GRAY);

    int cellSize = 20;

    for (int x = 0; x < getWidth(); x += cellSize) {
        g.drawLine(x, 0, x, getHeight());
    }

    for (int y = 0; y < getHeight(); y += cellSize) {
        g.drawLine(0, y, getWidth(), y);
    }
}
```

Здесь пока важно понять только идею:

```java
for (...)
```

повторяет рисование линий через каждые `20` пикселей.

---

# 20. Полный пример интерфейса

Ниже уже рабочий пример.

## Main.java

```java
import javax.swing.*;
import java.awt.*;

public class Main {

    public static void main(String[] args) {

        JFrame window = new JFrame("Snake");

        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(600, 650);
        window.setLocationRelativeTo(null);

        // Основной Layout
        window.setLayout(new BorderLayout());

        // =========================
        // Верхняя панель
        // =========================

        JPanel topPanel = new JPanel();

        JLabel title = new JLabel("SNAKE");
        JLabel score = new JLabel("Score: 0");

        title.setFont(new Font("Arial", Font.BOLD, 28));
        score.setFont(new Font("Arial", Font.PLAIN, 18));

        topPanel.add(title);
        topPanel.add(score);

        // =========================
        // Игровая панель
        // =========================

        GamePanel gamePanel = new GamePanel();

        // =========================
        // Нижняя панель
        // =========================

        JPanel bottomPanel = new JPanel();

        JButton restartButton = new JButton("RESTART");

        bottomPanel.add(restartButton);

        // =========================
        // Добавляем всё в окно
        // =========================

        window.add(topPanel, BorderLayout.NORTH);
        window.add(gamePanel, BorderLayout.CENTER);
        window.add(bottomPanel, BorderLayout.SOUTH);

        // Показываем окно
        window.setVisible(true);
    }
}
```

## GamePanel.java

```java
import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {

    private final int cellSize = 20;

    public GamePanel() {
        setBackground(Color.BLACK);
    }

    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);

        // =========================
        // Рисуем сетку
        // =========================

        g.setColor(Color.DARK_GRAY);

        for (int x = 0; x < getWidth(); x += cellSize) {
            g.drawLine(x, 0, x, getHeight());
        }

        for (int y = 0; y < getHeight(); y += cellSize) {
            g.drawLine(0, y, getWidth(), y);
        }

        // =========================
        // Временная змейка
        // =========================

        g.setColor(Color.GREEN);

        g.fillRect(100, 100, cellSize, cellSize);
        g.fillRect(120, 100, cellSize, cellSize);
        g.fillRect(140, 100, cellSize, cellSize);

        // =========================
        // Временная еда
        // =========================

        g.setColor(Color.RED);

        g.fillOval(300, 200, cellSize, cellSize);
    }
}
```

---

# 21. Как должны выглядеть файлы

Создай проект:

```text
Snake
│
└── src
    │
    ├── Main.java
    │
    └── GamePanel.java
```

Пока этого достаточно.

---

# 22. Что нужно понять на паре

Не пытайся запомнить весь код.

Главное понять эту структуру:

```text
JFrame
│
├── JPanel topPanel
│   ├── JLabel title
│   └── JLabel score
│
├── GamePanel
│   └── paintComponent()
│       ├── сетка
│       ├── змейка
│       └── еда
│
└── JPanel bottomPanel
    └── JButton restart
```

И особенно:

```java
JFrame
```

= окно

```java
JPanel
```

= область интерфейса

```java
JLabel
```

= текст

```java
JButton
```

= кнопка

```java
Graphics
```

= инструмент рисования

```java
paintComponent()
```

= место, где мы рисуем

```java
BorderLayout
```

= способ расположить элементы

---

# 23. Что делать после этого

Когда интерфейс будет понятен, следующий порядок для Snake:

### Этап 1 — интерфейс

- окно;
- игровое поле;
- счёт;
- кнопка Restart.

### Этап 2 — рисование

- сетка;
- голова змейки;
- тело;
- еда.

### Этап 3 — управление

Научиться получать:

```text
↑
↓
←
→
```

с клавиатуры.

Для этого понадобится `KeyListener` или более современный Swing-подход через **Key Bindings**.

### Этап 4 — движение

Например:

```text
→ → → →
```

Змейка начинает перемещаться по клеткам.

### Этап 5 — игровая логика

- столкновение со стеной;
- столкновение с собой;
- поедание еды;
- увеличение длины;
- увеличение счёта;
- Game Over.

---

# 24. Главное правило для Swing

Не смешивай всё сразу.

Плохой вариант:

```text
Main.java
    ↓
всё окно
    ↓
рисование
    ↓
клавиатура
    ↓
движение
    ↓
еда
    ↓
счёт
    ↓
Game Over
```

Лучше постепенно разделять:

```text
Main
 │
 └── GameWindow
       │
       ├── TopPanel
       │
       └── GamePanel
             │
             └── рисование
```

А игровую логику добавлять позже.

---

# 25. Маленькое задание на пару

Не копируй сразу всю Snake.

Попробуй самостоятельно сделать следующие изменения:

### Задание 1

Измени размер окна:

```java
window.setSize(800, 700);
```

### Задание 2

Измени цвет игрового поля:

```java
setBackground(Color.BLUE);
```

### Задание 3

Измени цвет змейки:

```java
g.setColor(Color.YELLOW);
```

### Задание 4

Добавь ещё один квадрат:

```java
g.fillRect(160, 100, cellSize, cellSize);
```

### Задание 5

Измени текст:

```java
new JLabel("Score: 100");
```

### Задание 6

Измени размер клетки:

```java
private final int cellSize = 30;
```

---

# 26. Мини-шпаргалка

```java
// Окно
JFrame window = new JFrame();

// Панель
JPanel panel = new JPanel();

// Текст
JLabel label = new JLabel("Hello");

// Кнопка
JButton button = new JButton("Click");

// Добавить компонент
panel.add(button);

// Добавить панель в окно
window.add(panel);

// Layout
window.setLayout(new BorderLayout());

// Позиция
window.add(panel, BorderLayout.NORTH);

// Цвет
panel.setBackground(Color.BLACK);

// Размер
window.setSize(600, 600);

// Закрытие
window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

// Центр
window.setLocationRelativeTo(null);

// Показать
window.setVisible(true);
```

Для рисования:

```java
@Override
protected void paintComponent(Graphics g) {
    super.paintComponent(g);

    g.setColor(Color.GREEN);

    g.fillRect(100, 100, 20, 20);
}
```

---

# 27. Что изучать в первую очередь

Если времени мало, сосредоточься на этих 5 вещах:

```text
1. JFrame
2. JPanel
3. BorderLayout
4. paintComponent(Graphics g)
5. Graphics
```

После этого ты уже сможешь самостоятельно собрать основу интерфейса Snake.

Не обязательно понимать каждую строку сразу. Твоя первая цель — понять архитектуру:

```text
ОКНО
  ↓
ПАНЕЛИ
  ↓
ИГРОВАЯ ПАНЕЛЬ
  ↓
paintComponent()
  ↓
РИСОВАНИЕ
```

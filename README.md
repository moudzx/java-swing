# java-swing

A collection of GUI desktop applications built with **Java Swing**.

---

## Projects

### 📚 BookService (MVC)

A book management application structured around the **Model-View-Controller (MVC)** design pattern.

- Manage a collection of books through a clean Swing interface
- Separation of concerns: model handles data, view handles display, controller handles logic
- Located in `BookService/mvcs/`

### 🎨 Canvas

A freehand drawing application that lets you paint directly on screen.

- Draw with the mouse using a 2D graphics canvas
- Built around Java's `Graphics2D` API
- Located in `Canvas/`

### 📝 Notepad

A simple text editor in the spirit of Windows Notepad.

- Create, edit, and view plain text
- Lightweight and minimal — good reference for `JTextArea`, menus, and file I/O
- Located in `Notepad/`

---

## Requirements

- **Java 8+** (JDK)
- No external dependencies — uses only the standard Java SE library (`javax.swing`, `java.awt`)

---

## Running a Project

Each project can be compiled and run from its directory. For example, to run the Notepad:

```bash
cd Notepad
javac *.java
java Notepad
```

For the BookService Maven project:

```bash
cd BookService/mvcs
mvn compile exec:java
```

Or open any project folder in your IDE (IntelliJ IDEA, Eclipse, NetBeans) and run the `main` class directly.

---

## Structure

```
java-swing/
├── BookService/
│   └── mvcs/          # MVC book management app (Maven)
├── Canvas/            # Freehand drawing canvas
└── Notepad/           # Simple text editor
```

---

## Tech Stack

| Technology | Role |
|---|---|
| Java SE | Language & runtime |
| javax.swing | GUI components |
| java.awt | 2D graphics, events |
| Maven | Build tool (BookService) |

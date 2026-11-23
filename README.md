# Java OOP Practicals (Advanced Learner Track)

Java OOP work for the advanced learner activities.

## Structure

```
java-oop-practicals/
├── README.md
├── GIT_GUIDE.md                 <- step by step commands (branch, PR, review)
├── Questions/
│   ├── Programming/             <- 10 hard programming questions (Q01 to Q10)
│   └── Theory_Answers.md        <- 10 hard theory questions with answers
└── StudentManagementSystem/     <- mini-project (see its README)
```

## How to run

Each program in `Questions/Programming` is a single file:

```bash
cd Questions/Programming
javac Q01_AbstractShapes.java
java Q01_AbstractShapes
```

Mini-project:

```bash
cd StudentManagementSystem
javac -d out src/sms/*.java
java -cp out sms.Main
```

## Topics covered

| Q | Topic |
|---|---|
| 1 | Abstract class, runtime polymorphism |
| 2 | Interface default/static methods, diamond problem |
| 3 | Custom exceptions, try-with-resources, chaining |
| 4 | Producer-Consumer (wait/notify) |
| 5 | Race condition and `synchronized` |
| 6 | HashMap, TreeMap, Comparator |
| 7 | Generics, bounded types |
| 8 | Nested, inner, local, anonymous classes |
| 9 | equals/hashCode, Comparable, HashSet/TreeSet |
| 10 | Singleton (thread-safe), Builder |

## Activity mapping

| # | Activity | Where |
|---|---|---|
| 1 | Maintain a GitHub repo for Java OOP practicals | this repository |
| 2 | Mini-project: inheritance, polymorphism, abstraction, interfaces | `StudentManagementSystem` |
| 3 | Collaboration with branches, commits, pull requests | `GIT_GUIDE.md` |
| 4 | Exception handling, multithreading, collections | `StudentManagementSystem`, Q03 to Q06 |
| 5 | Peer code review via pull requests | `GIT_GUIDE.md` |
| 6 | Management system with documentation | `StudentManagementSystem/README.md` |

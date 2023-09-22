# Transfer-application
Изучение ООП и Коллекций путем моделирования работы различных инкассаций и создание приложения для денежных переводов.

## Introduction
- Для написания программ использовалась версия Java 8.
- Отладка кода воспроизводилась на Intellij IDEA CE.
- Правила форматирования кода соответствуют общепринятым стандартам [Oracle](https://www.oracle.com/java/technologies/javase/codeconventions-namingconventions.html).

### Introduction to exercises
Задача проекта — автоматизировать бизнес-процесс, связанный с переводами определенных сумм между участниками нашей системы.

Каждый пользователь системы может перевести определенную сумму другому пользователю. Нам необходимо убедиться, что даже если мы потеряем историю входящих и исходящих переводов для конкретного пользователя, мы все равно сможем восстановить эту информацию.

Внутри системы все денежные операции хранятся в виде пар дебет/кредит. Например, Джон перевел Майку 500 долларов. Система сохраняет транзакцию для обоих пользователей:
```java
John -> Mike, -500, OUTCOME, transaction ID
Mike -> John, +500, INCOME, transaction ID
```
Для восстановления соединения внутри таких пар следует использовать идентификаторы каждой транзакции.

Запись о передаче, очевидно, может быть потеряна в такой сложной системе — она может не быть записана для одного из пользователей (для эмуляции и отладки такой ситуации разработчику необходимо иметь возможность удалить данные о передаче у одного из пользователей индивидуально). Поскольку такие ситуации реальны, необходим функционал для отображения всех «неподтвержденных переводов» (транзакций, записанных только для одного пользователя) и решения подобных проблем.

### Exercise 00

Exercise 00:||
---|---
Turn-in directory |	ex00
Files to turn-in |	User.java, Transaction.java, Program.java
**User classes can be employed, along with:**
Types (+ all methods of these types) |	Integer, String, UUID, enumerations

В данном упражнении реализованы базовые модели предметной области, а именно классы пользователей и транзакций.

Таким образом, для класса User характерен следующий набор состояний (полей):

-	Identifier
-	Name
-	Balance

Класс транзакции описывает денежный перевод между двумя пользователями. Здесь также следует определить уникальный идентификатор. Поскольку количество таких транзакций может быть очень большим, определим идентификатор как строку UUID. Таким образом, для класса Transaction характерен следующий набор состояний (полей):
-	Identifier
-	Recipient (User type)
-	Sender (User type)
-	Transfer category (debits, credits)
-	Transfer amount

Необходимо проверить начальный баланс пользователя (он не может быть отрицательным), а также баланс исходящих (только отрицательные суммы) и входящих (только положительные суммы) транзакций (использование методов get/set).

Пример использования таких классов должен содержится в файле программы (создание, инициализация, вывод содержимого объекта на консоль). Все данные для полей класса жестко запрограммированы в программе.

### Exercise 01

Exercise 01:||
---|---
Turn-in directory |	ex01
Files to turn-in |	UserIdsGenerator.java, User.java, Program.java
**All permissions from the previous exercise can be used**

В этом упражнении был создан класс UserIdsGenerator. Поведение объекта этого класса определяет функционал генерации идентификаторов пользователей.

Итак, класс UserIdsGenerator содержит в качестве своего состояния последний сгенерированный идентификатор. Поведение UserIdsGenerator определяется методом intgenerId(), который возвращает вновь сгенерированный идентификатор при каждом вызове.

UserIdsGenerator написан по паттерну Singleton, так как наличие нескольких объектов этого класса не может гарантировать уникальность всех идентификаторов пользователей.

Идентификатор пользователя должен быть доступен только для чтения, поскольку он инициализируется только один раз (при создании объекта) и не может быть изменен позже во время выполнения программы.

Пример использования таких классов должен содержаться в файле программы (создание, инициализация, вывод содержимого объекта на консоль).

### Exercise 02

Exercise 02:||
---|---
Turn-in directory	| ex02
Files to turn-in |	UsersList.java, UsersArrayList.java, User.java,Program.java, etc.
**All permissions from the previous exercise  + throw can be used.**

В этом упражнении был реализован функционал для хранения пользователей во время работы программы.

На данный момент ваше приложение не имеет постоянного хранилища (например, файловой системы или базы данных). Чтобы обеспечить большую гибкость, был определен интерфейс UsersList, который описывает следующее поведение:

- Добавить пользователя
- Получить пользователя по ID
- Получить пользователя по индексу
- Получить количество пользователей

Также создан класс UsersArrayList, моделирующий поведение ArrayList<T>, реализующий интерфейс UsersList.

Этот класс использует массив для хранения пользовательских данных. Размер массива по умолчанию равен 10. Если массив полон, его размер увеличивается вдвое. Метод добавления пользователя помещает объект типа Пользователь в первую пустую (свободную) ячейку массива.

В случае попытки получить пользователя с несуществующим идентификатором выбрасывается непроверенное исключение UserNotFoundException.

Пример использования таких классов должен содержаться в файле программы (создание, инициализация, вывод содержимого объекта на консоль).

### Exercise 03

Exercise 03:||
---|---
Turn-in directory |	ex03
Files to turn-in | TransactionsList.java, TransactionsLinkedList.java, User.java, Program.java, etc.
**All permissions from the previous exercise can be used**

В отличие от пользователей, список транзакций требует особого подхода к реализации. Поскольку количество операций создания транзакций может быть очень большим, нам нужен метод хранения, чтобы избежать дорогостоящего увеличения размера массива.

В этой задаче создан интерфейс TransactionsList, описывающий следующее поведение:
- Добавить транзакцию (O(1))
- Удалить транзакцию по ID (в данном случае используется строковый идентификатор UUID)
- Преобразование в массив (например, Transaction[] toArray())

Список транзакций реализован в виде связанного списка (LinkedList) в классе TransactionsLinkedList. Поэтому каждая транзакция должна содержать поле со ссылкой на следующий объект транзакции.

Если предпринята попытка удалить транзакцию с несуществующим идентификатором, выбрасывается исключение времени выполнения TransactionNotFoundException.

Пример использования таких классов должен содержаться в файле программы (создание, инициализация, вывод содержимого объекта на консоль).

### Exercise 04

Exercise 04:||
---|---
Turn-in directory |	ex04
Files to turn-in |	TransactionsService.java, Program.java, etc.
**All permissions from the previous exercise can be used**

В этом упражнении создан класс TransactionsService на основе паттерна Фасад, способного инкапсулировать поведение нескольких классов. С функционалом:

- Добавление пользователя
- Получение баланса пользователя
- Выполнение транзакции перевода (указываются идентификаторы пользователей и сумма перевода). В этом случае создаются две транзакции типа ДЕБЕТ/КРЕДИТ и добавляются к получателю и отправителю. Идентификаторы обеих транзакций идентичны.
- Получение переводов конкретного пользователя (возвращается МАССИВ переводов).
- Удаление транзакции по идентификатору для конкретного пользователя (указывается идентификатор транзакции и идентификатор пользователя)
- Проверка достоверности транзакций (возвращает МАССИВ непарных транзакций).

В случае попытки перевода суммы, превышающей остаточный баланс пользователя, выдается исключение времени выполнения IllegalTransactionException.

Пример использования таких классов должен содержаться в файле программы (создание, инициализация, вывод содержимого объекта на консоль).

### Exercise 05

Exercise 05:||
---|---
Turn-in directory |	ex05
Files to turn-in |	Menu.java, Program.java, etc.
**All permissions from the previous exercise can be used, as well as try/catch**

В результате получается работающее приложение с консолью. Функциональность меню реализована в соответствующем классе с полем ссылки на TransactionsService.

Каждый пункт меню должен сопровождаться номером команды, введенной пользователем для вызова действия.

Приложение поддерживать два режима запуска — product (стандартный режим) и dev (где информация о передаче для конкретного пользователя может быть удалена по идентификатору пользователя, а также может быть запущена функция, проверяющая достоверность всех передач).

Если генерируется исключение, появится сообщение, содержащее информацию об ошибке, и пользователю будет предоставлена возможность ввести действительные данные.

Сценарий работы приложения следующий:

```
$ java Program --profile=dev

1. Add a user
2. View user balances
3. Perform a transfer
4. View all transactions for a specific user
5. DEV – remove a transfer by ID
6. DEV – check transfer validity
7. Finish execution
-> 1
Enter a user name and a balance
-> Jonh 777
User with id = 1 is added
---------------------------------------------------------
1. Add a user
2. View user balances
3. Perform a transfer
4. View all transactions for a specific user
5. DEV – remove a transfer by ID
6. DEV – check transfer validity
7. Finish execution
-> 1
Enter a user name and a balance
-> Mike 100
User with id = 2 is added
---------------------------------------------------------
1. Add a user
2. View user balances
3. Perform a transfer
4. View all transactions for a specific user
5. DEV – remove a transfer by ID
6. DEV – check transfer validity
7. Finish execution
-> 3
Enter a sender ID, a recipient ID, and a transfer amount
-> 1 2 100
The transfer is completed
---------------------------------------------------------
1. Add a user
2. View user balances
3. Perform a transfer
4. View all transactions for a specific user
5. DEV – remove a transfer by ID
6. DEV – check transfer validity
7. Finish execution
-> 3
Enter a sender ID, a recipient ID, and a transfer amount
-> 1 2 150
The transfer is completed
---------------------------------------------------------
1. Add a user
2. View user balances
3. Perform a transfer
4. View all transactions for a specific user
5. DEV – remove a transfer by ID
6. DEV – check transfer validity
7. Finish execution
-> 3
Enter a sender ID, a recipient ID, and a transfer amount
-> 1 2 50
The transfer is completed
---------------------------------------------------------
1. Add a user
2. View user balances
3. Perform a transfer
4. View all transactions for a specific user
5. DEV – remove a transfer by ID
6. DEV – check transfer validity
7. Finish execution
-> 2
Enter a user ID
-> 2
Mike - 400
---------------------------------------------------------
1. Add a user
2. View user balances
3. Perform a transfer
4. View all transactions for a specific user
5. DEV – remove a transfer by ID
6. DEV – check transfer validity
7. Finish execution
-> 4
Enter a user ID
-> 1
To Mike(id = 2) -100 with id = cc128842-2e5c-4cca-a44c-7829f53fc31f
To Mike(id = 2) -150 with id = 1fc852e7-914f-4bfd-913d-0313aab1ed99
TO Mike(id = 2) -50 with id = ce183f49-5be9-4513-bd05-8bd82214eaba
---------------------------------------------------------
1. Add a user
2. View user balances
3. Perform a transfer
4. View all transactions for a specific user
5. DEV – remove a transfer by ID
6. DEV – check transfer validity
7. Finish execution
-> 5
Enter a user ID and a transfer ID
-> 1 1fc852e7-914f-4bfd-913d-0313aab1ed99
Transfer To Mike(id = 2) 150 removed
---------------------------------------------------------
1. Add a user
2. View user balances
3. Perform a transfer
4. View all transactions for a specific user
5. DEV – remove a transfer by ID
6. DEV – check transfer validity
7. Finish execution
-> 6
Check results:
Mike(id = 2) has an unacknowledged transfer id = 1fc852e7-914f-4bfd-913d-0313aab1ed99 from John(id = 1) for 150
```
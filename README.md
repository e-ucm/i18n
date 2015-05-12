# i18n - Simple Java library to manage i18n

Features:

* Load strings from `.properties` files or by code
* Admits arguments for the strings
* Handles singular/plural keys
* Handles inline variables in the format `${variable}`

## License Apache 2.0

Maven dependency:

```xml
<dependency>
    <groupId>es.e-ucm</groupId>
    <artifactId>i18n</artifactId>
    <version>1.0</version>
</dependency>
```

## Simple use

`messages.properties`:

```
zero.items = Player! You have 0 items
one.item = Player! You have 1 item
```

```java
I18N i18n = new I18N();
i18n.addMessages(new File("messages.properties"));

System.out.println(i18n.m("zero.items"));
// Player! You have 0 items

System.out.println(i18n.m("one.item"));
// Player! You have 1 item
```

## Passing arguments

`messages.properties`:

```
items = Player! You have {} items
```

```java
I18N i18n = new I18N();
i18n.addMessages(new File("messages.properties"));

System.out.println(i18n.m("items", 0));
// Player! You have 0 items

System.out.println(i18n.m("items", 1));
// Player! You have 1 items
```

## Singular and plural
`messages.properties`:

```
item = Player! You have {} item
items = Player! You have {} items
```

```java
I18N i18n = new I18N();
i18n.addMessages(new File("messages.properties"));

System.out.println(i18n.m(5, "item", "items"));
// Player! You have 5 items

System.out.println(i18n.m(1, "item", "items"));
// Player! You have 1 item
```

## Variables

`messages.properties`:

```
item = ${name}! You have {} item
items = ${name}! You have {} items
```

`variables.properties`

```
name = Link
```

```java
I18N i18n = new I18N();
i18n.addMessages(new File("messages.properties"));
i18n.addVariables(new File("variables.properties"));

System.out.println(i18n.m(5, "item", "items"));
// Link! You have 5 items

System.out.println(i18n.m(1, "item", "items"));
// Link! You have 1 item
```

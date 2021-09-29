# The Story - FAC

> FFHS, FAC (Formale Sprachen, Automaten und Compilerbau), Semesterarbeit

## Quick Start

Run this command `./gradlew build` *(JDK 15)* for build and execute test.

### Requirement

 * JDK 15

## Start Interpreter

Run this command `./gradlew runStory`. This command will interpret (valid) code that is written in the following describe programming language.

Example program is stored under `programs/hugo`.

```shell
# Execute program:
./gradlew runStory -DfilePath="<PATH_TO_THIS_PROJECT>/programs/hugo"
```

## Language

### Datatype

* String Literal
* Number
* Boolean

### Variable

A variable can be set one of the following ways:

```
Let x be 1
Put 1 into x
Now x will be 1
```

A variable ...

* ... has no fix datatype.
* ... can have the following prefix: `a`, `an`, `the`, `my` and `your`
* ... can also be a name like `Black Pearl`.
* ... is case-**insensitiv**. (`my dog` = `My dog`)

### Mathematics

|operator|keywords|
|:---:|:---:|
|`+`|`plus`/`with`|
|`-`|`minus`/`without`|
|`*`|`times`/`of`|
|`/`|`over`|

### Condition

|operator|keywords|
|:---:|:---:|
|`>`|`is higher/greater/bigger/stronger than`|
|`<`|`is lower/less/smaller/weaker than`|
|`>=`|`is as high/great/big/strong as`|
|`<=`|`is as low/little/small/weak as`|
|`==`|`is exactly the same as`|
|`!=`|`is not the same as`|

### Conditionals

Conditional expressions start with the `If` (`if`) keyword, followed by a condition or a boolean value. An `Else` (`else`) block can be written
directly after an `If` block. The code block of a `If` or `If-Else` ends by a blank line.

Number and String Literal will evaluate as `false`!

### Loops

Loop expression start with the `While` (`while`) keyword, followed by a condition or a boolean value. The code block ends by a blank line.

### Function

Functions are declared with a variable name followed by the `takes` keyword and a list of arguments separated by one of the following: `and` or `,`.
The code block of a function ends by a blank line. Functions are called using the `taking` keyword and must have at least one argument. Multiple
arguments are separated with one of the following: `and` or `,`.

The last statement of a function will be also a return value.

```
Square takes X
X times X

Square taking 4
```

### Print

The following keywords can be used to write variables or values into the console.

```
Say "Hello, World!"
Shout 42
Put 3.13 into pi
Print pi
```

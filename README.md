# FizzBuzz

An overly-engineered implementation of Fizz Buzz that shows some aplication design
elements I like to use including:

1. Immutable objects.
2. Factories and builders to construct objects.
3. Polymorphism to isolate logic. 
4. Judicious use of Java's streaming API.
5. Logging.
6. Static analysis tools.

## Prerequisites

An instillation of Java. I'm running 12, but anything reasonably recent should work.

## Building
On the command line, type in `./gradlew build`. This will run static analysis
reports and place an executable jar in the build/libs directory.

## Reports
Static analysis reports will be available in the build/reports directory after the build.

## Running
On the command line in the project root directory, 
type in `java -jar build/libs/fizz-buzz-1.0.0.jar`.
This will run a FizzBuzz routine from the numbers 1 through 100.

Type in `java -jar build/libs/fizz-buzz-1.0.0.jar -h` for additional command line options.

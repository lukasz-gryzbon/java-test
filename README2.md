### Building the project
```bash
$ gradle clean build
```

Expected output:
```bash
BUILD SUCCESSFUL in 1s
10 actionable tasks: 10 executed
```

### Executing the program
The above build step generates the following file:
```bash
build/distributions/java-test-1.0-SNAPSHOT.zip
```

Unzip the file to a folder and execute the following command inside that folder:
```bash
$ bin/java-test '{"soup":3, "bread":2}'
```

Replace the JSON parameter with the required value.

To execute the program for a specific date, add an ISO formatted date as a second argument, e.g.:
```bash
$ bin/java-test '{"soup":3, "bread":2}' 2019-09-01
```

Expected output:
```bash
{"totalCost":3.15}
```

#### Tests
The project includes a number of unit tests to ensure all classes work as expected individually.

It also includes an set of acceptance tests which execute the entire flow and check the result against the provided examples. 
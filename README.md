### JAVA-PROJECT 
## DIFFER
[![Actions Status](https://github.com/markiMiracle/java-project-71/actions/workflows/hexlet-check.yml/badge.svg)](https://github.com/markiMiracle/java-project-71/actions)
[![Actions Status](https://github.com/markiMiracle/java-project-71/actions/workflows/tests.yml/badge.svg)](https://github.com/markiMiracle/java-project-71/actions)
[![Maintainability](https://api.codeclimate.com/v1/badges/fb08ff0fe34105eb7e2b/maintainability)](https://codeclimate.com/github/markiMiracle/java-project-71/maintainability)
[![Test Coverage](https://api.codeclimate.com/v1/badges/fb08ff0fe34105eb7e2b/test_coverage)](https://codeclimate.com/github/markiMiracle/java-project-71/test_coverage)

- ### ***About it:***
    This app will show you a difference between two files.
    You can use two formats : json/yml


    
- ### ***Output formats:***
    - **stylish** (a simple format that clearly shows the difference using '+', '-' and ' ')
    - **plain** (returns the diff in text format (use this format if you need to compare the files that can contain a complex value) )
    - **json** (returns the diff in json format)

#### run with -f to choose the format [default: *stylish*]



## Setup

```bash
make build
```

## Run

```bash
make run
```

## Run checkstyle

```bash
make lint
```

## Run tests

```bash
make test
```

    
### Asciinemas:
- [GetDiff(yml)](https://asciinema.org/a/jWwxR8u1VLmqutPIOgxq7WZsy)

- [GetDiff(format=stylish)](https://asciinema.org/a/DouPELEDveSNXuxf80Tq3TXP1)

- [GetDiff(format=plain)](https://asciinema.org/a/jgGNAppHIlEfPayclT0weF3fi)

- [GetDiff(format=json)](https://asciinema.org/a/IE0zE3NwS3EKrJ4N54R7c8VXB)


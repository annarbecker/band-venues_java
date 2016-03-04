# Band Venues

#### Web app to track bands and played venues using Java and Postgres | March 4, 2016

#### By Anna Becker

## Description
A program to track bands and the venues where they've played concerts.

## Setup/Installation Requirements

Clone this repository.
```
$ cd ~/Desktop
$ git clone https://github.com/annarbecker/band-venues_java.git
$ cd band-venues_java
```

Open terminal and run Postgres:
```
$ postgres
```

Open a new tab in terminal and create the `band_venues` database:
```
$ psql
```

In psql run:
```
# CREATE DATABASE band_venues;
```

In terminal run:
```
$ psql band_venues < band_venues.sql
```

Switch to psql and run:
```
# \c band_venues
# \dt
```
This will list all of the tables in the `band_venues` database

Navigate back to the directory where this repository has been cloned and run gradle:
```
$ gradle run
```

Open your web browser of choice to localhost:4567

Make sure you have Java and Gradle installed.
  * For Java:
      * Download and install [Java SDK](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
      * Download and install [Java JRE](http://www.java.com/en/)
  * For Gradle: if you are using Homebrew on Mac:
      * $ brew update
      * $ brew install gradle




## Technologies Used

Java, Spark, Junit, Velocity, Fluentlenium, Bootstrap, Postgres

### License

This software is licensed under the MIT license.

Copyright (c) 2016 Anna Becker

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.

# jmm

A compiler of Java-- ([MiniJava][minijava]) programs to Java bytecode. 

## Usage

In the `src/` directory, generate the parser (with [JavaCC][javacc] and [JJTree][jjtree]) with:
```
jjtree Parser.jjt
javacc Parser.jj
javac *.java
```
To run the parser through a Java-- file, use:
```
java Parser <jmm file>
```
You can also use `make` to generate the parser and `make test` to run it through
a test input file (in `src/input.txt`).

[minijava]: http://www.cs.tufts.edu/~sguyer/classes/comp181-2006/minijava.html
[javacc]: https://javacc.org/
[jjtree]: https://javacc.org/jjtree

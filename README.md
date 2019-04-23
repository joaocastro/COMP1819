# jmm

A compiler of Java-- ([MiniJava][minijava]) programs to Java bytecode. 

## Usage

In the `src/` directory, generate the compiler (with [JavaCC][javacc] and [JJTree][jjtree]) with
```
jjtree Parser.jjt
cd AST/
javacc Parser.jj
cd ..
javac *.java
```
To compile a Java-- file, use
```
java jmm <jmm file>
```

### With `make` 

You can also use `make` to generate the parser and `make run` to run it through
the sample test files in the [`src/test/`](src/test/) directory.

[minijava]: http://www.cs.tufts.edu/~sguyer/classes/comp181-2006/minijava.html
[javacc]: https://javacc.org/
[jjtree]: https://javacc.org/jjtree

# jmm

A compiler of Java-- ([MiniJava][minijava]) programs to Java bytecode. 

# Usage

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


[minijava]: http://www.cs.tufts.edu/~sguyer/classes/comp181-2006/minijava.html
[javacc]: https://javacc.org/
[jjtree]: https://javacc.org/jjtree

# jmm

A compiler of Java-- ([MiniJava][minijava]) programs to Java bytecode. 

## Usage

In the `src/` directory, generate the compiler 
(with [JavaCC][javacc] and [JJTree][jjtree]) with
```
# inside src/
jjtree Parser.jjt
cd AST/
javacc Parser.jj
cd ..
javac jmm.java
```
To use the built compiler, run
```
java jmm [-r=<num>] [-o] <input_file.jmm>
```

[minijava]: http://www.cs.tufts.edu/~sguyer/classes/comp181-2006/minijava.html
[javacc]: https://javacc.org/
[jjtree]: https://javacc.org/jjtree

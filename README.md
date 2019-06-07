# jmm (group 65)

NAME1: Guilherme Vale, NR: 201709049, GRADE: 13, CONTRIBUTION: 35%

NAME2: João Pedro Castro, NR: 201404896, GRADE: 12, CONTRIBUTION: 30%

NAME3: Miguel Pedrosa, NR: 201604343, GRADE: 12, CONTRIBUTION: 30%

NAME4: Guilherme Amaro, NR: 201508537, GRADE: 9, CONTRIBUTION: 5%


## GLOBAL Grade of the project: 13


### SUMMARY: 

A compiler of Java-- ([MiniJava][minijava]) programs to Java bytecode. 
Given a file written in Java--, the compiler will generate ASCII descriptions of the Java classes
in the file, written in an assembler-like syntax, which can be fed to [Jasmin][jasmin] to create 
binary Java class files.


### EXECUTE: 

In the `src/` directory, generate the compiler (with [JavaCC][javacc] and [JJTree][jjtree]) with
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
java jmm <input_file.jmm>
```

A `Makefile` is made available which speeds up the building process. Running `make`, followed by `make run` in the root 
should run the sequence of steps outlined above.

A suite of example Java-- files is included in `src/test/`, for the sake of convenience.


### DEALING WITH SYNTACTIC ERRORS: 

The compiler will exit early if it detects a token not found in the defined grammar. The compiler only handles 
errors for `while` loop conditions. It treats this sort of error by skipping to the next opening bracket.


### SEMANTIC ANALYSIS: 

A symbol table is built as the nodes in the generated abstract syntax tree are processed, which is a conventional approach.
The compiler detects several semantic errors, such as uninitialised identifiers, assigning to undeclared variables, undeclared
variables (in current or parent scopes).

The symbol table construction and semantic analysis are done using the [visitor pattern][visitor].


### INTERMEDIATE REPRESENTATIONS (IRs): 

No intermediate representation was created. The code generation is done via just the syntax tree and the symbol table.


### CODE GENERATION: 

Code generation is capable of launching a .j file that can be interpreted by Jasmin. It can't convert every information read from the symbol table, but can create the basic structure of the language and some other data


## OVERVIEW: 

The approach taken was relatively straight-forward and conventional.
The visitor pattern is used for processing the tree for symbol table generation and semantic analysis, whereas a *regular* "children-iterator" approach is taken for code generation. 

The starting point of the application is in `src/jmm.java`, with all of the parser logic located in `src/Parser.jjt`.


### TASK DISTRIBUTION: 

Guilherme Vale:
Helped with the parser token and grammar definition. 
Primarily wrote the syntax analysis, syntax tree and symbol table generation, and semantic analysis. Wrote most of this readme.

João Pedro Castro:
Helped with the parser token and grammar definition. Helped with symbol table generation. Primarily wrote the code generation.

Miguel Pedrosa:
Helped with the parser token and grammar definition. Helped with symbol table generation. Helped with code generation.

Guilherme Amaro:
Helped with the grammar definitions


### PROS: 

Uses the visitor pattern for recursively processing the syntax tree, allowing for making passes for different purposes (symbol table, smenatic, code generation) isolated and more maintainable.


### CONS: 

Very rudimentary error handling, not very helpful error messages, and poor code generation support.
No optimisations were implemented in the compiler.

[minijava]: http://www.cs.tufts.edu/~sguyer/classes/comp181-2006/minijava.html
[javacc]: https://javacc.org/
[jjtree]: https://javacc.org/jjtree
[jasmin]: http://jasmin.sourceforge.net/
[visitor]: https://en.wikipedia.org/wiki/Visitor_pattern

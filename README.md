# jmm (group 65)

NAME1: Guilherme Vale, NR: 201709049, GRADE: 14, CONTRIBUTION: 55%

NAME2: João Pedro Castro, NR: 201404896, GRADE: 11, CONTRIBUTION: 20%

NAME3: Miguel Pedrosa, NR: 201604343, GRADE: 11, CONTRIBUTION: 20%

NAME4: Guilherme Amaro, NR: 201508537, GRADE: 9, CONTRIBUTION: 5%

## GLOBAL Grade of the project: 13

### SUMMARY: 

A compiler of Java-- ([MiniJava][minijava]) programs to Java bytecode. 
Given a file written in Java--, the compiler will generate ASCII descriptions of the Java classes in the file, written in an assembler-like syntax, which can be fed to [Jasmin][jasmin] to create binary Java class files.

### EXECUTE: 

Several shell scripts are made available to make bootstrapping and running the compiler easier.

- `compile.sh`: compiles all necessary source files. They are compiled into the `bin/` directory.
- `clean.sh` : removes all `.class` and `.j` files, anywhere in the project.
- `run.sh` : if the compiler was built, this can be used to compile a given a `.jmm` file into a `.j` file. These files are placed in the `gen/` directory.
- `run_all.sh` : runs the above procedure for every single `.jmm` file inside the test suite.
- `gen_class.sh` : calls Jasmin on a given `.j` file to generate a class file. The class file is placed in `gen/`.

A suite of example files is included in `test/`.

### DEALING WITH SYNTACTIC ERRORS: 

The compiler will exit early if it detects a token not found in the defined grammar. The compiler only handles errors for `while` loop conditions. It treats this sort of error by skipping to the next opening bracket.

### SEMANTIC ANALYSIS: 

A symbol table is built as the nodes in the generated abstract syntax tree are processed, which is a conventional approach.
The compiler detects several semantic errors, such as uninitialised identifiers, assigning to undeclared variables, undeclared
variables (in current or parent scopes).

The symbol table construction and semantic analysis are done using the [visitor pattern][visitor].

### INTERMEDIATE REPRESENTATIONS (IRs): 

No intermediate representation was created. The code generation is done via just the syntax tree and the symbol table.

### CODE GENERATION: 

Code generation naturally creates a `.j`file which can be used via Jasmin (and the `gen_class` shell script) to create a Java classfile. The code generation supports:

- Class extension (`extends` keyword)
- Global variables
- Loading integer literals
- Loading boolean literals
- If blocks (incl else condition)

## OVERVIEW: 

The approach taken was relatively straight-forward and conventional.
The visitor pattern is used for processing the tree for symbol table generation and semantic analysis, whereas a *regular* "children-iterator" approach is taken for code generation. 

The starting point of the application is in `src/jmm/jmm.java`. 

### TASK DISTRIBUTION: 

Guilherme Vale:
Helped with the parser token and grammar definition. 
Primarily wrote the syntax analysis, syntax tree and symbol table generation, and semantic analysis. 
Wrote this readme file.

João Pedro Castro:
Helped with the parser token and grammar definition. Helped with symbol table generation. Primarily wrote the foundation of code generation.

Miguel Pedrosa:
Helped with the parser token and grammar definition. Helped with symbol table generation. Helped with code generation.

Guilherme Amaro:
Helped with the grammar definitions.

### PROS: 

- Uses the visitor pattern for recursively processing the syntax tree, allowing for making passes for different purposes (symbol table, smenatic, code generation) isolated and more maintainable.

### CONS: 

- Very rudimentary error handling.
- Error messages could be more helpful.
- Poor code generation support. 
- No optimisations were implemented.

[minijava]: http://www.cs.tufts.edu/~sguyer/classes/comp181-2006/minijava.html
[javacc]: https://javacc.org/
[jjtree]: https://javacc.org/jjtree
[jasmin]: http://jasmin.sourceforge.net/
[visitor]: https://en.wikipedia.org/wiki/Visitor_pattern

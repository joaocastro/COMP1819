rm -rf bin
mkdir -p bin

cd src/AST/

jjtree Parser.jjt
javacc Parser.jj

cd ../../

javac -d bin -sourcepath . src/*/*.java

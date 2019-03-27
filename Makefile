PROG := jmm

all: $(PROG)

$(PROG): 
	cd src && \
	jjtree src/Parser.jjt && \
	javacc Parser.jj && \
	javac *.java && \
	cd ..

test:
	cd src && \
	java Parser input.txt && \
	cd ..


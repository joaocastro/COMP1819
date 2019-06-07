PROG := jmm

TEST_DIR = test
J_DIR = src/bin/generatedFiles
OUT_DIR = 

FILE = $(TEST_DIR)/$(IN).jmm
J_FILE = $(J_DIR)/$(IN).j

# Used for IN file
ifeq ($(IN), )
	IN = Random
endif


all: $(PROG)

$(PROG): 
	cd src && \
	jjtree Parser.jjt && \
	cd AST && \
	javacc Parser.jj && \
	cd .. && \
	javac jmm.java && \
	cd ..

run:
	cd src && \
	java $(PROG) $(FILE) && \
	cd ..
	java -jar jasmin.jar $(J_FILE)
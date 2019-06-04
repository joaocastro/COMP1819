PROG := jmm

TEST_DIR = test

INPUT1 = $(TEST_DIR)/Random.jmm
INPUT2 = $(TEST_DIR)/HelloWorld.jmm
INPUT3 = $(TEST_DIR)/MonteCarlo.jmm
INPUT4 = $(TEST_DIR)/FindMaximum.jmm

# Used for IN file
ifeq ($(IN), )
	IN = $(INPUT1)
endif
ifeq ($(IN), 1)
	IN = $(INPUT1)
endif
ifeq ($(IN), 2)
	IN = $(INPUT2)
endif
ifeq ($(IN), 3)
	IN = $(INPUT3)
endif
ifeq ($(IN), 4)
	IN = $(INPUT4)
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
	java jmm $(IN) && \
	cd ..

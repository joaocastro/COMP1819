PROG := jmm

TEST_DIR = tests

DEFAULT_INPUT = $(TEST_DIR)/input.txt
INPUT1 = $(TEST_DIR)/input.txt
INPUT2 = $(TEST_DIR)/input.txt


#Used for input file
ifeq ($(INPUT), )
	INPUT = $(DEFAULT_INPUT)
endif

all: $(PROG)

$(PROG): 
	cd src && \
	jjtree Parser.jjt && \
	javacc Parser.jj && \
	javac *.java && \
	cd ..

test:
	cd src && \
	java Parser $(INPUT) && \
	cd ..

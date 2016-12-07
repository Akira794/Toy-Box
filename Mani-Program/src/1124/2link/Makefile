#Makefile
.PHONY: all
all: 2LMani

2LMani: 2link-Manipulator.o
	gcc -o 2LMani 2link-Manipulator.o -lm

2link-Manipulator.o: 2link-Manipulator.cpp 
	gcc -c 2link-Manipulator.cpp -lm 

func.o: func.h -lm

.PHONY: clean
clean:
	rm -rf 2LMani 2link-Manipulator.o

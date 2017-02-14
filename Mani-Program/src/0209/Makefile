#Makefile
.PHONY: all
all: 3LMani

3LMani: 3link-Manipulator.o
	gcc -o 3LMani 3link-Manipulator.o -lm

3link-Manipulator.o: 3link-Manipulator.cpp 
	gcc -c 3link-Manipulator.cpp -lm 

func.o: func.h -lm

.PHONY: clean
clean:
	rm -rf 3LMani 3link-Manipulator.o

#Makefile

.PHONY: all
all: hello raiden

hello: hello.o edajima.o
	gcc -o hello hello.o edajima.o

raiden: raiden.o
	gcc -o raiden raiden.o
hello.o: hello.c
	gcc -c hello.c

edajima.o: edajima.c
	gcc -c edajima.c

raiden.o: raiden.c
	gcc -c raiden.c

.PHONY: clean
clean: 
	rm -f hello hello.o edajima.o raiden raiden.o

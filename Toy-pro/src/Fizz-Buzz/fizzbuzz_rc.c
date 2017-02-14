/*Fizz-Buzz_rc.c*/

#include<stdio.h>

void fizz_buzz(int n){
	
	if(n > 1)
		fizz_buzz(n-1);

	if (n % 3 == 0 && n % 5 == 0)
		printf("Fizz Buzz\n");

	else if (n % 3 == 0)
		printf("Fizz\n");

	else if (n % 5 == 0)
		printf("Buzz\n");

	else
		printf("%d\n",n);
}

int main(void){

	fizz_buzz(100);

	return 0;
}


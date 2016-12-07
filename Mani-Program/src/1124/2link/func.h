#include <iostream>
#include <math.h>

#define PI 3.14159265

double DtR(double x){
/*----Degree to Radian---*/
	double y;
	y =  x * (PI/180);
	return y;
}

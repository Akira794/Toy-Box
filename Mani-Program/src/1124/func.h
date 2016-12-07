#include <iostream>
#include <math.h>

#define PI 3.14159265
/*----Degree to Radian---*/

double DtR(double x){
	double y;
	y =  x * (PI/180);
	return y;
}
/*----calc-3link-----------------*/
void Forward_Kinematics(double L1, double L2, double L3, double deg1, double deg2, double deg3, double *x, double *y){
        /*-deg to rad-*

        double rad1 = deg1 * (PI/180);
        double rad2 = deg2 * (PI/180);
        /*------------*/
	double rad1 = DtR(deg1);
	double rad2 = DtR(deg2);
	double rad3 = DtR(deg3);

        *x =L1 * cos (rad1) +L2 * cos (rad1 + rad2) + L3 * cos(rad1 + rad2 + rad3);
        *y =L1 * sin (rad2) +L2 * sin (rad1 + rad2) + L3 * sin(rad1 + rad2 + rad3);
}


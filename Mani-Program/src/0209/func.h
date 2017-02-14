#include <iostream>
#include <math.h>

#define PI 3.14159265
/*----Degree to Radian---*/

double RtD(double x){
	double y;
	y =  x * (180/PI);
	return y;
}
/*----calc-2link_IK-----------------*/
void Inverse_Kinematics(double L1, double L2, double x, double y, double *th_1, double *th_2){
        /*-deg to rad-*

        double rad1 = deg1 * (PI/180);
        double rad2 = deg2 * (PI/180);
        /*------------*
	double rad1 = DtR(deg1);
	double rad2 = DtR(deg2);
	*/

        *th_1 = atan2( y, x ) - atan2(sqrt(x*x + y*y - L1*L1), L1);
        *th_2 = atan2(sqrt(x*x + y*y - L1*L1), L1 ) + atan2(sqrt(x*x + y*y - L2*L2), L2 );

/*--
	double th1 = RtD(*th_1);
	double th2 = RtD(*th_2);
*/
}


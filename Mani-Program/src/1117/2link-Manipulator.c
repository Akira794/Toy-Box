#include <stdio.h>
#include "func.h"
#include <math.h>
//Link1 Link2 joint1 joint2 end

void Forward_Kinematics(double L1, double L2, double deg1, double deg2, double *x, double *y){
	/*-deg to rad-*/

	double rad1 = DtR(deg1);
	double rad2 = DtR(deg2);
	/*------------*/


	*x =L1 * cos (rad1) +L2 * cos (rad1 + rad2);
	*y =L1 * sin (rad2) +L2 * sin (rad1 + rad2);
}

int main(void)
{
	double x,y;
	double a,b;
	printf("input deg1 & deg2 =>");
	scanf("%lf %lf",&a,&b);

	Forward_Kinematics(9.0, 5.0, a, b, &x, &y);

	printf("(x, y) = (%lf, %lf)\n",x,y);
}



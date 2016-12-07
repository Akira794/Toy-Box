#include <stdio.h>
#include <math.h>

#define PI 3.14159265

//Link1 Link2 joint1 joint2 end

void Forward_Kinematics(double L1, double L2, double deg1, double deg2, double *x, double *y){
	/*-deg to rad-*/

	double rad1 = deg1 * (PI/180);
	double rad2 = deg2 * (PI/180);
	/*------------*/


	*x = cos (rad1) + cos (rad1 + rad2);
	*y = sin (rad2) + sin (rad1 + rad2);
}

int main(void)
{
	double x,y;
	double a,b;
	printf("input deg1 & deg2 =>");
	scanf("%lf %lf",&a,&b);

	Forward_Kinematics(1.0, 1.0, a, b, &x, &y);

	printf("(x, y) = (%lf, %lf)\n",x,y);
}



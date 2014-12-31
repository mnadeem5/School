/*
 * prog2.c
 *
 *  Created on: Nov 26, 2013
 *      Author: ZiZo
 */

#include<stdio.h>
#include<stdlib.h>

//creates the complex_t tagged structure
struct complex_t{double real, imaginary;};

//prototypes
struct complex_t mul (struct complex_t x, struct complex_t y);
struct complex_t* divid(struct complex_t* x, struct complex_t* y);

int main (void)
{
	//creates the complex structures
	struct complex_t x, y, z, *p;

	//requests the complex numbers.
	printf("Please enter the first part of the first complex number (a + ib)	a1: ");
	scanf("%lf", &x.real);
	printf("Please enter the second part of the first complex number (a + ib)	b1: ");
	scanf("%lf", &x.imaginary);
	printf("Please enter the first part of the second complex number (a + ib)	a2: ");
	scanf("%lf", &y.real);
	printf("Please enter the second part of the second complex number (a + ib)	b2: ");
	scanf("%lf", &y.imaginary);

	//calculates the the values of the the complex number in both multiplication and division operations.
	z=mul(x, y);
	printf("Result of multiplication: 	%lf + (i) x %lf\n", z.real, z.imaginary);
	p=divid(&x, &y);
	printf("Result of division: 		%lf + (i) x %lf", p->real, p->imaginary);
	return 0;
}

//A function that performs a multiplication operation on two complex numbers.
struct complex_t mul (struct complex_t x, struct complex_t y)
{
	//creates structure z.
	struct complex_t z;
	double xr=x.real, yr=y.real, xi=x.imaginary, yi=y.imaginary;
	//calculates the real and the imaginary parts of the complex number, and returns the value.
	z.real=(xr*yr)-(xi*yi);
	z.imaginary=(xr*yi)+(xi*yr);
	//returns the complex number containing the new value.
	return z;
}

//A function that performs a division operation on two complex numbers.
struct complex_t* divid (struct complex_t *x, struct complex_t *y)
{
	//creates a pointer of type complex_t and points it to the memory location created by the malloc function.
	struct complex_t *z = malloc(sizeof(struct complex_t));
	double xr=x->real, yr=y->real, xi=x->imaginary, yi=y->imaginary;
	//calculates the real and the imaginary parts of the complex number, and returns the value.
	z->real=((xr*yr)+(xi*yi))/(yr*yr+yi*yi);
	z->imaginary=((yr*xi)-(yi*xr))/(yr*yr+yi*yi);
	//returns the pointer to the memory space that the result is conained in.
	return z;
}

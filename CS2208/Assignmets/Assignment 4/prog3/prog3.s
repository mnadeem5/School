		AREA	prog3, CODE, READWRITE
		ENTRY

		MOV		r0, #0x03				;Stores the value of x inside r0.
		ADR		sp, STK					;Loads the address of the memory that will contain the stack values in r13, sp.
		BL		FUN						;calls the FUN, the subroutine
		MOV		r1, r0, LSL #1			;Squares the value of r0 and stores it into r1.
		
ILOOP	B		ILOOP					;Ends the program.

FUN		STMFD 	sp!,{r1, r2, lr}		;Pushes the values of r1, r2, and lr to the stack.
		
		LDR		r1, AV					;Loads the value of variable "a" into r1.
		MUL		r2, r1, r0				;Multiplies r1, the value of "a", by r0, the value of "x" and stores it in r2. r2=x*a
		
		LDR		r1, BV					;Loads the value of variable "b" into r1.
		
		ADD		r1, r1, r2				;Adds r1, the value of "b", and r2, the value of "x*a" and stores it in r1. r1=a*x+b
		MUL		r2, r1, r0				;Multiplies r1, the value of "a*x+b", by r0, the value of "x" and stores it in r2. r2=x*(a*x+b).
		
		LDR		r1, CV					;Loads the value of variable "c" into r1.
		ADD		r0, r2, r1				;Adds the value of r2, "x*(a*x+b)", to the value of of r1, "c" and saves them in to r0. r0=x*(a*x+b)+c
		
		LDR		r1, DV					;Loads the value of variable "d" into r1.	
		
		CMP		r0, r1					;Compares the value of the expression "a*x^2 + b*x + c" to the value of "d"
		MOVGT	r0, r1					;If it is greater, then replace that value with the value of "d"
		
		LDMFD 	sp!,{r1, r2, pc}		;Pops the values of r1, r2, and pc, and returns there previous values.
				
		AREA	prog3, DATA, READWRITE
AV		DCD		-0x05					;Sotores the value of the variable "a" in memory.
BV		DCD		0x06					;Sotores the value of the variable "b" in memory.
CV		DCD		0x07					;Sotores the value of the variable "c" in memory.
DV		DCD		0x32					;Sotores the value of the variable "d" in memory.
		space	0xff					;Leaves space for the stack.
STK		DCD		0x00					;Defines the beginning of the stack.
		END
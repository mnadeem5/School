		AREA	prog2, CODE, READWRITE
		ENTRY
		
		MOV		r0, #4					;Loads the value of n into r0.
		ADR		sp, STK					;Points the sp to the memory location for the stack.
		BL		FUN
	
ILOOP	B		ILOOP					;Ends the program.

FUN		STMFD 	sp!,{r1, r2, lr}		;Pushes the values of the r1, r2 and lr onto the stack.
		BL		FACT					;Calls FACT.
		MUL		r2, r0, r1				;Multiplies n by (n-1)!
		MOV		r0, r2					;Saves the value into r0.
		LDMFD 	sp!,{r1, r2, pc}		;Pops the values of r1, r2 and lr(to pc)back.
	
FACT	STMFD 	sp!,{r0, lr}			;Pushes the values of r0 and lr onto the stack.
	
		CMP		r0, #1					;Checks if the base case is reached.
		MOVEQ	r1, r0					;If it is then moves 1 into r1.
		SUBNE	r0, r0, #1				;If not, then decrement r0.
		BLNE	FACT					;Call FACT.
		
		MUL		r2, r0, r1				;Multiplies r0 by r1, (n-1k)(n-2k)
		MOV		r1, r2					;Moves the result into r1, therefore r1 contains (n-1k)!
		
		LDMFD	sp!, {r0, pc}			;Pops the values of r0 and lr back, return statement.
		
		AREA	prog2, DATA, READWRITE
		ALIGN 
		space	0x3C8					;Leaves space for the stack.
STK		DCD		0x00					;Defines the beginning of the stack.
		END
			
;The largest number that this prgram can calcuate is any n! that is less than 2^32 because the register is a 32 bit register.
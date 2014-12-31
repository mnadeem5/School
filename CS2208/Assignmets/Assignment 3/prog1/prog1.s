		AREA   prog1, CODE, READWRITE
		ENTRY
		
		LDR		r0, =UPC					;loads the memory location of UPC into register zero
		MOV		r1, #0						;loads the value 0 into register one
		MOV		r2, #1						;loads the value 1 into register two
		
LOOP
		LDRB	r3, [r0, r1]				;loads a byte from the memory location r0 at pointer r1 into r3
		LDRB	r4, [r0, r2]				;loads a byte from the memory location r0 at pointer r2 into r4
		
		SUB		r3, r3, #48					;changes the ASCII value of the byte to an integer
		SUB		r4, r4, #48					;changes the ASCII value of the byte to an integer
		
		ADD		r5, r5, r3					;adds up the odd integers
		ADD		r6, r6, r4					;adds up the even integers
		
		ADD		r1, r1, #2					;increments the pointer r1 by 2
		ADD		r2, r2, #2					;increments the pointer r2 by 2
		
		CMP		r1, #12						;checks if r1 is equal to 12 which means we already stored 12 inetegers
		BNE		LOOP						;branches back to LOOP if CMP doesnt return a 0
		
		ADD		r5, r5, LSL #1				;multiplies the r5 value by 3 by using a left logical shift
		
		ADD		r1, r5, r6					;adds the odd numbers multiplied by 3 and the even numbers
		
		MOV		r0, #0						;sets the value of r0 to 0
		B		MOD10						;branches to MOD10
		
MOD10	
		CMP 	r1, #9						;checks if the value og r1 is less than 10 
		SUBGT 	r1, r1, #10					;subtracts 10 from the value of r1
		MOVLT	r0, #1						;changes the value of r0 to 1 if the value of r1 is less than 10
		CMP		r0, #1						;checks if r0 is 1 which means that r1 is less than 10
		BNE		MOD10						;braches to MOD10 again as long as r0 is not 1

CHECK
		CMP		r1, #0						;checks of the final value of r1 is equal to 0
		BEQ		VALID						;braches to VALID if the final value is equal 0 which means that the UPC is valid
		BNE		NOTVLID						;ifnot, branches to NOTVALD
VALID	
		MOV		r0, #1						;sets the value of r0 to 1
		B		JUMP						;braches to JUMP
NOTVLID
		MOV		r0, #0						;sets the value of r0 to 0		
JUMP
	
		AREA    prog1, DATA, READWRITE
UPC 	DCB 	"013800150738", 0 			;UPC string
EoS 	DCB 	0x00 						;end of string
		END              					;end of program
		AREA	prog3, CODE, READWRITE
		ENTRY
		
		ADR		sp, STK
		MOV		r0, #3
		BL		FUN
		MUL		r1, r0, #2

ILOOP	B		ILOOP

FUN		STMFD	sp!, {r1-r3, lr}

		LDR		r1, VA
		LDR		r2, VB
		MLA		r3, r1, r0, r2
		
		LDR		r1, VC
		MLA		r2, r3, r0, r1
		MOV		r0, r2
		
		LDR		r1, VD
		CMP		r0, r1
		MOVGT	r0, r1

		LDMFD	sp!, {r1-r3, pc}

		AREA	prog3, DATA, READWRITE
VA		DCD		5
VB		DCD		6
VC		DCD		7
VD		DCD		90
		space 	0xFF
STK		DCD		0x0
		END

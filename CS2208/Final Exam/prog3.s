		AREA	prog3, CODE, READWRITE
		ENTRY
		
		LDR		r0, =STRING1
		LDR		r1, =STRING2
		
		MOV		r3, #0
		
WHILE
		LDRB	r2, [r0], #1
		
		CMP		r3, #0
		BEQ		CHECK0

		CMP		r3, #1
		BEQ		CHECK1

		CMP		r3, #2
		BEQ		CHECK2
		
		CMP		r3, #3
		BEQ		CHECK3
		
		CMP		r3, #4
		BEQ		CHECK4
		
INSERT	
		STRB	r2, [r1], #1
		CMP		r2, #0
		BNE		WHILE

ILOOP	B		ILOOP
		
CHECK0
		CMP		r2, #0x20
		ADDEQ	r3, #1
		BEQ		INSERT
		CMP		r2, #0x74
		ADDEQ	r3, #2
		B		INSERT
		
CHECK1
		CMP		r2, #0x74
		ADDEQ	r3, #1
		MOVNE	r3, #0
		B		INSERT
		
CHECK2
		CMP		r2, #0x68
		ADDEQ	r3, #1
		MOVNE	r3, #0
		B		INSERT
		
CHECK3
		CMP		r2, #0x65
		ADDEQ	r3, #1
		MOVNE	r3, #0
		B		INSERT
		
CHECK4
		CMP		r2, #0x20
		CMPNE	r2, #0x0
		SUBEQ	r1, #3
	
		MOV		r3, #0
		B		INSERT

		AREA	prog3, DATA, READWRITE
STRING1 DCB 	"the and the man said they must go the" 	;String1
EoS 	DCB 	0x00 										;end of string1
STRING2 space 	0xFF
		END

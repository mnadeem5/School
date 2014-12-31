		AREA	prog2, CODE, READWRITE
		ENTRY
		
		LDR		r0, =STRING1
		LDR		r1, =STRING3
		MOV		r2, #0		
		
WHILE
		LDRB	r3, [r0], #1
		
		CMP		r3, #0
		BEQ		NEXTS
		
		STRBNE	r3, [r1], #1
		
		B		WHILE
		
NEXTS	
		CMP		r2, #0
		ADDEQ	r2, #1
		LDREQ	r0, =STRING2
		BEQ		WHILE
		BNE		DONE
		
DONE	MOV		r0, #0	
		STRB	r0, [r1]
		
ILOOP	B		ILOOP

		AREA	prog2, DATA, READWRITE
STRING1 DCB 	"This is a test string1" 		;String1
EoS 	DCB 	0x00 							;end of string1
STRING2 DCB 	"This is a test string2" 		;String
EoS2 	DCB 	0x00 							;end of string2
STRING3 space 	0xFF
		END
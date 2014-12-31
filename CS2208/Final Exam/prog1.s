		AREA	pro1, CODE, READWRITE
		ENTRY
		
		LDR		r0, =UPC
		SUB		r0, #1
		
		MOV		r4, #3
		MOV		r5, #6
		
LOOP	
		LDRB	r1, [r0, #1]!
		LDRB	r2, [r0, #1]!
		
		SUB		r1, #0x30
		SUB		r2, #0x30
		
		ADD		r3, r1
		MLA		r3, r2, r4, r3
		
REDUCE	CMP		r3, #0xA
		SUBGE	r3, #0xA
		SUBGE	pc, #16
		
		SUBS	r5, #1
		
		BNE		LOOP
		
		MOVEQ	r0, #0
		MOVNE	r0, #1
		
		
ILOOP	B		ILOOP
		
		AREA	prog1, DATA, READWRITE
UPC 	DCB		"010800050738" 		;UPC string
EoS 	DCB	 	0x00 				;end of string
		END
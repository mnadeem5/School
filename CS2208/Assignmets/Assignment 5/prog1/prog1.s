		AREA 	prog1, CODE, READWRITE
		ENTRY
		
		ADR		r0, List					;Stores the address of List into r0.
		LDR		r1, FIND					;Loads the value of FIND into r1.
LOOP	
		LDR		r3, [r0], #4;				;Loads the address of the next item in List.
		LDR		r2, [r0], #4;				;Loads the value of the contents of the item.
		
		CMP		r1, r2						;Compares the value of the item to the value of find.
		BEQ		IN							;If they are equal, branch to IN.
		CMP		r3, #0x00					;Compares the value of the next item's address to null.
		BEQ		OUT							;If they are equal, bra
		MOV		r0, r3;						;Stores the address of the next item in the list into r0.
		B		LOOP						;Branches to loop.
		
OUT		LDR		r2, FAIL					;Loads the value of FAIL into r2.
		B		ILOOP						;Ends the program.
		
IN		LDR		r2, SUC						;Loads the value of SUC into r2.

ILOOP	B 		ILOOP						;Ends the program.
		
		AREA	prog1, DATA, READWRITE
List 	DCD 	Item5, 0x12341111
Item2 	DCD 	Item3, 0x12342222
Item3 	DCD 	Item4, 0x12343333
Item4 	DCD 	Item6, 0x12344444
Item5 	DCD 	Item2, 0x12345555
Item6 	DCD 	Item7, 0x12346666
Item7 	DCD 	0x00,  0x1234777, 0
FIND	DCD		0x12346646, 0				;The value of the item to be found.
SUC		DCD		0xFFFFFFFF, 0				;The success indicator.
FAIL	DCD		0xF0F0F0F0, 0				;The failure indicator.
		END
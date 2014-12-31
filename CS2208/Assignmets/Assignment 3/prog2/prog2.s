		AREA	prog2, CODE, READWRITE
		ENTRY

		ADR		r0, STRING						;set up r0 to point to STRING's memory position
		ADR		r1, PLDRM						;loads the memory location of PLDRM into r1 
		MOV		r2, #0x0						;moves the value of EoS into r2
LOOP
		LDRB	r3, [r1, r4]					;loads a byte from the memory location stored in r1 at pointer r4
		B		CHKLB							;branches to CHKLB
CHKLB	
		CMP		r3, #0x40						;checks if r3's value is above the hexadicimal value 40, which equals to A
		BGT		CHKUB							;branches to CHKUB if the value of r3 is greater
		B		NEXT							;branches to NEXT if its lower
CHKUB		
		CMP		r3, #0x7B						;checks if r3's value is lower than the hexadicimal value 7B, which equals to (
		BLT		CHK0							;branches to CHK0 if the value of r3 is lower
		B		NEXT							;branches to NEXT if its higher
CHK0
		CMP		r3, #0x5A						;checks if r3's value is greater equal or lower than 5A hexadicimal
		BGT		CHK1							;branches to CHK1 if it is greater
		BLE		CNVRT							;branches to CNVRT if it is lower or equal
CHK1
		CMP		r3, #0x61						;checks if r3's value is value is greater equal or lower than 61 hexadicimal
		BGE		CNVRT							;branches to CNVRT if it is greater
		BLT		NEXT							;branches to NEXT if it is not
CNVRT
		CMP 	r3, #0x5B						;checks if the the value of r3 is lower than 5B
		ADDLT	r3, #0x20						;if it is, then it adds 20 hexadicimal to it and makes it lowercase
STORE
		STRB	r3, [r0, r5]					;stores the byte value into the location stored in r0 and pointed by r5
		ADD		r5, r5, #1						;increments the pointer r5
NEXT
		ADD		r4, r4, #1						;incements the points r4
		LDRB	r6, [r1, r4]					;loads the next byte 
		CMP		r6, r2							;checks if the byte is equal to 0x00
		BNE		LOOP							;if it is not, then branches back to LOOP
	
		MOV		r1, r5							;sets the value of r1 to the value of r5
		MOV		r2, #0							;sets the value of r2 to 0
		SUB		r1, r1, #1						;subtracts 1 from the pointer r1
CALC
		LDRB 	r3, [r0, r2]					;loads a byte from the location in r0 pointer by r2
		LDRB	r4, [r0, r1]					;loads a byte from the location in r0 pointer by r1
		ADD		r2, r2, #1						;increments the pointer r2
		SUB		r1, r1, #1						;decrements the pointer r1
		CMP		r3, r4							;checks if r3 and r4 are equal
		BEQ		CALCCHK							;if they are, it branches to CALCCHK
		BNE		FAIL							;if they don't, it branches to FAIL
CALCCHK
		CMP		r2, r1							;checks if the value of r2 points is less than r1
		BLT		CALC							;if it is, it braches back to CALC
		B		PASS							;if its not, then it branches to PASS
FAIL
		MOV		r0, #0							;sets the value of r0 to 0
		B		ILOOP							;branches to JUMP
PASS	
		MOV		r0, #1							;sets the value of r0 to 1
ILOOP	B		ILOOP
		
		AREA 	prog2, DATA, READWRITE
PLDRM 	DCB 	"M!a,da?m+",0					;string
STRING	DCB		0x00							;space for new string
		END
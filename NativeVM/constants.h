/* Fichier généré automatiquement : ne pas éditer. */

/* Constantes pour les opcodes */

/** opcode LABEL */
#define I_LABEL -1

/** opcode GALLOC */
#define I_GALLOC 0

/** opcode PUSH */
#define I_PUSH 1

/** opcode GSTORE */
#define I_GSTORE 2

/** opcode POP */
#define I_POP 3

/** opcode JUMP */
#define I_JUMP 4

/** opcode GFETCH */
#define I_GFETCH 5

/** opcode CALL */
#define I_CALL 6

/** opcode RETURN */
#define I_RETURN 7

/** opcode FETCH */
#define I_FETCH 8

/** opcode JFALSE */
#define I_JFALSE 9

/** opcode STORE */
#define I_STORE 10

/** Noms des opcodes */
extern const char *opcode_names[];

/* Constantes pour les types */

/** type UNIT */
#define T_UNIT 0

/** type INT */
#define T_INT 1

/** type PRIM */
#define T_PRIM 2

/** type FUN */
#define T_FUN 3

/** type BOOL */
#define T_BOOL 4

/* type T_PAIR (réservé) */
#define T_PAIR 5

/** Noms des types */
extern const char *type_names[];

/* Constantes pour les primitives */

/** primitive + */
#define P_ADD 0

/** primitive - */
#define P_SUB 1

/** primitive * */
#define P_MUL 2

/** primitive / */
#define P_DIV 3

/** primitive == */
#define P_EQ 4

/** primitive < */
#define P_INF 5

/** primitive > */
#define P_SUP 6

/** primitive <= */
#define P_INFEQ 7

/** primitive >= */
#define P_SUPEQ 8

/** primitive cons */
#define P_CONS 9

/** primitive car */
#define P_CAR 10

/** primitive cdr */
#define P_CDR 11

/** primitive nil */
#define P_NIL 12

/** primitive print */
#define P_PRINT 13

/** primitive tcreate */
#define P_TCREATE 14

/** primitive tjoin */
#define P_TJOIN 15

/** Noms des primitives */
extern const char *primitive_names[];

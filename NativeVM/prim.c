/* UPMC -- licence informatique
 * (C) 2009-2011 Equipe enseignante
 * LI223: Initiation à la Compilation et aux Machines Virtuelles
 *
 * Redistribution possible sous licence GPL v2.0 ou ultérieure
 */

#include <stdio.h>
#include <assert.h>
#include <stdlib.h>
#include <pthread.h>
#include "vm.h"
#include "constants.h"

/** \file prim.c
 * Implémentation des primitives
 ******/

/** Retourne l'élément neutre d'une primitive arithmétique. */
static int arith_neutral(int prim) {
  switch(prim) {
  case P_ADD:
  case P_SUB: return 0;
  case P_MUL:
  case P_DIV: return 1;
  default: {
    printf("unknow primitive: %d\n", prim);
    abort();
  }
  }
}

/** Application d'une primitive arithmétique. */
static int apply_arith_prim(int prim, int n1, int n2) {
  switch(prim) {
  case P_ADD: return (n1 + n2);
  case P_SUB: return (n1 - n2);
  case P_MUL: return (n1 * n2);
  case P_DIV: return (n1 / n2);
  default: {
    printf("unknow primitive: %d\n", prim);
    abort();
  }
  }
}

/** Application itérée d'un opérateur arithmétique binaire sur n opérandes
 * situés sur la zone de pile.
 * \param[in,out] stack la zone de pile concernée.
 * \param n le nombre d'opérandes (>=1).
 * \param op l'opérateur (primitive) à appliquer.
 */
void do_arith_prim(varray_t *stack, int n, int op) {
  int r;
  assert(n>0);
  // cas spécifique à un seul argument
  // (- n) == -n
  // (/ n) == 1/n
  if(n == 1) {
    r = apply_arith_prim(op, 
                         arith_neutral(op), 
                         value_int_get(varray_top(stack)));
    varray_popn(stack, 1);
  } else { // au moins deux arguments
    int i;
    r = value_int_get(varray_top(stack));
		
    for(i=1; i<n; i++) {
      r = apply_arith_prim(op, r, value_int_get(varray_top_at(stack, i)));
    }
    varray_popn(stack, n-1);
  }

  //varray_expandn(stack, 1);
  value_fill_int(varray_top(stack), r);
}

int do_eq_pair(value_t *pair1, value_t *pair2){
	int r = 0;
	int type1 = value_get_car(pair1)->type;
	int type2 = value_get_cdr(pair1)->type;
	
	//Si on a (paire, paire)
	if((type1 == T_PAIR) && (type2 == T_PAIR)){
		r = do_eq_pair(value_get_car(pair1), value_get_car(pair2))
		 && do_eq_pair(value_get_cdr(pair1), value_get_cdr(pair2));
	}
	//Si on a (paire, autre)
	else if (type1 == T_PAIR){
		r = do_eq_pair(value_get_car(pair1), value_get_car(pair2))
		 && (value_get_cdr(pair1)->data.as_int == value_get_cdr(pair2)->data.as_int);
		
	}
	//Si on a (autre, paire)
	else if (type2 == T_PAIR){
		r = do_eq_pair(value_get_cdr(pair1), value_get_cdr(pair2))
		 && (value_get_car(pair1)->data.as_int == value_get_car(pair2)->data.as_int);
	}
	//Si on a (autre, autre)
	else{   	 
		r = ((value_get_cdr(pair1)->data.as_int == value_get_cdr(pair2)->data.as_int)
		  && (value_get_car(pair1)->data.as_int == value_get_car(pair2)->data.as_int));
	    }
	return r;
}

/** Primitive d'égalité
 * \param[in,out] stack la zone de pile concernée.
 */
void do_eq_prim(varray_t *stack) {
  int r = 0; // par défaut le résultat est faux
  
  // Tester si les types des arguments sont égaux
  if(varray_top_at(stack,0)->type 
     == varray_top_at(stack, 1)->type) {
    switch(varray_top_at(stack,0)->type) {
      // pour les booléens et les entiers, on compare la valeur
    case T_BOOL:
    case T_INT:
      r = (value_int_get(varray_top_at(stack, 0))
           == value_int_get(varray_top_at(stack, 1)));
      break;
    case T_PAIR: 
      /* Ajout JH */
      /*On vérifie si les paires contiennent des éléments de meme type */
      if((value_get_cdr(varray_top_at(stack,0))->type == value_get_cdr(varray_top_at(stack,1))->type)
      && (value_get_car(varray_top_at(stack,0))->type == value_get_car(varray_top_at(stack,1))->type)){
	r = do_eq_pair(varray_top_at(stack,0),varray_top_at(stack,1));
      }
      else{// sinon les types sont differents
      	   printf("Unable to apply eq with types: (%d,%d) and (%d,%d)\n", 
           value_get_car(varray_top_at(stack,0))->type,
           value_get_car(varray_top_at(stack,1))->type,
           value_get_cdr(varray_top_at(stack,0))->type,
           value_get_cdr(varray_top_at(stack,1))->type);
   	   abort();
      };
     
      break;
      // pour le reste on ne peut comparer par égalité (?)
    default:
      printf("Unable to apply eq with type: %d\n", 
             varray_top_at(stack, 0)->type);
      abort();
    }
    
    // on dépile tous les arguments (moins 1).
    varray_popn(stack, 1);
    // et on place le résultat en sommet de pile
    value_fill_bool(varray_top_at(stack, 0), r);  
  }
  else { // sinon les types sont différents
    printf("Unable to apply eq with types: %d and %d\n", 
           varray_top_at(stack, 0)->type,
           varray_top_at(stack, 1)->type);
    abort();
  }
}

/** Primitive de construction
 * \param[in,out] vm l'état de la machine.
 * \param[in,out] stack la zone de pile concernée.
 */
void do_cons_prim(vm_t *vm, varray_t *stack) {
  // C'est facile mais il faut construire la paire
  // directement dans la zone de pile pour qu'elle soit
  // accessible à tout moment par le GC.

  // en sommet de pile on trouve les deux arguments: [car cdr ...]
  
  // on ajoute une place pour le résultat (la paire construite)
  varray_expandn(stack, 1);   
  // initialiser une paire vide
  value_fill_nil(varray_top(stack));   
  // on place le car
  value_set_car(vm,varray_top(stack), varray_top_at(stack, 1)); 
  // et le cdr
  value_set_cdr(vm,varray_top(stack), varray_top_at(stack, 2)); 
  // puis on copie le résultat -> [res cdr res ...]
  varray_set_top_at(stack, 2, varray_top(stack)); 
  // et finalement on enlève les deux premiers éléments -> [res ...]
  varray_popn(stack, 2);                                         
  
}

/** Construction de liste
 * \param[in,out] vm l'état de la machine.
 * \param[in,out] stack la zone de pile concernée.
 * \param n le nombre d'opérandes (>=1).
 */
void do_list_prim(vm_t * vm, varray_t *stack, int n) {
  int i;
  
  // ici la pile contient les éléments
  // de la liste avec le dernier élément au sommet [cadddXXXr .... cadddr caddr cadr car]
  // on ajoute deux éléments au sommet
  varray_expandn(stack, 2);       
  // le premier est le résultat
  value_fill_nil(varray_top_at(stack, 0));

  for(i=0; i<n; i++) { // on itère pour tous les éléments
    // copier le résultat (au sommet) sur le second
    varray_set_top_at(stack, 1,varray_top_at(stack, 0));
    // on reconstruit une liste au sommet
    value_fill_nil(varray_top(stack)); 
    // on va chercher le car en  n-i-1+2  et on le met au sommet
    value_set_car(vm,varray_top(stack), 
                  varray_top_at(stack, n - i + 1)); 
    // et le cdr est le reste de la construction (le résultat)
    value_set_cdr(vm,varray_top(stack), varray_top_at(stack, 1));

  }
  
  // on copie le résultat (au sommet) à l'index n - 1 + 2 
  varray_set_top_at(stack, n + 1,varray_top(stack));

  // on a donc sur la pile: [temp1 temp2 car cadr ....  res]   (temp1 en sommet de pile)
  // on dépile les n-1+2 premiers éléments et il reste donc sur la pile: [res]
  varray_popn(stack, n + 1); 

}

/** Premier élément d'une paire.
 * \param[in,out] stack la zone de pile concernée.
 */
void do_car_prim(varray_t *stack) {
  varray_set_top(stack, value_get_car(varray_top(stack)));  
}

/** Second élément d'une paire.
 * \param[in,out] stack la zone de pile concernée.
 */
void do_cdr_prim(varray_t *stack) {
  varray_set_top(stack, value_get_cdr(varray_top(stack)));  
}

void* vm_execute_aux(void * arg){
	vm_execute(arg);
	return NULL;
}

void do_th_create_prim(vm_t * vm, varray_t *stack){
	int i,r;
	program_t * progthread = malloc(sizeof(program_t));
	//Recopie du programme de la vm
	progthread->bytecode = malloc(sizeof(int)*vm->program->size);
	progthread->size = vm->program->size;
	for(int i=0; i<vm->program->size; i++){
		progthread->bytecode[i] = vm->program->bytecode[i];	
	}
	// On change le programme en remplacant l'appel a la primitive par l'appel a la fonction
	progthread->bytecode[vm->frame->pc-5] = 6; //CALL
	progthread->bytecode[vm->frame->pc-4] = 1; //1
 	//progthread->bytecode[vm->frame->pc-3] = 3; //POP
	// Puis on lui dis d'ignorer le reste du programme une fois revenu de la fonction
	progthread->size = vm->frame->pc-4;

/*printf("Program vm. Size : %d pc : %d\n",vm->program->size, vm->frame->pc);
bytecode_print(vm->program);
printf("----------------------------\n");
printf("Progthread. Size : %d\n",progthread->size);
bytecode_print(progthread);*/
	
	//Initialisation de la vm qui va exécuter le thread
	vm_t * vmth = init_vm(progthread, vm->debug_vm, 0, DEFAULT_GC_FREQUENCY);

	//Le tas et le gc sont partagés par les deux vm
	vmth->globs = vm->globs;
	vmth->gc = vm->gc;
	//On place le pointeur de pile de vmth pour re-executer la mise en pile des arguments
	vmth->frame = frame_push(NULL, NULL, 0, vm->frame->pc-10);
	
	//Creation du thread POSIX
	pthread_t thread;
	i = pthread_create(&thread, NULL, &vm_execute_aux, (void*)vmth);
	if(i){	
		printf("Erreur creation thread : -%d-\n",i);
	}
	//Stockage du pthread_t dans la vm initiale
	vm->threads[vm->nbthreads] = thread;
	//Association du pthread_t avec un entier
	r = ++vm->nbthreads;
	//Depilement des arguments
	varray_popn(stack,1);
	//Empilement de la valeur de retour
	value_fill_int(varray_top(stack), r);
	
}

void do_th_join_prim(vm_t * vm, varray_t *stack) {
	int r=0;
	value_t * t_num;
	//Recupération de l'index ou est stocké le pthread_t
	t_num = varray_top(stack);
	//Join POSIX sur le pthread_t
	if(pthread_join(vm->threads[t_num->data.as_int-1],NULL)){
		printf("Erreur join thread \n");	
		r=1;
	}
	//Copie de la valeur de retour sur la pile
	value_fill_int(varray_top(stack), r);
}

/** Exécution d'une primitive.
 * Les arguments sont sur la pile du cadre d'appel (frame) courant 
 * (premier argument au sommet, etc.).
 * \param[in,out] stack la zone de pile.
 * \param prim le numéro de la primitive à invoquer.
 * \param n le nombre d'arguments à dépiler.
 */
void execute_prim(vm_t * vm, varray_t *stack, int prim, int n) {
  /*printf("P_TCREATE : -%d- prim before switch -%d-",P_TCREATE,prim);*/
  switch(prim) {
    
    // Les fonctions arithmétiques sont traitées d'un coup.
  case P_ADD: 
  case P_SUB: 
  case P_MUL: 
  case P_DIV: 
    do_arith_prim(stack, n, prim); break;
    
    // égalité
  case P_EQ:
    do_eq_prim(stack); break;
    break;

  
    // Construire une nouvelle paire.
  case P_CONS:
    do_cons_prim(vm,stack); break;
    break;
    
   /*  // On construit une liste, donc une suite de paires:
  case P_LIST: 
    do_list_prim(vm,stack,n); break; */

    // car 
  case P_CAR:
    do_car_prim(stack); break;

    // cdr
  case P_CDR:
    do_cdr_prim(stack); break;

  case P_TCREATE:
    do_th_create_prim(vm,stack);
    break;

  case P_TJOIN:
    do_th_join_prim(vm,stack);
    break;
    
    
  default:
    printf("unknow primitive: %d with %d args\n", prim, n);
    abort();
  }
  
}

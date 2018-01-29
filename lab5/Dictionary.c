//Dictionary.c
//Aaron Hom
//awhom
//lab5
//Implements the Dictionary ADT (in C!)

#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include<assert.h>
#include"Dictionary.h"

// private types --------------------------------------------------------------

// NodeObj
typedef struct NodeObj{
  char* key;
  char* val;
  struct NodeObj* next;
} NodeObj;

// Node
typedef NodeObj* Node;

// newNode()
// constructor of the Node type
Node newNode(char* k, char* v) {
  Node P = malloc(sizeof(NodeObj));
  assert(P != NULL);
  P->key = k;
  P->val = v;
  P->next = NULL;
  return (P);
}

// freeNode()
// destructor for the Node type
void freeNode(Node* pP){
  if(pP != NULL && *pP != NULL){
    free(*pP);
    *pP = NULL;
  }
}

//DictionaryObj
typedef struct DictionaryObj{
  Node head;
  Node tail;
  int numItems;
} DictionaryObj;


// public functions -----------------------------------------------------------

// Dictionary()
// constructor for the Dictionary type
Dictionary newDictionary(void){
  Dictionary D = malloc(sizeof(DictionaryObj));
  assert(D != NULL);
  D->head = NULL;
  D->tail = NULL;
  D->numItems = 0;
  return D;
}

// freeDictionary()
// destructor for the Dictionary type
void freeDictionary(Dictionary* pD){
  if(pD != NULL && *pD != NULL){
    if(!isEmpty(*pD))makeEmpty(*pD);
      free(*pD);
      *pD = NULL;
  }
}


// isEmpty()
// returns 1 (true) if D is empty, 0 (false) otherwise
// pre: none
int isEmpty(Dictionary D){
  if(D == NULL){
    fprintf(stderr, "Dictionary Error: calling isEmpty() on NULL Stack reference\n");
  }  
  if(D->numItems == 0){
    return 1;
  }else{
    return 0;
  }
}

//size()
//returns number of key/val (k, v) pairs in Dictionary D
//pre: none
  int size(Dictionary D){
    return D->numItems;
  }

// lookup()
// returns value that the pair key/val is in, and returns NULL it doesn't exist
// pre: none
char* lookup(Dictionary D, char* k){
  Node P = D->head;
  if(D == NULL){
    fprintf(stderr, "Dictionary Error: calling lookup() on NULL Dictionary\n");
    exit(EXIT_FAILURE);
  }
  while(P != NULL){
    if(strcmp(P->key, k) == 0)
      return P->val;
      P = P->next;
    }
      return NULL;
}

// insert()
//inserts a new key/val pair (k, v) into Dictionary D
// pre: key does not already exist
  void insert(Dictionary D, char* k, char* v){
    if(lookup(D, k) != NULL){
      fprintf(stderr, "Cannot insert already existing key and value pair\n");
      exit(EXIT_FAILURE);
    }
    if(D->numItems == 0){
      D->head = D->tail = newNode(k, v);
    }else{
      Node P = newNode(k, v);
      D->tail->next = P;
      D->tail = P;
    }
    D->numItems++;
}

// delete()
// deletes the pair assoicated with the key x
// pre: !isEmpty(D)
  void delete(Dictionary D, char* k){
    Node P = D->head;  
    if(lookup(D, k) == NULL){
      fprintf(stderr, "Dictionary Error: cannot delete non existent key\n");
      exit(EXIT_FAILURE);
    }
    if(strcmp(P->key, k) == 0){
      Node Q = D->head;
      Node R = Q->next;
      D->head = R;
      freeNode(&Q);
    }else{
      while(P != NULL && P->next != NULL){
	if(strcmp(P->next->key, k) == 0){
	  Node R = P;
	  Node T = P->next;
	  R->next = T->next;
	  P = R;
	  freeNode(&T);
	}
	P = P->next;
      }
    }
    D->numItems--; 
  }

// makeEmpty()
// resets Dictionary D to an empty state
// pre: none
  void makeEmpty(Dictionary D){
    Node P = D->head;
    while(P != NULL){
      free(P);
      P = P->next;
    }
    D->head = NULL;
    D->tail = NULL;
    D->numItems = 0;
  }

// printDictionary()
// prints a text representation of Dictionary A to the file pointed to by out
// pre: none
void printDictionary(FILE* out, Dictionary D){
  Node P;
  if(D == NULL){
    fprintf(stderr, "Dictionary Error: calling printDictionary() on NULL Dictionary reference\n");
    exit(EXIT_FAILURE);
  }
  for(P = D->head; P != NULL; P = P->next){
    fprintf(out, "%s %s \n", P->key, P->val);
  }
}

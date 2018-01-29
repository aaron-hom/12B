//Dictionary.c
//Aaron Hom
//awhom
//pa5
//A Dictionary ADT that uses a hash table

#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include<assert.h>
#include"Dictionary.h"
const int tableSize = 101;

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

//ListObj
typedef struct ListObj{
  Node head;
} ListObj;

//List
typedef ListObj* List;

//newList()
//constructor for Node type
List newList(void){
  List L = malloc(sizeof(ListObj));
  assert(L != NULL);
  L->head = NULL;
  return L;
}

void freeList(List* pL){
  if(pL != NULL && *pL != NULL){
  free(*pL);
  *pL = NULL;
  }
}

//DictionaryObj
typedef struct DictionaryObj{
  int numItems;
  List table;
} DictionaryObj;

//hash functions --------------------------------------------------------------

//rotateLeft()
// rotate the bits in an unsigned int
unsigned int rotate_left(unsigned int value, int shift) {
  int sizeInBits = 8*sizeof(unsigned int);
  shift = shift & (sizeInBits - 1);
  if ( shift == 0 )
    return value;
  return (value << shift) | (value >> (sizeInBits - shift));
}

// pre_hash()
// turn a string into an unsigned int
unsigned int pre_hash(char* input) { 
  unsigned int result = 0xBAE86554;
  while (*input) { 
    result ^= *input++;
    result = rotate_left(result, 5);
  }
  return result;
}

// hash()
// turns a string into an int in the range 0 to tableSize-1
int hash(char* key){
  return pre_hash(key)%tableSize;
}

// private helper functions --------------------------------------------------

//findKey()
//returns the Node containing the key k in the tree subrooted at P, or returns NULL if no key exists
Node findKey(Node P, char* k){
  while(P != NULL){
    if(strcmp(P->key, k) == 0){
      break;
    } P = P->next;
  } return P;
}

//deleteAll()
//deletes all Nodes in the subtree rooted at P

  void deleteAll(Node P){
    if(P != NULL){
      deleteAll(P->next);
      freeNode(&P);
     }
  }
// public functions -----------------------------------------------------------

// Dictionary()
// constructor for the Dictionary type
Dictionary newDictionary(void){
  Dictionary D = malloc(sizeof(DictionaryObj));
  assert(D != NULL);
  D->table = calloc(tableSize, sizeof(ListObj));
  D->numItems = 0;
  return D;
}

// freeDictionary()
// destructor for the Dictionary type
void freeDictionary(Dictionary* pD){
  if(pD != NULL && *pD != NULL){
    if(!isEmpty(*pD)) makeEmpty(*pD);
    free(*pD);
    *pD = NULL;
  }
}

// isEmpty()
// returns 1 (true) if D is empty, 0 (false) otherwise
// pre: none
int isEmpty(Dictionary D){
  if(D == NULL){
    fprintf(stderr, "Dictionary Error: calling isEmpty() on unfound Dictionary reference\n");
    exit(EXIT_FAILURE);
  }
  return(D->numItems == 0);
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
  int tabIndex;
  Node P;
  List L;
  tabIndex = hash(k);
  L = &D->table[tabIndex];
  P = findKey(L->head, k);
  if(P == NULL)
    return NULL;
  else
    return P->val;
}

// insert()
//inserts a new key/val pair (k, v) into Dictionary D
// pre: key does not already exist
void insert(Dictionary D, char* k, char* v){
  int tabIndex;
  Node P;
  List L;
  tabIndex = hash(k);
  P = newNode(k, v);
  L = &D->table[tabIndex];
  if(findKey(L->head, k) != NULL){
    fprintf(stderr, "Dictionary Error: cannot insert a key that already exists\n");
    exit(EXIT_FAILURE);
  }
    P->next = L->head;
    L->head = P;
    P = NULL;
    D->numItems++;
}

//delete()
// deletes the pair assoicated with the key x
// pre: !isEmpty(D)
void delete(Dictionary D, char* k){
  if(D == NULL){
    fprintf(stderr, "Dictionary Error: cannot delete non existent key\n");
    exit(EXIT_FAILURE);
  }
  int tabIndex;
  Node P;
  List L;
  tabIndex = hash(k);
  L = &D->table[tabIndex];
  P = L->head;
  if(findKey(L->head, k) == L->head){
    P = L->head;
    L->head = L->head->next;
    freeNode(&P);
  }else{
    P = findKey(L->head, k);
    Node keep = L->head;
    Node temp = L->head->next;
    while(temp != P){
      temp = temp->next;
      keep = keep->next;
    }
    keep->next = P->next;
    P->next = NULL;
  }
  D->numItems--;
  freeNode(&P);
}

// makeEmpty()
// resets Dictionary D to an empty state
// pre: none
void makeEmpty(Dictionary D){
  List L;
  if(D->numItems == 0){
    fprintf(stderr, "Dictionary Error: calling makeEmpty on empty Dictionary\n");
  }
  if(D == NULL){
    fprintf(stderr, "Dictionary Error: calling makeEmpty on unfound Dictionary reference\n");
    exit(EXIT_FAILURE);
  }
  for(int i = 0; i < tableSize; i++){
    L = &D->table[i];
    deleteAll(L->head);
    free(&D->table[i]);
    }
  D->table = NULL;
  D->numItems = 0;
}

// printDictionary()
// prints a text representation of Dictionary A to the file pointed to by out
// pre: none
void printDictionary(FILE* out, Dictionary D){
  Node P;
  List L;
  if(D == NULL){
    fprintf(stderr, "Dictionary Error: calling printDictionary() on NULL Dictionary reference\n");
    exit(EXIT_FAILURE);
  }
  for(int i = 0; i < tableSize; i++){
    L = &D->table[i];
    P = L->head;
    while(P != NULL){
    fprintf(out, "%s %s \n", P->key, P->val);
    P = P->next;
    }
  }
}

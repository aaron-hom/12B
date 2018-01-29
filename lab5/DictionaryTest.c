//DictionaryTest.c
//Aaron Hom
//awhom
//lab5
//A client to test different functions from Dictionary.c

#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include"Dictionary.h"
#define MAX_LEN 180

int main(int argc, char* argv[]){  

  Dictionary library = newDictionary();
  char* x;
  char* y;
  char* book1[] = {"7", "11", "23", "27", "96"};
  char* book2[] = {"hello", "hola", "ni hao", "shalom", "czesc"};
  int i;

  for(i = 0; i < 5; i++){
    insert(library, book1[i], book2[i]);
  }

  printDictionary(stdout, library);

  printf("%d\n", size(library));
  printf("%s\n", (isEmpty(library)?"true":"false"));

  delete(library, "11");
  delete(library, "96");
  //delete(library, "12"); //returns non-existent key error

  //insert(library, "27", "hi") //returns duplicate key error

  printDictionary(stdout, library);
  printf("%d\n", size(library));

  makeEmpty(library);
  printf("%s\n", (isEmpty(library)?"true":"false"));

  freeDictionary(&library);
  return(EXIT_SUCCESS);
}


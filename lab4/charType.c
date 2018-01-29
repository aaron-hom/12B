//charType.c
//Aaron Hom
//awhom
//Lab 3
//Takes input file and classifies the characters on each line, printed to out file

#include<stdio.h>
#include<ctype.h>
#include<stdlib.h>
#include<stdlib.h>
#include<assert.h>
#include<string.h>

#define MAX_STRING_LENGTH 100

void extract_chars(char* s, char* a, char* d, char* p, char* w);

int main(int argc, char* argv[]){
   FILE* in; //handle for input file
   FILE* out; // handles for  output files 
   char* line; // string holding input line 
   char* alpha;
   char* digit;
   char* punct;
   char* whi_spa;

   // check command line for correct number of arguments
   if(argc != 3){
      printf("Usage: %s <input file> <output file>\n", argv[0]);
      exit(EXIT_FAILURE);
   }

   // open input file for reading 
   in = fopen(argv[1], "r");
   if(in==NULL){
      printf("Unable to read from file %s\n", argv[1]);
      exit(EXIT_FAILURE);
   }

   // open output file for writing 
   out = fopen(argv[2], "w");
   if(out==NULL){
      printf("Unable to write to file %s\n", argv[2]);
      exit(EXIT_FAILURE);
   }

   // allocate strings line and alpha_num on the heap 
   line = calloc(MAX_STRING_LENGTH+1, sizeof(char));
   alpha = calloc(MAX_STRING_LENGTH+1, sizeof(char));
   digit = calloc(MAX_STRING_LENGTH+1, sizeof(char));
   punct = calloc(MAX_STRING_LENGTH+1, sizeof(char));
   whi_spa = calloc(MAX_STRING_LENGTH+1, sizeof(char));
   assert(line != NULL && alpha != NULL && digit != NULL && punct != NULL && whi_spa != NULL);

   // read words from each line in input file, extracts 5 types of characters
   int i = 1; 
   while(fgets(line, MAX_STRING_LENGTH, in) != NULL){
       extract_chars(line, alpha, digit, punct, whi_spa);
       fprintf(out, "line %d contains: \n", i);
       if(strlen(alpha) != 1){
           fprintf(out, "%d alphabetic characters: %s\n", (int)strlen(alpha), alpha);
       }else{
           fprintf(out, "%d alphabetic character: %s\n", (int)strlen(alpha), alpha);
       }
       if(strlen(digit) != 1){
           fprintf(out, "%d numeric characters: %s\n", (int)strlen(digit), digit);
       }else{
	   fprintf(out, "%d numeric character: %s\n", (int)strlen(digit), digit);
       }
       if(strlen(punct) != 1){
           fprintf(out, "%d punctuation characters: %s\n", (int)strlen(punct), punct);
       }else{
	   fprintf(out, "%d punctuation character: %s\n", (int)strlen(punct), punct);
       }
       if(strlen(whi_spa) != 1){
           fprintf(out, "%d white space characters: %s\n", (int)strlen(whi_spa), whi_spa);
       }else{
	   fprintf(out, "%d white space character: %s\n", (int)strlen(whi_spa), whi_spa);
       }
       fprintf(out, "\n");
       i++;
   }
   

   // free heap memory 
   free(line);
   free(alpha);
   free(digit);
   free(punct);
   free(whi_spa);

   // close input and output files 
   fclose(in);
   fclose(out);

   return(EXIT_SUCCESS);
}

void extract_chars(char* s, char* a, char* d, char* p, char* w){    
  int i=0, j=0, k=0, l=0, m=0;
    while(s[i] != '\0' && i < MAX_STRING_LENGTH){
        if(isalpha((int)s[i])){
	    a[j++] = s[i];
	}
        if(isdigit((int)s[i])){
            d[k++] = s[i];
        }
        if(ispunct((int)s[i])){
	    p[l++] = s[i];
        }
        if(isspace((int)s[i])){
            w[m++] = s[i];
       }
       i++;
    }
    a[j] = '\0';
    d[k] = '\0';
    p[l] = '\0';
    w[m] = '\0';
}

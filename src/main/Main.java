package main;

public class Main {
	
	public static void main(String[] args) {
		int []myArr=new int[12];
		System.out.println("Array: ");
		myArr[0]=9;
		myArr[1]=7;
		myArr[2]=2;
		myArr[3]=5;
		myArr[4]=-6;
		myArr[5]=1;
		myArr[6]=4;
		myArr[7]=5;
		myArr[8]=8;
		myArr[9]=-23;
		myArr[10]=2;
		myArr[11]=-6;
		mostrar(myArr);
		System.out.println("\nArray Ordenado: ");
		mostrar(QuikShortLL(myArr));
	}

	//MOSTRAR ARRAY
	public static void mostrar(int [] myArr) {
		System.out.print("[");
		for(int i=0;i<myArr.length;i++) {
		    if(i!=myArr.length-1) System.out.print(myArr[i]+", ");
			else System.out.println(myArr[i]+"]");
		}
	}

	//ALGORITMO DE INSERCIÓN
	public static void insertion(int[] myArr) {
		int i,j,aux;
		for(int pos=1;pos<myArr.length;pos++) {
			i=pos;
			j=pos-1;
			//Look 4 acktracking gap 1 always
			while(j>=0&&myArr[j]>myArr[i]) {
				aux=myArr[i];
				myArr[i]=myArr[j];
				myArr[j]=aux;
				i--;
				j--;
			}
		}
	}
	
	//ALGORITMO DE LA BURBUJA
	public static void bubleShort(int []myArr) {
		int pasada=0,numPasadas,j,aux;
		do{
			numPasadas=0;
			for(int i=0;i<myArr.length-pasada;i++) {
				j=i+1;
				if(j<myArr.length-pasada&&myArr[i]>myArr[j]) {
					aux=myArr[i];
					myArr[i]=myArr[j];
					myArr[j]=aux;
					numPasadas++;
				}
			}
			pasada++;
		}while(numPasadas>0);
	}
 	
	//ALGORITMO DE SHELL
	public static void shell(int []myArr) {
		int pasada=1,k,aux,j; //k: gap
		do {
			k=myArr.length/(2*pasada);
			for(int i=0;i<myArr.length;i++) {
				if((i+k)>=myArr.length) break;
				else {
					//Look 4 change
					if(myArr[i]>myArr[i+k]) {
						aux=myArr[i];
						myArr[i]=myArr[i+k];
						myArr[i+k]=aux;
						//Look 4 backtraking with my gap (k)
						j=i;
						while((j-k)>=0) {
							if(myArr[j-k]<myArr[j]) break;
							else {
								aux=myArr[j];
								myArr[j]=myArr[j-k];
								myArr[j-k]=aux;								
							}
							j-=k;
						}
					}
				}
			}
			pasada++;
		}while(k>1);
	}
	
	//ALGORITMO DE MERGESORT
	public static int[] mergeShort(int []myArr) {
		int length1=(int) myArr.length/2,j=0;
		int[] izda=new int[length1];
		int[] dcha=new int[myArr.length-length1];
		
		//Partimos el array
		for(int i=0;i<length1;i++) izda[i]=myArr[i];
		for(int i=length1;i<myArr.length;i++) {
			dcha[j]=myArr[i];
			j++;
		}
		//El myArr será tu izda o tu derecha de una versión más grande
		//izda-->myArr||dcha-->myArr
		if(myArr.length==1) return myArr;
		mergeShort(izda);
		mergeShort(dcha);
		ordenar(myArr,izda,dcha);	
		return myArr;
	}
	
	  public static void ordenar(int v[], int izq[],int der[]){
          int i=0;
          int j=0;
          for(int k=0;k<v.length;k++){
                  if(i>=izq.length){
                          v[k]=der[j];
                          j++;
                          continue;
                  }
                  else if(j>=der.length){
                          v[k]=izq[i];
                          i++;
                          continue;
                  }
                  if(izq[i]<der[j]){
                          v[k]=izq[i];
                          i++;
                  }
                  else {
                          v[k]=der[j];
                          j++;
                  }
          }
	  }
	  
	  //ALGORITMO DE QUIKSORT
	  public static int[] QuikShortLL(int []myArr) {
		  int pivote=myArr.length-1,pRed=0,pBlue=0,cont=0;
		  int[]izda;
		  int[]dcha;
		  
		  //1º
		  while(pRed<myArr.length){
			  if(pRed==myArr.length-1&&myArr[pivote]<myArr[pBlue]) {
				 pivote=pBlue;
				 intercambio(myArr,pRed,pivote);
			  }
			  else {
				  if(myArr[pRed]<myArr[pivote]) {
					 intercambio(myArr,pRed,pBlue);
					  pBlue++;
				  }
			  }
			  pRed++;
		  }

		  //2º
		  if(pivote>0) {
			  if(pivote==myArr.length-1) {
				  izda=new int[myArr.length-1];
				  dcha=new int[1];			  
			  }
			  else {
				  izda=new int[pivote+1];
				  dcha=new int[myArr.length-pivote-1];
			  }
		  }
		  else {
			  izda=new int[1];
			  dcha=new int[myArr.length-1];
		  }
		  
		  //3º
		  cont=0;
		  for(int i=0;i<izda.length;i++) {
			  izda[i]=myArr[i];
		  }
		  for(int i=izda.length;i<myArr.length;i++) {
			  dcha[cont]=myArr[i];
			  cont++;
		  }
		  
		  if(myArr.length==1) return myArr;
		  
		  QuikShortLL(izda);
		  QuikShortLL(dcha);
		  cont=0;
		  for(int i=0;i<myArr.length;i++) {
			  if(i>=izda.length) {
				  myArr[i]=dcha[cont];
				  cont++;
			  }
			  else myArr[i]=izda[i];
		  }
		  return myArr;
	  }
	  
	  private static void intercambio(int[]myArr,int in1,int in2) {
		  int aux;
		  aux=myArr[in1];
		  myArr[in1]=myArr[in2];
		  myArr[in2]=aux;
	  }

}

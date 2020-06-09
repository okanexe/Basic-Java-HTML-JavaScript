def islem(kelime , operasyon , indeks):
    
    if(operasyon=="*"):
        yildiz(kelime,indeks)
        return yildiz(kelime,indeks)
    elif(operasyon=="+"):
        arti(kelime,indeks)
        return arti(kelime,indeks)
    else:
        return False
    
def yildiz(kelime , indeks):
    a=0
    b1=0
    b2=0
    string =""
    liste=list(kelime)
    if(liste[indeks]=="*"):
        indeks=indeks+1
    while(liste[indeks]== "*"):
        indeks=indeks+1 
    for i in range(indeks,len(liste)) :
        if(liste[i]=="*"):
            a=a+1
            if(a==1):
                b1=i
            if(a==2):
                b2=i+1
    if(a==0):
        for i in liste:
            string=string + i            
        return string
    elif(a==1):
        del liste[b1]
        for i in liste:
            string=string + i
        return string
    elif(a>=2):
        del liste[b1:b2]
        for i in liste:
            string=string + i
        return string


def arti(kelime , indeks):
    d=0
    d1=0
    d2=0
    artiListeEkle=[]
    artiString=""
    artiListe=list(kelime)
    if(artiListe[indeks]=="+"):
        indeks = indeks+1
    while(artiListe[indeks]=="*"):
        indeks = indeks +1
    if artiListe == "*":
        return artiListe
    for j in range(indeks,len(artiListe)):
        if(artiListe[j]=="+"):
            d+=1
            if(d==1):
                d1=j
            if(d==2):
                d2=j
    if(d==0):
        for k in artiListe:
            artiString=artiString + k
        return artiString
    if(d==1):
        del artiListe[d1]
        for k in artiListe:
            artiString=artiString + k
        return artiString

    if(d>=2):
       
        for g in range(d1+1,d2):
            artiListeEkle.append(artiListe[g])
        del artiListe[d1:d2+1]
        artiListe=artiListe+artiListeEkle
        for k in artiListe:
            artiString=artiString + k
        return artiString
    
print(islem('*****', '*', 3))

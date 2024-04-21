# Mobil programozás beadandó
  
**ReAction - reakcióidő mérő alkalmazás**

Az alkalmazás célja: a felhasználó reakcióidejének mérése

Bemutatóvideó az alkalmazásról használat közben: 
[link a videóhoz](https://www.youtube.com/watch?v=OydSBuQ9D5k)

[link a forráskódhoz (master branch)](https://github.com/tamasregenye/mobilprog_beadando/tree/master)

Code majd Download ZIP-re kattintva letölthető a projekt

  
# Funkciók
- reakcióidő mérése (erről bővebben később)
- Kétnyelvűség (angol, magyar)
- világos és sötét mód
- világos és sötét mód közötti automatikus váltás a fényérzékelő szenzor mérései alapján
    - 50 luxig sötét mód
    - 50 luxnál nagyob érték esetén világos mód
    - Megjegyzés: az automatikus mód bekapcsolása esetén manuálisan nem válthatunk a módok között
- Github profilom megnyitása a Credits/Rólunk menüben
- kilépés az alkalmazásból

# Reakcióidő mérése
- A főmenüből elindítható
- 1 másodpercenként felvillannak a fények, majd ha mindhárom lámpa piros színű akkor 0.1 és 5 mp közötti véletlenszerűen eltelt idő után kialszanak a fények
- ezután kell a képernyőhöz hozzáérni (ha túl korán koppintottunk akkor ezt is jelzi az alkalmazás)
- a szoftver végül kiírja a reakcióidőt, és Toast üzenetben visszajelzést is ad gyorsaságunkról
- a játék bármennyiszer újraindítható

## Android Studio verzió
Az általam használt verzió az Android Studio HedgeHhg | 2023.1.1 Patch 2 volt

1.Trieda Drop nám slúži na to, aby postava po stlačení BACKSPACE mohla zhadzovať predmety z batohu na zem.

2.Trieda Fire umožňuje postave vystreliť po stlačení SPACE, čím sa nastaví pohyb v smere postavy.

3.Trieda Move umožňuje vďaka šípkam UP, RIGHT, DOWN, LEFT nastaviť smer pohybu postavy na základe jej rýchlosti a polohy, ak pri stretnutí postava narazí do steny, cez ňu nemôže prejsť a umožní strele zasiahnuť stenu a hercov.

4.Trieda Shift umožňuje presúvať predmety v batohu vďaka S.

5.Trieda Take umožňuje vďaka ENTER vziať zberateľské predmety v batohu a potom s nimi môžete interagovať.

6.Trieda Use umožňuje interakciu predmetov z batohu s inými objektmi na scéne s typom Usable vďaka U.

7.Rozhranie Behaviour je spôsob, ako definovať a nastavovať správanie aktérov v hre.

8.Trieda Observing umožňuje postavám reagovať na udalosti tak, že kontroluje podmienky a spúšťa požadované akcie.

9.Trieda RandomlyMoving nastavuje chaotický pohyb postáv, ktorý nezávisí od ovládania hráčom.

10.Trieda Alien vytvára nepriateľskú nepriateľskú postavu, ktorá má vlastné zdravie a rýchlosť, keď sa zrazí s Ripleym, uberú mu zdravie. 

11.Rozhranie Alive umožňuje postavám v tomto rozhraní mať vlastné zdravie a interagovať s ním obnovovaním alebo odoberaním.

12.Rozhranie Armed umožňuje nastaviť a ovládať ich zbrane. 

13.Rozhranie Enemy nastavuje typ postáv vytvorených v hre.

14.Trieda Health spravuje zdravie aktérov tým, že ukladá ich maximálne množstvo zdravia a udržiava ho aktuálne, okrem toho funkcia umožňuje rôzne interakcie s ním obnovovaním alebo odoberaním.

15.Trieda Ripley vytvára hlavnú postavu našej hry, ktorá má svoje vlastné množstvo zdravia a rýchlosti vďaka šípkam ho môžeme ovládať a vďaka funkcii Fire strieľať zo svojich zbraní v sebe preberá väčšinu funkcií ako batoh, zdravie, strelná zbraň a iné vďaka tejto funkcii a na obrazovke vidíme aktuálne množstvo jeho zdravia a munície a ktorá spracováva jeho animáciu v prípade smrti.

16.Trieda KeeperController umožňuje ovládať akcie z balíka akcií, ktoré sa vykonávajú na objektoch typu Keeper vďaka klávesom na klávesnici.

17.Trieda MovableController umožňuje pomocou šípok na klávesnici nastaviť trajektóriu pohybu postáv typu Movable a podporuje aj prípady, keď sú šípky stlačené súčasne. 

18.Trieda ShooterController umožňuje inicializovať výstrely zo zbrane postavy typu Armed pomocou stlačenia klávesy na klávesnici.

19.Trieda Ammo implementuje muníciu postavy realizujúcu rozhranie Usable<Armed>, ktoré umožňuje interakciu so Zbraní postavy, napríklad ako vyberanie a ukladanie jej množstva.

20.Trieda Batoh implementuje ukladanie predmetov pre postavy, zabezpečuje ich pridávanie, mazanie, kontrolu, či sú plné, a zobrazovanie ich obsahu. Poskytuje tiež možnosť prepínať dostupné predmety

21.Rozhranie Collectible umožňuje určiť typ aktérov, s ktorými chcete komunikovať, konkrétne toto rozhranie umožňuje označiť predmety, ktoré možno zdvihnúť. 

22.Trieda Energia implementuje animáciu pre samotné zdravie, umožňuje doplniť zdravie hlavnej postavy, ak bude po zdvihnutí menšie ako zadané množstvo, zmizne zo scény.

23.Použiteľné rozhranie umožňuje určiť typ objektov, s ktorými možno v budúcnosti interagovať, pričom objekty môžu implementovať metódy v ňom uvedené.

24.Trieda Door vytvára na scéne dvere, ktoré môžu byť dvoch typov, buď VERTIKÁLNE alebo HORIZONTÁLNE, čo umožňuje tieto dvere zatvárať a otvárať v prípade, že sú dvere zatvorené nastavuje typ WALL, aby cez ňu postavy nemohli prejsť vďaka getTile a setType ak sa dvere otvoria dlaždice menia svoj typ na tomto mieste na CLEAR, čo umožňuje postavám cez ne opäť prejsť.

25.Rozhranie Openable umožňuje ovládať stav objektov, ktoré ho implementujú, zatváraním, otváraním a kontrolou ich stavu, takže dvere alebo truhlice možno otvárať a zatvárať.

26.Trieda Bullet implementuje guľku, ktorá v prípade kolízie s objektom zmizne zo scény, ale ak ide o aktéra, ktorý implementuje rozhranie Alive, umožňuje ho poškodiť.

27.Rozhranie Fireable definuje typ aktéra, ktorý kombinuje triedy Movable a Actor.

28.Trieda Firearm je abstraktná a umožňuje vytvárať rôzne typy zbraní prostredníctvom implementácie metódy createBullet. Spravuje muníciu a poskytuje metódy na streľbu a dopĺňanie munície, pričom slúži ako základ pre špecifické typy zbraní v hre.

29.Trieda Gun je implementáciou abstraktnej triedy Firearm, vytvára náboje pri výstrele a spravuje muníciu.

30.Trieda enum Direction implementuje rôzne strany sveta a umožňuje postavám pohybovať sa v hre. Podporuje kombinovanie smerov, čo umožňuje spracovať súčasné stlačenie viacerých klávesov a správne určiť trajektóriu pohybu postáv.

31.Rozhranie Keeper sa používa na označenie aktérov schopných ukladať a spravovať predmety prostredníctvom batohu.

32.Trieda Main je trieda, ktorá implementuje hru „Project Ellen“, nastaví herné okno, inicializuje a pridá scénu „EscapeRoom“ a spustí herný cyklus. Zabezpečuje tiež ukončenie hry po stlačení klávesu ESCAPE.

33.Rozhranie Movable rozširuje Actor tým, že umožňuje pohyb postavy, ktorá interaguje so stenami a neprechádza cez ne pomáha triede Bullet pochopiť, kedy interaguje so živými postavami spracúva začiatok a koniec pohybu

34.Trieda SpawnPoint vytvára objekt, ktorý spawnuje cudzie postavy, ak sa hlavná postava nachádza v jeho okruhu. Okrem toho vieme nastaviť počet nepriateľov, ktorí sa objavia na scéne v 3-sekundových intervaloch.

35.Trieda MotherAlien je podtrieda triedy Alien, ktorá v našej hre vytvára šéfa

36.Trieda LockedDoor je podtriedou triedy Door, ktorá označuje, či sú dvere zamknuté alebo odomknuté. Ak sú dvere zamknuté, nie je možné ich otvoriť, kým sa nepoužije karta AccessCard.

37.Trieda AccessCard predstavuje objekt, ktorý možno vziať do ruky a použiť na odomknutie objektov typu LockedDoor. Implementáciou rozhraní Collectible a Usable<LockedDoor> poskytuje možnosť interakcie so zamknutými dverami a pri použití mení ich stav na odomknuté.

Ripley sa môže voľne pohybovať po mape pomocou šípok na klávesnici, pričom jeho animácia a smer pohybu sa prispôsobujú stlačeným klávesom.
Hra obsahuje batoh, v ktorom Ripley môže zbierať rôzne predmety (napríklad kladivo a hasiaci prístroj) a potom ich v správnej chvíli použiť alebo zhodiť na zem.
Kolízny systém nielenže zabraňuje Ripleymu prechádzať cez steny, ale tiež umožňuje zistiť, kedy sa skrížia cesty s nepriateľmi (Alien), a spôsobiť im poškodenie, ak držíte zbraň.
Niektoré predmety (Usable) na scéne možno zdvihnúť a použiť na riešenie herných úloh (napr. odomknúť dvere pomocou prístupovej karty alebo opraviť generátor reaktora).
Konečným cieľom je preskúmať lokalitu bojom s nepriateľmi a používaním inventára na otvorenie zamknutých dverí a zbieranie zdrojov a nakoniec uniknúť z miestnosti prekonaním prekážok, ktoré sa objavia.

toto 5 minut video
https://drive.google.com/file/d/120WkJk0fVf28ZRfzM1b581VmX_VSRcX0/view?usp=sharing

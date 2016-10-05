# pai_projekt
Projektowanie Aplikacji Internetowych - Projekt

Treœæ zadania:
Gra sieciowa. Reguly gry:
- Gra zaklada nieskonczona liczbe graczy lub gdy ze wzgledu na reguly to nie ma sensu, gre w wielu pokojach rownoczesnie
- komunikacja pomiedzy klientami (graczami) odbywa sie zawsze za posrednictwem serwera
- Serwer jest odporny na nieprawidlowe polecenia przesylane przez klienta
- Konfiguracja serwera w pliku XML.
- Nalezy dostarczyc takze zbior testow jednostkowych.

Opis w³asny tworzonej gry:
Gra sieciowa bêd¹ca wariacj¹ gry w wisielca. 
- Na serwer bêdzie siê mog³a pod³¹czyæ dowolna liczba graczy.
- Z aktualnej puli graczy, (którzy bêd¹ trzymani na liœcie b¹dŸ mapie), bêdzie losowana osoba, która bêdzie musia³a wymyœliæ has³o. 
- Has³o bêdzie sprawdzane, przy pomocy regex'a i/lub jakiegoœ s³ownika, czy jest wyrazem.
- Pozostali gracze, zobacz¹ liczbê liter oraz pierwsz¹ literê tego wyrazu, a tak¿e odpowiedzi pozosta³ych graczy.
- Serwer bêdzie przechodzi³ w pêtli przez wszystkich pozosta³ych graczy, i prosi³ o podanie odpowiedzi.
- Jeœli u¿ytkownik odgadnie, dostajê punkty i bêdzie wymyœla³ nowe has³o ( b¹dŸ bêdzie losowana zupe³nie nowa osoba, która to zrobi)
- Jeœli nikt w tym przejœciu nie odgadnie wyrazu, pojawi siê kolejna litera. 
- Jeœli nikt nie odgadnie has³a, punkty dostanie osoba która wymyœla³a has³o.
- Wygrywa ta osoba, która zdobêdzie okreœlon¹ liczbê punktów.
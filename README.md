# pai_projekt
Projektowanie Aplikacji Internetowych - Projekt

Treść zadania:

Gra sieciowa. Reguly gry:
- Gra zaklada nieskonczona liczbe graczy lub gdy ze wzgledu na reguly to nie ma sensu, gre w wielu pokojach rownoczesnie
- komunikacja pomiedzy klientami (graczami) odbywa sie zawsze za posrednictwem serwera
- Serwer jest odporny na nieprawidlowe polecenia przesylane przez klienta
- Konfiguracja serwera w pliku XML.
- Nalezy dostarczyc takze zbior testow jednostkowych.

Opis własny tworzonej gry:

Gra sieciowa będąca wariacją gry w wisielca. 
- Na serwer będzie się mogła podłączyć dowolna liczba graczy.
- Z aktualnej puli graczy, (którzy będą trzymani na liście bądź mapie), będzie losowana osoba, która będzie musiała wymyślić hasło. 
- Hasło będzie sprawdzane, przy pomocy regex'a i/lub jakiegoś słownika, czy jest wyrazem.
- Pozostali gracze, zobaczą liczbę liter oraz pierwszą literę tego wyrazu, a także odpowiedzi pozostałych graczy.
- Serwer będzie przechodził w pętli przez wszystkich pozostałych graczy, i prosił o podanie odpowiedzi.
- Jeśli użytkownik odgadnie, dostaję punkty i będzie wymyślał nowe hasło ( bądź będzie losowana zupełnie nowa osoba, która to zrobi)
- Jeśli nikt w tym przejściu nie odgadnie wyrazu, pojawi się kolejna litera. 
- Jeśli nikt nie odgadnie hasła, punkty dostanie osoba która wymyślała hasło.
- Wygrywa ta osoba, która zdobędzie określoną liczbę punktów.

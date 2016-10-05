# pai_projekt
Projektowanie Aplikacji Internetowych - Projekt

Tre�� zadania:
Gra sieciowa. Reguly gry:
- Gra zaklada nieskonczona liczbe graczy lub gdy ze wzgledu na reguly to nie ma sensu, gre w wielu pokojach rownoczesnie
- komunikacja pomiedzy klientami (graczami) odbywa sie zawsze za posrednictwem serwera
- Serwer jest odporny na nieprawidlowe polecenia przesylane przez klienta
- Konfiguracja serwera w pliku XML.
- Nalezy dostarczyc takze zbior testow jednostkowych.

Opis w�asny tworzonej gry:
Gra sieciowa b�d�ca wariacj� gry w wisielca. 
- Na serwer b�dzie si� mog�a pod��czy� dowolna liczba graczy.
- Z aktualnej puli graczy, (kt�rzy b�d� trzymani na li�cie b�d� mapie), b�dzie losowana osoba, kt�ra b�dzie musia�a wymy�li� has�o. 
- Has�o b�dzie sprawdzane, przy pomocy regex'a i/lub jakiego� s�ownika, czy jest wyrazem.
- Pozostali gracze, zobacz� liczb� liter oraz pierwsz� liter� tego wyrazu, a tak�e odpowiedzi pozosta�ych graczy.
- Serwer b�dzie przechodzi� w p�tli przez wszystkich pozosta�ych graczy, i prosi� o podanie odpowiedzi.
- Je�li u�ytkownik odgadnie, dostaj� punkty i b�dzie wymy�la� nowe has�o ( b�d� b�dzie losowana zupe�nie nowa osoba, kt�ra to zrobi)
- Je�li nikt w tym przej�ciu nie odgadnie wyrazu, pojawi si� kolejna litera. 
- Je�li nikt nie odgadnie has�a, punkty dostanie osoba kt�ra wymy�la�a has�o.
- Wygrywa ta osoba, kt�ra zdob�dzie okre�lon� liczb� punkt�w.
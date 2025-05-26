% relates two lists with their concatenation (similar to append)
concat(nil, L, L).
concat(cons(H, T), L, cons(H, M)):- concat(T, L, M).

% relates a list with with one of its elements and its Peano position
position(cons(E, _), zero, E).
position(cons(H, T), s(N), E) :- position(T, N, E).

% relates a list and an element with its occurrences
count(nil, E, zero).
count(cons(E, L), E, s(N)) :- count(L, E, N).
count(cons(E, L), E2, N) :- E \= E2, count(L, E2, N).

% count (List , Element , NOccurrences )
% it uses count (List , Element , NOccurrencesSoFar , NOccurrences )
count ( List , E , N ) : - count ( List , E , zero , N).
count ( nil , E , N , N) .
count ( cons (E , L) , E , N , M) :- count (L , E , s( N) , M).
count ( cons (E , L) , E2 , N , M) :- E \= E2 , count (L , E2 , N , M ).

% search for an element in a list
search (X , cons (X , _)).
search (X , cons (_ , Xs )) :- search (X , Xs ).

% search2 (Elem , List )
% looks for two consecutive occurrences of Elem
search2 (X , cons (X , cons (X , _)) ).
search2 (X , cons (_ , Xs ) ) :- search2 (X , Xs ).

% search_two (Elem , List )
% looks for two occurrences of Elem with one element in between
search_two(X , cons (X , cons (_ , cons (X, _)))).
search_two(X , cons (_ , Xs ) ) :- search_two (X , Xs ).

% search_anytwo (Elem , List )
% looks for any Elem that occurs two times , anywhere
search_anytwo(X , cons (X , Xs ) ) :- search(X, Xs).
search_anytwo(X , cons (_ , Xs ) ) :- search_anytwo (X , Xs ).

% size (List , Size )
% Size will contain the number of elements in List, 
% written using notation zero , s( zero ), s(s( zero )) ...
% Scrivere s(N) nella testa significa: la dimensione della lista aumenta di uno rispetto alla coda.
size(nil, zero).
size(cons(H, T), s(N)) :- size(T, N).

% Peano Numbers
succ(X, s(X)).
sum(X, zero, X).
sum(X, s(Y), s(Z)) :- sum(X, Y, Z).

% sum_list (List , Sum )
sum_list(nil, zero).
sum_list(cons(H, T), S):- sum_list(T, S1), sum(H, S1, S).

% max(List , Max )
% Max is the biggest element in List
% Suppose the list has at least one element
maxtemp (cons (H,T) , M) : - maxtemp ( L, H, M).
maxtemp (nil , M , M) .
maxtemp (cons (H , T) , Temp , M) :- H > Temp, maxtemp (T , H , M).
maxtemp (cons (_ , T) , Temp , M) :- H <= Temp, maxtemp (T , Temp , M).

% max(List , Max )
% Max is the biggest element in List
% Suppose the list has at least one element
max (cons (H, nil) , H) .
max (cons (H , T), H) :- max (T , MT), H > MT.
max (cons (H , T), MT) :- max (T , MT), H =< MT.

% min - max (List ,Min , Max )
% Min is the smallest element in List
% Max is the biggest element in List
% Suppose the list has at least one element
minmax (cons (H, nil) , H , H).
minmax (cons (H , T), H, MAX) :- minmax (T , MIN, MAX), H < MIN.
minmax (cons (H , T), MIN, H) :- minmax (T , MIN, MAX), H > MAX.
minmax (cons (H , T), MIN, MAX) :- minmax (T , MIN, MAX), H >= MIN, H =< MAX.

% % same (List1 , List2 )
% are the two lists exactly the same ?
same (cons(H, nil), cons(H, nil)).
same (cons(H, T1), cons(H, T2)):- same(T1, T2).

% all_bigger (List1 , List2 )
% all elements in List1 are bigger than those in List2, 1 by 1
allbigger(cons(H1,nil), cons(H2, nil)) :- H1 > H2.
allbigger(cons(H1,T1), cons(H2, T2)) :- H1 > H2, allbigger(T1, T2).

% sublist (List1 , List2 )
% List1 should contain all elements in List2
sublist(L1, cons(H2, nil) :- search(H2, L1).
sublist(L1, cons(H2,T2)) :- search(H2, L1), sublist(L1, T2).

% seq(N,E, List ) --> List is [E,E ,... ,E] with size N
seq ( zero , _ , nil ).
seq (s (N) , E , cons (E ,T )) :- seq (N , E , T)

% seqR (N, List ) --> Peano numbers from zero to N
seqR (zero, cons(zero, nil)).
seqR (s(N), cons(N,L) :- seqR(N, L)

% last (L, E, Res) aggiunge un elemento alla fine
last(nil, E, cons(E, nil).
last(cons(H,T) , E, cons(H,T2) :- last(T, E, T2)

% filter based on a pre-defined predicate
filter(nil, nil).
pred(X) :- X > 3.
filter(cons(H,T), cons(H,FilteredT)) :- pred(H), filter(T, FilteredT).
filter(cons(H,T), FilteredT) :- \+ pred(H), filter(T, FilteredT).

% map
map(nil, nil)
mapping(X, X+5)
map(cons(H,T), cons(NH, MappedT)) :- mapping(H, NH), map(T, MappedT)


